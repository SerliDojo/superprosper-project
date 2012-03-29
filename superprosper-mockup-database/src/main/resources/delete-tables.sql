---
--- SUPPRESSION DES CLEFS ETRANGERES
---
alter table CONTRAT drop constraint FK_CONTRAT_NUMERO;
alter table PROSPECT drop constraint FK_PROSPECT_CLIENT;
alter table PROSPECT drop constraint FK_PROSPECT_AGENT;

---
--- SUPPRESSION DES TABLES
---
drop table AGENT;
drop table CLIENT;
drop table CONTRAT;
drop table PROSPECT;
drop table TEXTES;
