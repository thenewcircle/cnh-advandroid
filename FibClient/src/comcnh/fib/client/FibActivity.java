package comcnh.fib.client;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.cnh.fib.common.IFibService;

public class FibActivity extends Activity {
	static final String TAG = "FibActivity";
	static final Intent SERVICE_INTENT = new Intent(
			"com.cnh.fib.common.IFibService");
	EditText in;
	TextView out;
	IFibService service;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		in = (EditText) findViewById(R.id.in);
		out = (TextView) findViewById(R.id.out);

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

	public void onClick(View v) throws RemoteException {
		long n = Long.parseLong(in.getText().toString());

		long start = System.currentTimeMillis();
		long resultJ = service.fibJ(n);
		long timeJ = System.currentTimeMillis() - start;
		out.append(String.format("\nfibJ(%d)=%d (%d ms)", n, resultJ, timeJ));

		start = System.currentTimeMillis();
		long resultN = service.fibN(n);
		long timeN = System.currentTimeMillis() - start;
		out.append(String.format("\nfibN(%d)=%d (%d ms)", n, resultN, timeN));

	}
}