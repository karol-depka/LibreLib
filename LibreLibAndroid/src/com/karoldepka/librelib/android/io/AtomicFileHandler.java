package com.karoldepka.librelib.android.io;

import java.io.File;

/**
 * Handles partial/old file for atomic writing.
 * 
 * Logic in this class is separated from AtomicFileWriter to avoid tying it
 * to Writer (as opposed to other uses in e.g. OutputStream).
 * 
 * @author Karol Depka Pradzinski
 * @license LGPL
 */
public class AtomicFileHandler {
	private final File targetFile;
	
	public AtomicFileHandler(File targetFile) {
		this.targetFile = targetFile;
	}
	
	public static File getPartFile(File file) {
		return getFileWithSuffix(file, ".part");
	}

	public static File getFileWithSuffix(File file, String suffix) {
		return new File(file.getParentFile(), file.getName()+suffix);
	}
	
	public void onClose() {
		renamePartFileToTarget();
	}

	public void renamePartFileToTarget() {
		File partFile = getPartFile();
		File fileWithOldSuffix = getFileWithSuffix(targetFile, ".old");
		
		if ( targetFile.exists() ) {
			targetFile.renameTo(fileWithOldSuffix);
		}
		partFile.renameTo(targetFile);
		fileWithOldSuffix.delete();
	}

	public File getPartFile() {
		return getPartFile(targetFile);
	}

}
