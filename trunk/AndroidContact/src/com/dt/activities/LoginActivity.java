package com.dt.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends Activity{
	
	String inputPassword = "";
	String phoneNumber = "";
	boolean isMatch = false;
	EditText etPassword;
	final String loginUrl = "http://";
	
	Button btnLogin;
	Button btnRegister;

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
		
		btnLogin = (Button) this.findViewById(R.id.btn_login);
		btnLogin.setOnClickListener(new OnClickListener(){
		
			@Override
			public void onClick(View arg0) {
				if(arg0.getId() == R.id.btn_login){
					
					inputPassword = etPassword.getText().toString();
								
					isMatch = com.dt.utils.Authentication.doLogin(loginUrl, phoneNumber, inputPassword);
					
					if(isMatch){
						LoginActivity.this.finish();
						Intent intent = new Intent( LoginActivity.this, AndroidContact.class);
						startActivity(intent);
					
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
