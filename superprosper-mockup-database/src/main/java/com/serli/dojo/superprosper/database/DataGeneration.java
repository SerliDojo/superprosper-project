package com.serli.dojo.superprosper.database;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.google.common.io.LineProcessor;

public class DataGeneration {

	public static void main(String[] args) throws Exception {
		generateContratProduits();
	}

	private static DateFormat dateFormat = new SimpleDateFormat("''yyyy-MM-dd''");

	public static void generateContratProduits() throws IOException {
		final Random random = new Random();
		final File contratProduitDataset = new File(
				"D:\\Documents\\SERLI\\CodingDojo\\superprosper-project\\superprosper-database\\src\\main\\dataset\\data-contrats-produits.sql");

		for (int contratId = 1; contratId < 1942; contratId++) {
			String query = "INSERT INTO CONTRAT_PRODUIT(CONTRAT, PRODUIT) VALUES(" + contratId + ", %s);\n";
			if(random.nextBoolean()) {
				Files.append(String.format(query, "'ABOPRE'"), contratProduitDataset, Charsets.UTF_8);
			} else {
				Files.append(String.format(query, "'ABOBAS'"), contratProduitDataset, Charsets.UTF_8);
				if(random.nextBoolean()) {
					Files.append(String.format(query, "'OPTFID'"), contratProduitDataset, Charsets.UTF_8);
				}
				if(random.nextBoolean()) {
					Files.append(String.format(query, "'OPTASS'"), contratProduitDataset, Charsets.UTF_8);
				}
				if(random.nextBoolean()) {
					Files.append(String.format(query, "'OPTLIV'"), contratProduitDataset, Charsets.UTF_8);
				}
			}
		}
	}

	public static void generateContrats() throws IOException, ParseException {
		List<String> propKeys = new ArrayList<String>();
		Random random = new Random();
		Calendar firstCalendar = new GregorianCalendar();
		firstCalendar.add(Calendar.DAY_OF_YEAR, -300);
		Date firstDay = firstCalendar.getTime();

		File contratDataset = new File(
				"D:\\Documents\\SERLI\\CodingDojo\\superprosper-project\\superprosper-database\\src\\main\\dataset\\data-contrats.sql");
		File prospectionDataset = new File(
				"D:\\Documents\\SERLI\\CodingDojo\\superprosper-project\\superprosper-database\\src\\main\\dataset\\data-prospections.sql");

		for (int i = 0; i < 2000; i++) {
			int indexProspection = random.nextInt(40000);
			int indexJour = random.nextInt(30);

			if (!propKeys.contains(Integer.toString(indexProspection))) {
				propKeys.add(Integer.toString(indexProspection));
				String line = Files.readLines(prospectionDataset, Charsets.UTF_8, new LineIdProcessorExtractor(
						indexProspection));
				String clientId = line.substring(line.lastIndexOf('('));
				clientId = clientId.substring(1, clientId.indexOf(','));
				String contact = line.substring(line.lastIndexOf(','));
				contact = contact.substring(2, contact.lastIndexOf(')'));

				Calendar calendar = new GregorianCalendar();
				calendar.setTime(dateFormat.parse(contact));
				calendar.add(Calendar.DAY_OF_YEAR, indexJour);
				String effet = dateFormat.format(calendar.getTime());

				String query = "INSERT INTO CONTRAT(CLIENT, SIGNATURE, EFFET) VALUES(" + clientId + ", " + contact
						+ ", " + effet + ");";
				Files.append(query + "\n", contratDataset, Charsets.UTF_8);
			}
		}
	}

	public static void generateProspections() throws IOException {
		List<String> propKeys = new ArrayList<String>();
		Random random = new Random();
		Calendar firstCalendar = new GregorianCalendar();
		firstCalendar.add(Calendar.DAY_OF_YEAR, -300);
		Date firstDay = firstCalendar.getTime();

		File agentDataset = new File(
				"D:\\Documents\\SERLI\\CodingDojo\\superprosper-project\\superprosper-database\\src\\main\\dataset\\data-agents.sql");
		File prospectionDataset = new File(
				"D:\\Documents\\SERLI\\CodingDojo\\superprosper-project\\superprosper-database\\src\\main\\dataset\\data-prospections.sql");

		for (int i = 0; i < 50000; i++) {
			int indexAgent = random.nextInt(700);
			int indexClient = random.nextInt(30000);
			int indexJour = random.nextInt(300);

			Calendar calendar = new GregorianCalendar();
			calendar.setTime(firstDay);
			calendar.add(Calendar.DAY_OF_YEAR, indexJour);

			String contact = dateFormat.format(calendar.getTime());

			String agentId = Files.readLines(agentDataset, Charsets.UTF_8, new LineIdProcessorExtractor(indexAgent));
			agentId = agentId.substring(67);
			agentId = agentId.substring(0, agentId.indexOf(','));

			String clientId = Integer.toString(indexClient);

			String query = "INSERT INTO PROSPECTION(CLIENT, AGENT, CONTACT) VALUES(" + clientId + ", " + agentId + ", "
					+ contact + ");";
			if (!propKeys.contains(clientId + contact)) {
				propKeys.add(clientId + contact);
				Files.append(query + "\n", prospectionDataset, Charsets.UTF_8);
			}
		}
	}

	public static void alterateClients() throws IOException {
		final Random random = new Random();
		final File clientDatasetComplete = new File(
				"D:\\Documents\\SERLI\\CodingDojo\\superprosper-project\\superprosper-database\\data-clients-full.sql");
		File clientDataset = new File(
				"D:\\Documents\\SERLI\\CodingDojo\\superprosper-project\\superprosper-database\\src\\main\\dataset\\data-clients.sql");
		Files.readLines(clientDataset, Charsets.UTF_8, new LineProcessor<Void>() {

			@Override
			public boolean processLine(String line) throws IOException {
				String naissance = "NULL";
				String adresse = "NULL";
				String foyer = "NULL";
				if (random.nextInt(3) == 0) {
					GregorianCalendar calendar = new GregorianCalendar(1900 + random.nextInt(90), random.nextInt(12),
							1 + random.nextInt(31));
					naissance = dateFormat.format(calendar.getTime());
				}
				if (random.nextInt(10) == 0) {
					foyer = "'" + (1 + random.nextInt(5)) + "'";
				}
				String newLine = line.replace("NULL, NULL, NULL", naissance + ", " + adresse + ", " + foyer);
				Files.append(newLine + "\n", clientDatasetComplete, Charsets.UTF_8);
				return true;
			}

			@Override
			public Void getResult() {
				return null;
			}

		});
	}

	private static final class LineIdProcessorExtractor implements LineProcessor<String> {
		private int count = 0;
		private int max;
		private String lineRed;

		public LineIdProcessorExtractor(int max) {
			this.max = max;
		}

		@Override
		public boolean processLine(String line) throws IOException {
			if (count == max) {
				lineRed = line;
				return false;
			} else {
				count++;
			}
			return true;
		}

		@Override
		public String getResult() {
			return lineRed;
		}
	}
}
