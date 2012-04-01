package com.serli.dojo.superprosper.database.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import org.myjavadev.utility.TextAreaOutputStream;

/**
 * Panneau permettant l'affichage de traces.
 * 
 * @author Pascal MERON
 * @author Laurent RUAUD
 */
public class LoggingPanel extends JPanel {

	/** Numéro de série. */
	private static final long serialVersionUID = 1L;

	/** Flux sortant pour insérer des données dans le panneau de trace. */
	private TextAreaOutputStream outputStream;

	/** Zone de texte pour l'affichage des traces. */
	private JTextArea txtLog;

	/**
	 * Constructeur.
	 */
	public LoggingPanel() {
		txtLog = new JTextArea();
		txtLog.setEditable(false);
		txtLog.setBackground(Color.BLACK);
		txtLog.setForeground(Color.WHITE);
		txtLog.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
		txtLog.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec congue elit eu turpis vehicula nec sagittis lectus gravida. Nulla facilisi. Sed consequat turpis vitae turpis interdum ut elementum metus rhoncus. Sed est sem, congue sed venenatis a, aliquet nec tellus. Nunc convallis massa vitae nunc placerat mollis. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Nunc sollicitudin nulla sed dui semper non fringilla est imperdiet. Donec ut nisi felis.\n"
				+ "Etiam semper malesuada faucibus. Maecenas nulla purus, interdum sit amet facilisis sed, euismod in leo. Nulla nisi eros, condimentum faucibus scelerisque eu, sagittis vel erat. Donec diam libero, dictum non elementum a, feugiat rhoncus nunc. Integer ac convallis odio. Donec ante felis, dictum non auctor in, tristique in felis. Nunc a nibh velit. Duis adipiscing volutpat neque ut sollicitudin. Aliquam vel elit at erat volutpat viverra. In eu volutpat turpis.\n"
				+ "Ut dapibus, eros eu malesuada semper, ipsum risus ullamcorper ante, ac sagittis quam purus in nisi. Nam at erat nec diam dignissim placerat. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Phasellus vel sapien elit, et laoreet augue. Nullam eros est, facilisis ut cursus in, gravida sit amet quam. Quisque tempus turpis nulla, id volutpat erat. Donec commodo molestie placerat. Phasellus sit amet nunc a est imperdiet euismod. Vestibulum sed sem et lectus rhoncus ultricies varius non lectus. Phasellus lobortis, tortor nec vulputate tempus, mauris erat eleifend metus, ut imperdiet tellus ante nec ligula. Etiam semper sem eget nunc vestibulum fringilla. Pellentesque hendrerit nisi et felis egestas vel tempor ipsum convallis. Sed sed tellus sit amet est fringilla malesuada ut et diam. Suspendisse pulvinar vehicula sem.\n"
				+ "Etiam ac velit sed nulla varius sollicitudin non a erat. Quisque a nunc nunc, quis mattis leo. Etiam pharetra elementum convallis. Nulla et lacus sit amet felis bibendum iaculis. Donec adipiscing erat sed justo ultrices laoreet. Maecenas in augue venenatis tortor faucibus fringilla. Sed feugiat libero a velit tempus ultrices. Pellentesque ullamcorper purus eu massa laoreet lobortis. In et sapien arcu, sagittis rutrum tellus. Sed commodo consequat condimentum. Fusce iaculis dolor a justo laoreet ut consectetur libero imperdiet. Duis rhoncus vehicula libero, non vestibulum nulla luctus ut. Vestibulum a purus at est laoreet fringilla.\n"
				+ "Sed quis tempor neque. Curabitur ut metus et quam rutrum porta. Aliquam at tortor mauris. Nunc convallis felis felis. In ultricies laoreet orci at adipiscing. Proin fermentum tortor et quam placerat scelerisque. Donec est nibh, aliquam ut gravida et, pulvinar eu lectus. Donec adipiscing volutpat lorem sed varius. Vestibulum sed erat sit amet diam pulvinar porta. Pellentesque quis congue mi. Nullam sit amet sem id felis rhoncus interdum et a eros. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque vehicula, dui vitae tincidunt suscipit, est nibh laoreet velit, vel mollis risus massa et tortor. Vestibulum vitae arcu in ligula tempus interdum.\n");

		outputStream = new TextAreaOutputStream(txtLog);

		setBorder(new TitledBorder("Traces"));
		setLayout(new BorderLayout());
		add(new JScrollPane(txtLog), BorderLayout.CENTER);
	}

	/**
	 * Renvoie la valeur de {@linkplain #outputStream outputStream}.
	 * 
	 * @return la valeur de outputStream
	 */
	public TextAreaOutputStream getOutputStream() {
		return outputStream;
	}

	/**
	 * Efface le contenu du panneau de trace.
	 */
	public void clear() {
		txtLog.setText("");
	}

}
