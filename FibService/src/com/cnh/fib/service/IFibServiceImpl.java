package com.cnh.fib.service;

import java.util.List;

import android.os.RemoteException;
import android.util.Log;

import com.cnh.fib.common.FibRequest;
import com.cnh.fib.common.IFibListener;
import com.cnh.fib.common.IFibService;

public class IFibServiceImpl extends IFibService.Stub {

	@Override
	public long fibJ(long n) throws RemoteException {
		return FibLib.fibJ(n);
	}

	@Override
	public long fibN(long n) throws RemoteException {
		return FibLib.fibN(n);
	}

	@Override
	public long fib(FibRequest request) throws RemoteException {
		if(request.getType()==1) {
			return FibLib.fibJ(request.getN());
		} else if(request.getType()==2) {
			return FibLib.fibN(request.getN());
		} 
		return 0;
	}

	
	@Override
	public List<String> getSomeData() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void fibAsync(long n, IFibListener listener) throws RemoteException {
		Log.d("IFibServiceImpl", "fibAsync start"+this.hashCode());
		long result = FibLib.fibJ(n);
		Log.d("IFibServiceImpl", "fibAsync stop"+this.hashCode());
		listener.onResponse(result);
	}

}
