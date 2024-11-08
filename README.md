## Manual de execução
* O script SQL se encontra em src/main/sql/RUN_DATABASE.sql.
* Na classe DatabaseConnection é necessário alterar usuário e senha do banco de dados.
* Tomcat vs 10.1
* Dynamic web module version 6.0
  
## Documentação Técnica: Sistema de Gerenciamento de Livros

### 1. Introdução
* **Objetivo:** O sistema tem como objetivo principal gerenciar o acervo de uma biblioteca, permitindo o cadastro, edição, exclusão e pesquisa de livros.
* **Público-alvo:** A ferramenta foi desenvolvida para ser utilizada por funcionários de bibliotecas, com um nível de conhecimento técnico variado.
* **Escopo:** Este documento técnico detalha a arquitetura do sistema, as funcionalidades implementadas, as tecnologias utilizadas e os desafios enfrentados durante o desenvolvimento.

### 2. Arquitetura do Sistema
O sistema foi desenvolvido utilizando uma arquitetura MVC (Model-View-Controller).

* **Model:** Representa a camada de dados, composta por classes que mapeiam as entidades do banco de dados (Livro).
* **View:** Responsável pela interface do usuário, construída utilizando JSP (JavaServer Pages).
* **Controller:** Atua como intermediário entre a view e o model, processando as requisições do usuário e atualizando o modelo. Servlets foram utilizados como controladores.

**Tecnologias:**

* Backend: Java
* Servidor Web: Tomcat
* Banco de dados: MySQL
* Tecnologia Web: JSP
* Conexão com banco de dados: JDBC

### 3. Funcionamento do Sistema
**Fluxo principal:**

* O usuário acessa a aplicação e é apresentado com uma lista de livros cadastrados.
* O usuário pode filtrar a lista por nome, autor ou ISBN.
* Para cadastrar um novo livro, o usuário clica no botão "Adicionar Livro" e preenche o formulário.
* Para editar um livro, o usuário clica no ícone de edição e realiza as alterações necessárias.
* Para excluir um livro, o usuário clica no ícone de exclusão e confirma a operação.

**Funcionalidades detalhadas:**

* **Tela inicial:** A tela inicial apresenta os livros cadastrados em formato de lista ou card, dependendo da preferência do usuário. A pesquisa é realizada em tempo real, filtrando os resultados à medida que o usuário digita.
* **Cadastro de livros:** O formulário de cadastro solicita informações como título, autor, ISBN, ano de publicação e outras relevantes.
* **Edição de livros:** Permite a alteração de todos os campos do livro, exceto o ID.
* **Exclusão de livros:** Ao confirmar a exclusão, o livro é removido do banco de dados.

### 4. Dificuldades e Soluções
* **Usabilidade:** Para garantir uma boa experiência do usuário, foram utilizados componentes visuais intuitivos e mensagens de erro claras. Além disso, a interface foi projetada para ser responsiva, adaptando-se a diferentes tamanhos de tela.
* **Performance:** A performance da aplicação foi otimizada através da utilização de índices no banco de dados e da minificação de arquivos CSS e JavaScript.
* **Segurança:** Foram implementadas medidas de segurança para evitar injeção de SQL e outras vulnerabilidades comuns.

### 5. Code snippets
**Conexão com Banco de Dados:** Classe separada para garantir resposabilidade única.
```
public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/crud_db";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Carregar o driver do MySQL
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.err.println("Driver JDBC não encontrado.");
            e.printStackTrace();
            return null;
        } catch (SQLException e) {
            System.err.println("Erro ao conectar com o banco de dados.");
            e.printStackTrace();
            return null;
        }
    }
}
```

**Classe de SQL Queries:** Classe separada para garantir resposabilidade única. Campos statics e final.
```
public class DatabaseQueries {
	
	public static final String INSERT_BOOK_SQL = "INSERT INTO livros"
			+ "  (nome, autor, nmrPaginas, isbn, capa) VALUES " + " (?, ?, ?, ?, ?);";
	
	public static final String SELECT_BOOK_BY_ID = "select id,nome, autor, nmrPaginas, isbn, capa from livros where id =?";
	
	public static final String SELECT_ALL_BOOKS = "select * from livros where isDeleted = 1";
	
	public static final String DELETE_BOOK_SQL = "update livros set isDeleted = false  where id =?;";
	
	public static final String UPDATE_BOOK_SQL = "update livros set nome = ?,autor= ?, nmrPaginas =?, isbn =?, capa =?  where id =?;";
	
	public static final String SEARCH_BOOKS = "SELECT * FROM livros WHERE (nome LIKE ? OR autor LIKE ? OR isbn = ?) AND isDeleted = 1";


}

```

