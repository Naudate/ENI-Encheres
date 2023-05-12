# ENI-Encheres aka ENIBay
## Summary

>The association "Les objets sont nos amis" wishes to set up a web platform to allow the transfer of second hand objects without financial exchange. The value of the items will be determined by an auction system based on a number of points. The points are earned by selling objects, and can then be used to acquire other objects.

## Librairies 📖

### Java 🍵
- [JDK-17.0.5](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [JAXB-API-2.2.8](https://mvnrepository.com/artifact/javax.xml.bind/jaxb-api)
- [Activation-1.1](https://mvnrepository.com/artifact/javax.xml.bind/activation)
- [JAXB-impl-2.2.7](https://mvnrepository.com/artifact/javax.xml.bind/jaxb-impl)
- [JAXB-core-2.2.7](https://mvnrepository.com/artifact/javax.xml.bind/jaxb-core)
- [JSTL-1.2](https://mvnrepository.com/artifact/javax.servlet.jsp.jstl/jstl)
- [Validation-api-1.1.0.Final](https://mvnrepository.com/artifact/javax.validation/validation-api)

### Database 📑
We use MSSQL 2019
- [Sqljdbc42](https://mvnrepository.com/artifact/com.microsoft.sqlserver/sqljdbc42)
### Tomcat 🐈
- [Tomcat 9.0.71](https://archive.apache.org/dist/tomcat/tomcat-9/v9.0.71/)

## Features 
| **Itération** | **Importance** | **Catégorie** | **"ID"** | **nom** | **Description** |
|---|---|---|---|---|---|
| 1 | 2000 | Gestion des utilisateurs | 1001 | "Se connecter" | En tant qu'utilisateur, je peux me connecter sur la plateforme Enchères.org avec un login (adresse mail ou pseudo) et un mot de passe. |
| 1 | 1900 | Gestion des utilisateurs | 1003 | S’inscrire | En tant qu’utilisateur, je peux m’inscrire sur la plateforme Enchères.org. Le pseudo doit être unique sur toute la plateforme, ainsi que l’email. Le pseudo n’accepte que des caractères alphanumériques. Si la création du profil est validée, l’utilisateur est dirigé vers la page d’accueil (liste des enchères). Un crédit initial de 100 points est alloué à la création du compte. |
| 1 | 1800 | Navigation | 6002 | Page d’accueil | La page d’accueil du site est la page qui liste les enchères. Cette page doit être chargée automatiquement si aucune ressource n’est indiquée dans l’url (http://localhost:8080/encheres/) |
| 1 | 1750 | Gestion des utilisateurs | 1009 | Se déconnecter | En tant qu’utilisateur connecté, je peux me déconnecter. Je suis alors ramené vers la page d’accueil en mode déconnecté. |
| 1 | 1700 | Gestion des utilisateurs | 1006 | Afficher un profil | En tant qu’utilisateur, je peux afficher le profil d’un utilisateur. Le pseudo, nom, prénom, email, téléphone, rue, code postal, ville sont affichés. |
| 1 | 1600 | Gestion des utilisateurs | 1007 | Modifier mon profil | En tant qu’utilisateur, je peux modifier mes informations de profil : Pseudo, Nom, prénom, email, téléphone, rue, code postal, ville, et mot de passe. |
| 1 | 1500 | Gestion des utilisateurs | 1004 | Supprimer mon compte | En tant qu’utilisateur, je peux supprimer mon compte. Dans ce cas je suis déconnecté et retourné à la page d’accueil. |
| 1 | 1400 | Gestion des enchères | 2001 | Vendre un article | En tant qu’utilisateur, je peux vendre un article sur la plateforme ENI-Enchères. Pour cela je donne les informations sur l’article vendu : nom, description et catégorie j’indique un prix de départ ( en points ), une date et une heure d’ouverture de l’enchère, une date et une heure de fin d’enchères et les modalités du retrait :  adresse (par défaut celle du vendeur). |
| 1 | 1300 | Gestion des enchères | 2004 | Lister les enchères en mode déconnecté | En tant qu’utilisateur non connecté, je peux lister les enchères en cours. Je peux filtrer ma recherche par catégorie, et par nom d’article (l’article est affiché si il contient le critère saisi) – Pour consulter le détail des enchères, l’utilisateur doit se connecter. |
| 1 | 1200 | Gestion des enchères | 2005 | Lister les enchères en mode connecté | En tant qu’utilisateur connecté, je peux lister les enchères en cours, les enchères auxquelles je participe, c’est à dire celles sur lesquelles j’ai fait au moins une offre, et mes enchères gagnées. Je peux aussi sélectionner mes ventes, non commencées, en-cours ou terminées |
| 1 | 1100 | Gestion des enchères | 2006 | Faire une enchère | En tant qu’utilisateur, je peux faire une enchère sur un article si je propose un prix (en points) supérieur au tarif actuel et si mon compte de points ne devient pas négatif. Si l’enchère est possible, mon crédit de points est débité du montant proposé. Le meilleur enchérisseur précédent si il existe est re-crédité de son offre. |
| 1 | 1000 | Gestion des enchères | 2007 | Remporter une vente | En tant qu’enchérisseur, je deviens acquéreur si au terme de l’enchère j’ai proposé l’enchère la plus haute. |
| 1 | 950 | Navigation | 6003 | Lien logo Encheres | Un click sur le logo du site ramène l’utilisateur sur la page principale (page de recherche d’articles) si celui-ci est connecté. |
| 1 | 900 | Gestion des enchères | 2009 | Afficher le détail d’une enchère | En tant qu’utilisateur, je peux afficher le détail d’une enchère. Les informations sur l’article vendu sont affichés ( nom, description, meilleure offre, mise à prix, début et fin de l’enchère, adresse de retrait, vendeur. En fonction de l’état de la vente, et du rôle de l’utilisateur (vendeur ou acheteur), l’utilisateur peux seulement consulter les information, enchérir, ou modifier la vente (si il est le vendeur et que l’enchère n’a pas débuté). |
| 1 | 850 | Navigation | 6001 | Boutons rafraîchir et retour du navigateur | En tant qu’utilisateur, je peux rafraîchir la page courante ou revenir sur la page précédente en utilisant le bouton « back » du navigateur. |
| 2 | 800 | Responsive Web Design | 5001 | Version mobile | Les fonctionnalités sont accessibles depuis un petit appareil de type smartphone connecté au web. Cf maquettes |
| 2 | 750 | Sécurité | 8001 | Sessions utilisateur de 5mn | L’utilisateur doit être déconnecté automatiquement après 5 minutes d’inactivité |
| 2 | 700 | Gestion des utilisateurs | 1002 | Se souvenir de moi | En tant qu'utilisateur, je peux choisir d'enregistrer mon login sur mon ordinateur pour ne pas avoir à le ressaisir à la connexion suivante. |
| 2 | 675 | Gestion des enchères | 2002 | Modifier une vente | En tant que vendeur, je peux modifier les informations liées à ma vente tant que la date de début d’enchère n’est pas arrivée. |
| 2 | 650 | Gestion des enchères | 2003 | Annuler une vente | En tant que vendeur d’un article, je peux annuler cette vente tant que la date de début d’enchère n’est pas arrivée |
| 2 | 600 | Gestion des enchères | 2008 | Photo pour la vente | en tant que vendeur, je peux uploader une photo de l’article à vendre. Celle ci sera visible sur le détail de la vente. |
| 2 | 500 | Administration | 3001 | Supprimer des comptes utilisateurs | En tant qu’administrateur, je peux supprimer des comptes utilisateurs. |
| 2 | 450 | Administration | 3002 | Désactiver un compte utilisateur | En tant qu’administrateur, je peux désactiver un compte utilisateur. Toutes les ventes proposées par cet utilisateur sont alors annulées, toutes les enchères faites par cet utilisateur sont elles aussi annulées, l’utilisateur ne peut pas créer de nouvelles ventes ou faire de nouvelles enchères. |
| 2 | 400 | Gestion des utilisateurs | 1005 | Mot de passe oublié | En tant qu'utilisateur, je peux faire une demande de ré-initialisation de mot de passe. La plateforme créé un lien vers un écran de saisie du nouveau mot de passe. (Ce lien sera envoyé par mail à l'utilisateur. L'envoi par mail n'est pas demandé) |
| 3 | 350 | Gestion des enchères | 2010 | Pagination | Sur la page permettant de lister les enchères suivant des critères de recherche, j’affiche un nombre d’enchères maximum ( 6) et  j’accède  aux autres pages résultat par l’intermédiaire de liens numérotés. |
| 3 | 300 | Multilingue | 9001 | Version anglaise | En tant qu’utilisateur, je peux choisir la langue utilisée sur la plateforme. J’ai le choix entre français et anglais. |
| 3 | 250 | Journalisation | 7001 | Gestion des logs | En tant que développeur ou support informatique, j’ai accès à des fichiers de logs côté serveur me permettant d’avoir des informations détaillées sur les éventuelles erreurs en production, et des informations de tracing plus précises en phase de recette et de développement. |
| 3 | 200 | Gestion des enchères | 2011 | Voir les enchérisseurs | En tant que vendeur, je peux voir la liste des enchérisseurs de mes ventes. Les enchérisseurs sont triés en fonction du montant de leur dernière enchère par ordre décroissant. |
| 3 | 150 | Administration | 3003 | Gestion catégories | En tant qu’administrateur, je peux gérer, c’est à dire ajouter, supprimer, modifier les catégories d’articles. |
| 3 | 100 | Notification par mail | 10001 | Notifier l’achat | A la date de fin d’enchère, un traitement batch calcule le prix de vente et notifie l’acheteur par mail. |
| 3 | 50 | Gestion des utilisateurs | 1008 | Achat de crédits | En tant qu’utilisateur, je peux acheter des crédits. |









## Contributors 💜

Lucie FRIBAULT, Sara MAHEO & Nathan JAOUEN
