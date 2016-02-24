DROP VIEW IF EXISTS mark_by_student;
DROP VIEW IF EXISTS intervenions_by_intervenant_by_promotion;
DROP VIEW IF EXISTS done_internship;


-- Creation des vues

-- Fin Création des vues

-- ----- --

-- Creation des fonctions

-- Fonctions de selection --

-- Notes --

-- 1. Affiche les notes d'un étudiant

CREATE VIEW mark_by_student AS
  SELECT
         s.ue_id,
         de.student_id,
         s.subject_id,
         de.evaluation_id,
         mark,
         e.coeff AS coeff_eval,
         s.coeff AS coeff_subject,
         uep.coeff AS coeff_ue
  FROM
         subject AS s,
         evaluation AS e,
         do_eval AS de,
         ue_promotion AS uep
  WHERE
         uep.ue_id = s.ue_id
    AND
         s.subject_id = e.subject_id
    AND
         de.evaluation_id = e.evaluation_id
  ORDER BY
         s.ue_id ASC,
         subject_id ASC,
         evaluation_id ASC;

-- 2. Calculer la moyenne des notes d'un étudiant

-- 2.1 Par Matière :

-- 2.2 Par UE :

-- 2.3 Par Semetre :

-- 3. Liste des matières / UE avec moyenne

-- 4. Liste des notes des étudiants par matière avec moyenne générale, min, max

-- 5. Liste des étudiants par promo triée dans l'ordre décroissant de moyennes des étudiants

-- Fin Notes --

-- -- --

-- Intervenants --

-- 6. Liste des interventions par intervenant par promotion

CREATE VIEW interventions_by_intervenant_by_promotion AS
  SELECT
          intervention_id,
          promotion_id,
          date_intervention,
          quote
  FROM
          member

  INNER JOIN
          intervention ON member_id = intervenant_id;


-- Fin Intervenants --

-- -- --

-- Stages --

-- 7. Suivi des stages / étudiant

CREATE VIEW done_internship AS
  SELECT
          i.*,
          o.name,
          o.address,
          o.phone,
          o.email
  FROM
          internship AS i,
          internship_log AS log,
          organization AS o,
          member AS m,
          is_register_in AS reg
  WHERE
          i.siret = o.siret
    AND
          i.internship_id = log.internship_id
    AND
          log.member_id = m.member_id
    AND
          reg.student_id = m.member_id

-- 8. Stats sur le suivi des stages (??)

-- Fin Stages --

-- Fin Fonctions de selection --


-- Fonctions d'insertion --

-- Fin Fonctions d'insertion --

-- Fin création des fonctions --

-- ---- --
