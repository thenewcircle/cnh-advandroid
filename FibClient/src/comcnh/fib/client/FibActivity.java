package comcnh.fib.client;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.cnh.fib.common.FibRequest;
import com.cnh.fib.common.IFibListener;

public class FibActivity extends Activity {
	static final String TAG = "FibActivity";
	EditText in;
	TextView out;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		in = (EditText) findViewById(R.id.in);
		out = (TextView) findViewById(R.id.out);
		
	}
	
	static final int OUR_MESSAGE = 47;
	Handler responseHandler = new Handler() {
		
		@Override
		public void handleMessage(Message msg) {
			if (msg.what == OUR_MESSAGE) {
				Long result = (Long) msg.obj;
				out.append(String.format("\nGot response: %d", result));
				Log.d(TAG, "handleMessage "+this.hashCode());
			}
		}
		
	};

	IFibListener listener = new IFibListener.Stub() {
		@Override
		public void onResponse(long result) throws RemoteException {
			responseHandler.sendMessage( responseHandler.obtainMessage(OUR_MESSAGE, result));
			Log.d(TAG, "sendMessage "+this.hashCode());

		}
	};
	

	public void onClick(View v) throws RemoteException {
		long n = Long.parseLong(in.getText().toString());

		// Async call to service
		((FibApp) getApplication()).getService().fibAsync(n, listener);
		Log.d(TAG, "onClick "+this.hashCode());

		long start = System.currentTimeMillis();
		long resultN = ((FibApp) getApplication()).getService().fib(
				new FibRequest(2, n));
		long timeN = System.currentTimeMillis() - start;
		out.append(String.format("\nfibN(%d)=%d (%d ms)", n, resultN, timeN));

	}

}