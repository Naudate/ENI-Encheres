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
if(article.getEnchere() != null && article.getEnchere().getMontant() != 0){
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
			<div class="col-md-4 mt-5">
				<c:choose>
					<c:when test="${article.image.picture != null}" >
						<img src="${pageContext.request.contextPath}/uploads/${article.image.picture}" alt="${truc.name}" style="width: 350px; height: 350px;"/>
					</c:when>
					<c:otherwise>
						<svg class="bd-placeholder-img card-img-top" width="350px"
											height="350px" xmlns="http://www.w3.org/2000/svg" role="img"
											aria-label="Placeholder: Image cap"
											preserveAspectRatio="xMidYMid slice" focusable="false">
									<title>Placeholder</title><rect width="100%" height="100%"
												fill="#868e96" /></svg>
					</c:otherwise>
				</c:choose>
				
			</div>
			<div class="col-md-8 mt-5">
				<h1>${article.nomArticle}</h1><br>
				<p><b>Description : </b>${article.description}</p>
				<p><b>Catégorie :</b> ${article.categorie.libelle}</p>				
				<c:choose>
					<c:when test="${article.enchere == null || article.enchere.montant==0}">
						<p><b>Meilleure offre :</b> Pas d'offre pour le moment</p>
					</c:when>
					<c:otherwise>
						<p><b>Meilleure offre :</b> ${article.enchere.montant} pts par <a href="<%=request.getContextPath()%>/user/${article.enchere.utilisateur.noUtilisateur}">${article.enchere.utilisateur.pseudo}</a></p>
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
					test="${article.utilisateur.noUtilisateur==sessionScope.connected.noUtilisateur && article.etatVente=='CR'}">
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