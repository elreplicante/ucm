#ifndef _CLIENTS_
#define _CLIENTS_

#include "message.h"
#include "mailbox.h"

#define MAX_CLIENTS 10

//Default number of seconds for client creation upon receiving a signal
extern int nr_secs;

//Default number of messages for client creation upon receiving a signal
extern int nr_msgs;

//Default client type
extern enum _message_types_ default_client_type;


// Creates a new thread executing a client which will produce n_messages of type m_type every period seconds
int create_client(sys_mbox_t* mbox, enum _message_types_ m_type, int n_messages, int period);

//int wait_for_clients();
int get_nclients();

pthread_t* get_client_list();

void clear_client_list();

#endif
