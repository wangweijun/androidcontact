package com.dt.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity{
	
	String inputPassword = "";
	String phoneNumber = "";
	boolean isMatch = false;
	EditText etPassword;
	final String loginUrl = "http://zoo.vn/tools/dang_nhap.php";
	
	Button btnLogin;
	Button btnRegister;
	
	TextView tvLogin;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_activity);
		
		
		
		etPassword = (EditText) this.findViewById(R.id.et_password);
		etPassword.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				etPassword.setText("");
				
			}
			
			
		});
		
		TelephonyManager tm = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
		phoneNumber = tm.getLine1Number();
		
		tvLogin = (TextView) this.findViewById(R.id.tv_login);
		
		btnLogin = (Button) this.findViewById(R.id.btn_login);
		btnLogin.setOnClickListener(new OnClickListener(){
		
			@Override
			public void onClick(View arg0) {
				if(arg0.getId() == R.id.btn_login){
					
					inputPassword = etPassword.getText().toString();
					Toast.makeText(LoginActivity.this, "Password inputed: " + inputPassword, Toast.LENGTH_LONG)
					.show();
					Toast.makeText(LoginActivity.this, "Username (phone no.): " + phoneNumber, Toast.LENGTH_LONG)
					.show();
								
					isMatch = com.dt.utils.Authentication.doLogin(loginUrl, phoneNumber, inputPassword);
					
					
					if(isMatch){
						Log.d("Login result", "Successful");
						LoginActivity.this.finish();
						
						Intent intent = new Intent( LoginActivity.this, AndroidContact.class);
						intent.putExtra("password", inputPassword);
						startActivity(intent);
					
					}
					else{
						tvLogin.setText("Login False! Again!");
						Log.d("Login result", "False");
						
					}
				}
			}
			
		});
		
		
		btnRegister = (Button) this.findViewById(R.id.btn_register);
		btnRegister.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				if(v.getId() == R.id.btn_register){
					LoginActivity.this.finish();
					Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
					startActivity(intent);
				}
				
			}
						
		});
		
		
	}
}
