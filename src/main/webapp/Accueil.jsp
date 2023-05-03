<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@page import="java.util.List" %>
<%@page import="bo.EnchereArticle" %>
<%
List<EnchereArticle> listeEA = (List<EnchereArticle>)request.getAttribute("listeEnchereArticle");
%> 
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="./WEB-INF/fragments/importhead.html" %>
        <title>Accueil</title>
    </head>
    <body>
        <%@ include file="./WEB-INF/fragments/header.jsp" %>
        <div class="container">
            <div class="row">
                <div class="col-12 text-center">
                    <h2>Liste des ench&egrave;res</h2>
                    <h2>${listeEA}</h2>
                </div>
                <div class="col-12">
                    <div class="accordion" id="accordionFiltres">
                        <div class="accordion-item">
                            <h2 class="accordion-header">
                                <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                                Filtres
                                </button>
                            </h2>
                            <div id="collapseOne" class="accordion-collapse collapse show" data-bs-parent="#accordionFiltres">
                                <div class="accordion-body">
                                    <form>
                                        <div>
                                            <select class="form-select" name="selectCategory" id="selectCategory" aria-label="Selection de cat�gories">
                                                <option selected disabled>Cat&eacute;gorie</option>
                                                <!--entrer les cat�gories en utilisant la database-->
                                            </select>
                                            <div class="mt-3">
                                                <input type="text" class="form-control" id="textArticle" placeholder="Rechercher article....">
                                            </div>
                                            <div class="d-grid mt-3">
                                                <button class="btn btn-primary" type="submit">Rechercher</button>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <c:choose>
                    <c:when test="${listeEA == NULL}">
						<c:forEach var="EA" items="${ListeEA}">
							<div class="col-3 mt-3">
			                    <a class="card text-decoration-none" href="#">
			                        <svg class="bd-placeholder-img card-img-top" width="100%" height="180" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: Image cap" preserveAspectRatio="xMidYMid slice" focusable="false"><title>Placeholder</title><rect width="100%" height="100%" fill="#868e96"/></svg>
			                        <!--<img src="..." class="card-img-top" alt="...">-->
			                        <div class="card-body">
			                            <div class="row">
			                                <div class="col-6">
			                                    <h5 class="card-title">${EA.nom_article}</h5>
			                                </div>
			                                <div class="col-6">
			                                    <div class="text-end">
			                                    	<c:if test="${EA.etat_vente == 'CR' }">
			                                    		<span class="card-text badge text-bg-success">Cr��</span>
			                                    	</c:if>
			                                    	<c:if test="${EA.etat_vente == 'EC' }">
			                                    		<span class="card-text badge text-bg-warning">En cours</span>
			                                    	</c:if>
			                                    	<c:if test="${EA.etat_vente == 'VD' }">
			                                    		<span class="card-text badge text-bg-secondary">Vendu</span>
			                                    	</c:if>
			                                    </div>
			                                </div>
			                            </div>
			                            <c:choose>
			                            	<c:when test="${EA.motant_enchere == NULL}">
			                            		<p class="card-text">Prix : ${EA.prix_initial }&euro;</p>
			                            	</c:when>
			                            	<c:otherwise>
			                            		<p class="card-text">Prix : ${EA.montant_enchere }&euro;</p>
			                            	</c:otherwise>
			                            </c:choose>
			                            <p class="card-text"><small class="text-body-secondary">Fin de l'ench&egrave;re : ${EA.date_fin_enchere}</small></p>
			                        </div>
			                    </a>
                			</div>
						</c:forEach>
                    </c:when>
                    <c:otherwise>
                    	<div class="col-12 text-center">
                    		<h4>Aucun ench&egrave;re</h4>
                		</div>
                    </c:otherwise>
                </c:choose>
                <div class="col-12 mt-5 d-flex justify-content-center">
                    <nav aria-label="Page navigation">
                        <ul class="pagination">
                            <li class="page-item"><a class="page-link" href="#">Previous</a></li>
                            <li class="page-item"><a class="page-link" href="#">1</a></li>
                            <li class="page-item"><a class="page-link" href="#">2</a></li>
                            <li class="page-item"><a class="page-link" href="#">3</a></li>
                            <li class="page-item"><a class="page-link" href="#">Next</a></li>
                        </ul>
                    </nav>
                </div>
            </div>
          </div>
        <%@ include file="./WEB-INF/fragments/importjs.html" %>
    </body>
</html>