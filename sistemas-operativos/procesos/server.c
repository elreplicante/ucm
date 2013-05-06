#include <stdlib.h>
#include <stdio.h>
#include "server.h"
#include "mailbox.h"
#include "message.h"
#include <time.h>
#include <string.h>

char serv_up=0;
pthread_t server_id;

//The global logfile
FILE* logfile;


pthread_t getServerThId() {
	return server_id;
}

int shutdown_server(sys_mbox_t* mbox)
{
	message_t* msg;

	serv_up = 0;
	
	// Create NONE message
	if (  ( msg=new_message(NONE ) )  == NULL ) {
		printf("Error when creating NONE message\n");
		return -1;
	}
	
	// Post it  message to mailbox
	mbox_post( mbox, msg);

	// Wait for server to finish the work
	sys_sem_wait(msg->op_completed);
	
	// Ignore answer
	if (msg != NULL) free_message(msg);
	
	return 0;
}


char server_is_up() {
	return serv_up;
}


// TODO The answer should be returned back in the message
int execute_time_service(message_t* msg, int msgcnt) {

	struct tm *newtime;
    time_t ltime;
	
	
	/* Get the time in seconds */
    time(&ltime);
	/* Convert it to the structure tm */
    newtime = localtime(&ltime);
	
	/* Print the local time as a string */
    fprintf(logfile,"SERVER [#%i]: The current date and time are %s",msgcnt,asctime(newtime));
	
	/* COPY string  into message content */
	strcpy((char*)msg->content, asctime(newtime));
	
	return 0;
}

void* server_thread(void* ptr)
{
	static int msgcount=0;
	message_t* msg;

	// Setup our pointer to the mailbox (it was created by main)
	sys_mbox_t* mbox = (sys_mbox_t*) ptr;

	// Mark server as running
	serv_up = 1;
	
	// Sequence for pseudo-random numbers
	srand(0);
		
	// Server must wait for a message in the mailbox,
	// execute the desired command and write the result back in the message
	while (server_is_up()) {
		msg=mbox_fetch(mbox);
		msgcount++;
		switch (msg->tipo)
		{
			case TIME:
				if (execute_time_service(msg,msgcount) != 0) {
					fprintf(stderr,"ERROR when executing TIME action\n");
				}
				break;
			case NONE:
				fprintf(logfile,"Server. NONE message received. Doing nothing\n");
				break;
			default:
				break;
		}
		
		// Signal client. Operation done
		sys_sem_signal(msg->op_completed);
	
	}
	
	// Server must shutdown
	printf("\n....Server is down...\n");
	pthread_exit(NULL);
}


/* Create a Server that waits for messages in the mail box */
int create_server(sys_mbox_t* m) {
	
	if (m==NULL) return -1;
	
	return pthread_create(&server_id,NULL, server_thread,(void *) m );
	
	
}
