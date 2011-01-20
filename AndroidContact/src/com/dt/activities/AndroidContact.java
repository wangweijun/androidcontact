package com.dt.activities;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.HttpResponse;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class AndroidContact extends Activity implements OnClickListener {

	final String backupUrl = "http://contact.tut.vn/tools/backup.php";
	Button btnBackup;
	String password = "";
	String phoneNumber = "";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.backup_restore_activity);

		// get saved password
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			password = extras.getString("password");
		}

		// get the owner's phonenumber to use as username
		TelephonyManager tm = (TelephonyManager) this
				.getSystemService(Context.TELEPHONY_SERVICE);
		phoneNumber = tm.getLine1Number();

		btnBackup = (Button) findViewById(R.id.btnBackup);
		btnBackup.setOnClickListener(this);

	}

	public void onClick(View v) {

		if (v.getId() == R.id.btnBackup) {

			ArrayList<HashMap<String, String>> contactList = com.dt.utils.PhoneUtils
					.getContacts(getContentResolver());

			// String url = "http://contact.tut.vn/tools/backup.php";
			// String url = "http://10.0.2.2/1/myscript.php"; //url of server on
			//    localhost

			String csvData = com.dt.utils.DataUtils.createCSVData(contactList);

			Toast.makeText(AndroidContact.this, csvData, Toast.LENGTH_LONG)
					.show();

			@SuppressWarnings("unused")
			HttpResponse response = com.dt.http.HttpSender.doPost(backupUrl,
					csvData, phoneNumber, password);

		}

	}

}