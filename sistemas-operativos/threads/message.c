#include <stdlib.h>
#include "message.h"



message_t* new_message(enum _message_types_ tipo) {
	message_t* m = (message_t*) malloc(sizeof(message_t));
	if (m==NULL) return NULL;
	
	m->tipo=tipo;
	switch (tipo) {
		case RANDOM:
			// The asnwer will hold an integer
			m->content = (int*) malloc(sizeof(int));
			break;
		//TODO	
		case TIME:
			// The answer will hold a 26-char string as output from asctime
			m->content = (int*) malloc(MAX_TIME_STRING_LEN*sizeof(char));
			break;
		case NONE:
			m->content = NULL;
		default:
			break;
	}
	
	// Init semaphores for siganling service done
	m->op_completed = sys_sem_new(0);
	
	return m;
}


void free_message(message_t* m) {

	if (m!= NULL) {
		if (m->content != NULL) free(m->content);
		if (m->op_completed) sys_sem_free(m->op_completed);
		free(m);
	}
	m= NULL;
}
