-- Insertions test

-- Insertion de rôles

INSERT INTO role (ROLE) VALUES('etudiant');
INSERT INTO role (ROLE) VALUES('responsable_formation');
INSERT INTO role (ROLE) VALUES('responsable_stage');
INSERT INTO role (ROLE) VALUES('responsable_cours');
INSERT INTO role (ROLE) VALUES('etudiant_invalide');
INSERT INTO role (ROLE) VALUES('admin');
-- INSERT INTO role VALUES('responsable_promo');

-- Fin Insertion de rôles

-- -- --

-- Insertion de membres

INSERT INTO member (FIRST_NAME,LAST_NAME,EMAIL,BIRTHDAY,PHONE,SIRET,PICTURE,ADDRESS) VALUES('Michael', 'Hauspie', 'hauspiem@univ-lille1.fr', '12/05/1985', null,null,null,null);
INSERT INTO member (FIRST_NAME,LAST_NAME,EMAIL,BIRTHDAY,PHONE,SIRET,PICTURE,ADDRESS) VALUES('Thomas', 'Ferro', 'thomas.ferro@univ-lille1.fr', '19/10/1995', null,null,null,null);
INSERT INTO member (FIRST_NAME,LAST_NAME,EMAIL,BIRTHDAY,PHONE,SIRET,PICTURE,ADDRESS) VALUES('Édouard', 'Cattez', 'edouard.cattez@univ-lille1.fr', '12/10/1994', null,null,null,null);
INSERT INTO member (FIRST_NAME,LAST_NAME,EMAIL,BIRTHDAY,PHONE,SIRET,PICTURE,ADDRESS) VALUES('Rémy', 'Fevre', 'remy.fevre@univ-lille1.fr', '05/03/1995', null,null,null,null);
INSERT INTO member (FIRST_NAME,LAST_NAME,EMAIL,BIRTHDAY,PHONE,SIRET,PICTURE,ADDRESS) VALUES('Phillipe', 'Mathieu', 'phillipe.mathieu@univ-lille1.fr', '12/11/1967', null,null,null,null);
INSERT INTO member (FIRST_NAME,LAST_NAME,EMAIL,BIRTHDAY,PHONE,SIRET,PICTURE,ADDRESS) VALUES('Fanny', 'Oui', 'fanny.oui@smile.fr', '12/03/1989', null,null,null,null);

-- Fin Insertion de membres

-- -- --

-- Insertion de credentials

INSERT INTO credential VALUES('hauspiem', '1000:aabc3949f5c2e0b06c2128dfe18de790d311ab43fd78dc4e:758ab728ab2498a5208df3b30f84fb265002022f1dbfbad6',1);
INSERT INTO credential VALUES('ferrot', '1000:f9be0d1ace62aadaa67b31289557e21cc734e704f5883949:da427c89f1ba1cb82ae5a85d0e34d905e641ed149a7c241b',2);
INSERT INTO credential VALUES('catteze', '1000:6139218b53a1bdc476853d1c3cac057aeece81df843571aa:56c5e709df10536967b63fc48ab30c0d3a6d10fb6613091a',3);
INSERT INTO credential VALUES('fevrer', '1000:84c63106c14eee935eb0c854efffb26ecf529d4caa14fe60:04803f89c222d471274223c6d1f35dd6409eee72223c851d',4);
INSERT INTO credential VALUES('mathieup', '1000:fae09990e41e01c10f55c4ea95988d562900073bc37ad5dd:9b3d6099cf32e58e87d01f66206a56107f0b1c7489b7ebe8',5);
INSERT INTO credential VALUES('fannySmile', '1000:81441b4781ee326e1bcc4934bf36a5c99d675fbc6e6b0065:7ac0c66ad9c9b68a0b68ef7758de2b9753b0b20264da1230',6);

-- Fin Insertion de credentials

-- -- --

-- Insertion d'entreprises

