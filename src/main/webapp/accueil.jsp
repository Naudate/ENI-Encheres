<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="fr">
<head>
<%@ include file="./WEB-INF/fragments/importhead.html"%>
<title>Accueil</title>
</head>
<body>
	<%@ include file="./WEB-INF/fragments/header.jsp"%>
	<div class="container">
		<div class="row">
			<div class="col-12 text-center">
				<h2>Liste des ench&egrave;res</h2>
			</div>
			<div class="col-12">
				<div class="accordion" id="accordionFiltres">
					<div class="accordion-item">
						<h2 class="accordion-header">
							<button class="accordion-button" type="button"
								data-bs-toggle="collapse" data-bs-target="#collapseOne"
								aria-expanded="true" aria-controls="collapseOne">
								Filtres</button>
						</h2>
						<div id="collapseOne" class="accordion-collapse collapse show"
							data-bs-parent="#accordionFiltres">
							<div class="accordion-body">
								<form action="accueil" method="get" id="filtre">
									<select class="form-select" name="selectCategory"
										id="selectCategory" aria-label="Selection de cat�gories"
										form="filtre">
										<c:choose>
											<c:when test="${categorie != null }">
												<option value="${categorie}" selected>${categorie}</option>
												<option value="" >Cat&eacute;gorie</option>
											</c:when>
											<c:otherwise>
												<option selected disabled>Cat&eacute;gorie</option>
											</c:otherwise>
										</c:choose>
										<c:forEach var="cat" items="${listeCategorie }">
											<c:if test="${!categorie.equals(cat.libelle)}">
												<option value="${cat.libelle}">${cat.libelle}</option>
											</c:if>
										</c:forEach>
									</select>
									<div class="mt-3">
										<c:choose>
											<c:when test="${textArticle.isBlank() == false}">
												<input type="text" class="form-control" id="textArticle"
													name="textArticle" value="${textArticle }">
											</c:when>
											<c:otherwise>
												<input type="text" class="form-control" id="textArticle"
													name="textArticle" placeholder="Rechercher article....">
											</c:otherwise>
										</c:choose>
									</div>
									<div class="d-grid mt-3">
										<button class="btn btn-primary" type="submit">Rechercher</button>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
			<c:choose>
				<c:when test="${listeEnchereArticle.size() > 0}">
					<c:forEach var="EA" items="${listeEnchereArticle}">
						<div class="col-3 mt-3">
							<a class="card text-decoration-none"
								href="<%=request.getContextPath()%>/detailArticle/${EA.no_article}">
								<svg class="bd-placeholder-img card-img-top" width="100%"
									height="180" xmlns="http://www.w3.org/2000/svg" role="img"
									aria-label="Placeholder: Image cap"
									preserveAspectRatio="xMidYMid slice" focusable="false">
									<title>Placeholder</title><rect width="100%" height="100%"
										fill="#868e96" /></svg>
								<div class="card-body">
									<h5 class="card-title">${EA.nom_article}</h5>
									<c:choose>
										<c:when test="${EA.montant_enchere > EA.prix_initial}">
											<p class="card-text">Prix : ${EA.montant_enchere }&euro;</p>
										</c:when>
										<c:otherwise>
											<p class="card-text">Prix : ${EA.prix_initial }&euro;</p>
										</c:otherwise>
									</c:choose>
									<p class="card-text">Fin de l'ench&egrave;re :
										${EA.date_fin_enchere}</p>
									<p class="card-text">
										<small class="text-body-secondary">Vendeur :
											${EA.pseudo}</small>
									</p>
								</div>
							</a>
						</div>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<div class="col-12 text-center">
						<h4>Aucun ench&egrave;re</h4>
					</div>
				</c:otherwise>
			</c:choose>
			<div class="col-12 mt-5 d-flex justify-content-center">
				<nav aria-label="Page navigation">
					<ul class="pagination">
						<li class="page-item"><a class="page-link" href="#">Previous</a></li>
						<li class="page-item"><a class="page-link" href="#">1</a></li>
						<li class="page-item"><a class="page-link" href="#">2</a></li>
						<li class="page-item"><a class="page-link" href="#">3</a></li>
						<li class="page-item"><a class="page-link" href="#">Next</a></li>
					</ul>
				</nav>
			</div>
		</div>
	</div>
	<%@ include file="./WEB-INF/fragments/importjs.html"%>
</body>
</html>