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

public class RegisterActivity extends Activity {

	TextView tvRegistration;
	EditText etRegisterPassword;
	EditText etConfirmRegisterPassword;
	Button btnOK;
	String inputPassword = "";
	String inputConfirmPassword = "";
	String phoneNumber = "";
	boolean isMatch;
	boolean isRegisterSuccess;
	final String registerUrl = "http://zoo.vn/tools/dang_ky.php";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register_activity);

		// get the owner's phonenumber to use as username
		TelephonyManager tm = (TelephonyManager) this
				.getSystemService(Context.TELEPHONY_SERVICE);
		phoneNumber = tm.getLine1Number();

		tvRegistration = (TextView) this.findViewById(R.id.tv_registration);

		// when click to EditText, ease the current text
		etRegisterPassword = (EditText) this
				.findViewById(R.id.et_registerPassword);
		etRegisterPassword.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (arg0.getId() == R.id.et_registerPassword) {

					etRegisterPassword.setText("");
					// etConfirmRegisterPassword.setWidth(200);
				}
			}
		});

		etConfirmRegisterPassword = (EditText) this
				.findViewById(R.id.et_confirmRegisterPassword);
		etConfirmRegisterPassword.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (arg0.getId() == R.id.et_confirmRegisterPassword) {

					etConfirmRegisterPassword.setText("");
					// etConfirmRegisterPassword.setWidth(200);
				}
			}
		});

		// Set action for the OK button
		btnOK = (Button) this.findViewById(R.id.btn_registerOK);
		btnOK.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (v.getId() == R.id.btn_registerOK) {
					inputPassword = etRegisterPassword.getText().toString();
					inputConfirmPassword = etConfirmRegisterPassword.getText()
							.toString();

					// matching the input password and confirm string
					isMatch = com.dt.utils.Authentication.matchingPassword(
							inputPassword, inputConfirmPassword);

					if (!isMatch) {

						tvRegistration.setText("False! Again!");
						// tvRegistration.setTextColor(R.color.Blue);
						etConfirmRegisterPassword.setText("");
						etRegisterPassword.setText("");
						
						Toast.makeText(RegisterActivity.this, "Password does not match!",
								Toast.LENGTH_LONG).show();

					} else {
						Toast.makeText(RegisterActivity.this, "Match!",
								Toast.LENGTH_LONG).show();

						isRegisterSuccess = com.dt.utils.Register.doRegister(
								registerUrl, phoneNumber, inputPassword);

						Toast.makeText(RegisterActivity.this, "Registering...",
								Toast.LENGTH_LONG).show();

						if (isRegisterSuccess) {
							Toast.makeText(
									RegisterActivity.this,
									"Register successful! Switch to Backup & Restore screen",
									Toast.LENGTH_LONG).show();
							Log.d("Register", "Successful!");
							
							RegisterActivity.this.finish();
							Intent intent = new Intent(RegisterActivity.this,
									AndroidContact.class);
							intent.putExtra("password", inputPassword);
							startActivity(intent);
							
						} else{
							
							Toast.makeText(RegisterActivity.this, "Register false!",
									Toast.LENGTH_LONG).show();
							
							Log.d("Register", "False!");
							
						}
						

					}
				}
			}
		});

	}
}