INSERT INTO organization VALUES('73282932000074', 'Smile', 'adresse Smile', 'phoneSmile', 'fax_smile','RH_smile');
INSERT INTO organization VALUES('73282932000075', 'OVH', 'adresse OVH', 'phoneOVH', 'fax_ovh','RH_ovh');
INSERT INTO organization VALUES('73282932000076', 'Opquast', 'adresse Opquast', 'phoneOpquast', 'fax_opquast','RH_opquast');


-- Fin Insertion d'entreprises

-- -- --

-- Insertion des roles de chaque membre

INSERT INTO has_role VALUES ('hauspiem','responsable_formation');
INSERT INTO has_role VALUES ('hauspiem','responsable_cours');
INSERT INTO has_role VALUES ('hauspiem','admin');
INSERT INTO has_role VALUES ('ferrot','etudiant');
INSERT INTO has_role VALUES ('catteze','etudiant');
INSERT INTO has_role VALUES ('fevrer','etudiant');
INSERT INTO has_role VALUES ('mathieup','responsable_cours');
INSERT INTO has_role VALUES ('fannySmile','responsable_cours');

-- Fin insertion des roles de chaque membre

-- -- --

-- Insertion de l'allocation des rôles

-- INSERT INTO is_allowed_to VALUES('responsable_formation', 1);
-- INSERT INTO is_allowed_to VALUES('etudiant', 2);
-- INSERT INTO is_allowed_to VALUES('etudiant', 3);
-- INSERT INTO is_allowed_to VALUES('etudiant', 4);
-- INSERT INTO is_allowed_to VALUES('responsable_cours', 5);
-- INSERT INTO is_allowed_to VALUES('responsable_cours', 6);


-- Fin Insertion l'allocation des rôles

-- -- --


-- Insertion d'UE

INSERT INTO ue (NAME) VALUES ('Informatique');
INSERT INTO ue (NAME) VALUES ('Professionnalisation');
INSERT INTO ue (NAME) VALUES ('Stage/Projet final');
INSERT INTO ue (NAME) VALUES ('Général');

-- Fin Insertion d'UE

-- -- --

-- Insertion de subject

INSERT INTO subject (NAME,COEFF,UE_ID) VALUES('Génie Logiciel', 2.0, 1);
INSERT INTO subject (NAME,COEFF,UE_ID) VALUES('Base de Données', 2.0, 1);
INSERT INTO subject (NAME,COEFF,UE_ID) VALUES('Système', 3.0, 1);
INSERT INTO subject (NAME,COEFF,UE_ID) VALUES('Administration BDD', 2.0, 2);
INSERT INTO subject (NAME,COEFF,UE_ID) VALUES('Réseau', 1.0, 2);
INSERT INTO subject (NAME,COEFF,UE_ID) VALUES('Projet final', 2.0, 3);
INSERT INTO subject (NAME,COEFF,UE_ID) VALUES('Stage', 1.0, 3);
INSERT INTO subject (NAME,COEFF,UE_ID) VALUES('Anglais', 2.0, 4);
INSERT INTO subject (NAME,COEFF,UE_ID) VALUES('TE', 1.0, 4);

-- Fin Insertion d'subject

-- -- --

-- Insertion d'évaluations

INSERT INTO evaluation (NAME,COEFF,TEACHER_ID,SUBJECT_ID,DATE_EVAL) VALUES('DS1', 3.0, 1, 9, null);
INSERT INTO evaluation (NAME,COEFF,TEACHER_ID,SUBJECT_ID,DATE_EVAL) VALUES('DS2', 2.0, 1, 9, null);
INSERT INTO evaluation (NAME,COEFF,TEACHER_ID,SUBJECT_ID,DATE_EVAL) VALUES('DS1', 2.0, 5, 2, null);
INSERT INTO evaluation (NAME,COEFF,TEACHER_ID,SUBJECT_ID,DATE_EVAL) VALUES('CV', 1.0, 6, 8, null);
INSERT INTO evaluation (NAME,COEFF,TEACHER_ID,SUBJECT_ID,DATE_EVAL) VALUES('Projet final', 5.0, 1, 5, null);
INSERT INTO evaluation (NAME,COEFF,TEACHER_ID,SUBJECT_ID,DATE_EVAL) VALUES('DS1', 2.0, 6, 7, null);
INSERT INTO evaluation (NAME,COEFF,TEACHER_ID,SUBJECT_ID,DATE_EVAL) VALUES('Stage', 5.0, 1, 6, null);
INSERT INTO evaluation (NAME,COEFF,TEACHER_ID,SUBJECT_ID,DATE_EVAL) VALUES('DS_PHP', 5.0, 1, 9, null);

