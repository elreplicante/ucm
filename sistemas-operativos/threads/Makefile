TARGET = practica3

CC = gcc
CPPSYMBOLS=
#CPPSYMBOLS=-DSYNCH_SIGNAL
CFLAGS = -g -Wall $(CPPSYMBOLS)
LDFLAGS = -pthread

OBJS = cbuffer.o clients.o mailbox.o main.o message.o sem.o server.o signal_handler.o

all: $(TARGET)

$(TARGET): $(OBJS)
	$(CC) $(CFLAGS) $(LDFLAGS) -o $(TARGET)  $(OBJS)

.c.o: 
	$(CC) $(CFLAGS) -I. -c  $<

clean: 
	-rm -f *.o $(TARGET) 
