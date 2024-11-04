package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import dao.BookDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Book;

//TODO: Arrumar nomes da tabela, tá um mix de ingles com pt. 
//TODO: implementar avisos para os métodos update/delete (exemplo no método insertBook) MURILO
//TODO: Dar um truncate na tabela livros e fazer insert corretos; VITOR
//TODO: tentar arrumar a visualização dos card. CSS  MURILO
//TODO: Criar Script SQL e colocar na pasta e github VITOR
//TODO: Criar documentação do projeto. Explicar funcionalidades etc. MURILO/VITOR
//TODO: Criar sistema de paginação na listagem dos dados MURILO
//TODO: Remover todos arquivos não utilizados. 
//TODO: REMOVER LINK DOS INDIANOS


@WebServlet("/")
public class BookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BookDAO bookDAO;

	public void init() {
		bookDAO = new BookDAO();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				insertBook(request, response);
				break;
			case "/delete":
				deleteBook(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				updateBook(request, response);
				break;
			case "/search":
				searchBook(request, response);
				break;
			default:
				listBook(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void searchBook(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		String procura = request.getParameter("query");
		List<Book> listBook = bookDAO.selectAllBooksFromSearch(procura);

		request.setAttribute("listBook", listBook);
		RequestDispatcher dispatcher = request.getRequestDispatcher("book-list.jsp");
		dispatcher.forward(request, response);

	}

	private void listBook(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Book> listBook = bookDAO.selectAllBooks();
		request.setAttribute("listBook", listBook);
		RequestDispatcher dispatcher = request.getRequestDispatcher("book-list.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("book-form.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Book existingBook = bookDAO.selectBook(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("book-form.jsp");
		request.setAttribute("book", existingBook);
		dispatcher.forward(request, response);

	}

	private void insertBook(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {

		try {
			String nome = request.getParameter("nome");
			String autor = request.getParameter("autor");
			int nmrPaginas = Integer.parseInt(request.getParameter("nmrPaginas"));
			Long isbn = Long.parseLong(request.getParameter("isbn"));
			String capa = request.getParameter("capa");
			Book newBook = new Book(nome, autor, nmrPaginas, isbn, capa);

			bookDAO.insertBook(newBook);

			// Armazenando mensagem de sucesso na sessão
			HttpSession session = request.getSession();
			session.setAttribute("message", "Livro inserido com sucesso!");
		} catch (Exception e) {
			// Armazenando mensagem de erro na sessão
			HttpSession session = request.getSession();
			session.setAttribute("error", "Erro ao inserir livro: " + e.getMessage());
		}

		response.sendRedirect("list");
	}

	private void updateBook(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String nome = request.getParameter("nome");
		String autor = request.getParameter("autor");
		int nmrPaginas = Integer.parseInt(request.getParameter("nmrPaginas"));
		Long isbn = Long.parseLong(request.getParameter("isbn"));
		String capa = request.getParameter("capa");

		Book book = new Book(id, nome, autor, nmrPaginas, isbn, capa);
		bookDAO.updateBook(book);
		response.sendRedirect("list");
	}

	private void deleteBook(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		bookDAO.deleteBook(id);
		response.sendRedirect("list");

	}

}