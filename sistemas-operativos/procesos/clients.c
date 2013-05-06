#include <pthread.h>
#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>

#include "mailbox.h"
#include "message.h"
#include "clients.h"


static pthread_t clients[MAX_CLIENTS];
static int n_clients=0;
int nr_secs=1;
int nr_msgs=3;
enum _message_types_ default_client_type=TIME;

struct client_arg {
	enum _message_types_ m_type;
	int n_messages;
	int period;
	sys_mbox_t* mbox;
};

int get_nclients() {
	return n_clients;
}

pthread_t* get_client_list() {
	return clients;
}

void clear_client_list() {
	n_clients=0;
}

static void process_answer(message_t* msg) {

	switch (msg->tipo) {
		case RANDOM:
			if (msg->content) {
				int* tmp = (int*) msg->content;
				fprintf(logfile,"CLIENT: random %d\n",*tmp);
			}
			else
				fprintf(stderr,"ERROR: client found null content in RANDOM message\n");
			break;
			
		case TIME:
			
			if (msg->content) {
				char* tmp = (char*) msg->content;
				fprintf(logfile,"CLIENT: time %s\n", tmp);
			}
			else
				fprintf(stderr,"ERROR: client found null content in TIME message\n");
			break;
			
		case NONE:
			fprintf(logfile,"CLIENT. NONE messaged processed\n");
			break;
		default:
			break;
	}
	
}

// La función de entrada de un thread siempre debe tener este prototipo
// pero esto no impide recibir argumentos "complejos" y devolver cualquier tipo 
// de resultado
void* client_thread (void* ptr) {
	struct client_arg* arg;
	int i;
	message_t* msg;
	
	arg = (struct client_arg*) ptr;
	fprintf(logfile,"NEW client of type %d with period %d \n",arg->m_type, arg->period);
	
	for (i=0;i<arg->n_messages; i++) {
		if (  ( msg=new_message(arg->m_type ) )  == NULL ) {
			fprintf(stderr,"Error when creating  message\n");
		pthread_exit(NULL);
		}
		
		// Post message to mailbox
		mbox_post( arg->mbox, msg);
		
		
		// Wait for server to finish the work
		sys_sem_wait(msg->op_completed);
		
		
		// We can process and free the message
		process_answer(msg);
		free_message(msg);
		
		// Sleep for period seconds before the next message
		sleep(arg->period);
		
	}
	return NULL;
}





int create_client(sys_mbox_t* mbox, enum _message_types_ m_type, int n_messages, int period) {
	
	struct client_arg* argument;
	pthread_t tmp;
	
	if (n_clients >= MAX_CLIENTS -1) {
		fprintf(stderr,"ERROR: max number of clients already executed.\n");
		fprintf(stderr,"Please call wait_for_clients before starting a new one\n");
		return -1;
	}
	
	// Build the argument to the new thread	
	argument = (struct client_arg*) malloc(sizeof(struct client_arg));
	argument->m_type = m_type;
	argument->n_messages = n_messages;
	argument->period = period;
	argument->mbox = mbox;
	
	// Create a thread 
	if ( pthread_create(&tmp,NULL, client_thread,(void *) argument) != 0) {
		fprintf(stderr,"Error when creating client thread\n");
		return -1;
	}
		 
	clients[n_clients] = tmp;
    n_clients++;
	
	return 0;
	
	
}
