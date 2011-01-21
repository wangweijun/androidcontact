package com.dt.utils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import android.app.Application;
import android.text.AndroidCharacter;

import com.dt.activities.FileUploader;
import com.dt.activities.R;
import com.dt.http.HttpFileUpload;

public class UploadController {
	static int numberPart = 3;

	public static int Upload(String server, String filePath) throws IOException {
		File file = new File(filePath);
		return UpLoad(server, file);
	}

	public static int UpLoad(String server, File file) throws IOException {
		long[] partSize = new long[numberPart];
		long total = 0;
		for (int i = 0; i < numberPart - 1; i++) {
			partSize[i] = file.length() / numberPart;
			total += partSize[i];
		}
		partSize[numberPart - 1] = file.length() - total;
		HttpFileUpload[] uploaders = new HttpFileUpload[numberPart];
		long offset = 0;
		for (int i = 0; i < numberPart; i++) {
			uploaders[i] = new HttpFileUpload();
			uploaders[i].SetUploadParameter(new URL(server), file, offset,
					partSize[i], i + 1);
			offset += partSize[i];
			Thread thread = new Thread(uploaders[i]);
			thread.start();
		}

		// wait for all thread finish
		boolean stop = false;
		while (!stop) {
			stop = true;
			for (HttpFileUpload upload : uploaders) {
				if (upload.getResult() == 0)
					stop = false;
				if (upload.getResult() == -1)
					return -1;
			}
		}
		return 1;
	}
}