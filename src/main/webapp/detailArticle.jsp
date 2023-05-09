<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="bo.Enchere"%>
<%@page import="bo.Utilisateur"%>
<%@page import="bo.Article"%>
<%@page import="bo.Categorie"%>
<%@page import="bo.Retraits"%>
<%
Article article = (Article) request.getAttribute("article");
int montantMin = (int) article.getPrixInitial() +1;
if(article.getEnchere().getMontant() != 0){
	montantMin = article.getEnchere().getMontant() + 1;
}
int userId = article.getUtilisateur().getNoUtilisateur();
Utilisateur util = (Utilisateur) request.getAttribute("util");
%>

<!DOCTYPE html>
<html lang="fr">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Détail de l'enchère - ${article.nomArticle}</title>
	<!-- Bootstrap CSS -->
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.0.2/css/bootstrap.min.css">
</head>
<body>
	<!-- Header -->
	<jsp:include page="./WEB-INF/fragments/header.jsp" />

	<!-- Contenu principal -->
	<main class="container my-5">
		<div class="row">
			<!-- Image -->
			<div class="col-md-4">
				<img src="image" alt="image de l'objet" class="img-fluid">
			</div>

			<!-- Détails de l'article -->
			<div class="col-md-8">
				<h1 class="mb-4">${article.nomArticle}</h1>

				<p class="mb-2"><b>Description :</b> ${article.description}</p>
				<p class="mb-2"><b>Catégorie :</b> ${article.categorie.libelle}</p>
				
				<!-- Meilleure offre -->
				<c:choose>
					<c:when test="${article.enchere.montant==0}">
						<p class="mb-2"><b>Meilleure offre :</b> Pas d'offre pour le moment</p>
					</c:when>
					<c:otherwise>
						<p class="mb-2"><b>Meilleure offre :</b> ${article.enchere.montant} pts par <a href="<%=request.getContextPath()%>/user/${article.enchere.utilisateur.noUtilisateur}">${article.enchere.utilisateur.pseudo}</a></p>
					</c:otherwise>
				</c:choose>
				
				<p class="mb-2">Mise à prix : ${article.prixInitial} points</p>
				<p class="mb-2">Fin de l'enchère : ${article.dateFinEnchere}</p>
				<p class="mb-2">Retrait : ${article.retrait.rue}<br> 
					${article.retrait.codePostal} ${article.retrait.ville}</p>
				<p class="mb-2">Vendeur : <a href="<%=request.getContextPath()%>/user/${article.utilisateur.noUtilisateur}">${article.utilisateur.pseudo}</a></p>
				
				<!-- Formulaire d'enchère -->
				<form method="post" action="<%=request.getContextPath()%>/detailArticle/${article.noArticle}">
					<div class="form-floating mb-3">
						<input type="number" name="proposition" id="proposition" required class="form-control" value="<%= montantMin %>" min="<%= montantMin %>">
						<label for="proposition">Ma proposition :</label>
					</div>

					<button type="submit" class="btn btn-primary">Enchérir</button>
				</form>
					
				<c:choose>
				  <c:when
					test="${article.utilisateur.noUtilisateur==sessionScope.connected.noUtilisateur}">
					<div class="col-3 d-grid mt-4">
							<a class="btn btn-danger"
							href="<%=request.getContextPath()%>/deleteArticle/${article.noArticle}">
							Supprimer
							</a>
					</div>	
				  </c:when>
				</c:choose>
			</div>
		</div>
	</div>
	<%@ include file="./WEB-INF/fragments/importjs.html"%>
</body>
</html>