package com.dt.activities;

import java.io.IOException;
import java.util.ArrayList;

import com.dt.utils.UploadController;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class FileUploader extends Activity {
	ArrayList<String> filesName;
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.file_upload_layout);
		filesName = getIntent().getExtras().getStringArrayList("com.dt.activities.filesName");
		TextView txtView = (TextView) findViewById(R.id.FileList);
		for(String s:filesName)
		{
			txtView.setText(txtView.getText()+ "\n"+s);
		}
		try {
			Upload();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void Upload() throws IOException
	{
		TextView txtView = (TextView) findViewById(R.id.FilesUploaded);
		for(String s:filesName)
		{
			UploadController.Upload("192.168.0.100/upload.php", s);
			txtView.setText(txtView.getText()+"\n"+s);
		}
		txtView.setText(txtView.getText()+"\nAll files is uploaded!");
	}
}
