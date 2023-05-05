<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="./WEB-INF/fragments/importhead.html"%>
<title>Inscription</title>
</head>
<body>
	<%@ include file="./WEB-INF/fragments/header.jsp"%>
	<div class="container mt-5">
		<div class="row justify-content-center">
			<div class="col-12 text-center">
				<h1>Inscription</h1>
			</div>
			<form action="inscription" method="post">
				<div class="row justify-content-center">
					<div class="col-4 mt-3">
						<div class="input-group">
							<span class="input-group-text" id="pseudo"><i class="bi bi-person-circle"></i></span>
							<input type="text" class="form-control" aria-label="Pseudo" aria-describedby="Pseudo" value="${pseudo}" placeholder="Pseudo" required>
						</div>	
					</div>
					<div class="col-4 mt-3">
						<div class="input-group">
							<span class="input-group-text" id="nom"><i class="bi bi-person-fill"></i></span>
							<input type="text" class="form-control" aria-label="nom" aria-describedby="nom" value="${nom}" placeholder="Nom" required>
						</div>
					</div>
				</div>
				<div class="row justify-content-center">
					<div class="col-4 mt-3">
						<div class="input-group">
							<span class="input-group-text" id="prenom"><i class="bi bi-person-fill"></i></span>
							<input type="text" class="form-control" aria-label="prenom" aria-describedby="prenom" value="${prenom}" placeholder="Prénom" required>
						</div>	
					</div>
					<div class="col-4 mt-3">
						<div class="input-group">
							<span class="input-group-text" id="email"><i class="bi bi-envelope-at-fill"></i></span>
							<input type="email" class="form-control" aria-label="email" aria-describedby="email" value="${email}" placeholder="Email"required>
						</div>
					</div>
				</div>
				<div class="row justify-content-center">
					<div class="col-4 mt-3">
						<div class="input-group">
							<span class="input-group-text" id="telephone"><i class="bi bi-telephone-fill"></i></span>
							<input type="tel" class="form-control" aria-label="telephone" aria-describedby="telephone" value="${telephone}" minlength="10" maxlength="10" placeholder="Numéro de téléphone" required>
						</div>	
					</div>
					<div class="col-4 mt-3">
						<div class="input-group">
							<span class="input-group-text" id="rue"><i class="bi bi-house-fill"></i></span>
							<input type="text" class="form-control" aria-label="rue" aria-describedby="rue" value="${rue}" placeholder="Adresse" required>
						</div>
					</div>
				</div>
				<div class="row justify-content-center">
					<div class="col-4 mt-3">
						<div class="input-group">
							<span class="input-group-text" id="codePostal"><i class="bi bi-building-fill"></i></span>
							<input type="number" class="form-control" aria-label="codePostal" aria-describedby="codePostal" value="${codePostal}" minlength="5" maxlength="5" placeholder="Code postal" required>
						</div>	
					</div>
					<div class="col-4 mt-3">
						<div class="input-group">
							<span class="input-group-text" id="ville"><i class="bi bi-building-fill"></i></span>
							<input type="text" class="form-control" aria-label="ville" aria-describedby="ville" value="${ville}" placeholder="Ville" required>
						</div>
					</div>
				</div>				
				<div class="row justify-content-center">
					<div class="col-4 mt-3">
						<div class="input-group">
							<span class="input-group-text" id="motDePasse"><i class="bi bi-key-fill"></i></span>
							<input type="password" class="form-control" name="motDePasse" aria-label="motDePasse" aria-describedby="motDePasse" value="${motDePasse}" placeholder="Mot de passe" required>
						</div>
					</div>
					<div class="col-4 mt-3">
						<div class="input-group">
							<span class="input-group-text" id="confirmation"><i class="bi bi-check2-all"></i></span>
							<input type="text" class="form-control" name="confirmation" aria-label="confirmation" aria-describedby="confirmation" value="${confirmation}" placeholder="Confirmation du mot de passe" required>
						</div>	
					</div>
				</div>
				<div class="row justify-content-center">
					<div class="col-3 d-grid mt-3">
						<input class="btn btn-primary" type="submit" value="Créer" id="submit"> 
					</div>
					<div class="col-3 d-grid mt-3">
						<a class="btn btn-primary"  href="accueil">Annuler</a>
					</div>
				</div>
			</form>
				<form action="inscription" method="post">


				</form>
			</div>
		</div>
	</div>
	<%@ include file="./WEB-INF/fragments/importjs.html"%>
</body>
</html>