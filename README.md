# Lup1
## Système de gestion de licence pro à l'IUT A de Lille 1 - DA2I

## Fèvre - Deplanque - Leleu - Cattez - Ferro

-------------------

# Sommaire

- Introduction
    - Présentation du projet
    - Fonctionnalités
    - Tutoriel de déploiement
        - Préparation de la base de données
            - Lancement des scripts SQL
        - Lancement du serveur
- Documentation utilisateur
    - Authentification
    - Responsable de formation / de cours
        - Gestion des notes
            - Consultation des notes des étudiants
            - Ajout d'un UE / d'une matière / d'une évaluation
            - Ajout de notes
        - Gestion des stages
            - Ajout d'une offre
            - Consultation des offres
            - Consultation des inscriptions aux offres de stage d'un étudiant
    - Étudiant
        - Gestion des notes
            - Consultation des notes et du bulletin
        - Gestion des stages
            - Consultation des offres
            - Inscription à une offre
            - Consultation des inscriptions aux offres de stage
- Documentation technique
    - Présentation de l'architecture de l'application
    - Présentation de la base de données
    - Technologies utilisées
    - Authentification
        - Par la base de données
        - Via LDAP / CAS
    - Back-End
        - Gestion des notes
            - Présentation de l'API
        - Gestion des stages
            - Présentation de l'API
    - Front-End
        - Gestion des notes
        - Gestion des stages
- Gestion de projet
- Conclusion

-----------------

# Introduction

## Présentation du projet

Lup1 est un système de gestion des licences professionnelles initialement prévu pour l'IUT A de Lille 1.

Il a été conçu dans le cadre d'un projet de fin d'étude en licence pro DA2I, promotion 2015-2016.

TODO : étoffer la présentation

## Fonctionnalités

Voici une liste des fonctionnalités principales de l'application :

- [ ] Gestion des notes
    - [ ] Gestion pour les différentes matières, rangées par UE
    - [ ] Ajout et modification d'une UE / d'une matière
    - [ ] Ajout et modification d'une évaluation
    - [ ] Ajout et modification de notes
    - [ ] Consultation des notes par l'étudiant
- [ ] Gestion des stages
    - [ ] Ajout et modification d'une offre de stage par le responsable de formation
    - [ ] Inscription et changement d'état d'un étudiant à une offre de stage
    - [ ] Consultation de l'état des différentes inscriptions d'un étudiant

TODO : Ajouter des fonctionnalités

## Tutoriel de déploiement

Nous détaillerons dans cette partie les différentes étapes du déploiement de l'application sur votre serveur. Cette installation se veut simple et rapide.

### Préparation de la base de données

La première étape consiste en la préparation des différentes tables dans la base de données.

