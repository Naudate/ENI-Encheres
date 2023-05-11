<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="./WEB-INF/fragments/importhead.html"%>
<title>Mot de passe oublié</title>
</head>
<body>

	<%@ include file="./WEB-INF/fragments/header.jsp"%>

	<div class="container mt-5">
		<div class="row justify-content-center">
			<div class="col-md-6">
				<h1 class="mb-4">Demande nouveau mot de passe</h1>
				<form action="motDePasseOublie" method="post">
					<div class="form-group">
						<label for="email">Email :</label> <input type="email" id="email"
							name="email" class="form-control" required value="${email}">
					</div>
					<div class="d-grid mt-3 gap-2">
						<button type="submit" class="btn btn-primary">Envoyer la
							demande</button>
					</div>
				</form>
			</div>
			<c:choose>
				<c:when test="${url != null}">
					<div class="row justify-content-center mt-3">
						<a class="text-center" href="${url}">${url}</a>
					</div>
				</c:when>

			</c:choose>

		</div>
	</div>


	<%@ include file="./WEB-INF/fragments/importjs.html"%>
</body>
</html>