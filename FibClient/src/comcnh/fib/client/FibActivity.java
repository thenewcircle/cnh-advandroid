package comcnh.fib.client;

import android.app.Activity;
import android.os.Bundle;
import android.os.RemoteException;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.cnh.fib.common.FibRequest;
import com.cnh.fib.common.IFibService;

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

	public void onClick(View v) throws RemoteException {
		long n = Long.parseLong(in.getText().toString());
 
		long start = System.currentTimeMillis();
		long resultJ = ((FibApp)getApplication()).getService().fibJ(n);
		long timeJ = System.currentTimeMillis() - start;
		out.append(String.format("\nfibJ(%d)=%d (%d ms)", n, resultJ, timeJ));

		start = System.currentTimeMillis();
		long resultN = ((FibApp)getApplication()).getService().fib( new FibRequest(2, n));
		long timeN = System.currentTimeMillis() - start;
		out.append(String.format("\nfibN(%d)=%d (%d ms)", n, resultN, timeN));

	}
}