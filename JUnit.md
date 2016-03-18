# Tester une DAO avec JUnit

Les tests unitaires de notre DAO seront effectués avec JUnit.

Après avoir réalisé une classe (dans notre exemple filé, la classe **MemberDAO**), nous la validerons en suivant la procèdure suivante:

- On créer, dans le dossier *src/main/tests*, une classe reprenant le nom et le package de la classe à tester suivi de **TestCase** (ex: **MemberDAOTestCase** dans le package *fr.da2i.lup1.dao*).  
- On écrit, pour chaque méthode de cette classe, une classe de test que l'on utiliseras une fois dans la méthode *test()* afin de vérifier son bon fonctionnement.
- Pour vérifier le bon fonctionnement, on lance la classe avec JUnit et on vérifie la trace.
- Une fois toutes les méthodes validées, on peut passer à la DAO suivante.
