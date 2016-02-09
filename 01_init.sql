-- Creation des tables

CREATE SCHEMA IF NOT EXISTS lup1;

------------------------------------------------------------
-- Table: role
------------------------------------------------------------
CREATE TABLE lup1.role(
	rno TEXT ,
	CONSTRAINT prk_constraint_role PRIMARY KEY (rno)
)WITHOUT OIDS;


------------------------------------------------------------
-- Table: credential
------------------------------------------------------------
CREATE TABLE lup1.credential(
	login    TEXT ,
	password TEXT   ,
	CONSTRAINT prk_constraint_credential PRIMARY KEY (login)
)WITHOUT OIDS;


------------------------------------------------------------
-- Table: member
------------------------------------------------------------
CREATE TABLE lup1.member(
	member_id  INTEGER ,
	first_name TEXT   ,
	last_name  TEXT   ,
	email      TEXT   ,
	birthday   DATE   ,
	rno        TEXT   ,
	login      TEXT   ,
	siret      TEXT   ,
	CONSTRAINT prk_constraint_member PRIMARY KEY (member_id)
)WITHOUT OIDS;


------------------------------------------------------------
-- Table: evaluation
------------------------------------------------------------
CREATE TABLE lup1.evaluation(
	evaluation_id INTEGER ,
	name          TEXT   ,
	coeff         FLOAT   ,
	member_id     INTEGER   ,
	subject_id    INTEGER   ,
	CONSTRAINT prk_constraint_evaluation PRIMARY KEY (evaluation_id)
)WITHOUT OIDS;


------------------------------------------------------------
-- Table: subject
------------------------------------------------------------
CREATE TABLE lup1.subject(
	subject_id INTEGER ,
	name       TEXT   ,
	coeff      FLOAT   ,
	ue_id      INTEGER   ,
	CONSTRAINT prk_constraint_subject PRIMARY KEY (subject_id)
)WITHOUT OIDS;


------------------------------------------------------------
-- Table: ue
------------------------------------------------------------
CREATE TABLE lup1.ue(
	ue_id INTEGER ,
	name  TEXT   ,
	CONSTRAINT prk_constraint_ue PRIMARY KEY (ue_id)
)WITHOUT OIDS;


------------------------------------------------------------
-- Table: formation
------------------------------------------------------------
CREATE TABLE lup1.formation(
	formation_id INTEGER ,
	name         TEXT   ,
	CONSTRAINT prk_constraint_formation PRIMARY KEY (formation_id)
)WITHOUT OIDS;


------------------------------------------------------------
-- Table: organization
------------------------------------------------------------
CREATE TABLE lup1.organization(
	siret   TEXT   ,
	name    TEXT   ,
	address TEXT   ,
	phone   TEXT   ,
	email   TEXT   ,
	CONSTRAINT prk_constraint_organization PRIMARY KEY (siret)
)WITHOUT OIDS;


------------------------------------------------------------
-- Table: internship
------------------------------------------------------------
CREATE TABLE lup1.internship(
	internship_id INTEGER,
	title         TEXT   ,
	missions      TEXT   ,
	description   TEXT   ,
	duration      TIMESTAMP   ,
	year          DATE   ,
	siret         TEXT   ,
	CONSTRAINT prk_constraint_internship PRIMARY KEY (internship_id)
)WITHOUT OIDS;


------------------------------------------------------------
-- Table: flag
------------------------------------------------------------
CREATE TABLE lup1.flag(
	flag_id INTEGER,
	label   TEXT   ,
	CONSTRAINT prk_constraint_flag PRIMARY KEY (flag_id)
)WITHOUT OIDS;


------------------------------------------------------------
-- Table: intervention
------------------------------------------------------------
CREATE TABLE lup1.intervention(
	intervention_id   INTEGER,
	quote             TEXT   ,
	date_intervention DATE   ,
	member_id         INTEGER   ,
	promotion_id      INTEGER   ,
	CONSTRAINT prk_constraint_intervention PRIMARY KEY (intervention_id)
)WITHOUT OIDS;


------------------------------------------------------------
-- Table: internship_log
------------------------------------------------------------
CREATE TABLE lup1.internship_log(
	internship_log_id INTEGER   ,
	date_log          TIMESTAMP   ,
	quote             TEXT   ,
	flag_id           INTEGER   ,
	member_id         INTEGER   ,
	internship_id     INTEGER   ,
	CONSTRAINT prk_constraint_internship_log PRIMARY KEY (internship_log_id)
)WITHOUT OIDS;


------------------------------------------------------------
-- Table: promotion
------------------------------------------------------------
CREATE TABLE lup1.promotion(
	promotion_id INTEGER   ,
	year         DATE   ,
	formation_id INTEGER   ,
	member_id    INTEGER   ,
	CONSTRAINT prk_constraint_promotion PRIMARY KEY (promotion_id)
)WITHOUT OIDS;


------------------------------------------------------------
-- Table: ue_promotion
------------------------------------------------------------
CREATE TABLE lup1.ue_promotion(
	semester     INTEGER  NOT NULL ,
	coeff        FLOAT  NOT NULL ,
	promotion_id INTEGER  NOT NULL ,
	ue_id        INTEGER  NOT NULL ,
	CONSTRAINT prk_constraint_ue_promotion PRIMARY KEY (promotion_id,ue_id)
)WITHOUT OIDS;


