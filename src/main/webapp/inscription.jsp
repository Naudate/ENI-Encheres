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

		<c:choose>
			<c:when test="${message!=null}">
				<div class="alert alert-danger" role="alert">
					<%=request.getAttribute("message")%>
				</div>
			</c:when>
		</c:choose>

		<div class="row justify-content-center">
			<div class="col-md-6">
				<h1 class="mb-4">Inscription</h1>
				<form action="inscription" method="post">
					<div style="float: left; width: 50%;">
						<label for="pseudo">Pseudo:</label> 
						<input type="text" name="pseudo" id="pseudo" required value="${pseudo}"><br> 
						
						<label for="prenom">Prénom:</label>						
						<input type="text" name="prenom" id="prenom" required value="${prenom}"><br> 
						
						<label for="telephone">Téléphone:</label> 						
						<input type="tel" name="telephone" id="telephone" required minlength="10" maxlength="10"  value="${telephone}"><br> 
						
						<label for="codePostal">Code Postal:</label> 
						<input type="number" name="codePostal" id="codePostal" required minlength="5" maxlength="5" value="${codePostal}"><br> 
						
						<label for="motDePasse">Mot de passe:</label> 
						<input type="password" name="motDePasse" id="motDePasse" required><br>
					</div>

					<div style="float: right; width: 50%;">
						<label for="nom">Nom:</label> 
						<input type="text" name="nom" id="nom" required value="${nom}"><br> 
						
						<label for="email">Email:</label> 
						<input type="email" name="email" id="email" required value="${email}"><br> 
						
						<label for="rue">Rue:</label> 
						<input type="text" name="rue" id="rue" required value="${rue}"><br>
						
						<label for="ville">Ville:</label> 
						<input type="text" name="ville"	id="ville" required value="${ville}"><br> 
						
						<label for="motDePasseConfirme">Confirmation:</label> 
						<input type="password" name="motDePasseConfirme" id="motDePasseConfirme" required><br>
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