# Gestion des notes : API

| Ressource | **GET** | **PUT** | **POST** | **DELETE** |
| :-------: | :-----: | :-----: | :------: | :--------: |
| `/api/{version}/promotions` | Retourne la liste des promotions | Remplace toute la collection de promotions par une autre | Créer une nouvelle promotion | Supprime toutes les promotions |
| `/api/{version}/promotions/<promotion_id>` | Retourne les informations de la promotion | Remplace les informations de cette promotion ou la créer si elle n'existe pas | X | Supprime la promotion à cette adresse |
| `/api/{version}/promotions/<promotion_id>/etudiants` | Retourne la liste des étudiants de la promotion | Remplace toute la collection d'étudiants par une autre | Ajoute un étudiant à cette promotion | Retire tous les étudiants de cette promotion |
| `/api/{version}/promotions/<promotion_id>/etudiants/<etudiant_id>` | Retourne les informations de l'étudiant | Remplace les informations de cet étudiant ou l'ajouter si il n'existe pas | X | Retire l'étudiant à cette adresse de la promotion |
| `/api/{version}/promotions/<promotion_id>/ues` | Retourne la liste des UE de la promotion | Remplace toute la collection d'UE par une autre | Ajouter une UE dans la promotion | Retire toutes les UE de la promotion |
| `/api/{version}/promotions/<promotion_id>/ues/<ue_id>` | Retourne les informations de l'UE | Remplace les informations de cette UE ou l'ajouter si elle n'existe pas | X | Retire l'UE à cette adresse de la promotion |
| `/api/{version}/promotions/<promotion_id>/ues/<ue_id>/matiere` | Retourne la liste des matières de l'UE | Remplace toute la collection des matières par une autre | Ajouter une matière dans l'UE | Retire toutes les matières de l'UE |
| `/api/{version}/promotions/<promotion_id>/ues/<ue_id>/matiere/<matiere_id>` | Retourne les informations de la matière | Remplace les informations de cette matière ou l'ajouter si elle n'existe pas | X | Retire la matière à cette adresse de l'UE |
| `/api/{version}/promotions/<promotion_id>/ues/<ue_id>/matiere/<matiere_id>/evaluations` | Retourne la liste des évaluations de la matière | Remplace toute la collection des evaluations par une autre | Ajouter une évaluation dans la matière | Retire toutes les évaluation de la matières |
| `/api/{version}/promotions/<promotion_id>/ues/<ue_id>/matiere/<matiere_id>/evaluations/<evaluation_id>` | Retourne les informations de l'évaluation | Remplace les informations de cette évaluation ou l'ajouter si elle n'existe pas | X | Retire l'évaluation à cette adresse de la matière |
| `/api/{version}/promotions/<promotion_id>/ues/<ue_id>/matiere/<matiere_id>/evaluations/<evaluation_id>/notes` | Retourne la liste des notes de l'évaluation | Remplace toute la collection des notes de l'évaluation par une autre | Ajouter une nouvelle liste de notes | Supprime toutes les notes |
| `/api/{version}/promotions/<promotion_id>/ues/<ue_id>/matiere/<matiere_id>/evaluations/<evaluation_id>/notes/<etudiant_id>` | Retourne les informations de la note pour un étudiant | Remplace les informations de cette note ou l'ajoute si elle n'existe pas | X | Supprime la note de l'étudiant à cette adresse pour cette évaluation |

Remarque : chaque URL avec `formation_id` a pour paramètre **annee** pour retrouver la promotion

Exemple : `/api/{version}/formation/12?annee=2015`
