package com.dt.activities;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils.StringSplitter;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class FileReviewer extends ListActivity{

	ArrayList<String> filesName = new ArrayList<String>();
	public FileReviewer()
	{
		
	}
    /** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		// Retrieve extra data , here is files name selected
		ArrayList<String> extra = this.getIntent().getStringArrayListExtra("com.dt.activities.filesName");
		for(int i=0;i<extra.size();i++)
		{
			filesName.add(extra.get(i));
		}
		// Choose own content view

		setContentView(R.layout.file_list_layout);
		setListAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_multiple_choice,
					filesName));
		// Set multiple choice mode
		final ListView listView = getListView();
		listView.setItemsCanFocus(false);
		listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		// check all the items;
		for(int i=0;i<extra.size();i++)
			listView.setItemChecked(i, true);
	}
	
	public void onListItemClick(ListView parent, View v, 
			int position,long id) {
	}
    public boolean onCreateOptionsMenu(Menu menu) {    
    	menu.add(0, 1, 0, "Upload");        
    	return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {       
		// Get all file name of which is selected
		final ListView listView = getListView();
		ArrayList<String> files = new ArrayList<String>();
		for(int i=0;i<listView.getCount();i++)
			if (listView.isItemChecked(i)) files.add(filesName.get(i));
	
		// Create a new intent 
		Intent intent = new Intent();
		// Set it Component to match right activity
		intent.setComponent(new ComponentName("com.dt.activities", "com.dt.activities.FileUploader"));
		// Put file name of which is selected
		intent.putStringArrayListExtra("com.dt.activities.filesName", files);
		startActivity(intent);
    	return true;
    }
}
