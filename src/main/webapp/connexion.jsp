<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="./WEB-INF/fragments/importhead.html" %>
        <title>Connexion</title>
    </head>
    <body>
    
    	<%@ include file="./WEB-INF/fragments/header.jsp" %>
       
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
                    <button type="submit" class="btn btn-primary">Se connecter</button>
                </form>
            </div>
        </div>
    </div>
        <%@ include file="./WEB-INF/fragments/importjs.html" %>
    </body>
</html>