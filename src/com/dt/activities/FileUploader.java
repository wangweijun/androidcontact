package com.dt.activities;

import java.util.ArrayList;

import android.app.ListActivity;
import android.os.Bundle;
import android.text.TextUtils.StringSplitter;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class FileUploader extends ListActivity{
	TextView selection;
	String[] filesName;
	public FileUploader()
	{
		
	}
	public FileUploader(String[] strings)
	{
		filesName = strings.clone();
	}
    /** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		ArrayList<String> extra = this.getIntent().getStringArrayListExtra("com.dt.activities.filesName");
		filesName = new String[extra.size()];
		for(int i=0;i<extra.size();i++)
		{
			filesName[i] = extra.get(i);
		}
		setContentView(R.layout.file_list_layout);
		setListAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_multiple_choice,
					filesName));
		selection=(TextView)findViewById(R.id.selection);
		final ListView listView = getListView();
		listView.setItemsCanFocus(false);
		listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
	}
	
	public void onListItemClick(ListView parent, View v, 
			int position,long id) {
	 	selection.setText(filesName[position]);
	}
	
}
