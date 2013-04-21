package com.andrew.apollo.utils.customwidget;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;
import com.andrew.apollo.utils.SpannableBuilder;
import com.android.music.R;
import com.devspark.appmsg.AppMsg;

public class WidgetSkinFragment extends SherlockFragment {
	ViewPager pager;
	File defaultskin;
	List<File> skindir;
	SkinAdapter adapter;
	String SKINPATH = Environment.getExternalStorageDirectory()
			+ "/ApolloMusic/widget";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.widgetskinchooser, container, false);

		pager = (ViewPager) v.findViewById(R.id.widgetskinpager);
		if (!new File(SKINPATH).exists())
			new Setup().copy(getSherlockActivity());

		skindir = new ArrayList<File>();

		File widget = new File(SKINPATH);
		File[] skin = widget.listFiles();

		for (int i = 0; i < skin.length; i++) {
			skindir.add(skin[i]);
		}
		adapter = new SkinAdapter(skindir);
		pager.setAdapter(adapter);

		pager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int i) {
				new SetSkin().execute(skindir.get(i));
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}
		});

		return v;
	}

	private class SetSkin extends AsyncTask<File, Void, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			AppMsg.makeText(getSherlockActivity(), "skin applied",
					AppMsg.STYLE_CONFIRM).show();
		}

		@Override
		protected Void doInBackground(File... f) {
			String s = f[0].getAbsolutePath();
			SharedPreferences pref = PreferenceManager
					.getDefaultSharedPreferences(getSherlockActivity());
			Editor edit = pref.edit();
			edit.putString(Utils.W_SKIN, s);
			edit.commit();
			return null;
		}

	}

	private class SkinAdapter extends PagerAdapter {
		private List<File> files;

		public SkinAdapter(List<File> skindir) {
			this.files = skindir;
		}

		@Override
		public int getCount() {
			return files.size();
		}

		@Override
		public boolean isViewFromObject(View view, Object obj) {
			// TODO Auto-generated method stub
			return view == ((View) obj);
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// TODO Auto-generated method stub
			((ViewPager) container).removeView((View) object);
		}

		@Override
		public Object instantiateItem(View container, int position) {
			LayoutInflater inflater = (LayoutInflater) getSherlockActivity()
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View view = inflater.inflate(R.layout.widget_preview, null);
			ImageView iv = (ImageView) view.findViewById(R.id.widget_preview);
			TextView tv = (TextView) view.findViewById(R.id.widget_info);

			File f = files.get(position);
			String preview = f.getAbsolutePath() + "/preview.png";
			WData data = Utils.getSkinInfo(f.getAbsolutePath());
			iv.setImageURI(Uri.fromFile(new File(preview)));

			SpannableBuilder sb = new SpannableBuilder(getSherlockActivity());
			sb.append(data.getTitle(), Typeface.BOLD, 1.5f,
					data.getTitlecolor())
					.appendLine()
					.append("#" + data.getAuthor(), Typeface.ITALIC,
							data.getArtistcolor()).appendLine();

			tv.setText(sb.toSpannableString());
			((ViewPager) container).addView(view, 0);
			return view;
		}
	}
}
