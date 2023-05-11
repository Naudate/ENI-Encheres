<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../WEB-INF/fragments/importhead.html"%>
<title>Gestion Utilisateurs</title>
</head>
<body>

	<script>
    function disableUser(checkbox, userId) {
    	let valid = true;
    	if(!checkbox.checked){
    		valid = false;
    		if(confirm('Êtes-vous sûr de vouloir désactiver ce compte ? Ces articles et enchères seront désactivés.')){
    			valid = true;
    		}
    	}
    	if(valid){
    		const form = document.querySelector(`#disable-user-form-`+userId);
            form.submit();
    	}        
    }

</script>

	<%@ include file="../WEB-INF/fragments/header.jsp"%>

	<div class="container mt-5">
		<h1 class="text-center">Liste des Utilisateurs</h1>

		<table class="table table-striped text-center mt-5">
			<thead>
				<tr>
					<th>Numéro Utilisateur</th>
					<th>Pseudo</th>
					<th>Nom</th>
					<th>Prénom</th>
					<th>Email</th>
					<th>Numéro de téléphone</th>
					<th>Adresse</th>
					<th>Crédit</th>
					<th>Administrateur</th>
					<th>Action</th>
				</tr>
			</thead>

			<tbody>
				<c:forEach var="utilisateur" items="${listUtil}">
					<tr>
						<td>${utilisateur.noUtilisateur}</td>
						<td>${utilisateur.pseudo}</td>
						<td>${utilisateur.nom}</td>
						<td>${utilisateur.prenom}</td>
						<td>${utilisateur.email}</td>
						<td>${utilisateur.telephone}</td>
						<td>${utilisateur.rue}, </br>${utilisateur.code_postal},
							${utilisateur.ville}</td>
						<td>${utilisateur.credit}</td>
						<td>${utilisateur.administrateur}</td>
						<td>
							<div class="d-flex justify-content-between">


								<form class="mx-4" method="get"
									action="<%=request.getContextPath()%>/admin/disableUser/${utilisateur.noUtilisateur}"
									id="disable-user-form-${utilisateur.noUtilisateur}">
									<div class="form-check form-switch">
										<input class="form-check-input" type="checkbox" name="actif"
											id="actif-${utilisateur.pseudo}"
											${utilisateur.actif ? "checked" : ""}
											onchange="disableUser(this, ${utilisateur.noUtilisateur})">
										<label class="form-check-label"
											for="actif-${utilisateur.pseudo}">
											${utilisateur.actif ? "Actif" : "Inactif"} </label>
									</div>
								</form>

								<a class="btn btn-danger"
									onclick="return confirm('Êtes-vous sûr de vouloir supprimer ce compte ?');"
									href="<%=request.getContextPath()%>/admin/deleteUser/${utilisateur.noUtilisateur}"><span
									class="bi bi-trash"></span></a>
							</div>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

	<%@ include file="../WEB-INF/fragments/importjs.html"%>

</body>
</html>
