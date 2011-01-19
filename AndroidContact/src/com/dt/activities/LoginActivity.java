package com.dt.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class LoginActivity extends Activity{

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_activity);
		
		Button btnLogin = (Button) this.findViewById(R.id.btn_login);
		btnLogin.setOnClickListener(new OnClickListener(){
		
			@Override
			public void onClick(View arg0) {
				
				Intent intent = new Intent( LoginActivity.this, AndroidContact.class);
			}
			
		});
		
		
	}
}
