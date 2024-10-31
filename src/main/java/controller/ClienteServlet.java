package controller;

import dao.ClienteDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Cliente;
import java.io.IOException;
import java.util.List;

@WebServlet("/primeirarota")
public class ClienteServlet extends HttpServlet {
	private ClienteDAO clienteDAO = new ClienteDAO();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<Cliente> clientes = clienteDAO.listarClientes();
		request.setAttribute("clientes", clientes);

		RequestDispatcher dispatcher = request.getRequestDispatcher("listaClientes.jsp");
		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Coleta os dados do formulário
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String telefone = request.getParameter("telefone");

		// Cria um novo cliente e adiciona ao banco de dados
		Cliente cliente = new Cliente(nome, email, telefone);
		clienteDAO.adicionarCliente(cliente);

		// Redireciona para atualizar a lista de clientes
		response.sendRedirect("clientes");
	}
}
