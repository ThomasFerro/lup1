# Gestion des stages : API

| Ressource | **GET** | **PUT** | **POST** | **DELETE** |
| :-------: | :-----: | :-----: | :------: | :--------: |
| */api/{version}/stages* | Retourne la liste des offres de stage | Remplace la collection d'offres de stage par une autre | Créer une nouvelle offre de stage | Supprime toutes les offres de stage |
| */api/{version}/stages/{stage_id}* | Retourne les informations de cette offre de stage | Remplace les informations de cette offre ou la créer si elle n'existe pas | X | Supprime l'offre de stage |
|  */api/{version}/etudiants/{etudiant_id}/stages* | Retourne les offres de stage auxquelles est inscrit un étudiant | Remplace la liste des inscriptions aux offres de stage de l'étudiant | Inscrit l'étudiant à une offre de stage | Supprime les inscriptions aux offres de l'étudiant |
|  */api/{version}/etudiants/{etudiant_id}/stages/{stage_id}* | Retourne les informations et logs de l'étudiant pour cette offre de stage | Remplace les informations et logs de cette offre pour cet étudiant ou l'inscrit à l'offre de stage si il ne l'est pas déjà | X | Supprime l'inscription à l'offre de stage |
