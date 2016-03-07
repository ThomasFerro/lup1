DROP VIEW IF EXISTS mark_by_student CASCADE;
DROP VIEW IF EXISTS moy_by_subject_by_student CASCADE;
DROP VIEW IF EXISTS moy_by_ue_by_student CASCADE;
DROP VIEW IF EXISTS moy_by_semester_by_student CASCADE;
DROP VIEW IF EXISTS moy_by_subject CASCADE;
DROP VIEW IF EXISTS moy_by_ue CASCADE;
DROP VIEW IF EXISTS moy_by_semester CASCADE;
DROP VIEW IF EXISTS mark_by_student_by_subject_with_moy_min_max CASCADE;
DROP VIEW IF EXISTS student_by_promotion_with_moy CASCADE;
DROP VIEW IF EXISTS interventions_by_intervenant_by_promotion CASCADE;
DROP VIEW IF EXISTS done_internship CASCADE;

-- Creation des vues

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
         uep.coeff AS coeff_ue,
         uep.semester,
         uep.promotion_id
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
         semester ASC,
         s.ue_id ASC,
         subject_id ASC,
         evaluation_id ASC;

-- 2. Calculer la moyenne des notes d'un étudiant

-- 2.1 Par Matière :

-- Liste des moyennes des matieres par etudiant :

CREATE VIEW moy_by_subject_by_student AS
  SELECT
        (SUM(mark * coeff_eval) / SUM(coeff_eval)) AS moyenne,
        student_id,
        subject_id,
        coeff_subject,
        ue_id
  FROM
        mark_by_student
  GROUP BY
        subject_id,
        student_id,
        coeff_subject,
        ue_id
  ORDER BY
        ue_id ASC,
        subject_id ASC,
        student_id ASC;

-- 2.2 Par UE :

-- Liste des moyennes des UE par etudiant :

CREATE VIEW moy_by_ue_by_student AS
  SELECT
        ((SELECT SUM(moyenne*coeff_subject) FROM moy_by_subject_by_student WHERE student_id=mark.student_id AND ue_id = mark.ue_id) / (SELECT SUM(coeff_subject) FROM moy_by_subject_by_student WHERE student_id=mark.student_id AND ue_id = mark.ue_id)) AS moyenne,
        student_id,
        ue_id,
        coeff_ue,
        semester
  FROM
        mark_by_student AS mark
  GROUP BY
        student_id,
        ue_id,
        coeff_ue,
        semester
  ORDER BY
        semester ASC,
        ue_id ASC,
        student_id ASC;

-- 2.3 Par semester :

-- Liste des moyennes des semesters par etudiant :

CREATE VIEW moy_by_semester_by_student AS
  SELECT
        ((SELECT SUM(moyenne*coeff_ue) FROM moy_by_ue_by_student WHERE student_id=mark.student_id AND semester=mark.semester) / (SELECT SUM(coeff_ue) FROM moy_by_ue_by_student WHERE student_id=mark.student_id AND semester=mark.semester)) AS moyenne,
        student_id,
        semester
  FROM
        mark_by_student AS mark
  GROUP BY
        student_id,
        semester
  ORDER BY
        semester ASC,
        student_id ASC;

-- 3. Liste des matières / UE / semester avec moyenne

-- Liste des matières avec moyenne :

CREATE VIEW moy_by_subject AS
  SELECT
        AVG(moyenne) AS moyenne,
        subject_id
  FROM
        moy_by_subject_by_student
  GROUP BY
        subject_id;

-- Liste des UE avec moyenne :

CREATE VIEW moy_by_ue AS
  SELECT
        AVG(moyenne) AS moyenne,
        ue_id
  FROM
        moy_by_ue_by_student
  GROUP BY
        ue_id;

-- Liste des semesters avec moyenne :

CREATE VIEW moy_by_semester AS
  SELECT
        AVG(moyenne) AS moyenne,
        semester
  FROM
        moy_by_semester_by_student
  GROUP BY
        semester;

-- 4. Liste des notes des étudiants par matière avec moyenne générale, min, max

CREATE VIEW mark_by_student_by_subject_with_moy_min_max AS
  SELECT
        student_id,
        subject_id,
        mark,
        (SELECT AVG(mark) FROM mark_by_student WHERE subject_id=mark.subject_id) AS moyenne,
        (SELECT MAX(mark) FROM mark_by_student WHERE subject_id=mark.subject_id)AS max,
        (SELECT MIN(mark) FROM mark_by_student WHERE subject_id=mark.subject_id) AS min
  FROM
        mark_by_student AS mark
  GROUP BY
        subject_id,
        mark,
        student_id
  ORDER BY
        subject_id ASC,
        student_id ASC;

-- 5. Liste des étudiants par promo triée dans l'ordre décroissant de moyennes des étudiants

CREATE VIEW student_by_promotion_with_moy AS
  SELECT
        promotion_id,
        student_id,
        semester,
        (SELECT moyenne FROM moy_by_semester_by_student WHERE student_id=mark.student_id AND semester=mark.semester) AS moyenne
  FROM
        mark_by_student AS mark
  GROUP BY
        promotion_id,
        student_id,
        semester,
        moyenne
  ORDER BY
        promotion_id ASC,
        semester ASC,
        moyenne DESC;

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
          reg.student_id = m.member_id;

-- 8. Stats sur le suivi des stages (??)

-- Fin Stages --

-- Fin Création des vues

-- ----- --

-- Fonctions d'insertion --



-- Fin Fonctions d'insertion --

-- ---- --
