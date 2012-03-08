package com.serli.dojo.superprosper;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.google.common.base.Charsets;
import com.google.common.base.Strings;
import com.google.common.io.Files;
import com.google.common.io.Resources;

public class DataGenerator {

	public static void main(String[] args) {
		generateAgents();
		generateClients();
	}

	private static void generateClients() {
		Random random = new Random();
		List<String> firstnames = loadFromResource("firstname_fr.txt");
		List<String> lastnames = loadFromResource("lastname_fr.txt");
		List<String> entries = new ArrayList<String>(30000);

		while (entries.size() < 30000) {
			int indexPrenom = random.nextInt(firstnames.size());
			int indexNom = random.nextInt(lastnames.size());

			String prenom = firstnames.get(indexPrenom);
			String nom = lastnames.get(indexNom);
			String telephone = Integer.toString(random.nextInt(100000000));
			Strings.padStart(telephone, 8, '0');
			String region = "";
			switch (random.nextInt(5)) {
			case 0:
				region = "NO";
				telephone = "02" + telephone;
				break;
			case 1:
				region = "NE";
				telephone = "03" + telephone;
				break;
			case 2:
				region = "SE";
				telephone = "04" + telephone;
				break;
			case 3:
				region = "SO";
				telephone = "05" + telephone;
				break;
			default:
				region = "IDF";
				telephone = "01" + telephone;
			}

			entries.add(String
					.format("INSERT INTO CLIENT(NOM, PRENOM, TELEPHONE, REGION) VALUES('%s', '%s', '%s', '%s');\n",
							nom, prenom, telephone, region));
		}

		writeToResource("data-clients.sql", entries);
	}

	private static void generateAgents() {
		Random random = new Random();
		List<String> firstnames = loadFromResource("firstname_en.txt");
		List<String> lastnames = loadFromResource("lastname_en.txt");
		Map<String, String> entries = new HashMap<String, String>(1000);

		while (entries.size() < 1000) {
			int indexPrenom = random.nextInt(firstnames.size());
			int indexNom = random.nextInt(lastnames.size());

			String prenom = firstnames.get(indexPrenom);
			String nom = lastnames.get(indexNom);
			String courriel = String.format("%s.%s@thatcompany.com", prenom,
					nom).toLowerCase();
			String matricule = prenom.charAt(0) + nom;
			matricule = matricule.substring(0,
					matricule.length() < 8 ? matricule.length() : 8)
					.toLowerCase();
			String profil = random.nextInt(8) > 0 ? "PRSPCT" : "SPRVSR";

			if (!entries.containsKey(matricule)) {
				entries.put(
						matricule,
						String.format(
								"INSERT INTO AGENT(MATRICULE, PROFIL, NOM, PRENOM, COURRIEL) VALUES('%s', '%s', '%s', '%s', '%s');\n",
								matricule, profil, nom, prenom, courriel));
			}
		}

		writeToResource("data-agents.sql", entries.values());
	}

	private static void writeToResource(String name, Collection<String> lines) {
		File file = new File(name);
		for (String line : lines) {
			try {
				Files.append(line, file, Charsets.UTF_8);
			} catch (IOException e) {
			}
		}
	}

	private static List<String> loadFromResource(String name) {
		List<String> lines = Collections.emptyList();
		URL resource = DataGenerator.class.getClassLoader().getResource(name);
		try {
			lines = Resources.readLines(resource, Charsets.UTF_8);
		} catch (IOException e) {
		}
		return lines;
	}
}
