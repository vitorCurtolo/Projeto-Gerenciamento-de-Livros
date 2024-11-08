package model;

public class Book {
	
	public int id;
	public String nome;
	public String autor;
	public int nmrPaginas;
	public Long isbn;
	public boolean isDeleted;
	public String capa;
	public String categoria;


	public Book() {
	}

	public Book(String nome, String autor, int nmr_paginas, Long isbn, String capa, String categoria) {
		super();
		this.nome = nome;
		this.autor = autor;
		this.nmrPaginas = nmr_paginas;
		this.isbn = isbn;
		this.capa = capa;
		this.categoria = categoria;
	}
	
	public Book(int id, String nome, String autor, int nmr_paginas, Long isbn, String capa, String categoria) {
		super();
		this.id = id;
		this.nome = nome;
		this.autor = autor;
		this.nmrPaginas = nmr_paginas;
		this.isbn = isbn;
		this.capa = capa;
		this.categoria = categoria;
	}
	
	

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public int getNmrPaginas() {
		return nmrPaginas;
	}

	public void setNmrPaginas(int nmrPaginas) {
		this.nmrPaginas = nmrPaginas;
	}

	public Long getIsbn() {
		return isbn;
	}

	public void setIsbn(Long isbn) {
		this.isbn = isbn;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getCapa() {
		return capa;
	}

	public void setCapa(String capa) {
		this.capa = capa;
	}


}
