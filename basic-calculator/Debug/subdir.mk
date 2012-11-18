################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
CPP_SRCS += \
../checkers.cpp \
../configuration.cpp \
../interaction.cpp \
../main.cpp \
../operations.cpp 

OBJS += \
./checkers.o \
./configuration.o \
./interaction.o \
./main.o \
./operations.o 

CPP_DEPS += \
./checkers.d \
./configuration.d \
./interaction.d \
./main.d \
./operations.d 


# Each subdirectory must supply rules for building sources it contributes
%.o: ../%.cpp
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C++ Compiler'
	g++ -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@:%.o=%.d)" -o "$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '


