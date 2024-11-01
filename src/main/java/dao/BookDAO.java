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
import util.DatabaseQueries;

public class BookDAO {

	
	
	private final Connection connection;

	public BookDAO() {
		this.connection = DatabaseConnection.getConnection();
	}

	public void insertBook(Book book) throws SQLException {
		try (PreparedStatement preparedStatement = this.connection.prepareStatement(DatabaseQueries.INSERT_BOOK_SQL)) {

			preparedStatement.setString(1, book.getName());
			preparedStatement.setString(2, book.getAutor());
			preparedStatement.setInt(3, book.getNmrPaginas());
			preparedStatement.setDouble(4, book.getIsbn());
			preparedStatement.setString(5, book.getCapa());

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public Book selectBook(int id) {

		Book book = null;

		try (PreparedStatement preparedStatement = this.connection.prepareStatement(DatabaseQueries.SELECT_BOOK_BY_ID);) {

			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String name = rs.getString("name");
				String autor = rs.getString("autor");
				int paginas = rs.getInt("nmrPaginas");
				Double isbn = rs.getDouble("isbn");
				String capa = rs.getString("capa");
				book = new Book(id, name, autor, paginas, isbn, capa);
			}

		} catch (SQLException e) {
			printSQLException(e);
		}

		return book;
	}

	public List<Book> selectAllBooks() {

		List<Book> books = new ArrayList<>();

		try (PreparedStatement preparedStatement = this.connection.prepareStatement(DatabaseQueries.SELECT_ALL_BOOKS);) {
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String autor = rs.getString("autor");
				int paginas = rs.getInt("nmrPaginas");
				Double isbn = rs.getDouble("isbn");
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

		try (PreparedStatement preparedStatement = this.connection.prepareStatement(DatabaseQueries.DELETE_BOOK_SQL);) {
			preparedStatement.setInt(1, id);
			rowDeleted = preparedStatement.executeUpdate() > 0;
		}

		return rowDeleted;
	}

	public boolean updateBook(Book book) throws SQLException {

		boolean rowUpdated;

		try (PreparedStatement preparedStatement = this.connection.prepareStatement(DatabaseQueries.UPDATE_BOOK_SQL);) {
			preparedStatement.setString(1, book.getName());
			preparedStatement.setString(2, book.getAutor());
			preparedStatement.setInt(3, book.getNmrPaginas());
			preparedStatement.setDouble(4, book.getIsbn());
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

	public List<Book> selectAllBooksFromSearch(String procura) {

		List<Book> books = new ArrayList<>();

		try (PreparedStatement preparedStatement = this.connection.prepareStatement(DatabaseQueries.SEARCH_BOOKS);) {

			preparedStatement.setString(1, "%" + procura + "%");
			preparedStatement.setString(2, "%" + procura + "%");

			if (procura.matches("\\d+")) {
				preparedStatement.setDouble(3, Double.parseDouble(procura));
			} else {
				preparedStatement.setDouble(3, 0);
			}

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String autor = rs.getString("autor");
				int paginas = rs.getInt("nmrPaginas");
				Double isbn = rs.getDouble("isbn");
				String capa = rs.getString("capa");
				books.add(new Book(id, name, autor, paginas, isbn, capa));
			}
			
			System.out.println(preparedStatement);
			System.out.println(rs);

		} catch (SQLException e) {
			printSQLException(e);
		}

		return books;
	}

}
