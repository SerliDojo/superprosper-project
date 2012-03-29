package org.myjavadev.utility;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class LoggingFormatter extends Formatter {

	private SimpleDateFormat dateFormat = null;

	public LoggingFormatter() {
		// Pattern par défaut
		this("MMM dd,yyyy HH:mm");
	}

	public LoggingFormatter(String patternDateFormat) {
		dateFormat = new SimpleDateFormat(patternDateFormat);
	}

	@Override
	public synchronized String format(LogRecord record) {
		StringBuffer sb = new StringBuffer("[");
		sb.append(dateFormat.format(new Date(record.getMillis())));
		sb.append("] ");
		sb.append(record.getLevel().getLocalizedName());
		sb.append(": ");
		sb.append(formatMessage(record));
		sb.append("\r\n");
		if (record.getThrown() != null) {
			try {
				StringWriter sw = new StringWriter();
				PrintWriter pw = new PrintWriter(sw);
				record.getThrown().printStackTrace(pw);
				pw.close();
				sb.append(sw.toString());
			} catch (Exception ex) {
			}
		}
		return sb.toString();
	}
}
