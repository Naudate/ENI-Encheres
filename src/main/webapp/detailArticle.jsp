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
<html>
<head>
<%@ include file="./WEB-INF/fragments/importhead.html"%>
<title>Détail de l'enchère - ${article.nomArticle}</title>
</head>
<body>
	<%@ include file="./WEB-INF/fragments/header.jsp"%>
	<div class="container">	
		<div class="row">
			<div class="col-md-4">
				<img src="data:image/jpeg;base64,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" alt="image de l'objet"
					class="img-fluid">
			</div>
			<div class="col-md-8">
				<h1>${article.nomArticle}</h1>
				<p>Description : ${article.description}</p>
				<p>Catégorie : ${article.categorie.libelle}</p>				
				<c:choose>
					<c:when test="${article.enchere.montant==0}">
						<p>Meilleure offre : Pas d'offre pour le moment</p>
					</c:when>
					<c:otherwise>
						<p>Meilleure offre : ${article.enchere.montant} pts par <a href="<%=request.getContextPath()%>/user/${article.enchere.utilisateur.noUtilisateur}">${article.enchere.utilisateur.pseudo}</a></p>
					</c:otherwise>
				</c:choose>
				<p>Mise à prix : ${article.prixInitial} points</p>
				<p>Fin de l'enchère : ${article.dateFinEnchere}</p>
				<p>Retrait : ${article.retrait.rue} <br/> 
				${article.retrait.codePostal} ${article.retrait.ville}</p>
				<p>Vendeur : <a href="<%=request.getContextPath()%>/user/${article.utilisateur.noUtilisateur}">${article.utilisateur.pseudo}</a></p>	
				<form method="post" action="<%=request.getContextPath()%>/detailArticle/${article.noArticle}">
				<label for="proposition">Ma proposition :</label> 
				<input type="number" name="proposition" id="proposition" required value="<%= montantMin %>" min="<%= montantMin %>">
				<input class="btn btn-outline-dark" type="submit" value="Enchérir"> 
				</form>	
				<c:choose>
				  <c:when
					test="${article.utilisateur.noUtilisateur==sessionScope.connected.noUtilisateur}">
					<div class="col-3 d-grid mt-3">
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