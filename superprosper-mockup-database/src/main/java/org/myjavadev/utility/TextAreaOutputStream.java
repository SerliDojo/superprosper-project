package org.myjavadev.utility;

import java.io.IOException;

import javax.swing.JTextArea;

public class TextAreaOutputStream extends java.io.OutputStream {

	private final JTextArea textArea;
	private final StringBuilder sb = new StringBuilder();

	public TextAreaOutputStream(JTextArea textArea) {
		this.textArea = textArea;
	}

	@Override
	public void write(int b) throws IOException {
		if (b == '\r')
			return;

		if (b == '\n') {
			textArea.append(sb.toString() + (char) b);
			sb.setLength(0);
		} else {
			sb.append((char) b);
		}
	}

	@Override
	public void flush() throws IOException {
		super.flush();
		this.textArea.setCaretPosition(textArea.getDocument().getLength());
	}

}
