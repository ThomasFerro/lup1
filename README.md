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
        - Authentification
        - Gestion des notes
        - Gestion des stages
- Gestion de projet
- Conclusion

-----------------

# Introduction

## Présentation du projet

Lup1 est un système de gestion des licences professionnelles initialement prévu pour l'IUT A de Lille 1.

Il a été conçu dans le cadre d'un projet de fin d'étude en licence pro DA2I, promotion 2015-2016.

Ce projet a pour objectifs de permettre aux responsables de formations de licences professionnelles une gestion aisée de ces dernières. Mais aussi un accès aux notes et offres de stages centralisé pour les étudiants et enfin une gestion des évaluations et des notes des plus simples pour les responsables de cours.

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

## Tutoriel de déploiement

Nous détaillerons dans cette partie les différentes étapes du déploiement de l'application sur votre serveur. Cette installation se veut simple et rapide.

### Préparation de la base de données

La première étape consiste en la préparation des différentes tables dans la base de données.

Cette installation est prévue pour une base sour **PostgreSQL**, il est possible que les scripts ne fonctionnent pas sur votre SGBD. Si tel est le cas, nous vous conseillons de passer sous [PostgreSQL](http://www.postgresql.org/), SGBD simple d'utilisation, gratuit et puissant.

Nous vous invitons à vérifier le nom des tables dans le premier script afin de ne pas écraser vos propres tables qui portent le même nom.

#### Lancement des scripts SQL

L'installation de la BDD se fait par l'execution d'un script pour les tables, un pour les vues et enfin un dernier, optionnel et pour tester votre installation, avec des données fictives.

Vous pouvez lancer ces scripts de plusieurs façons :

- En utilisant dans un terminal classique la commande suivante dans le même dossier que les scripts *SQL* : `psql -h <adresse de votre serveur> <nom de votre base> -f 01_init.sql -U <login>` puis changer le nom du fichier par `02_init.sql` et enfin `03_init.sql` si vous voulez inscrire les données de tests dans la base.

- Depuis une invite de commande *PostgreSQL* : `\i '<chemin vers le fichier>'` pour les scripts qui vous intéresse.

### Lancement du serveur

TODO : Description du lancement du serveur (Docker ?)

--------

# Documentation utilisateur

Nous allons décrire, dans cette partie, tout ce que vous devez savoir pour utiliser notre application, que ce soit du côté étudiant ou responsable de cours / de formation.

## Authentification

L'application redirige toute personne non authentifié sur la page de login.

Cette dernière est on ne peut plus simple, composée d'un champ *login* et un autre pour le *mot de passe*. Cette authentication se fait par la base de données mais pourra utiliser, à terme, un serveur LDAP.

TODO : Screens.

Une fois authentifié dans l'application, vous aurez accès à différentes parties de cette dernière selon votre rôle.

## Responsable de formation / de cours

Un responsable de formation est, comme son nom l'indique, en charge de tous les aspects de la gestion d'une formation, pour une promotion donnée. Ce dernier a donc la possibilité d'ajouter, modifier et supprimer des membres d'une de ses promotions, gérer les bulletins ou encore administrer les offres de stages.

Un responsable de cours, quant à lui, peut simplement gérer les évaluations et notes pour les matières qui lui sont attribuées

### Gestion des notes

TODO : Ajout d'un screen de la page d'accueil de la gestion des notes pour responsable

#### Consultation des notes des étudiants

TODO : Screen

La page de consultation des notes propose aux responsables une synthèse de toutes les évaluations, rangées par matière pour les responsables de formation. La consultation d'une de ces évaluations emmène l'utilisateur sur une nouvelle page de synthèse, cette fois-ci avec les notes des étudiants concernés.

Le responsable peut alors modifier les notes comme bon lui semble, mais aussi modifier les informations de l'évaluation.

#### Ajout d'un UE / d'une matière / d'une évaluation

TODO : Screen

L'ajout d'UEs ou de matières est réservée aux responsables de formation. Il peut y créer ses UEs, y affecter des matières déjà présentes dans la base ou en ajouter des nouvelles, choisir les coefficients et y affecter un responsable.

L'ajout d'une évaluation est assez similaire. Le responsable, de cours comme de formation, peut ajouter une évaluation avec ses informations de base puis y ajouter les notes des étudiants concernés par cette dernière.

#### Ajout de notes

TODO : Screen

L'ajout de note peut donc aussi se faire après la création de l'évaluation. Le responsable peut obtenir la liste des étudiants ayant réalisés l'évaluation et simplement leur attribuer leur note.

### Gestion des stages

TODO : Ajout d'un screen de la page d'accueil de la gestion des stages pour responsable

#### Ajout d'une offre

TODO : Screen

Seul le responsable de formation a accès à cette page qui permet de soumettre une offre de stage.

L'entreprise proposant cette offre peut soit déjà être dans la base, auquel cas le responsable n'aura qu'à choisir cette dernière parmi les choix proposés, soit être nouvelle. Dans ce dernier cas, le responsable aura à remplir les informations de cette entreprise afin de l'insérer dans la base avant de poursuivre l'enregistrement de l'offre.

Après le choix de l'entreprise, le responsable n'a plus qu'à indiquer les informations relatives à l'offre et valider son insertion. Les étudiants de la promotion pourront alors voir cette dernière et y postuler.

#### Consultation des offres

TODO : Screen

Le responsable peut à tout moment consulter les offres en ligne et les administrer comme bon lui semble. Il peut en effet en modifier les informations ou les supprimer.

#### Consultation des inscriptions aux offres de stage d'un étudiant

TODO : Screen

Les logs des étudiants concernants les offres de stage peuvent être consultées simplement par le responsable de formation. Ce dernier peut donc, après avoir choisi l'étudiant en question, vérifier l'état d'avancement de sa recherche de stage grâce à la journalisation datée des entrées dans la base.

## Étudiant

Les étudiants ont assez peu de droits dans l'application, principalement car leur utilisation de l'application se concentrera sur la consultation (des notes comme des offres de stage).

### Gestion des notes

TODO : Ajout d'un screen de la page d'accueil de la gestion des notes pour étudiant

#### Consultation des notes et du bulletin

TODO : Screen

Un étudiant souhaitant accéder à ses notes sera redirigé vers une synthèse de ses notes, rangées par matières et UEs, sous la forme d'un bulletin.

Il pourra filtrer ces données afin de trouver au plus vite la note qui l'intéresse.


### Gestion des stages

TODO : Ajout d'un screen de la page d'accueil de la gestion des stages pour étudiant

#### Consultation des offres

TODO : Screen

La page de consultation des offres de stage se veut très sobre et efficace. Elle est présentée sous la forme d'un tableau d'affichage des différentes offres, toutes sous le même format.

L'étudiant peut, sur cette page, voir toutes les offres qui sont proposées à sa promotion, filtrer ces offres afin de trouver ce qui l'intéresse au plus vite et bien sûr postuler à ces dernières.

#### Consultation des inscriptions aux offres de stage

TODO : Screen

Une fois inscrit à une offre de stage, l'étudiant peut accéder à la page contenant toutes les modifications d'état concernant cette offre.

Par exemple, si un entretien est convenu entre l'entreprise et l'étudiant, ce dernier pourra l'indiquer sur le site et ainsi ajouter une ligne au journal d'activité, et ce jusqu'à la signature de la convention.

--------

# Documentation technique

Cette partie de la documentation s'attarde sur l'aspect technique de l'application.

Vous trouverez des détails sur la conception de cette dernière et en apprendrez plus sur les technologies mises en œuvre.

## Présentation de l'architecture de l'application

TODO : Image architecture + explications

## Présentation de la base de données

TODO : Screen MCD + MPD

## Technologies utilisées

TODO : Tableau des technos comme pour le QuoiDNeuf

| **Technologie** | **Raison de son utilisation** | **Dans quelle partie du projet** |
| :---: | :---: | :---: |
| J2EE | Servlets | Serveur |
| Apache Maven | Automatisation de production du projet | Serveur |
| OrmLite | Mappage de la base de données en Java | Serveur |
| Jackson | Liaison objets JAVA/JSON | Serveur -> Client |
| HTML/CSS | Standards du Web | Vues de l'application |
| Bootstrap | Mise en page aisée pour desktop et mobile avec un design efficace | Vues de l'application |
| AngularJS | Framework performant et adapté à nos besoins | Vues et liaison avec le modèle |

## Back-End

### Authentification...

TODO : Présentation principe + système token

#### ... par la base de données

TODO : Description principe

#### ... via LDAP / CAS

TODO : Description principe

### Gestion des notes

#### Présentation de l'API

L'*API* pour la gestion des notes est la plus complexe car de nombreuses informations peuvent être demandées. Voici un tableau récapitulatif de ces ressources :

##### API pour les promotions

Les adresses des ressources suivantes commencent par **/api/{version}**

| Ressource | **GET** | **PUT** | **POST** | **DELETE** |
| :-------: | :-----: | :-----: | :------: | :--------: |
| */formations* | Retourne la liste des promotions | Remplace toute la collection de promotions par une autre | Créer une nouvelle promotion | Supprime toutes les promotions |
| */formations/{formation_id}* | Retourne la liste des promotions de cette formation | Remplace les informations de cette formation ou la créer si elle n'existe pas | X | Supprime la formation à cette adresse |
| */formations/{formation_id}/{annee}* | Retourne les informations de la promotion | Remplace les informations de la promotion ou l'ajouter si il n'existe pas | X | Supprime la promotion |

##### API pour les étudiants

Les adresses des ressources suivantes commencent par **/api/{version}**

| Ressource | **GET** | **PUT** | **POST** | **DELETE** |
| :-------: | :-----: | :-----: | :------: | :--------: |
| */etudiants* | Retourne la liste des étudiants | Remplace toute la collection des étudiants par une autre | Créer un nouvel étudiant | Supprime tous les étudiants |
| */etudiants/{etudiant_id}* | Retourne les informations de l'étudiant | Remplace les informations de cet étudiant ou l'ajouter si il n'existe pas | X | Supprime l'étudiant |

Les adresses des ressources suivantes commencent par **/api/{version}/formations/{formation_id}/annees/{annee}**

| Ressource | **GET** | **PUT** | **POST** | **DELETE** |
| :-------: | :-----: | :-----: | :------: | :--------: |
| */etudiants* | Retourne la liste des étudiants de la promotion | Remplace toute la collection d'étudiants par une autre | Ajoute un étudiant à cette promotion | Retire tous les étudiants de cette promotion |
| */etudiants/{etudiant_id}* | Retourne les informations de l'étudiant | Remplace les informations de cet étudiant ou l'ajouter si il n'existe pas | X | Retire l'étudiant à cette adresse de la promotion |

##### API pour les UEs

Les adresses des ressources suivantes commencent par **/api/{version}**

| Ressource | **GET** | **PUT** | **POST** | **DELETE** |
| :-------: | :-----: | :-----: | :------: | :--------: |
| */ues* | Retourne la liste des UEs  | Remplace toute la collection d'UEs par une autre | Créer une nouvelle UE | Supprime toutes les UEs |
| */ues/{ue_id}* | Retourne les informations de l'UE | Remplace les informations de cette UE ou l'ajouter si elle n'existe pas | X | Supprime l'UE |

Les adresses des ressources suivantes commencent par **/api/{version}/formations/{formation_id}/annees/{annee}/semestres/{semestre_id}**

| Ressource | **GET** | **PUT** | **POST** | **DELETE** |
| :-------: | :-----: | :-----: | :------: | :--------: |
| */ues* | Retourne la liste des UE de la promotion | Remplace toute la collection d'UE par une autre | Ajouter une UE dans la promotion | Retire toutes les UE de la promotion |
| */ues/{ue_id}* | Retourne les informations de l'UE | Remplace les informations de cette UE ou l'ajouter si elle n'existe pas | X | Retire l'UE à cette adresse de la promotion |

##### API pour les matières

Les adresses des ressources suivantes commencent par **/api/{version}**

| Ressource | **GET** | **PUT** | **POST** | **DELETE** |
| :-------: | :-----: | :-----: | :------: | :--------: |
| */matieres* | Retourne la liste des matières | Remplace toute la collection de matières par une autre | Créer une nouvelle matière | Supprime toutes les matières |
| */matieres/{matiere_id}* | Retourne les informations de la matière | Remplace les informations de cette matière ou l'ajouter si elle n'existe pas | X | Supprime la matière |

Les adresses des ressources suivantes commencent par **/api/{version}/formations/{formation_id}/annees/{annee}/semestres/{semestre_id}/ues/{ue_id}**

| Ressource | **GET** | **PUT** | **POST** | **DELETE** |
| :-------: | :-----: | :-----: | :------: | :--------: |
| */matiere* | Retourne la liste des matières de l'UE | Remplace toute la collection des matières par une autre | Ajouter une matière dans l'UE | Retire toutes les matières de l'UE |
| */matiere/{matiere_id}* | Retourne les informations de la matière | Remplace les informations de cette matière ou l'ajouter si elle n'existe pas | X | Retire la matière à cette adresse de l'UE |

##### API pour les évaluations

Les adresses des ressources suivantes commencent par **/api/{version}/formations/{formation_id}/{annee}/semestres/{semestre_id}/ues/{ue_id}/matiere/{matiere_id}**

| Ressource | **GET** | **PUT** | **POST** | **DELETE** |
| :-------: | :-----: | :-----: | :------: | :--------: |
| */evaluations* | Retourne la liste des évaluations de la matière | Remplace toute la collection des evaluations par une autre | Ajouter une évaluation dans la matière | Retire toutes les évaluation de la matières |
| */evaluations/{evaluation_id}* | Retourne les informations de l'évaluation | Remplace les informations de cette évaluation ou l'ajouter si elle n'existe pas | X | Retire l'évaluation à cette adresse de la matière |

##### API pour les notes

Les adresses des ressources suivantes commencent par **/api/{version}/formations/{formation_id}/annees/{annee}/semestres/{semestre_id}/ues/{ue_id}/matiere/{matiere_id}/evaluations/{evaluation_id}**

| Ressource | **GET** | **PUT** | **POST** | **DELETE** |
| :-------: | :-----: | :-----: | :------: | :--------: |
| */notes* | Retourne la liste des notes de l'évaluation | Remplace toute la collection des notes de l'évaluation par une autre | Ajouter une nouvelle liste de notes | Supprime toutes les notes |
| */notes/{etudiant_id}* | Retourne les informations de la note pour un étudiant | Remplace les informations de cette note ou l'ajoute si elle n'existe pas | X | Supprime la note de l'étudiant à cette adresse pour cette évaluation |

### Gestion des stages

#### Présentation de l'API

Pour la partie *Gestion des stages*, nous avons eu besoin de ressources pour les offres disponibles mais aussi pour les *logs* et pour les entreprises. Voici donc un résumé de notre *API* :

##### API pour les stages

Les adresses des ressources suivantes commencent par **/api/{version}/formations/{formation_id}/annees/{annee}**

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

Premièrement, nous allons utiliser le module **http** d'*Angular* afin de consommer le service */api/authentication* en **POST**. Cette requête côté serveur est décrite dans la partie *Back-End* de cette documentation technique.

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
