################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
CPP_SRCS += \
../bubble-sort.cpp \
../insertion-sort.cpp \
../quick-sort.cpp 

OBJS += \
./bubble-sort.o \
./insertion-sort.o \
./quick-sort.o 

CPP_DEPS += \
./bubble-sort.d \
./insertion-sort.d \
./quick-sort.d 


# Each subdirectory must supply rules for building sources it contributes
%.o: ../%.cpp
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C++ Compiler'
	g++ -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@:%.o=%.d)" -o "$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '


