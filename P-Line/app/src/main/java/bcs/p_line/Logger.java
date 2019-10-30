package bcs.p_line;

import android.util.Log;

public class Logger {

	public static final Boolean DEBUG = false;

	public static void logInfo(String tag, String message) {
		if (DEBUG)
			if (message != null)
				Log.i(tag, message);
			else
				Log.i(tag, "<NULL VALUE>");
	}

	public static void logError(String tag, String message) {
		if (DEBUG)
			if (message != null)
				Log.e(tag, message);
			else
				Log.e(tag, "<NULL VALUE>"); 
	}
}
