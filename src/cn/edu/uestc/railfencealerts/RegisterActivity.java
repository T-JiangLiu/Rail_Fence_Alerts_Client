package cn.edu.uestc.railfencealerts;

import cn.edu.uestc.service.UserService;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends Activity {
	private EditText register_username;
	private EditText register_password1;
	private EditText register_password2;
	private Button registerButton;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		register_username = (EditText) this.findViewById(R.id.usernameEditText);
		register_password1 = (EditText) this.findViewById(R.id.password1EditText);
		register_password2 = (EditText) this.findViewById(R.id.password2EditText);
		registerButton = (Button) this.findViewById(R.id.registerButton);
		/*
		register_username.setOnFocusChangeListener(new OnFocusChangeListener(){
			public void onFocusChange(View v, boolean hasFocus) {
				if(!hasFocus){
					if(register_username.getText().toString().trim().length()<4){
						Toast.makeText(getApplicationContext(), "用户名称不能小于4个字符", 0).show();
					}
				}
			}
		});
		register_password1.setOnFocusChangeListener(new OnFocusChangeListener(){
			public void onFocusChange(View v, boolean hasFocus) {
				if(!hasFocus){
					if(register_password1.getText().toString().length()<6){
						Toast.makeText(getApplicationContext(), "用户密码不能小于6个字符", 0).show();
					}
				}
			}
		});
		*/
		registerButton.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				if(checkEdit()){
					UserService service = new UserService();
					String username = register_username.getText().toString().trim();
					String password = register_password1.getText().toString();
					String path = "http://192.168.1.115:8080/Rail_Fence_Alerts_Server/RegisterServlet";
					String message = service.register(path, username, password);
					if(message.equals("success")){
						Toast.makeText(getApplicationContext(), "注册成功", 0).show();
						Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
						startActivity(intent);
						finish();
					}else if(message.equals("userexist")){
						Toast.makeText(getApplicationContext(), "该用户已被注册", 0).show();
					}else{
						Toast.makeText(getApplicationContext(), "请求错误", 0).show();
					}
				}
			}
		});
	}
	private boolean checkEdit(){
		if(register_username.getText().toString().trim().equals("")){
			Toast.makeText(this, "用户名不能为空", Toast.LENGTH_SHORT).show();
		}else if(register_password1.getText().toString().equals("")){
			Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
		}else if(!register_password2.getText().toString().equals(register_password1.getText().toString())){
			Toast.makeText(this, "两次密码输入不一致", Toast.LENGTH_SHORT).show();
		}else{
			return true;
		}
		return false;
	}
}
