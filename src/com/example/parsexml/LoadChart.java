package com.example.parsexml;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

public class LoadChart extends AsyncTask<Void, Void, Bitmap> {
	private ImageView img;
	private Context con;
	static String urlString ;
	Bitmap bitmap= null;
	
	

	

	public Bitmap getBitmap() {
		return bitmap;
	}

	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}

	public LoadChart(Context context, ImageView img1, String url) {
		this.img = img1;
		this.con = context;
		urlString = url;
	}

	public LoadChart(Context context, String url) {
		this.urlString = url;
		this.con = context;
	}

	@Override
	protected void onPreExecute() {
		
		super.onPreExecute();
	}

	@Override
	protected Bitmap doInBackground(Void... params) {
		
		Bitmap bitmap = DownloadImage();

		return bitmap;
	}

	@Override
	protected void onPostExecute(Bitmap bitmap) {
		this.bitmap = bitmap;
		//img.setImageBitmap(bitmap);
		

	}

	private static InputStream OpenHttpConnection(String urlString)
			throws IOException {

		Log.d("palval", "OpenHttpConnection");
		InputStream in = null;
		int response = -1;

		URL url = new URL(urlString);
		URLConnection conn = url.openConnection();

		if (!(conn instanceof HttpURLConnection))
			throw new IOException("Not an HTTP connection");

		try {
			HttpURLConnection httpConn = (HttpURLConnection) conn;
			httpConn.setAllowUserInteraction(false);
			httpConn.setInstanceFollowRedirects(true);
			httpConn.setRequestMethod("GET");
			httpConn.connect();

			response = httpConn.getResponseCode();

			if (response == HttpURLConnection.HTTP_OK) {
				in = httpConn.getInputStream();
			}

			String res = Integer.toString(response);
		} catch (Exception ex) {
			throw new IOException("Error connecting");
		}
		return in;
	}

	public static Bitmap DownloadImage() {
		Log.d("palval", "DownloadImage");
		Bitmap bitmap = null;
		InputStream in = null;
		try {

			 //in = OpenHttpConnection("https://chart.googleapis.com/chart?chs=440x220&chd=t:60,40&cht=p3&chl=Hello|World");
			in = OpenHttpConnection(urlString);
			bitmap = BitmapFactory.decodeStream(in);
			in.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return bitmap;
	}

}