#include <pthread.h>
#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <signal.h>
#include "clients.h"
#include "server.h"



unsigned char _pause = 1; // Shared variable to pause/resume execution
/// Semaphores to pause the signal_handler thread when waiting for signals
static pthread_mutex_t lock = PTHREAD_MUTEX_INITIALIZER;
static pthread_cond_t cond = PTHREAD_COND_INITIALIZER;
static sys_mbox_t* gbl_mbox=NULL;	//Initialized upon thread creation

// Flag to finish the "endless" signal waiting loop
unsigned char finish_thread = 0;

/** Funciones para parar/continuar este hilo (gestor de señales)
 **/
void my_pause() {
	_pause = 1;
	pthread_mutex_lock(&lock);
    while(_pause==1) { /* We're paused */
        pthread_cond_wait(&cond, &lock); /* Wait for play signal */
    }
    pthread_mutex_unlock(&lock);
	
}

void my_resume() {
	pthread_mutex_lock(&lock);
	_pause=0;
	pthread_cond_signal(&cond); // cond to reevaluate pause
	pthread_mutex_unlock(&lock);
}



/// PROPUESTA EJERCICIO: por cada USR1 -> crear un cliente nuevo
void USR1_handler(int signo) {
    
	pthread_t id;
	
	id = pthread_self();
	fprintf(logfile,"thread %u: caught signal %d (USR1)\n", (unsigned int) id, signo);

	// Create a client
	// ....
	
	my_resume();
}


void SIGINT_handler(int signo) {
    
	pthread_t id;
	
    id = pthread_self();
    fprintf(logfile,"thread %u: caught signal %d (SIGINT)\n", (unsigned int) id, signo);
}


void* signal_handler_thread(void * c)
{
	struct sigaction act;
	sigset_t set;
	
	// Make sure to UNBLOCK all
	sigemptyset(&set);
	pthread_sigmask(SIG_SETMASK,&set,NULL);
	
	// Setup handler for USR1 signal
	act.sa_handler = USR1_handler;
	act.sa_flags = 0;
	sigemptyset(&act.sa_mask);
	if (sigaction(SIGUSR1, &act, NULL) != 0) {
        printf("ERROR: can't install SIGUSR1 signal handler");
		return NULL;
	}
		
	// Setup handler for INT signal
	act.sa_handler = SIGINT_handler;
	act.sa_flags = 0;
	sigemptyset(&act.sa_mask);
	if (sigaction(SIGINT, &act, NULL) != 0) {
        printf("ERROR: can't install SIGINT signal handler");
		return NULL;
	}
	
	
	
	// Wait forever
	while (!finish_thread)
	{
		my_pause();
	}

	printf("Exiting signal_handler thread\n");
	return NULL;
	
}


int create_signal_handler(sys_mbox_t* mbox) {

	pthread_t tmp;
	int r;	

	//Initialize global reference to mailbox
	gbl_mbox=mbox;

	if ( ( r= pthread_create(&tmp,NULL, signal_handler_thread,NULL ) ) == 0)
		printf("Signal handler thread id: %u \n",(unsigned int)tmp);
	return r;
}
