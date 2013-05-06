#ifndef _MY_SEM
#define _MY_SEM

#include <pthread.h>
#include <errno.h>

#define _TIMEOUT_ 1000



// IMPLEMENTATION of binary semaphores with lock+condvar
struct sys_sem {
         int c;
	 pthread_cond_t cond;
	 pthread_mutex_t mutex;
};



typedef struct  sys_sem sys_sem_t;
 

struct sys_sem *sys_sem_new(int count);
void sys_sem_free(struct sys_sem *sem);   
void sys_sem_signal(struct sys_sem *sem);
int sys_sem_wait(struct sys_sem *sem);
#endif //_MY_SEM
