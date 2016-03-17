DROP VIEW IF EXISTS mark_by_student CASCADE;
DROP VIEW IF EXISTS avg_by_subject_by_student CASCADE;
DROP VIEW IF EXISTS avg_by_ue_by_student CASCADE;
DROP VIEW IF EXISTS avg_by_semester_by_student CASCADE;
DROP VIEW IF EXISTS avg_by_subject CASCADE;
DROP VIEW IF EXISTS avg_by_ue CASCADE;
DROP VIEW IF EXISTS avg_by_semester CASCADE;
DROP VIEW IF EXISTS mark_by_student_by_subject_with_avg_min_max CASCADE;
DROP VIEW IF EXISTS student_by_promotion_with_avg CASCADE;
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
         uep.formation_id,
         uep.year
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

CREATE VIEW avg_by_subject_by_student AS
  SELECT
        student_id,
        subject_id,
        coeff_subject,
        ue_id,
        formation_id,
        year,
        (SUM(mark * coeff_eval) / SUM(coeff_eval)) AS average
  FROM
        mark_by_student
  GROUP BY
        subject_id,
        student_id,
        coeff_subject,
        ue_id,
        formation_id,
        year
  ORDER BY
        ue_id ASC,
        subject_id ASC,
        student_id ASC;

-- 2.2 Par UE :

-- Liste des moyennes des UE par etudiant :

CREATE VIEW avg_by_ue_by_student AS
  SELECT
        student_id,
        ue_id,
        coeff_ue,
        semester,
        formation_id,
        year,
        ((SELECT SUM(average*coeff_subject) FROM avg_by_subject_by_student WHERE student_id=mark.student_id AND ue_id = mark.ue_id) / (SELECT SUM(coeff_subject) FROM avg_by_subject_by_student WHERE student_id=mark.student_id AND ue_id = mark.ue_id)) AS average
  FROM
        mark_by_student AS mark
  GROUP BY
        student_id,
        ue_id,
        coeff_ue,
        semester,
        formation_id,
        year
  ORDER BY
        semester ASC,
        ue_id ASC,
        student_id ASC;

-- 2.3 Par semester :

-- Liste des moyennes des semesters par etudiant :

CREATE VIEW avg_by_semester_by_student AS
  SELECT
        student_id,
        semester,
        formation_id,
        year,
        ((SELECT SUM(average*coeff_ue) FROM avg_by_ue_by_student WHERE student_id=mark.student_id AND semester=mark.semester) / (SELECT SUM(coeff_ue) FROM avg_by_ue_by_student WHERE student_id=mark.student_id AND semester=mark.semester)) AS average
  FROM
        mark_by_student AS mark
  GROUP BY
        student_id,
        semester,
        formation_id,
        year
  ORDER BY
        semester ASC,
        student_id ASC;

-- 3. Liste des matières / UE / semester avec moyenne

-- Liste des matières avec moyenne :

CREATE VIEW avg_by_subject AS
  SELECT
        subject_id,
        AVG(average) AS average
  FROM
        avg_by_subject_by_student
  GROUP BY
        subject_id;

-- Liste des UE avec moyenne :

CREATE VIEW avg_by_ue AS
  SELECT
        ue_id,
        AVG(average) AS average
  FROM
        avg_by_ue_by_student
  GROUP BY
        ue_id;

-- Liste des semesters avec avg_byenne :

CREATE VIEW avg_by_semester AS
  SELECT
        semester,
        AVG(average) AS average
  FROM
        avg_by_semester_by_student
  GROUP BY
        semester;

-- 4. Liste des notes des étudiants par matière avec moyenne générale, min, max

CREATE VIEW mark_by_student_by_subject_with_avg_min_max AS
  SELECT
        student_id,
        subject_id,
        evaluation_id,
        mark,
        (SELECT AVG(mark) FROM mark_by_student WHERE subject_id=mark.subject_id AND evaluation_id=mark.evaluation_id) AS average,
        (SELECT MAX(mark) FROM mark_by_student WHERE subject_id=mark.subject_id AND evaluation_id=mark.evaluation_id) AS max,
        (SELECT MIN(mark) FROM mark_by_student WHERE subject_id=mark.subject_id AND evaluation_id=mark.evaluation_id) AS min
  FROM
        mark_by_student AS mark
  GROUP BY
        subject_id,
        evaluation_id,
        mark,
        student_id
  ORDER BY
        subject_id ASC,
        student_id ASC;

-- 5. Liste des étudiants par promo triée dans l'ordre décroissant de moyennes des étudiants

CREATE VIEW student_by_promotion_with_avg AS
  SELECT
        formation_id,
        year,
        student_id,
        semester,
        (SELECT average FROM avg_by_semester_by_student WHERE student_id=mark.student_id AND semester=mark.semester) AS average
  FROM
        mark_by_student AS mark
  GROUP BY
        formation_id,
        year,
        student_id,
        semester,
        average
  ORDER BY
        year ASC,
        semester ASC,
        average DESC;

-- Fin Notes --

-- -- --

-- Intervenants --

-- 6. Liste des interventions par intervenant par promotion

CREATE VIEW interventions_by_intervenant_by_promotion AS
  SELECT
          intervention_id,
          formation_id,
          year,
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
          o.fax,
          o.hr
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
