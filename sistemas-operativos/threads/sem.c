
#include "sem.h"
#include <pthread.h>
#include <sys/time.h>
#include <stdlib.h>

/*-----------------------------------------------------------------------------------*/
struct sys_sem * sys_sem_new(int count)
{
	struct sys_sem *sem;
	
	sem = (struct sys_sem *)malloc(sizeof(struct sys_sem));
	if (sem != NULL) {
		sem->c = count;
		pthread_cond_init(&(sem->cond), NULL);
		pthread_mutex_init(&(sem->mutex), NULL);
	}
	return sem;
}
/*-----------------------------------------------------------------------------------*/


void sys_sem_free(struct sys_sem *sem)
{
	pthread_cond_destroy(&(sem->cond));
	pthread_mutex_destroy(&(sem->mutex));
	free(sem);
}

/*-----------------------------------------------------------------------------------*/
/** Wait de semaforo binario
 ** En caso de que el contador (sem->c) sea igual a 0, debe quedarse bloqueado hasta que
 **  otro hilo haga un sem_signal. En ese momento, todos los hilos bloqueados en 
 **  el wait "competirán" por seguir su ejecución (y sólo uno puede lograrlo)
 **  Tras salir del bloqueo (o si nunca entró en él), decrementará el contador
 ** y finalizará la ejecución
 **/
int
sys_sem_wait(struct sys_sem *sem)
{	

	//Completar 
		
	return 0;
}

/*-----------------------------------------------------------------------------------*/
/** Signal de nuestro semaforo binario 
 **/
void
sys_sem_signal(struct sys_sem *sem)
{
	pthread_mutex_lock(&(sem->mutex));

	sem->c = 1;
	
	pthread_cond_broadcast(&(sem->cond));
	pthread_mutex_unlock(&(sem->mutex));
}

/*-----------------------------------------------------------------------------------*/


