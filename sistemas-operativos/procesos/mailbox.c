#include <pthread.h>
#include <sys/time.h>
#include <stdlib.h>

#include "sem.h"
#include "mailbox.h"
#include "cbuffer.h"

#define UMAX(a, b)      ((a) > (b) ? (a) : (b))



/* El buzón en sí se crea mediante una llamada a mbox_new, que realiza el programa principal.
 * Como el buzón está en el "heap" es COMPARTIDO POR TODOS LOS HILOS.
 * TODOS los campos de la siguiente estructura están en memoria compartida.
 */
struct sys_mbox {
	cbuffer_t* cbuffer; 			/* Buffer circular en el que se almacenan los mensajes */
	struct sys_sem *not_empty; /* Semáforo para esperar hasta que el buzón no esté vacío */
	struct sys_sem *not_full;  /* Semáforo para esperar hasta que el buzón no esté lleno */
	pthread_mutex_t *mutex;			/* Cerrojo para garantizar exclusión mutua */
	int wait_send;					/* Contador de hilos que están bloqueados en un mbox_post */
};

/*-----------------------------------------------------------------------------------*/

struct sys_mbox* mbox_new(unsigned int max_size) {
	struct sys_mbox *mailbox;

	mailbox = (struct sys_mbox *) malloc(sizeof(struct sys_mbox));

	if (mailbox == NULL ) {
		return NULL ;
	}

	/* Create the data structure */
	if ((mailbox->cbuffer = create_cbuffer_t(max_size)) == NULL ) {
		free(mailbox);
		return NULL ;
	}
	mailbox->not_empty = sys_sem_new(0);
	mailbox->not_full = sys_sem_new(0);
	mailbox->mutex = (pthread_mutex_t *) malloc(sizeof(pthread_mutex_t));
	pthread_mutex_init(mailbox->mutex, NULL );

	mailbox->wait_send = 0;
	return mailbox;
}

/*-----------------------------------------------------------------------------------*/
void mbox_free(struct sys_mbox *mbox) {
	if ((mbox != NULL )) {

		sys_sem_free(mbox->not_empty);
		sys_sem_free(mbox->not_full);

		pthread_mutex_destroy(mbox->mutex);
		free(mbox->mutex);
		destroy_cbuffer_t(mbox->cbuffer);

		mbox->not_empty = mbox->not_full = NULL;
		mbox->mutex = NULL;
		mbox->cbuffer = NULL;

		free(mbox);

	}
}


/** mbox_post (equivalente a un productor)
 ** Debe escribir el mensaje "msg" en mbox->cbuffer siempre que haya espacio
 ** Si el buffer está lleno, se incrementerá wait_send (para hacer saber que hay hilos esperando),
 ** y el hilo se quedará bloqueado hasta que el hilo productor invoque a mbox_fetch().
 ** Tras salir del bloqueo, se decrementerá wait_send y se volverá a comprobar si hay espacio (bucle).
 ** Una vez haya espacio, se debe insertar el mensaje en el buffer
 ** En caso de que el buffer estuviera vacío ANTES de esa escritura (es decir,
 ** si este post supone introducir el ÚNICO elemento en el mailbox)
 ** se "informará" a potenciales hilos que estuvieran esperando en un "mbox_fetch()" a que hubiera elementos que consumir del buffer
 **/
/*-----------------------------------------------------------------------------------*/
void
mbox_post( struct sys_mbox *mbox, void *msg)
{
}

/*-----------------------------------------------------------------------------------*/
/** mbox_fetch --> cliente
 ** Codificación simétrica a la de post
 ** El hilo invocador comprobará si hay elementos en el buffer circular. Si es así se quedará bloqueado hasta que haya elementos.
 ** A continuación extraerá el primer mensaje del buffer. Una vez, hecho esto, deberá comprobarse si
 ** hay hilos esperando en "post" (es decir, si wait_send es mayor de 0)
 ** y, en tal caso, despertar a uno de esos hilos.
 **/

void*
mbox_fetch(struct sys_mbox *mbox)
{
	
}
/*-----------------------------------------------------------------------------------*/




