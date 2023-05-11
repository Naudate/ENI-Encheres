<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="./WEB-INF/fragments/importhead.html"%>
<title>Réinitialiser le mot de passe</title>
</head>
<body>

	<%@ include file="./WEB-INF/fragments/header.jsp"%>

	<div class="container mt-5">
		<div class="row justify-content-center">
			<div class="col-md-6">
				<h1 class="mb-4">Rentrer votre nouveau mot de passe</h1>
				<form action="updatePassword" method="post">
					<div class="form-group">
						<label for="email">Mot de passe :</label> <input type="password" id="password"
							name="password" class="form-control" required>
					</div>
					<div class="d-grid mt-3 gap-2">
					<input type="hidden" value="${param.token}" name="token" required>
						<button type="submit" class="btn btn-primary">Réinitialiser le mot de passe</button>
					</div>
					<div>
					</div>
				</form>
			</div>
		</div>
	</div>


	<%@ include file="./WEB-INF/fragments/importjs.html"%>
</body>
</html>