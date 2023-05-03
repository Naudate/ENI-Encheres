<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="./WEB-INF/fragments/importhead.html" %>
        <title>Accueil</title>
    </head>
    <body>
        <header>
            <nav class="navbar sticky-top navbar-expand-lg bg-body-tertiary">
                <div class="container-fluid">
                    <a class="navbar-brand" href="/ENI-Encheres/accueil">ENI-Encheres</a>
                    <form class="d-flex" role="search">
                      <button class="btn btn-outline-dark" type="submit">S'inscrire - Se connecter</button>
                    </form>
                </div>
            </nav>
        </header>
        <div class="container">
            <div class="row">
                <div class="col-12 text-center">
                    <h2>Liste des ench&egrave;res</h2>
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
                                            <select class="form-select" name="selectCategory" id="selectCategory" aria-label="Selection de catégories">
                                                <option selected disabled>Cat&eacute;gorie</option>
                                                <!--entrer les catégories en utilisant la database-->
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
                <div class="col-3 mt-3">
                    <a class="card text-decoration-none" href="#">
                        <svg class="bd-placeholder-img card-img-top" width="100%" height="180" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: Image cap" preserveAspectRatio="xMidYMid slice" focusable="false"><title>Placeholder</title><rect width="100%" height="100%" fill="#868e96"/></svg>
                        <!--<img src="..." class="card-img-top" alt="...">-->
                        <div class="card-body">
                            <div class="row">
                                <div class="col-6">
                                    <h5 class="card-title">PC Gamer</h5>
                                </div>
                                <div class="col-6">
                                    <div class="text-end">
                                        <span class="card-text badge text-bg-warning">En cours</span>
                                    </div>
                                </div>
                            </div>
                            <p class="card-text">Prix : 210&euro;</p>
                            <p class="card-text"><small class="text-body-secondary">Fin de l'ench&egrave;re : 03/05/2023</small></p>
                        </div>
                    </a>
                </div>
                <div class="col-3 mt-3">
                    <a class="card text-decoration-none" href="#">
                        <svg class="bd-placeholder-img card-img-top" width="100%" height="180" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: Image cap" preserveAspectRatio="xMidYMid slice" focusable="false"><title>Placeholder</title><rect width="100%" height="100%" fill="#868e96"/></svg>
                        <!--<img src="..." class="card-img-top" alt="...">-->
                        <div class="card-body">
                            <div class="row">
                                <div class="col-6">
                                    <h5 class="card-title">PC Gamer</h5>
                                </div>
                                <div class="col-6">
                                    <div class="text-end">
                                        <span class="card-text badge text-bg-danger">Termin&eacute;</span>
                                    </div>
                                </div>
                            </div>
                            <p class="card-text">Prix : 210&euro;</p>
                            <p class="card-text"><small class="text-body-secondary">Fin de l'ench&egrave;re : 01/05/2023</small></p>
                        </div>
                    </a>
                </div>
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