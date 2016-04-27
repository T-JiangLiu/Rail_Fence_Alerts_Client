package cn.edu.uestc.railfencealerts;

import cn.edu.uestc.service.UserService;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {
	private EditText usernameText;
	private EditText passwordText;
	private Button registerButton;
	private Button loginButton;
 
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		usernameText = (EditText) this.findViewById(R.id.username);
		passwordText = (EditText) this.findViewById(R.id.password);
		registerButton = (Button) this.findViewById(R.id.registerButton);
		loginButton = (Button) this.findViewById(R.id.loginButton);

		registerButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
				startActivity(intent);
			}
		});
		loginButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				UserService service = new UserService();
				String username = usernameText.getText().toString();
				String password = passwordText.getText().toString();
				String path = "http://192.168.1.115:8080/Rail_Fence_Alerts_Server/LoginServlet";
				if (validate(username, password)) { 
					if (service.login(path, username.trim(), password)) {
						Toast.makeText(getApplicationContext(), "登录成功", 0).show();
						Intent intent = new Intent(LoginActivity.this, MainActivity.class);
						startActivity(intent);
						finish();
					} else {
						Toast.makeText(getApplicationContext(), "用户名称或者密码错误，请重新输入", 0).show();
					}
				}
			}
		});
	}

	// 对用户名和密码进行非空验证
	private boolean validate(String username, String password) {
		if (username.trim().equals("") && password.equals("")) {
			Toast.makeText(getApplicationContext(), "请输入用户名称和用户密码", 0).show();
			return false;
		}
		if (username.trim().equals("")) {
			Toast.makeText(getApplicationContext(), "请输入用户名称", 0).show();
			return false;
		}
		if (password.equals("")) {
			Toast.makeText(getApplicationContext(), "请输入用户密码", 0).show();
			return false;
		}
		return true;
	}
}
