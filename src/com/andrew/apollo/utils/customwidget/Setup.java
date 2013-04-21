package com.andrew.apollo.utils.customwidget;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Environment;
import android.util.Log;

public class Setup {
	
	public boolean getDefaultFileDir(Context c) {
		String SKINPATH = Environment.getExternalStorageDirectory() + "/ApolloMusic/widget/default";
		File f = new File(SKINPATH);
		if (!f.exists())
			return false;
		else
			return true;

	}

	public void copy(Context c) {
		String SKINPATH = Environment.getExternalStorageDirectory() + "/ApolloMusic/widget/default";
		File f = new File(SKINPATH);
		if (!f.exists())
			f.mkdirs();

		AssetManager assetManager = c.getAssets();
		try {
			String[] files = assetManager.list("defaultskin");

			for (String filename : files) {
				InputStream in = null;
				OutputStream out = null;
				try {
					Log.e(filename, filename);
					in = assetManager.open("defaultskin/" + filename);
					out = new FileOutputStream(SKINPATH + "/" + filename);
					copyFile(in, out);
					in.close();
					in = null;
					out.flush();
					out.close();
					out = null;

				} catch (IOException e) {

				}
			}

		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}

	private void copyFile(InputStream in, OutputStream out) throws IOException {
		byte[] buffer = new byte[1024];
		int read;
		while ((read = in.read(buffer)) != -1) {
			out.write(buffer, 0, read);

		}
	}
}
