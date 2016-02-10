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
         lup1.subject AS s,
         lup1.evaluation AS e,
         lup1.do_eval AS de,
         lup1.ue_promotion AS uep
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

CREATE VIEW intervenions_by_intervenant_by_promotion AS
  SELECT
          intervention_id,
          year,
          formation_id,
          date_intervention,
          quote
  FROM
          lup1.member

  INNER JOIN
          lup1.intervention ON member_id = intervenant_id;


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
          lup1.internship AS i,
          lup1.internship_log AS log,
          lup1.organization AS o,
          lup1.member AS m,
          lup1.is_register_in AS reg
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
