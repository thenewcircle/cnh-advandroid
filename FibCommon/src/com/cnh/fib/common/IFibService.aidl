package com.cnh.fib.common;

import com.cnh.fib.common.FibRequest;

interface IFibService {
	long fibJ(long n);
	long fibN(long n);
	long fib(in FibRequest r);
	List<String> getSomeData();
}