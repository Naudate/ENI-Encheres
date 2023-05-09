<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%
boolean remember = (boolean) request.getAttribute("remember");  
%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="./WEB-INF/fragments/importhead.html" %>
        <title>Connexion</title>
    </head>
    <body>
    
    	<%@ include file="./WEB-INF/fragments/header.jsp" %>
       
        <div class="container mt-5">       
	             
        <div class="row justify-content-center">
            <div class="col-md-6">
                <h1 class="mb-4">Connexion</h1>
                <form action="connexion" method="post">
                    <div class="form-group">
                        <label for="pseudo">Pseudo :</label>
                        <input type="text" id="pseudo" name="pseudo" class="form-control" value="${pseudo}" required>
                    </div>
                    <div class="form-group">
                        <label for="password">Mot de passe :</label>
                        <input type="password" id="password" name="password" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label for="remember">Se souvenir de moi :</label>
                        ${System.out.println(remember)}
                        <input type="checkbox" id="remember" name="remember" <%if(remember == true) {%>checked<%}%>>
                    </div>
                    <div class="d-grid mt-3 gap-2">
                        <button type="submit" class="btn btn-primary">Se connecter</button>
                        <a class="btn btn-primary" href="inscription">S'inscrire</a>
                    </div>
                </form>
            </div>
        </div>
    </div>
        <%@ include file="./WEB-INF/fragments/importjs.html" %>
    </body>
</html>