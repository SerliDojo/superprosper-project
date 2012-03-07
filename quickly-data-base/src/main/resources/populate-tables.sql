
---
--- VALORISTATION DES AGENTS
---
insert into AGENT (MATRICULE, PROFIL, NOM, PRENOM, COURRIEL) values (1, 'tech', 'GROSSEIN',  'Emilia', 'GROSSEIN.Emilia@superprosper.com');
insert into AGENT (MATRICULE, PROFIL, NOM, PRENOM, COURRIEL) values (2, 'tech', 'TAILLEFESSE',  'Ramon', 'TAILLEFESSE.Ramon@superprosper.com');
insert into AGENT (MATRICULE, PROFIL, NOM, PRENOM, COURRIEL) values (3, 'expert', 'LABONNE',  'Maria', 'LABONNE.Maria@superprosper.com');
insert into AGENT (MATRICULE, PROFIL, NOM, PRENOM, COURRIEL) values (4, 'tech', 'BRANLARD',  'Enric', 'BRANLARD.Enric@superprosper.com');
insert into AGENT (MATRICULE, PROFIL, NOM, PRENOM, COURRIEL) values (5, 'tech', 'FRIGIDE',  'Eulalia', 'FRIGIDE.Eulalia@superprosper.com');
insert into AGENT (MATRICULE, PROFIL, NOM, PRENOM, COURRIEL) values (6, 'tech', 'POURCHIER',  'Titouan', 'POURCHIER.Titouan@superprosper.com');
insert into AGENT (MATRICULE, PROFIL, NOM, PRENOM, COURRIEL) values (7, 'expert', 'NIQUET',  'Catarina', 'NIQUET.Catarina@superprosper.com');

---
--- VALORISTATION DES CLIENTS
---
insert into CLIENT (NUMERO, NOM, PRENOM, TELEPHONE, REGION) values (1, 'ABUGEAU', 'Fausto', '0549405060', 'POICHA');
insert into CLIENT (NUMERO, NOM, PRENOM, TELEPHONE, REGION) values (2, 'AUTHEBERT', 'Federi', '0549425262', 'POICHA');
insert into CLIENT (NUMERO, NOM, PRENOM, TELEPHONE, REGION) values (3, 'BARRIBEAU', 'Tounin', '0540302010', 'POICHA');
insert into CLIENT (NUMERO, NOM, PRENOM, TELEPHONE, REGION) values (4, 'CATHINEAU', 'Jaco', '0570809000', 'POICHA');
insert into CLIENT (NUMERO, NOM, PRENOM, TELEPHONE, REGION) values (5, 'DE CRESSAC', 'Lisoun', '0209123456', 'BASNOR');
insert into CLIENT (NUMERO, NOM, PRENOM, TELEPHONE, REGION) values (6, 'DELAGEBEAUDOEUF', 'Basilia', '0208745632', 'BASNOR');
insert into CLIENT (NUMERO, NOM, PRENOM, TELEPHONE, REGION) values (7, 'GUENUCHE',  'Brita', '02112233778', 'PAYLOI');
insert into CLIENT (NUMERO, NOM, PRENOM, TELEPHONE, REGION) values (8, 'HIVONAIT',  'Leonora', '02412796347', 'PAYLOI');

---
--- VALORISTATION DES CONTRATS
---
insert into CONTRAT (NUMERO, PRODUIT, DATE_SIGNATURE, DATE_EFFET) values (1, 'PRODUIT_A', '2011-09-05', '2011-10-01');
insert into CONTRAT (NUMERO, PRODUIT, DATE_SIGNATURE, DATE_EFFET) values (2, 'PRODUIT_A', '2010-01-25', '2010-02-10');
insert into CONTRAT (NUMERO, PRODUIT, DATE_SIGNATURE, DATE_EFFET) values (3, 'PRODUIT_A', '2011-02-23', '2011-10-01');
insert into CONTRAT (NUMERO, PRODUIT, DATE_SIGNATURE, DATE_EFFET) values (2, 'PRODUIT_B', '2011-03-12', '2011-04-12');
insert into CONTRAT (NUMERO, PRODUIT, DATE_SIGNATURE, DATE_EFFET) values (4, 'PRODUIT_B', '2011-04-10', '2011-05-10');
insert into CONTRAT (NUMERO, PRODUIT, DATE_SIGNATURE, DATE_EFFET) values (5, 'PRODUIT_B', '2011-05-08', '2011-05-20');
insert into CONTRAT (NUMERO, PRODUIT, DATE_SIGNATURE, DATE_EFFET) values (4, 'PRODUIT_C', '2011-06-09', '2011-06-21');

---
--- VALORISTATION DES PROSPECTS
---
insert into PROSPECT (CLIENT, AGENT, DATE_CONTACT) values (1, 1, '2011-08-12');
insert into PROSPECT (CLIENT, AGENT, DATE_CONTACT) values (2, 2, '2011-09-12');
insert into PROSPECT (CLIENT, AGENT, DATE_CONTACT) values (3, 7, '2011-09-12');