------------------------------------------------------------
-- Table: is_register_in
------------------------------------------------------------
CREATE TABLE lup1.is_register_in(
	member_id    INTEGER,
	promotion_id INTEGER,
	CONSTRAINT prk_constraint_is_register_in PRIMARY KEY (member_id,promotion_id)
)WITHOUT OIDS;


------------------------------------------------------------
-- Table: do_eval
------------------------------------------------------------
CREATE TABLE lup1.do_eval(
	note          FLOAT   ,
	member_id     INTEGER,
	evaluation_id INTEGER,
	CONSTRAINT prk_constraint_do_eval PRIMARY KEY (member_id,evaluation_id)
)WITHOUT OIDS;


------------------------------------------------------------
-- Table: manage_internship
------------------------------------------------------------
CREATE TABLE lup1.manage_internship(
	member_id     INTEGER,
	internship_id INTEGER,
	CONSTRAINT prk_constraint_manage_internship PRIMARY KEY (member_id,internship_id)
)WITHOUT OIDS;


------------------------------------------------------------
-- Table: register_to_internship
------------------------------------------------------------
CREATE TABLE lup1.register_to_internship(
	internship_id INTEGER,
	member_id     INTEGER,
	CONSTRAINT prk_constraint_register_to_internship PRIMARY KEY (internship_id,member_id)
)WITHOUT OIDS;



ALTER TABLE lup1.member ADD CONSTRAINT FK_member_rno FOREIGN KEY (rno) REFERENCES lup1.role(rno);
ALTER TABLE lup1.member ADD CONSTRAINT FK_member_login FOREIGN KEY (login) REFERENCES lup1.credential(login);
ALTER TABLE lup1.member ADD CONSTRAINT FK_member_siret FOREIGN KEY (siret) REFERENCES lup1.organization(siret);
ALTER TABLE lup1.evaluation ADD CONSTRAINT FK_evaluation_member_id FOREIGN KEY (member_id) REFERENCES lup1.member(member_id);
ALTER TABLE lup1.evaluation ADD CONSTRAINT FK_evaluation_subject_id FOREIGN KEY (subject_id) REFERENCES lup1.subject(subject_id);
ALTER TABLE lup1.subject ADD CONSTRAINT FK_subject_ue_id FOREIGN KEY (ue_id) REFERENCES lup1.ue(ue_id);
ALTER TABLE lup1.internship ADD CONSTRAINT FK_internship_siret FOREIGN KEY (siret) REFERENCES lup1.organization(siret);
ALTER TABLE lup1.intervention ADD CONSTRAINT FK_intervention_member_id FOREIGN KEY (member_id) REFERENCES lup1.member(member_id);
ALTER TABLE lup1.intervention ADD CONSTRAINT FK_intervention_promotion_id FOREIGN KEY (promotion_id) REFERENCES lup1.promotion(promotion_id);
ALTER TABLE lup1.internship_log ADD CONSTRAINT FK_internship_log_flag_id FOREIGN KEY (flag_id) REFERENCES lup1.flag(flag_id);
ALTER TABLE lup1.internship_log ADD CONSTRAINT FK_internship_log_member_id FOREIGN KEY (member_id) REFERENCES lup1.member(member_id);
ALTER TABLE lup1.internship_log ADD CONSTRAINT FK_internship_log_internship_id FOREIGN KEY (internship_id) REFERENCES lup1.internship(internship_id);
ALTER TABLE lup1.promotion ADD CONSTRAINT FK_promotion_formation_id FOREIGN KEY (formation_id) REFERENCES lup1.formation(formation_id);
ALTER TABLE lup1.promotion ADD CONSTRAINT FK_promotion_member_id FOREIGN KEY (member_id) REFERENCES lup1.member(member_id);
ALTER TABLE lup1.ue_promotion ADD CONSTRAINT FK_ue_promotion_promotion_id FOREIGN KEY (promotion_id) REFERENCES lup1.promotion(promotion_id);
ALTER TABLE lup1.ue_promotion ADD CONSTRAINT FK_ue_promotion_ue_id FOREIGN KEY (ue_id) REFERENCES lup1.ue(ue_id);
ALTER TABLE lup1.is_register_in ADD CONSTRAINT FK_is_register_in_member_id FOREIGN KEY (member_id) REFERENCES lup1.member(member_id);
ALTER TABLE lup1.is_register_in ADD CONSTRAINT FK_is_register_in_promotion_id FOREIGN KEY (promotion_id) REFERENCES lup1.promotion(promotion_id);
ALTER TABLE lup1.do_eval ADD CONSTRAINT FK_do_eval_member_id FOREIGN KEY (member_id) REFERENCES lup1.member(member_id);
ALTER TABLE lup1.do_eval ADD CONSTRAINT FK_do_eval_evaluation_id FOREIGN KEY (evaluation_id) REFERENCES lup1.evaluation(evaluation_id);
ALTER TABLE lup1.manage_internship ADD CONSTRAINT FK_manage_internship_member_id FOREIGN KEY (member_id) REFERENCES lup1.member(member_id);
ALTER TABLE lup1.manage_internship ADD CONSTRAINT FK_manage_internship_internship_id FOREIGN KEY (internship_id) REFERENCES lup1.internship(internship_id);
ALTER TABLE lup1.register_to_internship ADD CONSTRAINT FK_register_to_internship_internship_id FOREIGN KEY (internship_id) REFERENCES lup1.internship(internship_id);
ALTER TABLE lup1.register_to_internship ADD CONSTRAINT FK_register_to_internship_member_id FOREIGN KEY (member_id) REFERENCES lup1.member(member_id);
