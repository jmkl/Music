package com.andrew.apollo.utils.customwidget;

import android.os.Bundle;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.view.MenuItem;
import com.andrew.apollo.ui.activities.BaseActivity;
import com.android.music.R;

public class WidgetSkin extends BaseActivity {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Set up the action bar
		final ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setTitle("Swipe Left/Right");

		getSupportFragmentManager().beginTransaction()
				.replace(R.id.activity_base_content, new WidgetSkinFragment())
				.commit();

	}

	public void setWidgetSkin() {

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean onOptionsItemSelected(final MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			return true;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int setContentView() {
		return R.layout.activity_base;
	}
}
