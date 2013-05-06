#ifndef _SERVER_
#define _SERVER_

#include "mailbox.h"
#include <pthread.h>


int create_server(sys_mbox_t* mbox);
int shutdown_server(sys_mbox_t* mbox);
pthread_t getServerThId(void);
#endif
