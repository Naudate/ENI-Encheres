<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="./WEB-INF/fragments/importhead.html"%>
<title>Ajout d'article</title>
<!-- Link Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
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

		<div class="row justify-content-center d-flex m-5">
		
		<div class="col-md-6 d-flex justify-content-center">
			<img id="uploadPreview" style="width: 350px; height: 350px;"/>
		</div>
			<div class="col-md-6">
				<h1 class="mb-4 font-weight-bold text-center">Nouvelle vente</h1>
				<form action="articles" method="post" enctype="multipart/form-data">
					<div class="form-group">
						<label for="nom_article">Nom de l'article :</label>
						<!-- Use Bootstrap Form Control Styles -->
						<input type="text" class="form-control" name="nom_article"
							id="nom_article" required value="${nomArticle}">
					</div>
					<div class="form-group">
						<label for="description">Description:</label> <input type="text"
							class="form-control" name="description" id="description" required
							value="${description}">
					</div>
					<div class="form-group">
						<label for="listCategorie">Catégorie:</label> <select
							class="form-control" name="listCategorie" id="listCategorie">
							<c:forEach var="value" items="${listCategorie}">
								<option value="${value.noCategorie}" id="listCategorie">${value.libelle}</option>
							</c:forEach>
						</select>
					</div>
					<div class="form-group">
						<label for="prix_initial">Mise à prix:</label> <input
							type="number" class="form-control" name="prix_initial"
							id="prix_initial" required>
					</div>
        			<div class="form-group">
						<label for="pictureFile">Image de l'article:</label> 
						<input
							class="form-control" type="file" id="pictureFile"
							name="pictureFile" accept="image/png, image/jpeg" 
							onchange="PreviewImage();"
							required/>
					</div>
					<div class="form-group">
						<label for="date_debut_enchere">Début de l'enchère:</label> <input
							type="date" class="form-control" name="date_debut_enchere"
							id="date_debut_enchere" required value="${dateDebutEnchere}">
					</div>
					<div class="form-group">
						<label for="date_fin_enchere">Fin de l'enchère:</label> <input
							type="date" class="form-control" name="date_fin_enchere"
							id="date_fin_enchere" required value="${dateFinEnchere}">
					</div>
					<h1 class="mb-4 font-weight-bold text-center">Retrait</h1>
					<div class="form-group">
						<label for="rue">Rue :</label> <input type="text"
							class="form-control" name="rue" id="rue" value="${rueText}"
							required>
					</div>
					<div class="form-group">
						<label for="code_postal">Code postal :</label> <input type="text"
							class="form-control" name="code_postal" id="code_postal"
							value="${villeText}" required>
					</div>
					<div class="form-group">
						<label for="ville">Ville :</label> <input type="text"
							class="form-control" name="ville" id="ville" value="${codeText}"
							required>
					</div>

					<div style="clear: both;"></div>
					<br> <input class="btn btn-outline-dark" type="submit"
						value="Enregistrer"> <a class="btn btn-outline-dark"
						href="accueil">Annuler</a>
				</form>
			</div>
		</div>
	</div>
	<%@ include file="./WEB-INF/fragments/importjs.html"%>
	<script type="text/javascript">
function PreviewImage() {
	var oFReader = new FileReader();	          
	oFReader.readAsDataURL(document.getElementById("pictureFile").files[0]);
	oFReader.onload = function (oFREvent) {	            
		document.getElementById("uploadPreview").src = oFREvent.target.result;
	};
};
</script>
</body>

</html>



