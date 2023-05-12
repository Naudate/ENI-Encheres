-------------

-- Script de création de la base de données ENCHERES_BDD
--   type :      SQL Server 2012
--

USE ENCHERES_BDD
GO

DROP TABLE ENCHERES;
DROP TABLE RETRAITS;
DROP TABLE IMAGES;
DROP TABLE ARTICLES_VENDUS;
DROP TABLE CATEGORIES;
DROP TABLE UTILISATEURS;
GO

CREATE TABLE CATEGORIES (
    no_categorie   INTEGER IDENTITY(1,1) NOT NULL,
    libelle        VARCHAR(30) NOT NULL
)

ALTER TABLE CATEGORIES ADD constraint categories_pk PRIMARY KEY (no_categorie)

CREATE TABLE ENCHERES (
    no_utilisateur   INTEGER NOT NULL,
    no_article       INTEGER NOT NULL,
    date_enchere     DATETIME NOT NULL,
	montant_enchere  INTEGER NOT NULL

)

ALTER TABLE ENCHERES ADD constraint encheres_pk PRIMARY KEY (no_article, date_enchere, montant_enchere)

CREATE TABLE RETRAITS (
	no_article       INTEGER NOT NULL,
    rue              VARCHAR(30) NOT NULL,
    code_postal      VARCHAR(15) NOT NULL,
    ville            VARCHAR(30) NOT NULL
)

ALTER TABLE RETRAITS ADD constraint retraits_pk PRIMARY KEY  (no_article)

CREATE TABLE IMAGES ( 
	no_article INTEGER NOT NULL, 
	picture nvarchar(max) NOT NULL
)

ALTER TABLE IMAGES ADD constraint images_pk PRIMARY KEY (no_article)

CREATE TABLE UTILISATEURS (
    no_utilisateur   INTEGER IDENTITY(1,1) NOT NULL,
    pseudo           VARCHAR(30) NOT NULL,
    nom              VARCHAR(30) NOT NULL,
    prenom           VARCHAR(30) NOT NULL,
    email            VARCHAR(100) NOT NULL,
    telephone        VARCHAR(15) NULL,
    rue              VARCHAR(30) NOT NULL,
    code_postal      VARCHAR(10) NOT NULL,
    ville            VARCHAR(30) NOT NULL,
    mot_de_passe     VARCHAR(30) NOT NULL,
    credit           INTEGER NOT NULL,
    administrateur   BIT NOT NULL,
	actif   BIT NOT NULL DEFAULT 1
)

ALTER TABLE UTILISATEURS ADD constraint utilisateurs_pk PRIMARY KEY (no_utilisateur)
ALTER TABLE UTILISATEURS ADD constraint utilisateurs_pseudo_uq UNIQUE (pseudo)
ALTER TABLE UTILISATEURS ADD constraint utilisateurs_email_uq UNIQUE (email) 


CREATE TABLE ARTICLES_VENDUS (
    no_article                    INTEGER IDENTITY(1,1) NOT NULL,
    nom_article                   VARCHAR(30) NOT NULL,
    description                   VARCHAR(300) NOT NULL,
	date_debut_enchere            DATETIME NOT NULL,
    date_fin_enchere              DATETIME NOT NULL,
    prix_initial                  INTEGER NULL,
    prix_vente                    INTEGER NULL,
    no_utilisateur                INTEGER NOT NULL,
    no_categorie                  INTEGER NOT NULL,
	etat_vente					  CHAR(2) NOT NULL DEFAULT 'CR',
	image 						  VARCHAR(150) NULL
)

ALTER TABLE ARTICLES_VENDUS ADD CONSTRAINT articles_vendus_pk PRIMARY KEY (no_article)
--CREER("CR"),EN_COURS("EC"),VENDU("VD"),RETIRER("RT");
ALTER TABLE ARTICLES_VENDUS ADD CONSTRAINT articles_vendus_etat_vente_ck CHECK (etat_vente IN ('CR','EC','VD','RT'))

--MISE EN PLACE DE L'INTEGRITE REFERENTIELLE
ALTER TABLE ARTICLES_VENDUS
    ADD CONSTRAINT articles_utilisateurs_fk FOREIGN KEY ( no_utilisateur ) REFERENCES UTILISATEURS ( no_utilisateur )
	ON DELETE CASCADE 
    ON UPDATE NO ACTION 
    
ALTER TABLE ARTICLES_VENDUS
    ADD CONSTRAINT articles_vendus_categories_fk FOREIGN KEY ( no_categorie )
        REFERENCES categories ( no_categorie )
	ON DELETE NO ACTION 
    ON UPDATE NO ACTION 
    
ALTER TABLE ENCHERES
    ADD CONSTRAINT encheres_articles_vendus_fk FOREIGN KEY ( no_article )
        REFERENCES ARTICLES_VENDUS ( no_article )
	ON DELETE NO ACTION
    ON UPDATE NO ACTION 
    
ALTER TABLE ENCHERES
    ADD CONSTRAINT ventes_utilisateurs_fk FOREIGN KEY ( no_utilisateur )
        REFERENCES utilisateurs ( no_utilisateur )
	ON DELETE CASCADE 
    ON UPDATE NO ACTION 

ALTER TABLE RETRAITS
    ADD CONSTRAINT retraits_articles_vendus_fk FOREIGN KEY ( no_article )
        REFERENCES ARTICLES_VENDUS ( no_article )
	ON DELETE CASCADE --A LA SUPPRESSION DE L'ARTICLE, SUPPRESSION DU RETRAIT LIES
    ON UPDATE NO ACTION 
	
ALTER TABLE IMAGES 
	ADD CONSTRAINT images_articles_vendus_fk FOREIGN KEY ( no_article ) 
	REFERENCES ARTICLES_VENDUS ( no_article ) 
	ON DELETE CASCADE --A LA SUPPRESSION DE L'ARTICLE, SUPPRESSION DE L IMAGE LIES 
	ON UPDATE NO ACTION

--------------------------------------
/*
 SELECT  a.no_article,nom_article,description,date_debut_encheres,date_fin_encheres,prix_initial,prix_vente,
			a.no_utilisateur as no_user,a.no_categorie,etat_vente,image,r.rue as arue,r.code_postal as acp,r.ville as aville, c.libelle, u.*,
			e.no_utilisateur as ec_no_utilisateur, e.date_enchere, e.montant_enchere 
			FROM  (((ARTICLES_VENDUS a LEFT JOIN RETRAITS r ON a.no_article = r.no_article) 
			LEFT JOIN CATEGORIES c ON c.no_categorie = a.no_categorie)
			LEFT JOIN UTILISATEURS u ON u.no_utilisateur = a.no_utilisateur)
			LEFT JOIN ENCHERES e ON (a.no_article = e.no_article AND e.no_utilisateur = (SELECT TOP(1) ec.no_utilisateur FROM ENCHERES ec WHERE ec.no_article = a.no_article ORDER BY date_enchere DESC))
			WHERE (GETDATE() BETWEEN date_debut_encheres AND date_fin_encheres)
*/		
			
-------------------------------------
 

			