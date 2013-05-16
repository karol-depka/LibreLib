package com.karoldepka.librelib.android.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/** Writes to a file in such a way to ensure that the previous contents
 * of the file will stay intact if the writing does not complete successfully.
 * 
 * Prevents a scenario in which the target file is left corrupted
 * with incomplete (or even empty) content due to failed attempt to write.
 * 
 * @author Karol Depka Pradzinski
 * @license LGPL
 */
public class AtomicFileWriter extends BufferedWriter {

	private final AtomicFileHandler partFileHandler;
	
	public AtomicFileWriter(File file, boolean append)
			throws IOException {
		super(new FileWriter(AtomicFileHandler.getPartFile(file), append));
		partFileHandler = new AtomicFileHandler(file);
		
	}
	
	@Override
	public void close() throws IOException {
		super.close();
		partFileHandler.onClose();
	}

}
