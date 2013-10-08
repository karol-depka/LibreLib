package com.karoldepka.librelib.android.views;

import android.app.Activity;

public class ViewGet<T> {
	
	private final Activity activity;
	private final int id;

	public ViewGet(Activity activity, int id) {
		this.activity = activity;
		this.id = id;
	}
	
	@SuppressWarnings("unchecked")
	public T get() {
		return (T) activity.findViewById(id);
	}

}
