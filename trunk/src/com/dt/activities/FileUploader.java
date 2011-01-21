package com.dt.activities;

import java.io.IOException;
import java.util.ArrayList;

import com.dt.utils.UploadController;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class FileUploader extends Activity {
	ArrayList<String> filesName;
	String string;
	TextView txtUploaded;

	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			txtUploaded.setText(txtUploaded.getText()+"\n"+string);
		}
	};

	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.file_upload_layout);
		txtUploaded = (TextView) findViewById(R.id.FilesUploaded);
		filesName = getIntent().getExtras().getStringArrayList(
				"com.dt.activities.filesName");
		TextView txtView = (TextView) findViewById(R.id.FileList);
		for (String s : filesName) {
			txtView.setText(txtView.getText() + "\n" + s);
		}
		Button btn = (Button) findViewById(R.id.Button01);
		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Thread t = new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						for (String s : filesName) {
							try {
								int result = UploadController.Upload(
										getString(R.string.server_name), s);
								if (result == 1)
									string = s;
								else
									string = s + " upload failed!";
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								string = s + " upload failed!";
							}
							finally
							{
								handler.sendEmptyMessage(0);
							}
						}
					}
				});
				t.start();
			}
		});
	}

	// /* public void Upload() throws IOException {
	// TextView txtView = (TextView) findViewById(R.id.FilesUploaded);
	// for (String s : filesName) {
	// int result = UploadController.Upload(
	// getString(R.string.server_name), s);
	// /*
	// * if (result == 1) FileUploader.setFilesUploaded(txtView, s); else
	// * FileUploader.setFilesUploaded(txtView, " upload failed!");
	// */
	// }
	// txtView.setText(txtView.getText() + "\nAll files is uploaded!");
	// }
}
