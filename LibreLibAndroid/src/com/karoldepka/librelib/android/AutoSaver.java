package com.karoldepka.librelib.android;

import android.os.Handler;


/** Save occurs: N milliseconds after counter restart.
 * After save the counter stops.
 * The counter starts after modification.
 * If the counter is already running, it will not start again (will run its course to the end).
 */
public abstract class AutoSaver {
	public boolean saveTimerRunning = false;

	public class RunnableSave implements Runnable {
		@Override public void run() {
			saveNowIfNeeded();
			saveTimerRunning = false;
		}
	}

	private final int delayMs;
	private final Handler mHandler = new Handler();
	private Runnable runnableSave = new RunnableSave();

	public AutoSaver(int delayMs) {
		this.delayMs = delayMs;
	}

	protected abstract void saveCustom();

	public void documentModified() {
		ensureSaveTimerRunning();
	}

	private void ensureSaveTimerRunning() {
		if ( !saveTimerRunning ) {
			mHandler.postDelayed(runnableSave, delayMs);
			saveTimerRunning = true;
		}
	}

	public void saveNowIfNeeded() {
		// TODO: only save if modified
		forceSaveNow();
	}

	/** Useful e.g. when shutting down the app*/
	public void forceSaveNow() {
		saveCustom();
		stopSaveTimer();
	}
	
	private void stopSaveTimer() {
		mHandler.removeCallbacks(runnableSave);
		saveTimerRunning = false;
	}

	public void destroy() {
		saveNowIfNeeded();
	}


}
