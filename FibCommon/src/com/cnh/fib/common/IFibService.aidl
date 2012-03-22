package com.cnh.fib.common;

import com.cnh.fib.common.FibRequest;
import com.cnh.fib.common.IFibListener;

interface IFibService {
	long fibJ(long n);
	long fibN(long n);
	long fib(in FibRequest r);
	oneway void fibAsync(long n, IFibListener listener);
	List<String> getSomeData();
}