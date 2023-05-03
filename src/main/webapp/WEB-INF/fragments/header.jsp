 <header>
    <nav class="navbar sticky-top navbar-expand-lg bg-body-tertiary">
        <div class="container-fluid">
            <a class="navbar-brand" href="/ENI-Encheres/accueil">ENI-Encheres</a>
            <form class="d-flex" role="search">
	            <c:choose>
	    		<c:when test="${connected!=null}">
	    			<a class="btn btn-outline-dark" href="deconnexion">Se déconnecter</a>
	    		</c:when>
		        <c:otherwise>
		        	<button class="btn btn-outline-dark" type="submit">S'inscrire - Se connecter</button>
		        </c:otherwise>
            	</c:choose>
            
              
            </form>
        </div>
    </nav>
</header>