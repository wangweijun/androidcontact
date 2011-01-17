package com.dt.utils;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;
import org.kxml.Xml;
import org.kxml.kdom.Document;
import org.kxml.kdom.Element;

public class DataUtils {

	// create XML string containt phonebook
	public static String createXMLData(ArrayList<HashMap<String, String>> data) {
		String xml = "";
		Document document = new Document();

		Element root = document.createElement(null, "BACKUP");
		document.addChild(Xml.ELEMENT, root);
		Element element = document.getRootElement();

		Element phoneBook = document.createElement(null, "PHONEBOOK");
		for (int i = 0; i < data.size(); i++) {
			HashMap<String, String> contactFromData = data.get(i);

			Element contact = document.createElement(null, "CONTACT");

			Element name = document.createElement(null, "NAME");
			name.addChild(Xml.TEXT, contactFromData.get("name"));
			contact.addChild(Xml.ELEMENT, name);

			Element phone = document.createElement(null, "PHONE");
			phone.addChild(Xml.TEXT, contactFromData.get("phone"));
			contact.addChild(Xml.ELEMENT, phone);

			phoneBook.addChild(Xml.ELEMENT, contact);

		}

		element.addChild(Xml.ELEMENT, phoneBook);

		xml = document.toString();

		return xml;

	}

	// create JSONObject containt phonebook and convert to a String
	public static String createJSONData(ArrayList<HashMap<String, String>> data)
			throws JSONException {

		JSONObject holder = new JSONObject();
		for (int i = 0; i < data.size(); i++) {
			HashMap<String, String> contactFromData = data.get(i);
			JSONObject contact = new JSONObject();

			contact.put("name", contactFromData.get("name"));
			contact.put("phone", contactFromData.get("phone"));

			holder.put("contact", contact);

		}

		return holder.toString();
	}

	// creat csv string containt phonebook

	public static String createCSVData(ArrayList<HashMap<String, String>> data) {

		String csvString = "name,phone\n";

		for (int i = 0; i < data.size(); i++) {
			HashMap<String, String> contactFromData = data.get(i);
			csvString += contactFromData.get("name") + ","
					+ contactFromData.get("phone") + "\n";

		}

		return csvString;
	}

}
