package com.dt.utils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import com.dt.http.HttpFileUpload;


public class UploadController {
	static int numberPart = 3;
	public static boolean Upload(String server,String filePath) throws IOException
	{
		File file = new File(filePath);
		return UpLoad(server, file);
	}
	public static boolean UpLoad(String server,File file) throws IOException
	{
		long[] partSize = new long[numberPart];
		long total=0;
		for(int i=0;i<numberPart-1;i++)
		{
			partSize[i] = file.length()/numberPart;
			total+= partSize[i];
		}
		partSize[numberPart-1] = file.length()-total;
		long offset=0;
		for(int i = 0;i<numberPart;i++)
		{
			HttpFileUpload upload = new HttpFileUpload();
			upload.SetUploadParameter(new URL("http://192.168.0.100:8080/upload.php"), file, offset, partSize[i],i+1);
			offset+=partSize[i];
			Thread thread = new Thread(upload);
			thread.start();
		}
		return true;
	}
}