<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../WEB-INF/fragments/importhead.html"%>
<title>Gestion des catégories</title>
</head>
<body>

	<%@ include file="../WEB-INF/fragments/header.jsp"%>

	<div class="container">
		<h1 class="text-center m-5">Liste des catégories</h1>
		<div class="row">
			<c:forEach items="${listCategorie}" var="categorie">
				<div class="col-md-3">
					<div class="card mb-4">
						<div class="card-body">
							<div class="row">
								<div class="col-6">
									<h6 class="card-title">${categorie.libelle}</h6>
								</div>
								<div class="col-6">
									<div class="text-end">
										<a href="<%=request.getContextPath()%>/admin/deleteCategorie/${categorie.noCategorie}"
											class="card-text badge text-bg-danger"
											onclick="return confirm('Êtes-vous sûr de vouloir supprimer cette catégorie ? Cela va supprimer tout les articles liés à la catégorie !');">
											<i class="bi bi-x bi-4x"></i>
										</a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
		<div class="row justify-content-center">
			<form method="post"
				action="<%=request.getContextPath()%>/admin/categories">
				<div class="row justify-content-center">
					<div class="col-5 mt-3">
						<div class="input-group">
							<span class="input-group-text" id="categorie"><i
								class="bi bi-bookmark-plus"></i></span> <input type="text"
								class="form-control" name="categorie" aria-label="categorie"
								aria-describedby="categorie" placeholder="Catégorie" required>
						</div>
					</div>
				</div>

				<div class="row justify-content-center">
					<div class="col-3 d-grid mt-3">
						<input class="btn btn-info" type="submit"
							value="Ajouter Catégorie" id="submit">
					</div>
				</div>

			</form>
		</div>
	</div>

	<%@ include file="../WEB-INF/fragments/importjs.html"%>
</body>
</html>