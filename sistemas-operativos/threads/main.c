#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <signal.h>
#include <string.h>
#include "server.h"
#include "clients.h"
#include "mailbox.h"
#include "message.h"
#include "signal_handler.h"

static int wait_for_server_is_down() {
	if (pthread_join(getServerThId(), NULL ) != 0)
		return -1;
	return 0;
}

static int wait_for_clients() {
	int i;
	int n_cl = get_nclients();
	pthread_t* client_list = get_client_list();

	for (i = 0; i <= n_cl; i++) {
		if (pthread_join(client_list[i], NULL ) != 0)
			return -1;
	}

	clear_client_list();
	return 0;
}

static void usage(const char* program_name) {
	printf("Usage: %s [options]\n", program_name);
	printf("** Supported options **\n");
	printf("-o <logfile>\n");
	printf("-n <mailbox_size>\n");
	printf("-s <default_nr_secs>\n");
	printf("-m <default_nr_msgs>\n");
	printf("-c <default_client_type>\n");
}

static void parse_commands_from_input(sys_mbox_t* mbox)
{

	int tokens=0;
	char * user_line = NULL;
	size_t len = 0;
	int interactive=isatty(fileno(stdin));
	unsigned char quit = 0;
	int val[3];
	pid_t pid=getpid();

	printf("PID=%i\n",pid);

		do {
			if (interactive)
				printf("> "); /* Show prompt */

			if (getline(&user_line, &len, stdin) == -1) {
				quit = 1;
			} else if (len == 0 || strcmp(user_line, "\n") == 0) {
				//Do nothing when the line is empty
			}
			else if (user_line[0]=='!') /* Run bash command */
			{
					/* Pass on the command to the shell (remove "!") */
					system(&user_line[1]);
			}
			else if (strcmp(user_line, "exit\n") == 0
					|| strcmp(user_line, "quit\n") == 0) {
				quit = 1;
			}
			else if (strcmp(user_line, "pid\n")==0)
			{
				printf("%i\n",(int)pid);
			}
			else if (strcmp(user_line, "show_params\n")==0)
			{
				printf("default_client_type=%i\n",default_client_type);
				printf("nr_msgs=%i\n",nr_msgs);
				printf("nr_secs=%i\n",nr_secs);
			}
			else if (strncmp(user_line,"client",6)==0)
				{

				tokens=sscanf(user_line, "client %i %i %i", &val[0], &val[1],&val[2]);
				/*
				 * Format: client <type> <messages> <seconds>
				 * */
				if ((tokens==3) && (val[0] == 0 || val[0] == 1) && (val[1] > 0) && (val[2] > 0)) {

					if (create_client(mbox, val[0], val[1], val[2]) != 0) {
						fprintf(stderr, "Error when creating client \n");
						exit(1);
					}
				} else {
					fprintf(stderr,
							"Wrong format. Usage: client <type> <messages> <seconds>\n");
				}
			} else if (sscanf(user_line, "nr_secs=%i", &val[0]) == 1)

			{
				if (val[0] > 0)
					nr_secs = val[0];
				else
					fprintf(stderr, "Value must be greater than zero\n");
			} else if (sscanf(user_line, "nr_msgs=%i", &val[0]) == 1) {
				if (val[0] > 0)
					nr_msgs = val[0];
				else
					fprintf(stderr, "Value must be greater than zero\n");
			} else if (sscanf(user_line, "default_client_type=%i", &val[0]) == 1) {
				if (val[0] == 0 || val[0] == 1)
					default_client_type = val[0];
				else
					fprintf(stderr, "Unsupported value\n");
			} else {
				fprintf(stderr, "Unrecognized command: %s", user_line);
				fprintf(stderr, "Supported commands: client, exit, quit, show_params, pid, <param>=<val>, !cmd \n");
			}
		} while (quit == 0);
}


int main(int argc, char *argv[])
{
	sigset_t set;
	FILE* fout;
	char optc;
	sys_mbox_t* mbox = NULL;
	unsigned int mailbox_size = SYS_MBOX_SIZE;


	/* By default logfile points to stdout */
	logfile = stdout;

	/* Loop to process options from command line */
	while ((optc = getopt(argc, argv, "+ho:n:s:m:c:")) != -1) {
		switch (optc) {
		case 'o':
			/* Open custom logfile if passed as an argument */
			if ((fout = fopen(optarg, "w")) == NULL ) {
				fprintf(stderr, "Can't open logfile\n");
				exit(1);
			}
			logfile = fout;
			break;
		case 'n':
			mailbox_size = atoi(optarg);
			if (mailbox_size <= 0) {
				fprintf(stderr, "Mailbox size must be greater than zero\n");
				exit(1);
			}
			break;
		case 'm':
			nr_msgs = atoi(optarg);
			if (nr_msgs <= 0) {
				fprintf(stderr, "nr_msgs be greater than zero\n");
				exit(1);
			}
			break;
		case 's':
			nr_secs = atoi(optarg);
			if (nr_secs <= 0) {
				fprintf(stderr, "nr_secs size must be greater than zero\n");
				exit(1);
			}
			break;
		case 'c':
			default_client_type = atoi(optarg);
			if (default_client_type != 0 && default_client_type != 1) {
				fprintf(stderr,
						"Values supported for default_client_type: {0,1}\n");
				exit(1);
			}
			break;
		case 'h':
			usage(argv[0]);
			exit(0);
			break;

		}
	}

	// Mask signals so that this thread (and the next ones created) DO NOT receive the specified signals
	// sigfillset() could be used instead of specifying signals
	sigemptyset(&set);
	sigaddset(&set, SIGHUP);
	sigaddset(&set, SIGINT);
	sigaddset(&set, SIGUSR1);
	sigaddset(&set, SIGUSR2);
	sigaddset(&set, SIGALRM);
	pthread_sigmask(SIG_BLOCK, &set, NULL );

	//create Mailbox 
	if ((mbox = mbox_new(mailbox_size)) == NULL ) {
		fprintf(stderr, "Error when creating mbox.\n");
		return -1;
	}

	// Create signal handler thread
	if (create_signal_handler(mbox) != 0) {
		fprintf(stderr, "Error when creating signal handler\n");
		return -1;
	}

	// Create Server
	if (create_server(mbox) != 0) {
		fprintf(stderr, "Error when creating server. \n");
		return -1;
	}

	//Parse user commands ==> This function terminates when the user types 'exit', 'quit' or EOF (Ctrl+D)
	parse_commands_from_input(mbox);

	// If we reach this point, we must shutdown
	printf("MAIN: Shutting down...\n");

	// Wait for client to finish.... 
	printf("Wait for clients to properly finish....\n");
	wait_for_clients();
	printf("ALL clients gone\n");

	//tell server to shutdown
	printf("Wait for server to finish...\n");
	shutdown_server(mbox);
	wait_for_server_is_down();
	printf("Server down\n");

	//Free mailbox resources and close 'logfile' if necessary
	mbox_free(mbox);
	if (logfile != stdout)
		fclose(logfile);

	printf("Good bye!!\n");

	return 0;

}
