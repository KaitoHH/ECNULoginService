package com.networkService;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;


public class NetworkConnection {
	private HttpURLConnection httpURLConnection;

	public NetworkConnection(String httpURL) throws IOException {
		httpURLConnection = getConnection(httpURL);
	}

	private HttpURLConnection getConnection(String connectionURL) throws IOException {
		URL url = new URL(connectionURL);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setDoOutput(true);
		conn.setDoInput(true);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.71 Safari/537.36");
		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		//conn.setRequestProperty("Accept-Encoding", "identity");
		conn.connect();
		return conn;
	}

	public String doPost(String postParameter) throws IOException {
		OutputStreamWriter osw = new OutputStreamWriter(httpURLConnection.getOutputStream(), "UTF-8");
		osw.write(postParameter);
		osw.flush();
		osw.close();
		InputStream inputStream = httpURLConnection.getInputStream();
		int len = httpURLConnection.getContentLength();
		if (len <= 0)
			len = inputStream.available();
		byte[] bytes = new byte[len];
		inputStream.read(bytes);
		return new String(bytes, "UTF-8");
	}

	public void close() {
		httpURLConnection.disconnect();
	}
}
