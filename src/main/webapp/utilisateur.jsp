<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="bo.Utilisateur"%>
<%
Utilisateur util = (Utilisateur) request.getAttribute("util");
%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="./WEB-INF/fragments/importhead.html"%>
<title>Connexion</title>
</head>
<body>

	<%@ include file="./WEB-INF/fragments/header.jsp"%>

	<c:choose>
		<c:when test="${me==true}">
			<div class="container">
				<div class="row">
					<h1>Mon profil</h1>
					<form action="<%=request.getContextPath()%>/user/me" method="post">
						<div class="row justify-content-center">
							<div class="col-4 mt-3">
								<div class="input-group">
									<span class="input-group-text" id="pseudo"><i
										class="bi bi-person-circle"></i></span> <input type="text"
										class="form-control" aria-label="Pseudo" name="pseudo"
										aria-describedby="Pseudo" value="${util.pseudo}"
										placeholder="Pseudo" required>
								</div>
							</div>
							<div class="col-4 mt-3">
								<div class="input-group">
									<span class="input-group-text" id="nom"><i
										class="bi bi-person-fill"></i></span> <input type="text"
										class="form-control" aria-label="nom" aria-describedby="nom" name="nom"
										value="${util.nom}" placeholder="Nom" required>
								</div>
							</div>
						</div>
						<div class="row justify-content-center">
							<div class="col-4 mt-3">
								<div class="input-group">
									<span class="input-group-text" id="prenom"><i
										class="bi bi-person-fill"></i></span> <input type="text"
										class="form-control" aria-label="prenom" name="prenom"
										aria-describedby="prenom" value="${util.prenom}"
										placeholder="Prénom" required>
								</div>
							</div>
							<div class="col-4 mt-3">
								<div class="input-group">
									<span class="input-group-text" id="email"><i
										class="bi bi-envelope-at-fill"></i></span> <input type="email"
										class="form-control" aria-label="email" name="email"
										aria-describedby="email" value="${util.email}"
										placeholder="Email" required>
								</div>
							</div>
						</div>
						<div class="row justify-content-center">
							<div class="col-4 mt-3">
								<div class="input-group">
									<span class="input-group-text" id="telephone"><i
										class="bi bi-telephone-fill"></i></span> <input type="tel"
										class="form-control" aria-label="telephone" name="telephone"
										aria-describedby="telephone" value="${util.telephone}"
										minlength="10" maxlength="10"
										placeholder="Numéro de téléphone" required>
								</div>
							</div>
							<div class="col-4 mt-3">
								<div class="input-group">
									<span class="input-group-text" id="rue"><i
										class="bi bi-house-fill"></i></span> <input type="text" name="rue"
										class="form-control" aria-label="rue" aria-describedby="rue"
										value="${util.rue}" placeholder="Adresse" required>
								</div>
							</div>
						</div>
						<div class="row justify-content-center">
							<div class="col-4 mt-3">
								<div class="input-group">
									<span class="input-group-text" id="codePostal"><i
										class="bi bi-building-fill"></i></span> <input type="number"
										class="form-control" aria-label="codePostal" name="codePostal"
										aria-describedby="codePostal" value="${util.code_postal}"
										minlength="5" maxlength="5" placeholder="Code postal" required>
								</div>
							</div>
							<div class="col-4 mt-3">
								<div class="input-group">
									<span class="input-group-text" id="ville"><i
										class="bi bi-building-fill"></i></span> <input type="text"
										class="form-control" aria-label="ville" name="ville"
										aria-describedby="ville" value="${util.ville}"
										placeholder="Ville" required>
								</div>
							</div>
						</div>

						<div class="row justify-content-center">
							<div class="col-4 mt-3">
								<div class="input-group">
									<span class="input-group-text" id="motDePasse"><i
										class="bi bi-key-fill"></i></span> <input type="password"
										class="form-control" name="motDePasse" aria-label="motDePasse"
										aria-describedby="motDePasse"
										placeholder="Mot de passe actuel" required>
								</div>
							</div>
						</div>

						<div class="row justify-content-center">
							<div class="col-4 mt-3">
								<div class="input-group">
									<span class="input-group-text" id="newMotDePasse"><i
										class="bi bi-key-fill"></i></span> <input type="password"
										class="form-control" name="newMotDePasse"
										aria-label="newMotDePasse" aria-describedby="newMotDePasse"
										placeholder="Nouveau mot de passe"
										>
								</div>
							</div>
							<div class="col-4 mt-3">
								<div class="input-group">
									<span class="input-group-text" id="confirmation"><i
										class="bi bi-check2-all"></i></span> <input type="password"
										class="form-control" name="confirmation"
										aria-label="confirmation" aria-describedby="confirmation"
										placeholder="Confirmation">
								</div>
							</div>
						</div>
						
						<div class="row justify-content-center">
							<div class="col-4 mt-3">
								<p class="text-center">Crédit : ${util.credit}</p>
							</div>							
						</div>
						
						<div class="row justify-content-center">
							<div class="col-3 d-grid mt-3">
								<input class="btn btn-primary" type="submit" value="Valider Modification"
									id="submit">
							</div>							
						</div>						
					</form>


				</div>
			</div>
		</c:when>
		<c:otherwise>
			<div class="container">
				<div class="row justify-content-center">
					<div class="col-md-8 text-center">
					</br>
						<p>Pseudo : ${util.pseudo}</p>
						<p>Nom : ${util.nom}</p>
						<p>Prénom : ${util.prenom}</p>
						<p>Email : ${util.email}</p>
						<p>Téléphone : ${util.telephone}</p>
						<p>Rue : ${util.rue}</p>
						<p>Code postal : ${util.code_postal}</p>
						<p>Ville : ${util.ville}</p>
					</div>
				</div>
			</div>
			<c:choose>
				<c:when
					test="${util.noUtilisateur==sessionScope.connected.noUtilisateur}">
					
					<div class="row justify-content-center text-center">
					<p>Crédit : ${util.credit}</p>
						<div class="col-3 d-grid mt-3">
							<a class="btn btn-primary"
								href="<%=request.getContextPath()%>/user/me">Modifier</a>
						</div>
						<div class="col-3 d-grid mt-3">
							<a class="btn btn-danger"
								onclick="return confirm('Êtes-vous sûr de vouloir supprimer votre compte ?');"
								href="<%=request.getContextPath()%>/deleteUser">Supprimer</a>
						</div>
					</div>
				</c:when>
			</c:choose>
		</c:otherwise>
	</c:choose>

	<%@ include file="./WEB-INF/fragments/importjs.html"%>
</body>
</html>