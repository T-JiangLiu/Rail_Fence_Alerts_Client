package cn.edu.uestc.service;

public class Const
{
	//服务器IP地址
    public final static String SOCKET_SERVER = "192.168.1.115";
    
    //服务器监听端口
	public final static int SOCKET_PORT = 8011;
	
	// 默认timeout时间 60s
	public final static int SOCKET_TIMOUT = 60 * 1000;
	
	//读取socket的timeout时间
	public final static int SOCKET_READ_TIMOUT = 15 * 1000;
	
	//如果没有连接无服务器，读线程的sleep时间
	public final static int SOCKET_SLEEP_SECOND = 30;
	
	//心跳包发送间隔时间
	public final static int SOCKET_HEART_SECOND = 30;
	
	public final static String BC = "BC";
	
	
}
