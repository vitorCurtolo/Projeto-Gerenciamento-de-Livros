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
</head>
<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: tomato">
			<div>
				<a href="https://www.javaguides.net" class="navbar-brand"> Book
					Management App </a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link">Books</a></li>
			</ul>
		</nav>
	</header>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${book != null}">
					<form action="update" method="post">
				</c:if>
				<c:if test="${book == null}">
					<form action="insert" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${book != null}">
            			Edit Book
            		</c:if>
						<c:if test="${book == null}">
            			Add New Book
            		</c:if>
					</h2>
				</caption>

				<c:if test="${book != null}">
					<input type="hidden" name="id" value="<c:out value='${book.id}' />" />
				</c:if>

				<fieldset class="form-group">
					<label>Nome</label> <input type="text"
						value="<c:out value='${book.name}' />" class="form-control"
						name="name" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>Autor</label> <input type="text"
						value="<c:out value='${book.autor}' />" class="form-control"
						name="autor">
				</fieldset>

				<fieldset class="form-group">
					<label>ISBN</label> <input type="text"
						value="<c:out value='${book.isbn}' />" class="form-control"
						name="isbn">
				</fieldset>

				<fieldset class="form-group">
					<label>Páginas</label> <input type="text"
						value="<c:out value='${book.nmrPaginas}' />" class="form-control"
						name="nmrPaginas">
				</fieldset>

				<fieldset class="form-group">
					<label>Capa</label> <input type="text"
						value="<c:out value='${book.capa}' />" class="form-control"
						name="capa">
				</fieldset>



				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
