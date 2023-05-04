 <header>
    <nav class="navbar sticky-top navbar-expand-lg bg-body-tertiary">
        <div class="container-fluid">
            <a class="navbar-brand" href="/ENI-Encheres/accueil">ENI-Encheres</a>
            <div class="d-flex ml-auto" role="search">
	            <c:choose>
	    		<c:when test="${connected!=null}">
	    			<h4 class="mb-0">Bonjour ${connected.pseudo} </h4>
	    			<a class="btn btn-outline-dark" href="deconnexion">Se déconnecter</a>
	    		</c:when>
		        <c:otherwise>
		        	<a class="btn btn-outline-dark"  href="connexion">S'inscrire - Se connecter</a>
		        </c:otherwise>
            	</c:choose> 
            </div>
        </div>
    </nav>
</header>

  <c:choose>
  		<c:when test="${message!=null}">
  		<div class="container mt-5">
  			 <div class="alert alert-danger" role="alert">
			<%=request.getAttribute("message")%>
			</div>
		</div>
  		</c:when> 
    </c:choose>