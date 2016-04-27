package cn.edu.uestc.railfencealerts;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

import android.app.Activity;
import android.app.AlarmManager;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

public class MainActivity extends Activity {
	private TextView messageText;
	private TextView logText;
	public static Context s_context;
	Handler handler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				String result = (String) msg.obj;
				messageText.setText(result);
				logText.append(result + "\n");
				break;

			default:
				break;
			}
		}
		
	};
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		messageText = (TextView) this.findViewById(R.id.textView);
		logText = (TextView) this.findViewById(R.id.text);
		s_context = this;
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				
					try {
						Socket client = new Socket("192.168.1.115", 9991);
						/*
						BufferedReader reader = new BufferedReader(
								new InputStreamReader(client.getInputStream()));
						String str = "";
						StringBuilder builder = new StringBuilder();
						while ((str = reader.readLine()) != null) {
							builder.append(str);
						}
						*/
						InputStream in = client.getInputStream();
						byte[] buffer = new byte[in.available()];
						in.read(buffer);
						String messsage = new String(buffer); 
						Message msg = new Message();
						msg.what = 1;
						msg.obj = messsage;
						handler.sendMessage(msg);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			
		}).start();
	}
	
}
