package com.karoldepka.librelib.android.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

public class LimitedButton extends Button {

	public LimitedButton(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		final int maxWidth = 300;
		
		if ( getMeasuredWidth() > maxWidth ) {
			super.onMeasure(MeasureSpec.makeMeasureSpec(maxWidth, MeasureSpec.EXACTLY), heightMeasureSpec);
//			setMeasuredDimension(maxWidth, getMeasuredHeight());
		}
		
	}

}
