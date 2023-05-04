
<header>
	<nav class="navbar sticky-top navbar-expand-lg bg-body-tertiary">
		<div class="container-fluid">
			<a class="navbar-brand" href="/ENI-Encheres/accueil">ENI-Ench&egrave;res</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbar"
				aria-controls="navbar" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbar">
				<c:choose>
					<c:when test="${connected!=null}">
						<ul class="navbar-nav me-auto mb-2 mb-lg-0">
							<li class="nav-item"><a class="nav-link" href="#">Ench&egrave;res</a>
							</li>
							<li class="nav-item"><a class="nav-link" href="#">Vendre
									un article</a></li>
							<li class="nav-item"><a class="nav-link" href="#">Mon
									profil</a></li>
						</ul>
						<div class="navbar-item">
							<a class="nav-link" href="deconnexion">Se d&eacute;connecter</a>
						</div>
					</c:when>
					<c:otherwise>
						<div class="navbar-item ms-auto">
							<a class="nav-link" href="connexion">S'inscrire - Se
								connecter</a>
						</div>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</nav>
</header>

<c:choose>
	<c:when test="${messageError!=null}">
		<div class="container mt-5">
			<div
				class="alert alert-danger alert-dismissible fade show d-flex align-items-center"
				role="alert">
				<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24"
					fill="currentColor"
					class="bi bi-exclamation-triangle-fill flex-shrink-0 me-2"
					viewBox="0 0 16 16" role="img" aria-label="Warning:">
    <path
						d="M8.982 1.566a1.13 1.13 0 0 0-1.96 0L.165 13.233c-.457.778.091 1.767.98 1.767h13.713c.889 0 1.438-.99.98-1.767L8.982 1.566zM8 5c.535 0 .954.462.9.995l-.35 3.507a.552.552 0 0 1-1.1 0L7.1 5.995A.905.905 0 0 1 8 5zm.002 6a1 1 0 1 1 0 2 1 1 0 0 1 0-2z" />
  </svg>
				<div><%=request.getAttribute("messageError")%></div>
				<button type="button" class="btn-close" data-bs-dismiss="alert"
					aria-label="Close"></button>
			</div>
		</div>
	</c:when>
</c:choose>

<c:choose>
	<c:when test="${messageSuccess!=null}">
		<div class="container mt-5">
			<div
				class="alert alert-success alert-dismissible fade show d-flex align-items-center"
				role="alert">
				<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24"
					fill="currentColor"
					class="bi bi-check-circle-fill flex-shrink-0 me-2"
					viewBox="0 0 16 16">
  <path
						d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0zm-3.97-3.03a.75.75 0 0 0-1.08.022L7.477 9.417 5.384 7.323a.75.75 0 0 0-1.06 1.06L6.97 11.03a.75.75 0 0 0 1.079-.02l3.992-4.99a.75.75 0 0 0-.01-1.05z" />
</svg>
				<div><%=request.getAttribute("messageSuccess")%></div>
				<button type="button" class="btn-close" data-bs-dismiss="alert"
					aria-label="Close"></button>
			</div>
		</div>
	</c:when>
</c:choose>