-- Fin Insertion d'évaluations

-- -- --


-- Insertion de formations

INSERT INTO formation (NAME,SEMESTRE_MIN,SEMESTRE_MAX) VALUES('DA2I',5,6);
INSERT INTO formation (NAME,SEMESTRE_MIN,SEMESTRE_MAX) VALUES('LPRéseau',5,6);

-- Fin Insertion de formations

-- -- --


-- Insertion de promotions

INSERT INTO promotion VALUES(1, '2015-2016', 1);
INSERT INTO promotion VALUES(1, '2014-2015', 5);
INSERT INTO promotion VALUES(2, '2015-2016', 5);

-- Fin Insertion de promotions

-- -- --


-- Insertion de listes UE/Promotion

INSERT INTO ue_promotion VALUES(1, '2015-2016', 5, 3.0, 1);
INSERT INTO ue_promotion VALUES(1, '2015-2016', 5, 3.0, 4);
INSERT INTO ue_promotion VALUES(1, '2015-2016', 8, 3.0, 4);
INSERT INTO ue_promotion VALUES(2, '2015-2016', 6, 3.0, 2);
INSERT INTO ue_promotion VALUES(2, '2015-2016', 7, 3.0, 3);

-- Fin Insertion de listes UE/Promotion

-- -- --


-- Insertion de flags

INSERT INTO flag (LABEL) VALUES('SIGNING');
INSERT INTO flag (LABEL)  VALUES('INTERVIEW');
INSERT INTO flag (LABEL)  VALUES('VALIDATING');
INSERT INTO flag (LABEL)  VALUES('DONE');

-- Fin Insertion de flags

-- -- --


-- Insertion d'interventions

INSERT INTO intervention (QUOTE,DATE_INTERVENTION,INTERVENANT_ID,FORMATION_ID,YEAR) VALUES('Intervention1', '10-12-2015', 6, 1, '2015-2016');
INSERT INTO intervention (QUOTE,DATE_INTERVENTION,INTERVENANT_ID,FORMATION_ID,YEAR) VALUES('Intervention2', '17-12-2015', 6, 1, '2015-2016');
INSERT INTO intervention (QUOTE,DATE_INTERVENTION,INTERVENANT_ID,FORMATION_ID,YEAR) VALUES('Intervention3', '17-12-2015', 5, 2, '2015-2016');
INSERT INTO intervention (QUOTE,DATE_INTERVENTION,INTERVENANT_ID,FORMATION_ID,YEAR) VALUES('Intervention4', '10-12-2015', 6, 2, '2015-2016');

-- Fin Insertion d'interventions

-- -- --


-- Insertion d'inscription dans une promo

INSERT INTO is_register_in VALUES(2, 1, '2015-2016');
INSERT INTO is_register_in VALUES(3, 1, '2015-2016');
INSERT INTO is_register_in VALUES(4, 1, '2015-2016');

-- Fin Insertion d'inscription dans une promo

-- -- --


-- Insertion de notes d'évaluations

INSERT INTO do_eval VALUES(5.0, 3, 1);
INSERT INTO do_eval VALUES(2.0, 3, 2);
INSERT INTO do_eval VALUES(10.0, 3, 3);
INSERT INTO do_eval VALUES(12.0, 3, 4);
INSERT INTO do_eval VALUES(19.9, 2, 1);
INSERT INTO do_eval VALUES(15, 4, 1);
INSERT INTO do_eval VALUES(16, 3, 6);
INSERT INTO do_eval VALUES(8, 3, 8);

