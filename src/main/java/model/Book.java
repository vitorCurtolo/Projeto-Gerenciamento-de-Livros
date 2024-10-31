package model;

public class Book {


	public Book() {
	}

	public Book(String name, String autor, int nmr_paginas, int isbn, String capa) {
		super();
		this.name = name;
		this.autor = autor;
		this.nmrPaginas = nmr_paginas;
		this.isbn = isbn;
		this.capa = capa;
	}
	
	public Book(int id, String name, String autor, int nmr_paginas, int isbn, String capa) {
		super();
		this.id = id;
		this.name = name;
		this.autor = autor;
		this.nmrPaginas = nmr_paginas;
		this.isbn = isbn;
		this.capa = capa;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public int getIsbn() {
		return isbn;
	}

	public void setIsbn(int isbn) {
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

	public int id;
	public String name;
	public String autor;
	public int nmrPaginas;
	public int isbn;
	public boolean isDeleted;
	public String capa;


}
