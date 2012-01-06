CREATE TABLE AGENT (
  MATRICULE INTEGER GENERATED BY DEFAULT AS IDENTITY (START WITH 1000) NOT NULL,
  PROFIL VARCHAR(6) NULL,
  NOM VARCHAR(100) NULL,
  PRENOM VARCHAR(100) NULL,
  COURRIEL VARCHAR(200) NULL,
  PRIMARY KEY(MATRICULE)
);

CREATE TABLE CLIENT (
  NUMERO INTEGER  GENERATED BY DEFAULT AS IDENTITY NOT NULL,
  NOM VARCHAR(100) NOT NULL,
  PRENOM VARCHAR(100) NOT NULL,
  TELEPHONE VARCHAR(20) NOT NULL,
  REGION VARCHAR(6) NOT NULL,
  PRIMARY KEY(NUMERO)
);

CREATE TABLE CONTRAT (
  NUMERO INTEGER NOT NULL,
  PRODUIT VARCHAR(100) NOT NULL,
  CLIENT INTEGER NOT NULL,
  DATE_SIGNATURE VARCHAR NULL,
  DATE_EFFET VARCHAR NULL,
  PRIMARY KEY(NUMERO, PRODUIT),
  FOREIGN KEY (CLIENT) REFERENCES CLIENT(NUMERO)
);

CREATE TABLE PROSPECT (
  CLIENT INTEGER NOT NULL,
  AGENT INTEGER NOT NULL,
  DATE_CONTACT VARCHAR NULL,
  PRIMARY KEY(CLIENT),
  FOREIGN KEY (AGENT) REFERENCES AGENT(MATRICULE),
  FOREIGN KEY (CLIENT) REFERENCES CLIENT(NUMERO)
);

CREATE TABLE TEXTES (
  CATEGORIE VARCHAR(10) NOT NULL,
  CODE VARCHAR(6) NOT NULL,
  LANGUE VARCHAR(2) NOT NULL,
  TEXTE VARCHAR(200) NOT NULL,
  PRIMARY KEY(CATEGORIE, CODE, LANGUE)
);

