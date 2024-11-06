<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Book Management Application</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<link
	href="https://fonts.googleapis.com/icon?family=Material+Icons+Outlined"
	rel="stylesheet">

<script>
	function toggleView(view) {
		document.getElementById('listView').style.display = (view === 'list') ? 'block'
				: 'none';
		document.getElementById('cardView').style.display = (view === 'card') ? 'flex'
				: 'none';
	}
</script>
<style>
.card {
	width: 14rem;
	margin: 1rem;
}

.card img {
	width: 100%;
    height: 315px;
}

#cardView {
	flex-wrap: wrap; /* Permite que os cards quebrem linha */
	justify-content: center;
}
.card-body{
    display: flex;
    flex-direction: column;
    justify-content: space-around;
}
</style>
</head>
<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: #3a415a">
			<div>
				<a href="./list" class="navbar-brand"> Sistema de Gerenciamento
					de Livros</a>
			</div>
			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link">Livros</a></li>
			</ul>
		</nav>
	</header>

	<c:if test="${not empty sessionScope.message}">
		<div id="successMessage" class="alert alert-success">${sessionScope.message}</div>
		<c:remove var="message" scope="session" />
	</c:if>

	<c:if test="${not empty sessionScope.error}">
		<div id="errorMessage" class="alert alert-danger">${sessionScope.error}</div>
		<c:remove var="error" scope="session" />
	</c:if>

	<br>

	<div class="container">
		<div class="text-center">
			<h3>Livros Cadastrados</h3>
			<hr>
			<button class="btn btn-primary" onclick="toggleView('list')">Vizualizar
				em lista</button>
			<button class="btn btn-secondary" onclick="toggleView('card')">Vizualizar
				em blocos</button>
		</div>

		<div
			class="card-buttons d-flex justify-content-between align-items-center mt-4">
			<!-- Botão à esquerda -->
			<a href="<%=request.getContextPath()%>/new" class="btn btn-success">Adicionar
				Livro</a>

			<!-- Barra de pesquisa -->
			<form action="<%=request.getContextPath()%>/search" method="GET"
				class="form-inline">
				<input type="text" name="query" class="form-control mr-2"
					placeholder="Nome, Autor e ISBN">
				<button type="submit" class="btn btn-primary">Pesquisar</button>
			</form>
		</div>

		<br>

		<!-- Lista de Visualização -->
		<div id="listView">
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>ID</th>
						<th>nome</th>
						<th>Autor</th>
						<th>Páginas</th>
						<th>ISBN</th>
						<th>Capa</th>
						<th>Ações</th>
						<th>Ações</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="book" items="${listBook}">
						<tr>
							<td><c:out value="${book.id}" /></td>
							<td><c:out value="${book.nome}" /></td>
							<td><c:out value="${book.autor}" /></td>
							<td><c:out value="${book.nmrPaginas}" /></td>
							<td><c:out value="${book.isbn}" /></td>
							<td><img src="<c:out value='${book.capa}' />"
								alt="Capa do Livro" width="60" /></td>
							<td><a href="edit?id=<c:out value='${book.id}' />"> <span
									class="material-icons-outlined">edit</span>
							</a></td>
							<td><a href="delete?id=<c:out value='${book.id}' />"
								onclick="return confirm('Tem certeza que deseja deletar este livro?');">
									<span class="material-icons-outlined">delete</span>
							</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>

		<!-- Visualização em Cartão -->
		<div id="cardView" style="display: none;">
			<c:forEach var="book" items="${listBook}">
				<div class="card">
					<img src="<c:out value='${book.capa}' />" class="card-img-top"
						alt="Capa do Livro">
					<div class="card-body">
						<h5 class="card-title">
							<c:out value="${book.nome}" />
						</h5>
						<p class="card-text">
							<strong>Autor:</strong>
							<c:out value="${book.autor}" />
							<br> <strong>Páginas:</strong>
							<c:out value="${book.nmrPaginas}" />
							<br> <strong>ISBN:</strong>
							<c:out value="${book.isbn}" />
						</p>
						<div class="buttons_card">
							<a href="edit?id=<c:out value='${book.id}' />"
								class="btn btn-primary">Edit</a> <a
								href="delete?id=<c:out value='${book.id}' />"
								class="btn btn-danger">Delete</a>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>

	</div>


	<script>
		// Função para ocultar mensagens após alguns segundos
		function hideMessages() {
			var successMessage = document.getElementById('successMessage');
			var errorMessage = document.getElementById('errorMessage');

			if (successMessage) {
				setTimeout(function() {
					successMessage.style.display = 'none';
				}, 5000); // Oculta após 5 segundos
			}

			if (errorMessage) {
				setTimeout(function() {
					errorMessage.style.display = 'none';
				}, 5000); // Oculta após 5 segundos
			}
		}

		// Chama a função após o carregamento da página
		window.onload = hideMessages;
	</script>

</body>
</html>
