#ifndef _MI_MESSAGE_
#define _MI_MESSAGE_

#include "sem.h"

#define MAX_TIME_STRING_LEN 50

enum _message_types_ {RANDOM,TIME,NONE};

struct _message_ {
	enum _message_types_ tipo;
	void* content;
	sys_sem_t* op_completed;
};

typedef struct _message_ message_t;


// Exported functions
message_t* new_message(enum _message_types_ tipo);
void free_message(message_t* m);

#endif
