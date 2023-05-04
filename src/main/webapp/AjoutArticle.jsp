<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="./WEB-INF/fragments/importhead.html"%>
<title>Ajout d'article</title>
</head>
<body>
	<%@ include file="./WEB-INF/fragments/header.jsp"%>

	<div class="container mt-5">

		<c:choose>
			<c:when test="${message!=null}">
				<div class="alert alert-danger" role="alert">
					<%=request.getAttribute("message")%>
				</div>
			</c:when>
		</c:choose>

		<div class="row justify-content-center">
			<div class="col-md-6">
				<h1 class="mb-4">Nouvelle article</h1>
				<form action="articles" method="post">
					<div style="float: left; width: 50%;">
						<label for="nom_article">Nom de l'article :</label> 
						<input type="text" name="nom_article" id="nom_article" required value="${nomArticle}"><br> 
						
						<label for="description">Description:</label>						
						<input type="text" name="description" id="description" required value="${description}"><br> 
						
						<label for="date_debut_enchere">Date du début de l'enchère:</label> 						
						<input type="date" name="date_debut_enchere" id="date_debut_enchere" required value="${dateDebutEnchere}"><br> 
						
						<label for="date_fin_enchere">Date de la fin de l'enchère:</label> 
						<input type="date" name="date_fin_enchere" id="date_fin_enchere" required value="${dateFinEnchere}"><br> 
						
						<label for="prix_initial">Prix Initial:</label> 
						<input type="number" name="prix_initial" id="prix_initial" required><br>
					
						<label for="prix_vente">Prix de vente:</label> 
						<input type="number" name="prix_vente" id="prix_vente" required><br>
						
						<!-- TODO CATEGORIE -->
						
						<label for="etat_vente">Etat de vente:</label>
						<select name="etat_vente" id="etat_vente">
 						<option value="RT">RT</option>
  						<option value="VD">VD</option>
  						<option value="EC">EC</option>
 						<option value="CR">CR</option>
						</select>			
						<!-- TODO UPLOAD IMAGE 
						<label for="image">Image de présentation:</label> 
						<input type="image" name="image" id="image" required><br> -->
										
						
					
					</div>

					

					<div style="clear: both;"></div>

					<input class="btn btn-outline-dark" type="submit" value="Créer"> 
					<a class="btn btn-outline-dark"  href="accueil">Annuler</a>
				</form>
			</div>
		</div>
	</div>
	<%@ include file="./WEB-INF/fragments/importjs.html"%>
</body>
</html>