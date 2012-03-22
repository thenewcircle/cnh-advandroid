package comcnh.fib.client;

import android.app.Application;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

import com.cnh.fib.common.IFibService;


public class FibApp extends Application {
	static final String TAG = FibApp.class.getSimpleName();
	static final Intent SERVICE_INTENT = new Intent(
			"com.cnh.fib.common.IFibService");
	IFibService service;

	@Override
	public void onCreate() {
		super.onCreate();
		boolean isBound = bindService(SERVICE_INTENT,
				new FibServiceConnection(), BIND_AUTO_CREATE);
		Log.d(TAG, "onCreate isBound: " + isBound);

	}

	class FibServiceConnection implements ServiceConnection {

		@Override
		public void onServiceConnected(ComponentName comonent, IBinder binder) {
			service = IFibService.Stub.asInterface(binder);
			Log.d(TAG, "onServiceConnected");
		}

		@Override
		public void onServiceDisconnected(ComponentName comonent) {
			service = null;
			Log.d(TAG, "onServiceDisconnected");
		}

	}
	
	public IFibService getService() {
		return service;
	}

}
