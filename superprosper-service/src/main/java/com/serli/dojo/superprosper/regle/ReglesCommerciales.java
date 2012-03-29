package com.serli.dojo.superprosper.regle;

import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.lang3.tuple.Pair;

import com.serli.dojo.superprosper.domain.Client;
import com.serli.dojo.superprosper.domain.Contrat;
import com.serli.dojo.superprosper.domain.Prospection;

public final class ReglesCommerciales extends ReglesGeneriques {

	/**
	 * Contruction privée.
	 */
	private ReglesCommerciales() {
	}

	/**
	 * Règle vérifiant qu'une prospection n'a pas déjà eu lieu à la même date
	 * pour le client précisé.
	 */
	public static final Regle<Pair<Prospection, Client>> PAS_DEUX_PROSPECTION_A_LA_MEME_DATE = new Regle<Pair<Prospection, Client>>() {
		@Override
		public void verifier(Pair<Prospection, Client> pair) {
			List<Prospection> prospectionsPrecedentes = pair.getRight().getProspections();
			for (Prospection prospectionPrecedente : prospectionsPrecedentes) {
				if (DateUtils.isSameDay(prospectionPrecedente.getContact(), pair.getLeft().getContact())) {
					throw new RuntimeException("Une prospection a déjà eu lieu à la même date.");
				}
			}
		}
	};

	/**
	 * Règle vérifiant qu'une prospection a bien eu lieu à la date de signature
	 * du contrat pour le client précisé.
	 */
	public static final Regle<Pair<Contrat, Client>> PROSPECTION_OBLIGATOIRE_AVANT_CONTRAT = new Regle<Pair<Contrat, Client>>() {
		@Override
		public void verifier(Pair<Contrat, Client> pair) {
			boolean prospecteAuMemeJour = false;
			List<Prospection> prospections = pair.getRight().getProspections();
			for (Prospection prospection : prospections) {
				prospecteAuMemeJour |= DateUtils.isSameDay(prospection.getContact(), pair.getLeft().getSignature());
			}
			if (!prospecteAuMemeJour) {
				throw new RuntimeException("Aucune prospection n'a eu lieu à la date de signature.");
			}
		}
	};
}
