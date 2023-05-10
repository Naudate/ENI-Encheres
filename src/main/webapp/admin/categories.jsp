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
							<div class="row align-items-center">
								<form class="align-items-center"
									action="<%=request.getContextPath()%>/admin/updateCategorie/${categorie.noCategorie}"
									method="post">
									<div class="col-12 p-0 m-0">
										<input type="text" class="form-control card-title"
											name="categorie-${categorie.noCategorie}"
											aria-label="${categorie.libelle}"
											aria-describedby="${categorie.libelle}"
											value="${categorie.libelle}"
											placeholder="${categorie.libelle}" required>
									</div>
									<div
										class="row d-flex ">
										<div class="col-6 ">
											<div class="text-end ">
												<button class="btn btn-primary h-100 w-auto align-self-center" type="submit"
													id="submit">
													<i class="bi bi-brush fs-6"></i>
												</button>
											</div>
										</div>
										<div class="col-6 ">
											<div class="">
												<a
													href="<%=request.getContextPath()%>/admin/deleteCategorie/${categorie.noCategorie}"
													class="btn btn-danger h-100 w-auto align-self-center"
													onclick="return confirm('Êtes-vous sûr de vouloir supprimer cette catégorie ? Cela va supprimer tout les articles liés à la catégorie !');">
													<i class="bi bi-x fs-6"></i>
												</a>
											</div>
										</div>
									</div>
								</form>

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