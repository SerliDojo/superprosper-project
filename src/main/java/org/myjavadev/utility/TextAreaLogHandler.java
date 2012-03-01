package org.myjavadev.utility;

import java.awt.TextArea;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

/**
 * 
 * @author pmeron
 */
public class TextAreaLogHandler extends Handler {

	TextArea text = null;

	/**
	 * Handler de log permettant l'affichage des messages log 
	 * dans un composant <code>TextArea</code>
	 * @param text le composant qui affiche les traces de log
	 */
	public TextAreaLogHandler(TextArea text) {
		this.text = text;
	}

	@Override
	public void publish(LogRecord record) {
		if (text != null) {
			text.append(getFormatter().format(record));
		}
	}

	@Override
	public void flush() {

	}

	@Override
	public void close() throws SecurityException {
		if (text != null) {
			text.setText("");
		}
	}
}