package com.dt.activities;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.HttpResponse;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class AndroidContact extends Activity implements OnClickListener {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		Button btnBackup = (Button) findViewById(R.id.btnBackup);
		btnBackup.setOnClickListener(this);

		// ff
	}

	public void onClick(View v) {

		if (v.getId() == R.id.btnBackup) {

			ArrayList<HashMap<String, String>> contactList = com.dt.utils.PhoneUtils
					.getContacts(getContentResolver());

			String url = "http://contact.tut.vn/tools/backup.php";
		//	String url = "http://10.0.2.2/1/myscript.php";

			/*
			 * int contactCount = contactList.size();
			 * 
			 * for (int i = 0; i < contactCount; i++) {
			 * 
			 * HashMap<String, String> choosenContact = contactList.get(i);
			 * 
			 * Toast.makeText( AndroidContact.this, "Contacts: " +
			 * choosenContact.get("name") + "\nPhone: " +
			 * choosenContact.get("phone"), Toast.LENGTH_LONG).show(); }
			 */

			// /*

			// String xmlData = com.dt.utils.DataUtils
			// .createXMLData(contactList);

			String csvData = com.dt.utils.DataUtils.createCSVData(contactList);

			Toast.makeText(AndroidContact.this, csvData, Toast.LENGTH_LONG)
					.show();

			HttpResponse response = com.dt.http.HttpSender.doPost(url,
			 csvData, "0903090209", "1q2w3e");

		}

	}

}