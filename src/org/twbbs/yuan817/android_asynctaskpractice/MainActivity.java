package org.twbbs.yuan817.android_asynctaskpractice;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener{
	
	private Button mGoView;
	private TextView mTextView;
	private int number = 1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setView();
	}

	private void setView() {
		mGoView = (Button)findViewById(R.id.button1);
		mGoView.setOnClickListener(this);
		mTextView = (TextView)findViewById(R.id.textView1);
	}

	@Override
	public void onClick(View v) {
		/*toDoNow();
		toDoNow();
		toDoNow();
		toDoNow();
		mTextView.setText(String.valueOf(number));*/

		new Calculator().execute();
	}
	
	private void toDoNow() {
		for(long i=0; i<10000000; i++){
			number = number*3*5;
		}
	}
	
	public class Calculator extends AsyncTask<Void, Integer, Long> {
		
		
		@Override
		protected Long doInBackground(Void... params) {
			for(int i=0;i<3;i++)
			{
				toDoNow();
				publishProgress(number);
			}			
			return null;
		}
		
		@Override
		protected void onPreExecute() {
			mTextView.setText("...");
			super.onPreExecute();
		}
		
		@Override
		protected void onProgressUpdate(Integer... progress) {
			mTextView.setText("P: "+progress[0]);
			Toast.makeText(getBaseContext(), "...", Toast.LENGTH_SHORT).show();
			
			super.onProgressUpdate(progress);
		}
		
		@Override
		protected void onPostExecute(Long result) {
			
			mTextView.setText(String.valueOf(number));
			Toast.makeText(getBaseContext(), "Finish", Toast.LENGTH_SHORT).show();
			super.onPostExecute(result);
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