-- Fin Insertion de notes d'évaluations

-- -- --


-- Insertion d'offres de stage

INSERT INTO internship (TITLE,MISSIONS,DESCRIPTION,TECHNOLOGY,DURATION,BEGIN_DATE,SIRET,FORMATION_ID,YEAR) VALUES('Stage 1', 'Missions stage 1', 'Description stage 1','JAVA', 20 ,'05-10-2015', '73282932000074',1,'2015-2016');
INSERT INTO internship (TITLE,MISSIONS,DESCRIPTION,TECHNOLOGY,DURATION,BEGIN_DATE,SIRET,FORMATION_ID,YEAR) VALUES('Stage 2', 'Missions stage 2', 'Description stage 2','PHP', 30 ,'07-04-2015', '73282932000075',1,'2015-2016');
INSERT INTO internship (TITLE,MISSIONS,DESCRIPTION,TECHNOLOGY,DURATION,BEGIN_DATE,SIRET,FORMATION_ID,YEAR) VALUES('Stage 3', 'Missions stage 3', 'Description stage 3','SQL', 15 ,'03-06-2015', '73282932000076',2,'2015-2016');

-- Fin Insertion d'offres de stage

-- -- --


-- Insertion de manager de stage

INSERT INTO manage_internship VALUES(6, 1);
INSERT INTO manage_internship VALUES(5, 2);
INSERT INTO manage_internship VALUES(1, 3);

-- Fin Insertion de manager de stage

-- -- --


-- Insertion d'inscription à une offre de stage
-- Devenu un log type "Inscrit à cette offre"

-- INSERT INTO register_to_internship VALUES(1, 2);
-- INSERT INTO register_to_internship VALUES(2, 2);
-- INSERT INTO register_to_internship VALUES(3, 3);
-- INSERT INTO register_to_internship VALUES(2, 3);
-- INSERT INTO register_to_internship VALUES(3, 4);

-- Fin Insertion d'inscription à une offre de stage

-- -- --


-- Insertion de logs de stage

INSERT INTO internship_log (DATE_LOG,QUOTE,FLAG_ID,MEMBER_ID,INTERNSHIP_ID) VALUES(TIMESTAMP '2015-12-19 10:23:54', 'Après passage d un responsable de la boite', 1, 2, 1);
INSERT INTO internship_log (DATE_LOG,QUOTE,FLAG_ID,MEMBER_ID,INTERNSHIP_ID) VALUES(TIMESTAMP '2015-12-22 15:10:54', 'Après passage d un responsable de la boite', 1, 3, 2);
INSERT INTO internship_log (DATE_LOG,QUOTE,FLAG_ID,MEMBER_ID,INTERNSHIP_ID) VALUES(TIMESTAMP '2016-01-05 10:23:54', 'Offre trouvée sur le site', 1, 4, 3);
INSERT INTO internship_log (DATE_LOG,QUOTE,FLAG_ID,MEMBER_ID,INTERNSHIP_ID) VALUES(TIMESTAMP '2015-12-19 10:23:54', 'Après passage d un responsable de la boite', 2, 2, 1);
INSERT INTO internship_log (DATE_LOG,QUOTE,FLAG_ID,MEMBER_ID,INTERNSHIP_ID) VALUES(TIMESTAMP '2015-12-25 10:23:54', 'Conventions en cours de validation...', 3, 2, 1);
INSERT INTO internship_log (DATE_LOG,QUOTE,FLAG_ID,MEMBER_ID,INTERNSHIP_ID) VALUES(TIMESTAMP '2016-01-05 10:23:54', 'Conventions validées, à signer.', 4, 2, 1);

-- Fin Insertion de logs de stage

-- -- --
