 <header>
    <nav class="navbar sticky-top navbar-expand-lg bg-body-tertiary">
        <div class="container-fluid">
            <a class="navbar-brand" href="/ENI-Encheres/accueil">ENI-Encheres</a>
            <div class="d-flex" role="search">
	            <c:choose>
	    		<c:when test="${connected!=null}">
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