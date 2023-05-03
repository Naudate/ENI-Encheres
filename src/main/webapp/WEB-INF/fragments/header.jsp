 <header>
    <nav class="navbar sticky-top navbar-expand-lg bg-body-tertiary">
        <div class="container-fluid">
            <a class="navbar-brand" href="/ENI-Encheres/accueil">ENI-Ench&egrave;res</a>
			<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbar" aria-controls="navbar" aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbar">
				<c:choose>
				<c:when test="${connected!=null}">
					<ul class="navbar-nav me-auto mb-2 mb-lg-0">
						<li class="nav-item">
							<a class="nav-link" href="#">Ench&egrave;res</a>
						</li>
						<li class="nav-item">
							<a class="nav-link" href="#">Vendre un article</a>
						</li>
						<li class="nav-item">
							<a class="nav-link" href="#">Mon profil</a>
						</li>
					</ul>
					<div class="navbar-item">
						<a class="nav-link" href="deconnexion">Se d&eacute;connecter</a>
					</div>
				</c:when>
				<c:otherwise>
					<div class="navbar-item ms-auto">
						<a class="nav-link"  href="connexion">S'inscrire - Se connecter</a>
					</div>
				</c:otherwise>
				</c:choose>
			</div>
        </div>
    </nav>
</header>