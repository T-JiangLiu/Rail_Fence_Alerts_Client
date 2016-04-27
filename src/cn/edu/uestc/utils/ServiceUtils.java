package cn.edu.uestc.utils;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

public class ServiceUtils {
	public static String sendGETRequest(String path, Map<String, String> params, String encode) throws Exception {
		// http://192.168.1.100:8080/Web/ManageServlet?username=xxx&password=xxx
		StringBuilder builder = new StringBuilder(path);
		builder.append("?");
		for(Map.Entry<String, String> entry : params.entrySet()){
			builder.append(entry.getKey()+"=");
			builder.append(URLEncoder.encode(entry.getValue(), encode)+"&");
		}
		builder.deleteCharAt(builder.length()-1);
		
		URL url = new URL(builder.toString());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setConnectTimeout(5000);
		conn.setRequestMethod("GET");
		if(conn.getResponseCode() == 200){
			InputStream in = conn.getInputStream();
			byte[] buffer = new byte[in.available()];
			in.read(buffer);
			String result = new String(buffer);
			in.close();
			return result;
		}
		return null;
	}
}
