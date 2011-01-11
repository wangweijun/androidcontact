package com.dt.utils;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.ContactsContract;

public class PhoneUtils {

	public static ArrayList<HashMap<String, String>> getContacts(
			ContentResolver cr) {

		// ContentResolver cr = getContentResolver();
		Cursor cCur = cr.query(ContactsContract.Contacts.CONTENT_URI, null,
				null, null, null);
		Cursor pCur = cr.query(
				ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null,
				null, null);

		ArrayList<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();

		HashMap<String, String> contacts = new HashMap<String, String>();

		while (cCur.moveToNext()) {
			String id = cCur.getString(cCur
					.getColumnIndex(ContactsContract.Contacts.LOOKUP_KEY));
			String name = cCur.getString(cCur
					.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

			contacts.put(id, name);
		}

		while (pCur.moveToNext()) {
			String id = pCur.getString(pCur
					.getColumnIndex(ContactsContract.Contacts.LOOKUP_KEY));

			String name = contacts.get(id);
			String phone = pCur
					.getString(pCur
							.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DATA));

			HashMap<String, String> h = new HashMap<String, String>();
			h.put("name", name);
			h.put("phone", phone);
			data.add(h);
		}

		pCur.close();
		cCur.close();

		return data;
	}
}
