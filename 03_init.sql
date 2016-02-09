-- Insertions test

-- Insertion de rôles

INSERT INTO lup1.role VALUES('etudiant');
INSERT INTO lup1.role VALUES('intervenant');
INSERT INTO lup1.role VALUES('responsable_promo');
INSERT INTO lup1.role VALUES('responsable_formation');
INSERT INTO lup1.role VALUES('responsable_stage');
INSERT INTO lup1.role VALUES('responsable_cours');
INSERT INTO lup1.role VALUES('etudiant_invalide');

-- Fin Insertion de rôles

-- -- --

-- Insertion de credentials

INSERT INTO lup1.credential VALUES('hauspiem', 'hauspiem');
INSERT INTO lup1.credential VALUES('ferrot', ' ferrot');
INSERT INTO lup1.credential VALUES('catteze', 'catteze');
INSERT INTO lup1.credential VALUES('fevrer', 'fevrer');
INSERT INTO lup1.credential VALUES('mathieup', 'mathieup');
INSERT INTO lup1.credential VALUES('fannySmile', 'fannySmile');

-- Fin Insertion de credentials

-- -- --

-- Insertion d'entreprises

INSERT INTO lup1.organization VALUES('SIRET-SMILE', 'Smile', 'adresse Smile', 'phoneSmile', 'mail@smile.fr');
INSERT INTO lup1.organization VALUES('SIRET-OVH', 'OVH', 'adresse OVH', 'phoneOVH', 'mail@ovh.fr');
INSERT INTO lup1.organization VALUES('SIRET-OPQUAST', 'Opquast', 'adresse Opquast', 'phoneOpquast', 'mail@opquast.fr');


-- Fin Insertion d'entreprises

-- -- --

-- Insertion de membres

INSERT INTO lup1.member VALUES(1, 'Michael', 'Hauspie', 'hauspiem@univ-lille1.fr', '12/05/1985', 'responsable_formation', 'hauspiem', null);
INSERT INTO lup1.member VALUES(2, 'Thomas', 'Ferro', 'thomas.ferro@univ-lille1.fr', '19/10/1995', 'etudiant', 'ferrot', null);
INSERT INTO lup1.member VALUES(3, 'Édouard', 'Cattez', 'edouard.cattez@univ-lille1.fr', '12/10/1994', 'etudiant', 'catteze', null);
INSERT INTO lup1.member VALUES(4, 'Rémy', 'Fevre', 'remy.fevre@univ-lille1.fr', '05/03/1995', 'etudiant', 'fevrer', null);
INSERT INTO lup1.member VALUES(5, 'Phillipe', 'Mathieu', 'phillipe.mathieu@univ-lille1.fr', '12/11/1967', 'responsable_cours', 'mathieup', null);
INSERT INTO lup1.member VALUES(6, 'Fanny', 'Oui', 'fanny.oui@smile.fr', '12/03/1989', 'intervenant', 'fannySmile', 'SIRET-SMILE');

-- Fin Insertion de membres

-- -- --

-- Insertion d'évaluations

-- Fin Insertion d'évaluations

-- -- --

-- Insertion de matières

-- Fin Insertion de matières

-- -- --

-- Insertion d'UE

-- Fin Insertion d'UE

-- -- --


-- Insertion de formations

-- Fin Insertion de formations

-- -- --


-- Insertion d'offres de stage

-- Fin Insertion d'offres de stage

-- -- --


-- Insertion de flags

-- Fin Insertion de flags

-- -- --


-- Insertion d'interventions

-- Fin Insertion d'interventions

-- -- --


-- Insertion de logs de stage

-- Fin Insertion de logs de stage

-- -- --


-- Insertion de promotions

-- Fin Insertion de promotions

-- -- --


-- Insertion de listes UE/Promotion

-- Fin Insertion de listes UE/Promotion

-- -- --


-- Insertion d'inscription dans une promo

-- Fin Insertion d'inscription dans une promo

-- -- --


-- Insertion de notes d'évaluations

-- Fin Insertion de notes d'évaluations

-- -- --


-- Insertion de manager de stage

-- Fin Insertion de manager de stage

-- -- --


-- Insertion d'inscription à une offre de stage

-- Fin Insertion d'inscription à une offre de stage

-- -- --
