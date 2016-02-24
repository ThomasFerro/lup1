-- Insertions test

-- Insertion de rôles

INSERT INTO role VALUES('etudiant');
-- INSERT INTO role VALUES('responsable_promo');
INSERT INTO role VALUES('responsable_formation');
INSERT INTO role VALUES('responsable_stage');
INSERT INTO role VALUES('responsable_cours');
INSERT INTO role VALUES('etudiant_invalide');

-- Fin Insertion de rôles

-- -- --

-- Insertion de credentials

INSERT INTO credential VALUES('hauspiem', 'hauspiem');
INSERT INTO credential VALUES('ferrot', ' ferrot');
INSERT INTO credential VALUES('catteze', 'catteze');
INSERT INTO credential VALUES('fevrer', 'fevrer');
INSERT INTO credential VALUES('mathieup', 'mathieup');
INSERT INTO credential VALUES('fannySmile', 'fannySmile');

-- Fin Insertion de credentials

-- -- --

-- Insertion d'entreprises

INSERT INTO organization VALUES('SIRET-SMILE', 'Smile', 'adresse Smile', 'phoneSmile', 'mail@smile.fr');
INSERT INTO organization VALUES('SIRET-OVH', 'OVH', 'adresse OVH', 'phoneOVH', 'mail@ovh.fr');
INSERT INTO organization VALUES('SIRET-OPQUAST', 'Opquast', 'adresse Opquast', 'phoneOpquast', 'mail@opquast.fr');


-- Fin Insertion d'entreprises

-- -- --

-- Insertion de membres

INSERT INTO member VALUES(1, 'Michael', 'Hauspie', 'responsable_formation', 'hauspiem@univ-lille1.fr', '12/05/1985', 'hauspiem', null);
INSERT INTO member VALUES(2, 'Thomas', 'Ferro', 'etudiant', 'thomas.ferro@univ-lille1.fr', '19/10/1995', 'ferrot', null);
INSERT INTO member VALUES(3, 'Édouard', 'Cattez', 'etudiant', 'edouard.cattez@univ-lille1.fr', '12/10/1994', 'catteze', null);
INSERT INTO member VALUES(4, 'Rémy', 'Fevre', 'etudiant', 'remy.fevre@univ-lille1.fr', '05/03/1995', 'fevrer', null);
INSERT INTO member VALUES(5, 'Phillipe', 'Mathieu', 'responsable_cours', 'phillipe.mathieu@univ-lille1.fr', '12/11/1967', 'mathieup', null);
INSERT INTO member VALUES(6, 'Fanny', 'Oui', 'responsable_cours', 'fanny.oui@smile.fr', '12/03/1989', 'fannySmile', 'SIRET-SMILE');

-- Fin Insertion de membres

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

INSERT INTO ue VALUES (1, 'Informatique');
INSERT INTO ue VALUES (2, 'Professionnalisation');
INSERT INTO ue VALUES (3, 'Stage/Projet final');
INSERT INTO ue VALUES (4, 'Général');

-- Fin Insertion d'UE

-- -- --

-- Insertion de subject

INSERT INTO subject VALUES(1, 'Génie Logiciel', 2.0, 1);
INSERT INTO subject VALUES(2, 'Base de Données', 2.0, 1);
INSERT INTO subject VALUES(9, 'Système', 3.0, 1);
INSERT INTO subject VALUES(3, 'Administration BDD', 2.0, 2);
INSERT INTO subject VALUES(4, 'Réseau', 1.0, 2);
INSERT INTO subject VALUES(5, 'Projet final', 2.0, 3);
INSERT INTO subject VALUES(6, 'Stage', 1.0, 3);
INSERT INTO subject VALUES(7, 'Anglais', 2.0, 4);
INSERT INTO subject VALUES(8, 'TE', 1.0, 4);

-- Fin Insertion d'subject

-- -- --

-- Insertion d'évaluations