**Switch no método doGet:** Essa prática ajuda no redirecionamente dos métodos que são responsáveis por manipular cada path.
```
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

```

**Mecanismo de FeedBack utilizando HttpSession:** esse mecanismo foi inserido em todas as etapas no CRUD, exceto Read.
```
JAVA
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

JSP

	<c:if test="${not empty sessionScope.message}">
		<div id="successMessage" class="alert alert-success">${sessionScope.message}</div>
		<c:remove var="message" scope="session" />
	</c:if>

	<c:if test="${not empty sessionScope.error}">
		<div id="errorMessage" class="alert alert-danger">${sessionScope.error}</div>
		<c:remove var="error" scope="session" />
	</c:if>

```

**Mecanismo de paginação:** Limita a 5 elemento por página.
```
JAVA - Servlet
		private void listBook(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		int page = 1;
		int recordsPerPage = 5;

		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}

		int offset = (page - 1) * recordsPerPage;
		List<Book> listBook = bookDAO.selectBooksPaginated(offset, recordsPerPage);
		int noOfRecords = bookDAO.getTotalRecords();
		int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

		request.setAttribute("listBook", listBook);
		request.setAttribute("noOfPages", noOfPages);
		request.setAttribute("currentPage", page);

		RequestDispatcher dispatcher = request.getRequestDispatcher("book-list.jsp");
		dispatcher.forward(request, response);
	}

JAVA - DAO

	// Método para buscar livros paginados
	public List<Book> selectBooksPaginated(int offset, int limit) {
		List<Book> books = new ArrayList<>();

		try (PreparedStatement preparedStatement = this.connection.prepareStatement(DatabaseQueries.GET_BOOKS_PAGINATED)) {
			preparedStatement.setInt(1, limit);
			preparedStatement.setInt(2, offset);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String nome = rs.getString("nome");
				String autor = rs.getString("autor");
				int paginas = rs.getInt("nmrPaginas");
				Long isbn = rs.getLong("isbn");
				String capa = rs.getString("capa");
				books.add(new Book(id, nome, autor, paginas, isbn, capa));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}

		return books;
	}

	// Método para contar o número total de registros
	public int getTotalRecords() {
		int totalRecords = 0;

		try (PreparedStatement preparedStatement = this.connection.prepareStatement(DatabaseQueries.GET_TOTAL_RECORDS)) {
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				totalRecords = rs.getInt("count");
			}
		} catch (SQLException e) {
			printSQLException(e);
		}

		return totalRecords;
	}

JSP

		<nav aria-label="Page navigation">
			<ul class="pagination">
				<c:if test="${currentPage > 1}">
					<li class="page-item"><a class="page-link"
						href="?page=${currentPage - 1}">Anterior</a></li>
				</c:if>

				<c:forEach var="i" begin="1" end="${noOfPages}">
					<li class="page-item ${i == currentPage ? 'active' : ''}"><a
						class="page-link" href="?page=${i}">${i}</a></li>
				</c:forEach>

				<c:if test="${currentPage < noOfPages}">
					<li class="page-item"><a class="page-link"
						href="?page=${currentPage + 1}">Próxima</a></li>
				</c:if>
			</ul>
		</nav>

```

### 6. Considerações Finais
O sistema de gerenciamento de livros apresenta uma solução eficaz para o controle do acervo de uma biblioteca. Futuras melhorias podem incluir a implementação de um sistema de empréstimo, a integração com outros sistemas e a geração de relatórios personalizados.
Agradecimentos: Agradecemos a toda a equipe de desenvolvimento pela dedicação e profissionalismo.

### 7. Time de Desenvolvedores
* Murilo Magossi - CP 301830X
* Vitor Curtolo - CP 3019055
