CREATE TABLE AGENT (
  MATRICULE VARCHAR(8) NOT NULL,
  PROFIL CHAR(6) NOT NULL,
  NOM VARCHAR(100) NOT NULL,
  PRENOM VARCHAR(100) NOT NULL,
  COURRIEL VARCHAR(100) NOT NULL,
  PRIMARY KEY(MATRICULE)
);

CREATE TABLE CLIENT (
  NUMERO INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL,
  NOM VARCHAR(100) NOT NULL,
  PRENOM VARCHAR(100) NOT NULL,
  TELEPHONE VARCHAR(20) NOT NULL,
  REGION CHAR(6) NOT NULL,
  PRIMARY KEY(NUMERO)
);

CREATE TABLE CONTRAT (
  NUMERO INTEGER NOT NULL,
  PRODUIT CHAR(6) NOT NULL,
  CLIENT INTEGER NOT NULL,
  SIGNATURE DATE NOT NULL,
  EFFET DATE NOT NULL,
  PRIMARY KEY(NUMERO, PRODUIT),
  FOREIGN KEY (CLIENT) REFERENCES CLIENT(NUMERO)
);

CREATE TABLE PROSPECT (
  CLIENT INTEGER NOT NULL,
  AGENT VARCHAR(8) NOT NULL,
  CONTACT DATE NOT NULL,
  PRIMARY KEY(CLIENT, CONTACT),
  FOREIGN KEY (AGENT) REFERENCES AGENT(MATRICULE),
  FOREIGN KEY (CLIENT) REFERENCES CLIENT(NUMERO)
);

CREATE TABLE TEXTE (
  CATEGORIE CHAR(10) NOT NULL,
  CODE CHAR(6) NOT NULL,
  LANGUE CHAR(2) NOT NULL,
  TEXTE VARCHAR(200) NOT NULL,
  PRIMARY KEY(CATEGORIE, CODE, LANGUE)
);

