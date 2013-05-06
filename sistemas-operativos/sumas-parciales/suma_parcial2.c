#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>
#include <semaphore.h>

int suma_total = 0;
sem_t sem;

void * suma_parcial(void * arg) {

	int j = 0;
	int tmp;
	int ni = ((int*) arg)[0];
	int nf = ((int*) arg)[1];

	for (j = ni; j <= nf; j++) {
		// Zona crítica
		sem_wait(&sem);
		tmp = suma_total;
		tmp = tmp + j;
		suma_total = tmp;
		// Fin zona crítica
		sem_post(&sem);
	}

	pthread_exit(0);
}

int main(int argc, char *argv[]) {
//	int threadCount = atoi(&argv[1]);
//	pthread_t threads[threadCount];
	pthread_t th1, th2;

	int num1[2] = { 1, 4999 };
	int num2[2] = { 5000, 10000 };

	sem_init(&sem, 10, 1);

	/* se crean dos threads con atributos por defecto */

	 pthread_create(&th1, NULL, suma_parcial, (void*) num1);
	 pthread_create(&th2, NULL, suma_parcial, (void*) num2);

//	int i;
//	for (i = 0; i < threadCount; i++) {
//		if (i % 2 == 0) {
//			pthread_create(&threads[i], NULL, suma_parcial, (void*) num1);
//		} else {
//			pthread_create(&threads[i], NULL, suma_parcial, (void*) num2);
//		}
//
//	}
	printf("El thread principal continua ejecutando\n");

//	for (i = 0; i < threadCount; i++) {
//		pthread_join(threads[i], NULL );
//	}
	/* se espera su terminacion*/

	 pthread_join(th1, NULL );
	 pthread_join(th2, NULL );

	sem_destroy(&sem);
	printf("La suma total es: %d y debería ser (50005000)\n", suma_total);

	return 0;
}
