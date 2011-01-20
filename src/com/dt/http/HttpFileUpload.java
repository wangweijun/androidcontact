package com.dt.http;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class HttpFileUpload implements Runnable {
	File file;
	long offset, length;
	URL url;
	int part;
	public void SetUploadParameter(URL url, File file, long offset, long length,int part) {
		this.file = file;
		this.url = url;
		this.offset = offset;
		this.length = length;
		this.part = part;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		HttpURLConnection conn = null;
		DataOutputStream dos;
		String lineEnd = "\r\n";
		String twoHyphens = "--";
		String boundary = "*****";
		try {
			conn = (HttpURLConnection) url.openConnection();
			// Allow Inputs
			conn.setDoInput(true);
			
			// Allow Outputs
			conn.setDoOutput(true);

			// Don't use a cached copy.
			conn.setUseCaches(false);

			// Use a post method.
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Connection", "Keep-Alive");
			conn.setRequestProperty("Content-Type",
					"multipart/form-data;boundary=" + boundary);
			
			dos = new DataOutputStream(conn.getOutputStream());
			dos.writeBytes(twoHyphens + boundary + lineEnd);
			dos
					.writeBytes("Content-Disposition: form-data; name=\"uploadedfile\";filename=\""
							+ file.getName() + ".00"+this.part+"\"" + lineEnd);
			dos.writeBytes(lineEnd);
			
			FileInputStream inFile = new FileInputStream(file);
			
			int size = 1024;
			FileChannel inChannel = inFile.getChannel();
			ByteBuffer buf = ByteBuffer.allocate(size);
			inChannel.position(offset);
			while (inChannel.position() < offset + length) {
				if (inChannel.position() + size > offset + length) {
					buf = ByteBuffer
							.allocate((int) (offset + length - inChannel
									.position()));
				}
				inChannel.read(buf);
				dos.write(buf.array());
				dos.flush();
				//System.out.println("Thread "+part+"- channel position:"+inChannel.position());
				buf.clear();

			}
			dos.writeBytes(lineEnd);
			dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);
			dos.flush();
			dos.close();

			DataInputStream inStream = new DataInputStream(conn
					.getInputStream());
			String str;

			while ((str = inStream.readLine()) != null) {
				System.out.println("Server Response " + str );
			}

			inStream.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.print(e);
		}
	}

}