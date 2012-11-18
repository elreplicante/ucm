################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
CPP_SRCS += \
../lineales/ColaDinamica.cpp \
../lineales/ColaEstatica.cpp \
../lineales/DobleCola.cpp \
../lineales/PilaDinamica.cpp \
../lineales/PilaEstatica.cpp 

OBJS += \
./lineales/ColaDinamica.o \
./lineales/ColaEstatica.o \
./lineales/DobleCola.o \
./lineales/PilaDinamica.o \
./lineales/PilaEstatica.o 

CPP_DEPS += \
./lineales/ColaDinamica.d \
./lineales/ColaEstatica.d \
./lineales/DobleCola.d \
./lineales/PilaDinamica.d \
./lineales/PilaEstatica.d 


# Each subdirectory must supply rules for building sources it contributes
lineales/%.o: ../lineales/%.cpp
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C++ Compiler'
	g++ -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@:%.o=%.d)" -o "$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '


