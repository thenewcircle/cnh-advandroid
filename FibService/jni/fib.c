#include "com_cnh_fib_service_FibLib.h"

jlong fib(jlong n) {
	if(n==0) return 0;
	if(n==1) return 1;
	return fib(n-1)+fib(n-2);
}

JNIEXPORT jlong JNICALL Java_com_cnh_fib_service_FibLib_fibN
  (JNIEnv *env, jclass clazz, jlong n) {
	return fib(n);
}