Cette installation est prévue pour une base sour **PostgreSQL**, il est possible que les scripts ne fonctionnent pas sur votre SGBD. Si tel est le cas, nous vous conseillons de passer sous [PostgreSQL](http://www.postgresql.org/), SGBD simple d'utilisation, gratuit et puissant.

Nous vous invitons à vérifier le nom des tables dans le premier script afin de ne pas écraser vos propres tables qui portent le même nom.

#### Lancement des scripts SQL

L'installation de la BDD se fait par l'execution d'un script pour les tables, un pour les vues et enfin un dernier, optionnel et pour tester votre installation, avec des données fictives.

Vous pouvez lancer ces scripts de plusieurs façon :

- En utilisant dans un terminal classique la commande suivante dans le même dossier que les scripts *SQL* : `psql -h <adresse de votre serveur> <nom de votre base> -f 01_init.sql -U <login>` puis changer le nom du fichier par `02_init.sql` et enfin `03_init.sql` si vous voulez inscrire les données de tests dans la base.

- Depuis une invite de commande *PostgreSQL* : `\i '<chemin vers le fichier>'` pour les scripts qui vous intéresse.

### Lancement du serveur

TODO : Description du lancement du serveur (Docker ?)

--------

# Documentation utilisateur

Nous allons décrire, dans cette partie, tout ce que vous devez savoir pour utiliser notre application, que ce soit du côté étudiant ou responsable de cours / de formation.

## Authentification

TODO : Description du login avec screens. Avec LDAP ? CAS ?

Une fois authentifié dans l'application, vous aurez accès à différentes parties de cette dernière selon votre rôle.

## Responsable de formation / de cours

TODO : Présentation des rôles de responsable de formation et responsable de cours

### Gestion des notes

TODO : Ajout d'un screen de la page d'accueil de la gestion des notes pour responsable

#### Consultation des notes des étudiants

TODO : Screen + présentation de l'interface et liste des fonctionnalités de la page

#### Ajout d'un UE / d'une matière / d'une évaluation

TODO : Screen + présentation de l'interface et liste des fonctionnalités de la page

#### Ajout de notes

TODO : Screen + présentation de l'interface et liste des fonctionnalités de la page

### Gestion des stages

TODO : Ajout d'un screen de la page d'accueil de la gestion des stages pour responsable

#### Ajout d'une offre

TODO : Screen + présentation de l'interface et liste des fonctionnalités de la page

#### Consultation des offres

TODO : Screen + présentation de l'interface et liste des fonctionnalités de la page

#### Consultation des inscriptions aux offres de stage d'un étudiant

TODO : Screen + présentation de l'interface et liste des fonctionnalités de la page

## Étudiant

TODO : Présentation du rôle d'étudiant

### Gestion des notes

TODO : Ajout d'un screen de la page d'accueil de la gestion des notes pour étudiant

#### Consultation des notes et du bulletin

### Gestion des stages

TODO : Ajout d'un screen de la page d'accueil de la gestion des stages pour étudiant

#### Consultation des offres

TODO : Screen + présentation de l'interface et liste des fonctionnalités de la page

#### Inscription à une offre

TODO : Screen + présentation de l'interface et liste des fonctionnalités de la page

#### Consultation des inscriptions aux offres de stage

TODO : Screen + présentation de l'interface et liste des fonctionnalités de la page

--------

# Documentation technique

Cette partie de la documentation s'attarde sur l'aspect technique de l'application.

Vous trouverez des détails sur la conception de cette dernière et en apprendrez plus sur les technologies mises en oeuvre.

## Présentation de l'architecture de l'application

TODO : Image architecture + explications

## Présentation de la base de données

TODO : Screen MCD

## Technologies utilisées

TODO : Tableau des technos comme pour le QuoiDNeuf

## Back-End

### Authentification...

TODO : Présentation principe + système token

#### ... par la base de données

TODO : Description principe

#### ... via LDAP / CAS

TODO : Description principe

### Gestion des notes

#### Présentation de l'API

TODO : Reprendre le tableau

### Gestion des stages

#### Présentation de l'API

Pour la partie *Gestion des stages*, nous avons eu besoin de resources pour les offres disponibles mais aussi pour les *logs* et pour les entreprises. Voici donc un résumé de notre API :

##### API pour les stages

Les adresses des ressources suivantes commencent par **/api/{version}/formation/{formation_id}/annees/{annee}**

| Ressource | **GET** | **PUT** | **POST** | **DELETE** |
| :-------: | :-----: | :-----: | :------: | :--------: |
| */stages* | Retourne la liste des offres de stage | Remplace la collection d'offres de stage par une autre | Créer une nouvelle offre de stage | Supprime toutes les offres de stage |
| */stages/{stage_id}* | Retourne les informations de cette offre de stage | Remplace les informations de cette offre ou la créer si elle n'existe pas | X | Supprime l'offre de stage |
|  */etudiants/{etudiant_id}/stages* | Retourne les offres de stage auxquelles est inscrit un étudiant | X | Inscrit l'étudiant à une offre de stage | Supprime les inscriptions aux offres de l'étudiant |
|  */etudiants/{etudiant_id}/stages/{stage_id}* | Retourne les informations et logs de l'étudiant pour cette offre de stage | Remplace les informations et logs de cette offre pour cet étudiant ou l'inscrit à l'offre de stage si il ne l'est pas déjà | Ajoute un nouveau log à l'offre de stage pour l'étudiant | Supprime l'inscription à l'offre de stage |

##### API pour les entreprises

Les adresses des ressources suivantes commencent par **/api/{version}**

| Ressource | **GET** | **PUT** | **POST** | **DELETE** |
| :-------: | :-----: | :-----: | :------: | :--------: |
| */entreprises/* | Liste les entreprises dans la base | X | Créer une nouvelle entrée dans la table des entreprises | X |
| */entreprises/{entreprises_siret}* | Retourne l'entreprise avec le SIRET indiqué | Modifie ou ajoute à la base l'entreprise au SIRET indiqué | X | X |

## Front-End

Pour le *Front-End*, nous avons principalement utilisé **Angular.js**, un framework *Javascript* permettant de facilité grandement le développement d'application "*One Page*" et offrant de nombreuses fonctionnalités que nous développerons plus loin de cette documentation.

### Authentification

L'authentification se déroule en plusieurs étapes, côté *Front-End*.

Premièrement, nous allons utilisé le module **http** d'*Angular* afin de consommer le service */api/authentication* en **POST**. Cette requête côté serveur est décrite dans la partie *Back-End* de cette documentation technique.

Si la requête n'a pas échouée, l'application place le token dans le *localStorage*, après l'avoir extrait du *header* renvoyé par le serveur.

Il est ensuite possible, grâce à l'**authentication-factory**, de vérifier si l'utilisateur est connecté, retourner ses rôles ou encore de le déconnecter.

Enfin, l'application redirige l'utilisateur selon le résultat de la requête et informe l'utilisateur de toute erreur dans le processus d'authentification.

### Gestion des notes

TODO : Description utilisation Angular pour liaison modèle

### Gestion des stages

TODO : Description utilisation Angular pour liaison modèle

--------

# Gestion de projet

Notre gestion de projet s'est faite en suivant des principes de méthodes Agile, notamment Scrum.

En effet, nous avons décomposé le problème dès le début du projet. Nous avons ensuite constitué notre *Backlog* afin d'avoir une idée précise de la quantité de travail à accomplir. Puis nous avons intégré ce *Backlog* à notre projet **Trello**, chaque tâche étant représentée par une carte.  

Cette décomposition nous a aussi donné l'occasion de répartir efficacement les tâches entre le *Back-End*, le *Front-End* et la *BDD* (Base de Données) et d'y assigner les différents membres du groupe.  

--------

# Conclusion
