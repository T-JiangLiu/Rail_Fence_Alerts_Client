package cn.edu.uestc.service;

import java.util.HashMap;
import java.util.Map;

import cn.edu.uestc.utils.ServiceUtils;

public class UserService {
	//注册方法
	public String register(String path, String username, String password){
		Map<String, String> params = new HashMap<String, String>();
		params.put("username", username);
		params.put("password", password);
		try {
			String result = ServiceUtils.sendGETRequest(path, params, "UTF-8");
			if(result!=null && result.equals("2")){
				return "success";
			}else if(result!=null && result.equals("1")){
				return "userexist";
			}else{
				return "fail";
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	// 登录方法
	public boolean login(String path, String username, String password) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("username", username);
		params.put("password", password);
		try {
			String result = ServiceUtils.sendGETRequest(path, params, "UTF-8");
			if(result!=null && result.equals("1")){
				return true;
			}
			return false;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
