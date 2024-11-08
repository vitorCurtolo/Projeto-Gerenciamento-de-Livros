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
<script>
	function validateForm() {
		let isValid = true;
		let nome = document.getElementById("nome");
		let autor = document.getElementById("autor");
		let isbn = document.getElementById("isbn");
		let nmrPaginas = document.getElementById("nmrPaginas");

		// Limpa as mensagens de erro
		document.getElementById("nomeError").textContent = "";
		document.getElementById("autorError").textContent = "";
		document.getElementById("isbnError").textContent = "";
		document.getElementById("nmrPaginasError").textContent = "";

		// Validação do campo Nome
		if (nome.value.trim() === "") {
			document.getElementById("nomeError").textContent = "Nome é obrigatório.";
			isValid = false;
		}

		// Validação do campo Autor
		if (autor.value.trim() === "") {
			document.getElementById("autorError").textContent = "Autor é obrigatório.";
			isValid = false;
		}

		// Validação do campo ISBN
		if (isbn.value.trim() === "" || isNaN(isbn.value) || isbn.value <= 0) {
			document.getElementById("isbnError").textContent = "ISBN deve ser um número positivo.";
			isValid = false;
		}

		// Validação do campo Número de Páginas
		if (nmrPaginas.value.trim() === "" || isNaN(nmrPaginas.value)
				|| nmrPaginas.value <= 0) {
			document.getElementById("nmrPaginasError").textContent = "Número de páginas deve ser um número positivo.";
			isValid = false;
		}

		return isValid;
	}
</script>

<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>
<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: #3a415a">
			<div>
				<a href="./list" class="navbar-brand">Sistema de Gerenciamento
					de Livros</a>
			</div>
			<ul class="navbar-nav">
				<li class="nav-item"><a
					href="<%=request.getContextPath()%>/list" class="nav-link">Livros</a></li>
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#" id="categoryDropdown"
					role="button" data-toggle="dropdown" aria-haspopup="true"
					aria-expanded="false"> Categorias </a>
					<div class="dropdown-menu" aria-labelledby="categoryDropdown">
						<!-- Add each category as a link with URL parameter for the category name -->
						<a class="dropdown-item"
							href="<%=request.getContextPath()%>/list?categoria=ficcao">Ficção</a>
						<a class="dropdown-item"
							href="<%=request.getContextPath()%>/list?categoria=aventura">Aventura</a>
						<a class="dropdown-item"
							href="<%=request.getContextPath()%>/list?categoria=romance">Romance</a>
						<a class="dropdown-item"
							href="<%=request.getContextPath()%>/list?categoria=TI">TI</a>
						<!-- Add more categories as needed -->
					</div></li>
			</ul>
		</nav>
	</header>

	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<form action="${book != null ? 'update' : 'insert'}" method="post"
					onsubmit="return validateForm();">

					<caption>
						<h2>
							<c:choose>
								<c:when test="${book != null}">Editar Livro</c:when>
								<c:otherwise>Adicionar Novo Livro</c:otherwise>
							</c:choose>
						</h2>
					</caption>

					<c:if test="${book != null}">
						<input type="hidden" name="id" value="<c:out value='${book.id}'/>" />
					</c:if>

					<fieldset class="form-group">
						<label>Nome</label> <input type="text" id="nome" name="nome"
							value="<c:out value='${book.nome}'/>" class="form-control">
						<small id="nomeError" class="text-danger"></small>
					</fieldset>

					<fieldset class="form-group">
						<label>Autor</label> <input type="text" id="autor" name="autor"
							value="<c:out value='${book.autor}'/>" class="form-control">
						<small id="autorError" class="text-danger"></small>
					</fieldset>

					<fieldset class="form-group">
						<label>ISBN</label> <input type="number" id="isbn" name="isbn"
							value="<c:out value='${book.isbn}'/>" class="form-control">
						<small id="isbnError" class="text-danger"></small>
					</fieldset>

					<fieldset class="form-group">
						<label>Páginas</label> <input type="number" id="nmrPaginas"
							name="nmrPaginas" value="<c:out value='${book.nmrPaginas}'/>"
							class="form-control"> <small id="nmrPaginasError"
							class="text-danger"></small>
					</fieldset>

					<fieldset class="form-group">
						<label>Categoria</label> <select id="categoria" name="categoria"
							class="form-control">
							<option value="TI"
								<c:if test="${book != null && book.categoria == 'TI'}">selected</c:if>>TI</option>
							<option value="Aventura"
								<c:if test="${book != null && book.categoria == 'Aventura'}">selected</c:if>>Aventura</option>
							<option value="Ficcao"
								<c:if test="${book != null && book.categoria == 'Ficcao'}">selected</c:if>>Ficção</option>
							<option value="Romance"
								<c:if test="${book != null && book.categoria == 'Romance'}">selected</c:if>>Romance</option>
						</select>
					</fieldset>

					<fieldset class="form-group">
						<label>Capa</label> <input type="text" id="capa" name="capa"
							value="<c:out value='${book.capa}'/>" class="form-control">
					</fieldset>

					<button type="submit" class="btn btn-success">Salvar</button>
				</form>
			</div>
		</div>
	</div>

</body>
</html>
