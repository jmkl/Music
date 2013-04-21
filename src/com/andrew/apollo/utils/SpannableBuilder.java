package com.andrew.apollo.utils;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.text.style.TextAppearanceSpan;

public class SpannableBuilder {

	private int position = 0;
	private List<Holder> holderList;
	private Context context;

	/**
	 * Create a new empty SpannableBuilder
	 * 
	 * @param context
	 *            Context of caller
	 */
	public SpannableBuilder(Context context) {
		this.context = context;
		holderList = new ArrayList<Holder>();
	}

	/**
	 * Create a SpannableBuilder with some text and style
	 * 
	 * @param context
	 *            Context of caller
	 * @param text
	 *            Text to render
	 * @param typeface
	 *            Style to use - typically a {@link android.graphics.Typeface}
	 *            constant
	 */
	public SpannableBuilder(Context context, String text, int typeface) {
		this(context);
		append(text, typeface);
	}

	public SpannableBuilder(Context context, String text, float size) {
		this(context);
		append(text, Typeface.NORMAL, size);
	}

	/**
	 * 
	 * @param context
	 *            Context of caller
	 * @param text
	 *            Text to render
	 * @param typeface
	 *            Style to use - typically a {@link android.graphics.Typeface}
	 * @param size
	 *            Size of the text - relative to normal text size
	 */
	public SpannableBuilder(Context context, String text, int typeface,
			float size) {
		this(context);
		append(text, typeface, size);
	}

	public SpannableBuilder(Context context, String text, int typeface,
			float size, int color) {
		this(context);
		append(text, typeface, size, color);
	}

	/**
	 * Create a SpannableBuilder from a string resource and style
	 * 
	 * @param context
	 *            Context of caller
	 * @param resourceId
	 *            Id of a string resource in res/strings.xml
	 * @param typeface
	 *            Style to use - typically a {@link android.graphics.Typeface}
	 *            constant
	 */
	public SpannableBuilder(Context context, int resourceId, int typeface) {
		this(context);
	}

	/**
	 * Append text to the existing one
	 * 
	 * @param text
	 *            New text to append
	 * @param typeface
	 *            Style to use - typically a {@link android.graphics.Typeface}
	 *            constant like Typeface.BOLD
	 * @return this SpannableBuilder
	 */
	public SpannableBuilder append(String text, int typeface) {

		Holder h = new Holder(text, typeface, 1f, Color.WHITE);
		holderList.add(h);

		return this;
	}

	/**
	 * 
	 * @param text
	 *            New text to append
	 * @param size
	 *            Size of the text - relative to normal text size
	 * @return this SpannableBuilder
	 */
	public SpannableBuilder append(String text, float size) {

		Holder h = new Holder(text, Typeface.NORMAL, size, Color.WHITE);
		holderList.add(h);

		return this;
	}

	public SpannableBuilder append(String text, int typeface, float size,
			int color) {

		Holder h = new Holder(text, typeface, size, color);
		holderList.add(h);

		return this;
	}

	/**
	 * 
	 * @param text
	 *            New text to append
	 * @param typeface
	 *            Style to use - typically a {@link android.graphics.Typeface}
	 * @param size
	 *            Size relative to normal text
	 * @return this SpannableBuilder
	 */
	public SpannableBuilder append(String text, int typeface, float size) {

		Holder h = new Holder(text, typeface, size, Color.WHITE);
		holderList.add(h);

		return this;
	}

	/**
	 * Append text to the existing one with default NORMAL typeface
	 * 
	 * @param text
	 *            New text to append
	 * @return this SpannableBuilder
	 */
	public SpannableBuilder append(String text) {

		Holder h = new Holder(text, Typeface.NORMAL, 1f, Color.WHITE);
		holderList.add(h);

		return this;
	}

	/**
	 * Append text to the existing one
	 * 
	 * @param resourceId
	 *            Id of a string resource
	 * @param typeface
	 *            Style to use - typically a {@link android.graphics.Typeface}
	 *            constant
	 * @return this SpannableBuilder
	 */
	public SpannableBuilder append(int resourceId, int typeface) {
		String s = context.getString(resourceId);

		append(s, typeface);

		return this;
	}

	/**
	 * Append a single space character in normal type
	 * 
	 * @return this SpannableBuilder
	 */
	public SpannableBuilder appendSpace() {
		append(" ", Typeface.NORMAL);
		return this;
	}

	public SpannableBuilder appendLine() {
		append("\n", Typeface.ITALIC);
		return this;
	}

	/**
	 * Render a SpannableString from this SpannableBuilder
	 * 
	 * @return a SpannableString containing all the Spans appended.
	 */
	public SpannableString toSpannableString() {

		StringBuilder sb = new StringBuilder();
		for (Holder h : holderList) {
			sb.append(h.text);
		}
		SpannableString spannableString = new SpannableString(sb.toString());
		for (Holder h : holderList) {
			StyleSpan span = new StyleSpan(h.style);
			ForegroundColorSpan color = new ForegroundColorSpan(h.warna);
			spannableString.setSpan(span, position, position + h.len,
					Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			spannableString.setSpan(new RelativeSizeSpan(h.size), position,
					position, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			spannableString.setSpan(color, position, position + h.len,
					Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			position += h.len;
		}
		return spannableString;
	}

	private static class Holder {
		String text;
		int style;
		float size;
		int len = 0;
		int warna;

		Holder(String text, int typeface, float relativeSize, int warna) {
			this.warna = warna;
			this.text = text;
			this.style = typeface;
			this.size = relativeSize;
			if (text != null)
				this.len = text.length();
		}
	}
}