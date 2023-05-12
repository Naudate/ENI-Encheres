/*
Proc�dure � lancer p�riodiquement sur la base de donn�es
Elle consulte les articles dont les ench�res viennent de d�marrer et met � jour les donn�es en cons�quence.
- changement de l'�tat de l'article CR => EC
Elle consulte les articles dont les ench�res viennent d'arriver � �ch�ance et r�aliser les transactions financi�res associ�es.
- changement de l'�tat de l'article EC => VD
- cr�dite le vendeur
*/
CREATE PROCEDURE updateArticle      
AS   
	DECLARE @id int
	DECLARE @montant int
	DECLARE @util int

	--ouvre cursor
	DECLARE db_cursor CURSOR FOR 
	SELECT no_article 
	FROM ARTICLES_VENDUS
	WHERE etat_vente = 'CR' AND GETDATE() >= date_debut_enchere;
	
	OPEN db_cursor  
	FETCH NEXT FROM db_cursor INTO @id 

	WHILE @@FETCH_STATUS = 0  
	BEGIN  
		UPDATE ARTICLES_VENDUS SET  
		etat_vente = 'EC'
		WHERE no_article = @id;   

		FETCH NEXT FROM db_cursor INTO @id 
	END 
	
	--close cursor
	CLOSE db_cursor 
	DEALLOCATE db_cursor;
	
	--ouvre cursor Update article EC
	DECLARE db2_cursor CURSOR FOR 
	SELECT no_article, no_utilisateur
	FROM ARTICLES_VENDUS
	WHERE etat_vente = 'EC' AND GETDATE() > date_fin_enchere;
	
	OPEN db2_cursor  
	FETCH NEXT FROM db2_cursor INTO @id , @util

	WHILE @@FETCH_STATUS = 0  
	BEGIN 
		--set montant vente a zero
		SET @montant = 0;
		DECLARE db3_cursor CURSOR FOR 
		SELECT TOP(1) montant_enchere
		FROM ENCHERES
		WHERE no_article = @id ORDER BY montant_enchere DESC;
		
		OPEN db3_cursor  
		FETCH NEXT FROM db3_cursor INTO @montant 
		
		CLOSE db3_cursor 
		DEALLOCATE db3_cursor;
	
		UPDATE ARTICLES_VENDUS SET  
		etat_vente = 'VD',
		prix_vente = @montant
		WHERE no_article = @id;  

		--Si prix de vente > 0 cr�diter le propri�taire de l'article
		if @montant > 0 
		BEGIN
			UPDATE UTILISATEURS SET credit += @montant WHERE no_utilisateur = @util;
		END


		FETCH NEXT FROM db2_cursor INTO @id  , @util
	END 
	
	--close cursor
	CLOSE db2_cursor 
	DEALLOCATE db2_cursor;
	
    
GO 