package com.dt.activities;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

import java.util.List;

import com.dt.utils.FileArrayAdapter;
import com.dt.utils.Option;

import android.app.Activity;
import android.app.ListActivity;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

public class FileChooser extends ListActivity {
	private File currentDir;
	private FileArrayAdapter adapter;
	private ArrayList<String> selectedFiles = new ArrayList<String>();
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		currentDir = new File("/sdcard/");
		fill(currentDir);
	}

	private void fill(File f) {
		File[] dirs = f.listFiles();
		this.setTitle("Current Dir: " + f.getName());
		List<Option> dir = new ArrayList<Option>();
		List<Option> fls = new ArrayList<Option>();
		try {
			for (File ff : dirs) {
				if (ff.isDirectory())
					dir.add(new Option("/"+ff.getName(), " ", ff
							.getAbsolutePath()));
				else {
					fls.add(new Option(ff.getName(), "File Size: "
							+ ff.length()+" KB", ff.getAbsolutePath()));
				}
			}
		} catch (Exception e) {
		}
		Collections.sort(dir);
		Collections.sort(fls);
		dir.addAll(fls);
		if (!f.getName().equalsIgnoreCase("sdcard"))
			dir.add(0, new Option("..", "Parent Directory", f.getParent()));
		adapter = new FileArrayAdapter(FileChooser.this, R.layout.list_view_layout , dir);
		this.setListAdapter(adapter);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		Option o = adapter.getItem(position);
		if ((o.getData().equalsIgnoreCase(" "))
				|| o.getData().equalsIgnoreCase("parent directory")) {
			currentDir = new File(o.getPath());
			fill(currentDir);
		} else {
			onFileClick(o);
			if (!selectedFiles.contains(o.getPath()))
			{
				selectedFiles.add(o.getPath());
			}
			l.setItemChecked(position, true);
		}
	}
	
	private void onFileClick(Option o) {
		Toast.makeText(this, "File Selected: " + o.getName(),
						Toast.LENGTH_SHORT).show();
	}
	/* Creates the menu items */
    public boolean onCreateOptionsMenu(Menu menu) {    
    	menu.add(0, 1, 0, "Show the selected files");        
    	return true;
    }
    
    public boolean onOptionsItemSelected(MenuItem item) {       
		Intent intent = new Intent();
		intent.setComponent(new ComponentName("com.dt.activities", "com.dt.activities.FileUploader"));
		intent.putStringArrayListExtra("com.dt.activities.filesName", selectedFiles);
		startActivity(intent);
    	return true;
    }
}
