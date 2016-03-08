# Exemple de requêtes que l'on va peux-être utiliser :

---

## 1ère partie - Profs

- ### Afficher les cours auxquels un prof participe :

`SELECT DISTINCT subject.name FROM evaluation,subject WHERE subject.subject_id = evaluation.subject_id AND teacher_id = 1 ;`

- ### Afficher les évaluations d'un cours :

`SELECT name,coeff,teacher_id,subject_id,date_eval FROM evaluation WHERE subject_id = 9 ;`

- ### Afficher les notes d'une évaluation :

`SELECT mark FROM do_eval WHERE evaluation_id = 1 ;`

## 2nd partie - Stages

- ### Afficher tous les stages :

`SELECT title,missions,description,duration,begin_date,siret FROM internship ;`

- ### Afficher le suivi de la recherche de stage d'un éléve :

`SELECT date_log,quote,flag.label,int.title FROM internship_log AS intLog,flag,internship AS int WHERE flag.flag_id=intLog.flag_id AND int.internship_id=intLog.internship_id AND member_id = 3;`

## 3ième partie - Notes

- ### Afficher la note des étudiants pour un contrôle donnée :

`SELECT mark,student_id FROM do_eval WHERE evaluation_id = 1 ;`

- ### Afficher la note d'un étudiant pour un contrôle donnée :

`SELECT mark FROM do_eval WHERE evaluation_id = 1 AND student_id = 3 ;`

- ### Afficher les informations pour le bulletin de notes d'un élève :
