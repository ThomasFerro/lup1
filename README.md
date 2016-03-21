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
        - Démarrage du serveur
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
        - JSON Web Token (JWT)
            - Exemple d'un JWT
        - Les rôles
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

En premier lieu, assurez-vous que les commande `git` et `mvn` soient installées ainsi qu'une version récente de JAVA (7 minimum, 8 conseillée).

Récupérez ensuite le projet sur votre serveur à l'emplacement de votre choix via la commande suivante :

```
git clone https://github.com/ThomasFerro/lup1.git
```

### Préparation de la base de données

La première étape consiste en la préparation des différentes tables dans la base de données.

Cette installation est prévue pour une base sour **PostgreSQL**, il est possible que les scripts ne fonctionnent pas sur votre SGBD. Si tel est le cas, nous vous conseillons de passer sous [PostgreSQL](http://www.postgresql.org/), SGBD simple d'utilisation, gratuit et puissant.

Nous vous invitons à vérifier le nom des tables dans le premier script afin de ne pas écraser vos propres tables susceptibles de porter le même nom.

#### Lancement des scripts SQL

L'installation de la BDD se fait par l'éxecution de différents scripts dont le premier est prévu pour les tables, le second pour les vues et enfin le dernier, optionnel, pour tester votre installation, avec des données fictives.

Vous pouvez lancer ces scripts de plusieurs façons :

- En utilisant dans un terminal classique la commande suivante dans le même dossier que les scripts *SQL* : `psql -h <adresse de votre serveur> <nom de votre base> -f 01_init.sql -U <login>` puis changer le nom du fichier par `02_init.sql` et enfin `03_init.sql`.

- Depuis une invite de commande *PostgreSQL* : `\i '<chemin vers le fichier>'` pour les scripts qui vous intéresse.

### Démarrage du serveur

Afin de faciliter au mieux l'installation, nous avons fait le choix d'utiliser Maven. Son rôle est tout d'abord de gérer les dépendances des différentes librairies utilisée dans l'application. Dans un second temps, il permet de déployer toute l'application dans un serveur embarqué Jetty.

Assurez-vous toutefois que le proxy soit correctement défini dans le fichier ~/.m2/settings. Dans le cas contraire, les dépendances ne pourront être téléchargées.

```
<settings>
  .
  .
  <proxies>
   <proxy>
      <id>iut-proxy-http</id>
      <active>true</active>
      <protocol>http</protocol>
      <host>cache.univ-lille1.fr</host>
      <port>3128</port>
    </proxy>
    <proxy>
      <id>iut-proxy-https</id>
      <active>true</active>
      <protocol>https</protocol>
      <host>cache.univ-lille1.fr</host>
      <port>3128</port>
    </proxy>
  </proxies>
  .
  .
</settings>
```

Vous pouvez désormais déployer l'application via l'unique commande :

```
mvn jetty:run
```

Si tout se déroule comme prévu, vous devriez avoir accés en local à l'application à l'url `http://localhost:8080/`.

Notez que le port peut être modifié directement dans le fichier `pom.xml` :

```
<build>
  .
  .
  <plugins>
    <plugin>
      <groupId>org.eclipse.jetty</groupId>
	    <artifactId>jetty-maven-plugin</artifactId>
		<version>${jetty.version}</version>
		<configuration>
		  <httpConnector>
		    <port>8080</port>
		  </httpConnector>
		  <scanIntervalSeconds>10</scanIntervalSeconds>
		</configuration>
    </plugin>
  </plugins>
  .
  .
</build>
```

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
| J2EE | Webservices en REST | Serveur |
| JWT | Authentification et sécurité de l' application | Client <-> Serveur |
| Apache Maven | Automatisation de production du projet | Serveur |
| OrmLite | Mappage de la base de données en Java | Serveur |
| Jackson | Liaison objets JAVA/JSON | Serveur <-> Client |
| HTML/CSS | Standards du Web | Vues de l'application |
| Bootstrap | Mise en page aisée pour desktop et mobile avec un design efficace | Vues de l'application |
| AngularJS | Framework performant et adapté à nos besoins | Vues et liaison avec le modèle |

## Back-End

### Authentification...

La sécurité de l'application repose sur deux éléments :
- le JSON Web Token (JWT)
- les rôles

#### JSON Web Token (JWT)

Le JWT est un système très puissant car il assure l'identité du client vis à vis du serveur. Ce token est (re)généré et **signé** côté serveur et est envoyé dans l'en-tête HTTP du client. Le client s'assure ensuite d'envoyer le token dans l'en-tête de chaque requête HTTP qu'il est susceptible de faire. Ainsi, le token sera vérifié par le serveur avant toute opération.

La génération du token est réalisée à l'authentification première (identifiant + mot de passe) qui vérifie l'existence en base de l'utilisateur désirant se connecter. Le token est regénéré lorsque sa date d'expiration est dépassée.

La signature du token se fait par l'intermédiaire du clé sérialisée. Ainsi, le rechargement du serveur, s'il a lieu d'être, est totalement transparent pour le client.

Le token est séparé en 3 parties, chacune délimitée par un '.' :
- le header qui représente le type du token et l'algorithme utilisé pour le générer
- le payload qui représente les données que l'on souhaite stocker chez le client
- la vérification de la signature

##### Exemple d'un JWT

**Encodé**

eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJjYXR0ZXplIiwiaXNzIjoibHVwMSIsImV4cCI6MTQ1ODQ4NjgxMiwicm9sZXMiOlsiZXR1ZGlhbnQiXX0.6_U-nUsP59qnFFqmVSeyJHL4S7Q45-T1oy4tSEQZAVb6sqfnwQ5nECjYUWA01oohQTACxP-XMvQTU8DRBhQRxw

**Décodé**

Header: { "typ": "JWT", "alg": "HS512" }
Payload: { "sub": "catteze", "iss": "lup1", "exp": 1458486812, "roles": ["etudiant"] }

Plus d'informations sur le JWT sont disponibles à l'adresse [http://jwt.io/](http://jwt.io/)

#### Les rôles

Les rôles quant à eux sont gérés côté serveur. Ils sont définis en base pour chaque utilisateur. Un utilisateur peut avoir 0 à n rôles (exemples: responsable_formation, responsable_cours, etudiant, admin...).

L'accès à certaines ressources du serveur dépend de chacun de ses rôles. Pour exemple, un étudiant peut consulter son bulletin mais pas celui des autres, tandis que le responsable de formation peut consulter le bulletin de tous les étudiants de sa formation.

#### ... par la base de données

L'authentification première (identifiant + mot de passe) se fait par l'intermédiaire d'un webservice dédié. Via le système de DAO mis en place, ce service effectue les vérifications d'existence dans la table `credential` et génère le JWT en conséquence.

#### ... via LDAP / CAS

Actuellement, l'application n'est pas prévue pour fonctionner avec un LDAP. En revanche et à terme, le webservice d'authentification pourra disposer d'un pool de connexion relié au LDAP de l'IUT. Ainsi, tout comme avec la base de données, le service établiera les vérifications souhaitées et générera le JWT en conséquence.

### Gestion des notes

#### Présentation de l'API

L'*API* pour la gestion des notes est la plus complexe car de nombreuses informations peuvent être demandées. Voici un tableau récapitulatif de ces ressources :

##### API pour les promotions

Les adresses des ressources suivantes commencent par **/api/{version}**

| Ressource | **GET** | **PUT** | **POST** | **DELETE** |
| :-------: | :-----: | :-----: | :------: | :--------: |
| */formations* | Retourne la liste des promotions | Remplace toute la collection de promotions par une autre | Créer une nouvelle promotion | Supprime toutes les promotions |
| */formations/{formation_id}* | Retourne la liste des promotions de cette formation | Remplace les informations de cette formation ou la créer si elle n'existe pas | X | Supprime la formation à cette adresse |
| */formations/{formation_id}/annees/{annee}* | Retourne les informations de la promotion | Remplace les informations de la promotion ou l'ajouter si il n'existe pas | X | Supprime la promotion |

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

##### API pour les bulletins

Les adresses des ressources suivantes commencent par **/api/{version}/formations/{formation_id}/annees/{annee}/semestres/{semestre_id}/**

| Ressource | **GET** | **PUT** | **POST** | **DELETE** |
| :-------: | :-----: | :-----: | :------: | :--------: |
| */* | Retourne le bulletin de chaque étudiant | X | Télécharge une archive contenant les bulletin de chaque étudiant au format pdf |
| */{etudiant_id}* | Retourne le bulletin d'un étudiant | X | Télécharge le bulletin d'un étudiant au format pdf

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

Pour le *Front-End*, nous avons principalement utilisé **Angular.js**, un framework *Javascript* permettant de faciliter grandement le développement d'application "*One Page*" et offrant de nombreuses fonctionnalités que nous développerons plus loin dans cette documentation.

### Authentification

L'authentification se déroule en plusieurs étapes, côté *Front-End*.

Premièrement, nous allons utiliser le module **http** d'*Angular* afin de consommer le service */api/authentication* en **POST**. Cette requête côté serveur est décrite dans la partie *Back-End* de cette documentation technique.

Si la requête n'a pas échouée, l'application place le token dans le *localStorage*, après l'avoir extrait du *header* renvoyé par le serveur.

Il est ensuite possible, grâce à l'**authentication-factory**, de vérifier si l'utilisateur est connecté, retourner ses rôles ou encore de le déconnecter.

**access-factory** est le service qui vérifie le status de connexion du visiteur.Lié avec **route.js**, il permet de sécuriser les pages en fonction des roles de chacun.

Enfin, par l'utilisation du module **$rootscope** présent dans le fichier **app.js**, l'application redirige l'utilisateur selon le résultat de la requête et informe l'utilisateur de toute erreur dans le processus d'authentification.


### Gestion des notes

Pour lier, le back-end avec le front-end, l'utilisation de contrôleurs dédiés à une url ou à une directive précise a été faite.
Le contrôleur a pour rôle d'appeler l'api correspondante à l'aide du module **http** et de sa méthode **Get** pour récupérer les données sous forme de json. Par la suite, nous manipulons ce json à l'aide d'attribut propre à angularjs tels que **ng-repeat** qui permet de boucler sur des tableaux json ou encore **{{expressoin}}** permettant l'affichage des données.

### Gestion des stages

TODO : Description utilisation Angular pour liaison modèle

--------

# Gestion de projet

Notre gestion de projet s'est faite en suivant des principes de méthodes Agile, notamment Scrum.

En effet, nous avons décomposé le problème dès le début du projet. Nous avons ensuite constitué notre *Backlog* afin d'avoir une idée précise de la quantité de travail à accomplir. Puis nous avons intégré ce *Backlog* à notre projet **Trello**, chaque tâche étant représentée par une carte.  

Cette décomposition nous a aussi donné l'occasion de répartir efficacement les tâches entre le *Back-End*, le *Front-End* et la *BDD* et d'y assigner les différents membres du groupe de manière optimale. En effet, nous avons choisi de tirer partie des qualités de chacun pour réaliser ce projet dans les meilleures conditions possibles.  

--------

# Conclusion
