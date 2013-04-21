package com.andrew.apollo.utils.customwidget;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;


public class MakeWidgetUtils {
	public MakeWidgetUtils(){
	}
	public Bitmap MakeCover(Bitmap cover, String curSkin,int scale) {
		Bitmap bulek = Bitmap.createBitmap(scale, scale, Bitmap.Config.ARGB_8888);
		Bitmap tes = Bitmap.createScaledBitmap(MaskingBitmap(cover,curSkin),scale,scale,true);
		Canvas c = new Canvas(bulek);
		c.drawBitmap(tes, 0, 0, null);
		Bitmap b = BitmapFactory.decodeFile(curSkin+Utils.W_COVER);//.decodeResource(mContext.getResources(), R.drawable.theme_tes);
		Bitmap cov = Bitmap.createScaledBitmap(b, scale, scale, true);
		c.drawBitmap(cov, 0,0, null);
		return bulek;
	}

	private Bitmap MaskingBitmap(Bitmap cover,String curSkin) {
		Bitmap masker = BitmapFactory.decodeFile(curSkin+Utils.W_MASK);
		Bitmap mask = Bitmap.createBitmap(masker.getWidth(),masker.getHeight(), Bitmap.Config.ARGB_8888);
		Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
		p.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
		Canvas c = new Canvas(mask);
		c.drawBitmap(
				Bitmap.createScaledBitmap(cover, masker.getWidth(),
						masker.getHeight(), true), 0, 0, null);
		c.drawBitmap(masker, 0, 0, p);

		return mask;
	}
}
