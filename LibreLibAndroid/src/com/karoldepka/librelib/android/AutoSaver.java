package com.karoldepka.librelib.android;

import android.os.Handler;

/**
 * Re-usable class for batched/delayed saves/updates.
 * Save occurs: at most {@link #delayMs} milliseconds after modification is reported
 * (which is also the moment of timer restart).
 * After save, the timer stops.
 * After a modification is reported, timer restarts.
 * If the timer is already running, it will not start again (will run its course to the end),
 * otherwise there would be a risk of never saving if the modifications were reported
 * at certain big frequency.
 *
 * @author Karol Depka Pradzinski
 * @license LGPL
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
