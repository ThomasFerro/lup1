# Gestion des stages : API

## Stages

| Ressource | **GET** | **PUT** | **POST** | **DELETE** |
| :-------: | :-----: | :-----: | :------: | :--------: |
| */api/{version}/formation/{formation_id}/annees/{annee}/stages* | Retourne la liste des offres de stage | Remplace la collection d'offres de stage par une autre | Créer une nouvelle offre de stage | Supprime toutes les offres de stage |
| */api/{version}/formation/{formation_id}/annees/{annee}/stages/{stage_id}* | Retourne les informations de cette offre de stage | Remplace les informations de cette offre ou la créer si elle n'existe pas | X | Supprime l'offre de stage |
|  */api/{version}/formation/{formation_id}/annees/{annee}/etudiants/{etudiant_id}/stages* | Retourne les offres de stage auxquelles est inscrit un étudiant | X | Inscrit l'étudiant à une offre de stage | Supprime les inscriptions aux offres de l'étudiant |
|  */api/{version}/formation/{formation_id}/annees/{annee}/etudiants/{etudiant_id}/stages/{stage_id}* | Retourne les informations et logs de l'étudiant pour cette offre de stage | Remplace les informations et logs de cette offre pour cet étudiant ou l'inscrit à l'offre de stage si il ne l'est pas déjà | Ajoute un nouveau log à l'offre de stage pour l'étudiant | Supprime l'inscription à l'offre de stage |

## Entreprises

| Ressource | **GET** | **PUT** | **POST** | **DELETE** |
| :-------: | :-----: | :-----: | :------: | :--------: |
| */api/{version}/entreprises/* | Liste les entreprises dans la base | X | Créer une nouvelle entrée dans la table des entreprises | Supprime les entreprises |
| */api/{version}/entreprises/{entreprises_siret}* | Retourne l'entreprise avec le SIRET indiqué | Modifie ou ajoute à la base l'entreprise au SIRET indiqué | X | Supprime de la base l'entreprise avec le SIRET indiqué |
