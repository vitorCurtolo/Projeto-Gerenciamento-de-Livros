package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Book;
import util.DatabaseConnection;

public class BookDAO {

	// TODO: Criar arquivo com msgs de erros

	// TODO: colocar query em um arquivo separado
	private static final String INSERT_BOOK_SQL = "INSERT INTO livros" + "  (name, autor, nmrPaginas, isbn, capa) VALUES "
			+ " (?, ?, ?, ?, ?);";
	private static final String SELECT_BOOK_BY_ID = "select id,name, autor, nmrPaginas, isbn, capa from livros where id =?";
	private static final String SELECT_ALL_BOOKS = "select * from livros where isDeleted = 1";
	private static final String DELETE_BOOK_SQL = "update livros set isDeleted = false  where id =?;";
	private static final String UPDATE_BOOK_SQL = "update livros set name = ?,autor= ?, nmrPaginas =?, isbn =?, capa =?  where id =?;";

	private final Connection connection;

	public BookDAO() {
		this.connection = DatabaseConnection.getConnection();
	}

	public void insertBook(Book book) throws SQLException {
		try (PreparedStatement preparedStatement = this.connection.prepareStatement(INSERT_BOOK_SQL)) {

			preparedStatement.setString(1, book.getName());
			preparedStatement.setString(2, book.getAutor());
			preparedStatement.setInt(3, book.getNmrPaginas());
			preparedStatement.setInt(4, book.getIsbn());
			preparedStatement.setString(5, book.getCapa());

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public Book selectBook(int id) {

		Book book = null;

		try (PreparedStatement preparedStatement = this.connection.prepareStatement(SELECT_BOOK_BY_ID);) {

			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String name = rs.getString("name");
				String autor = rs.getString("autor");
				int paginas = rs.getInt("nmrPaginas");
				int isbn = rs.getInt("isbn");
				String capa = rs.getString("capa");
				book = new Book(id,  name, autor, paginas, isbn, capa);
			}
			
		} catch (SQLException e) {
			printSQLException(e);
		}
		
		return book;
	}

	public List<Book> selectAllBooks() {

		List<Book> books = new ArrayList<>();

		try (PreparedStatement preparedStatement = this.connection.prepareStatement(SELECT_ALL_BOOKS);) {
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String autor = rs.getString("autor");
				int paginas = rs.getInt("nmrPaginas");
				int isbn = rs.getInt("isbn");
				String capa = rs.getString("capa");
				books.add(new Book(id, name, autor, paginas, isbn, capa));
			}
			
		} catch (SQLException e) {
			printSQLException(e);
		}
		
		return books;
	}

	public boolean deleteBook(int id) throws SQLException {
		
		boolean rowDeleted;
		
		try (PreparedStatement preparedStatement = this.connection.prepareStatement(DELETE_BOOK_SQL);) {
			preparedStatement.setInt(1, id);
			rowDeleted = preparedStatement.executeUpdate() > 0;
		}
		
		return rowDeleted;
	}

	public boolean updateBook(Book book) throws SQLException {
		
		boolean rowUpdated;
		
		try (PreparedStatement preparedStatement = this.connection.prepareStatement(UPDATE_BOOK_SQL);) {
			preparedStatement.setString(1, book.getName());
			preparedStatement.setString(2, book.getAutor());
			preparedStatement.setInt(3, book.getNmrPaginas());
			preparedStatement.setInt(4, book.getIsbn());
			preparedStatement.setString(5, book.getCapa());
			preparedStatement.setInt(6, book.getId());

			rowUpdated = preparedStatement.executeUpdate() > 0;
		}
		
		return rowUpdated;
	}

	private void printSQLException(SQLException ex) {
		
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}

}
