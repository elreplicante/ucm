#ifndef _MI_MBOX_
#define _MI_MBOX_
#include <stdio.h>
#define SYS_MBOX_SIZE 128



// IMPLEMENTATION of a mailbox mechanism with our own counting sem. and locks.


struct sys_mbox;
typedef struct sys_mbox sys_mbox_t;
//Global LOGFILE
extern FILE* logfile;

///Mailbox functions
struct sys_mbox *mbox_new(unsigned int max_size);
void mbox_free(struct sys_mbox *mbox);
void  mbox_post( struct sys_mbox *mbox, void *msg);
void* mbox_fetch(struct sys_mbox *mbox);
#endif /* _MI_MBOX_ */