INSERT INTO evaluation VALUES(1, 'DS1', 3.0, 1, 9);
INSERT INTO evaluation VALUES(2, 'DS2', 2.0, 1, '9');
INSERT INTO evaluation VALUES(3, 'DS1', 2.0, 5, 2);
INSERT INTO evaluation VALUES(4, 'CV', 1.0, 6, 8);
INSERT INTO evaluation VALUES(5, 'Projet final', 5.0, 1, 5);
INSERT INTO evaluation VALUES(6, 'DS1', 2.0, 6, 7);
INSERT INTO evaluation VALUES(7, 'Stage', 5.0, 1, 6);

-- Fin Insertion d'évaluations

-- -- --


-- Insertion de formations

INSERT INTO formation VALUES(1, 'DA2I');
INSERT INTO formation VALUES(2, 'LPRéseau');

-- Fin Insertion de formations

-- -- --


-- Insertion de promotions

INSERT INTO promotion VALUES(1, 2015, 1, 1);
INSERT INTO promotion VALUES(2, 2014, 1, 5);
INSERT INTO promotion VALUES(3, 2015, 2, 5);

-- Fin Insertion de promotions

-- -- --


-- Insertion de listes UE/Promotion

INSERT INTO ue_promotion VALUES(1, 5, 3.0, 1);
INSERT INTO ue_promotion VALUES(2, 6, 3.0, 2);
INSERT INTO ue_promotion VALUES(3, 5, 3.0,  3);

-- Fin Insertion de listes UE/Promotion

-- -- --


-- Insertion de flags

INSERT INTO flag VALUES(1, 'CONTACT');
INSERT INTO flag VALUES(2, 'APPOINTMENT');
INSERT INTO flag VALUES(3, 'INTERVIEW');
INSERT INTO flag VALUES(4, 'CALLBACK');
INSERT INTO flag VALUES(5, 'MAKING');
INSERT INTO flag VALUES(6, 'CHECKING');
INSERT INTO flag VALUES(7, 'VALIDATING');
INSERT INTO flag VALUES(8, 'SIGNING');
INSERT INTO flag VALUES(9, 'DONE');

-- Fin Insertion de flags

-- -- --


-- Insertion d'interventions

INSERT INTO intervention VALUES(1, 'Intervention1', '10-12-2015', 6, 1);
INSERT INTO intervention VALUES(2, 'Intervention2', '17-12-2015', 6, 1);
INSERT INTO intervention VALUES(3, 'Intervention3', '17-12-2015', 5, 3);
INSERT INTO intervention VALUES(4, 'Intervention4', '10-12-2015', 6, 3);

-- Fin Insertion d'interventions

-- -- --


-- Insertion d'inscription dans une promo

INSERT INTO is_register_in VALUES(2, 1);
INSERT INTO is_register_in VALUES(3, 1);
INSERT INTO is_register_in VALUES(4, 1);

-- Fin Insertion d'inscription dans une promo

-- -- --


-- Insertion de notes d'évaluations

INSERT INTO do_eval VALUES(5.0, 3, 1);
INSERT INTO do_eval VALUES(2.0, 3, 2);
INSERT INTO do_eval VALUES(10.0, 3, 3);
INSERT INTO do_eval VALUES(19.9, 2, 1);
INSERT INTO do_eval VALUES(15, 4, 1);

-- Fin Insertion de notes d'évaluations

-- -- --


-- Insertion d'offres de stage

INSERT INTO internship VALUES(1, 'Stage 1', 'Missions stage 1', 'Description stage 1', 20 ,2015, 'SIRET-SMILE');
INSERT INTO internship VALUES(2, 'Stage 2', 'Missions stage 2', 'Description stage 2', 30 ,2015, 'SIRET-OVH');
INSERT INTO internship VALUES(3, 'Stage 3', 'Missions stage 3', 'Description stage 3', 15 ,2015, 'SIRET-OPQUAST');

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

INSERT INTO internship_log VALUES(1, TIMESTAMP '2015-12-19 10:23:54', 'Après passage d un responsable de la boite', 1, 2, 1);
INSERT INTO internship_log VALUES(2, TIMESTAMP '2015-12-22 15:10:54', 'Après passage d un responsable de la boite', 1, 3, 2);
INSERT INTO internship_log VALUES(3, TIMESTAMP '2016-01-05 10:23:54', 'Offre trouvée sur le site', 1, 4, 3);

-- Fin Insertion de logs de stage

-- -- --
