package cn.edu.uestc.service;

import java.io.InputStream;
import java.net.Socket;

import cn.edu.uestc.railfencealerts.MainActivity;
import android.content.Intent;

public class SocketInputThread extends Thread {

	@Override
	public void run() {
		try {
			Socket client = new Socket("192.168.1.115", 9994);
			InputStream in = client.getInputStream();
			byte[] buffer = new byte[in.available()];
			String receivedString = new String(buffer);
			Intent intent = new Intent(Const.BC);
			intent.putExtra("response", receivedString);
			MainActivity.s_context.sendBroadcast(intent);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
