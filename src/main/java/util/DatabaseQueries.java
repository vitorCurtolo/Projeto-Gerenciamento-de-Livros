package util;

public class DatabaseQueries {
	
	public static final String INSERT_BOOK_SQL = "INSERT INTO livros"
			+ "  (nome, autor, nmrPaginas, isbn, capa) VALUES " + " (?, ?, ?, ?, ?);";
	
	public static final String SELECT_BOOK_BY_ID = "select id,nome, autor, nmrPaginas, isbn, capa from livros where id =?";
	
	public static final String SELECT_ALL_BOOKS = "select * from livros where isDeleted = 1";
	
	public static final String DELETE_BOOK_SQL = "update livros set isDeleted = false  where id =?;";
	
	public static final String UPDATE_BOOK_SQL = "update livros set nome = ?,autor= ?, nmrPaginas =?, isbn =?, capa =?  where id =?;";
	
	public static final String SEARCH_BOOKS = "SELECT * FROM livros WHERE (nome LIKE ? OR autor LIKE ? OR isbn = ?) AND isDeleted = 1";
	
	public static final String GET_TOTAL_RECORDS = "SELECT COUNT(*) AS count FROM livros where isDeleted = 1";
	
	public static final String GET_BOOKS_PAGINATED = "SELECT * FROM livros where isDeleted = 1 ORDER BY id LIMIT ? OFFSET ?";


}
