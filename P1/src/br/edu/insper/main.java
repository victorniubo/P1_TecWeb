package br.edu.insper;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class main
 */
@WebServlet("/main")
public class main extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public main() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		request.getRequestDispatcher("view/main.jsp").forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			DAO dao = new DAO();
			
			String action = request.getParameter("action");
			
			if (action.contentEquals("add")) {
				
			
				String tarefa = request.getParameter("tarefa");
				String data = request.getParameter("data");
				dao.adiciona(tarefa, data);
				RequestDispatcher  dispathcer = request.getRequestDispatcher("view/main.jsp");
				dispathcer.forward(request, response);
			}
			
			else if (action.contentEquals("del")) {
				
				Integer id = Integer.parseInt(request.getParameter("id"));
				
				dao.remove(id);
				
				RequestDispatcher  dispathcer = request.getRequestDispatcher("view/main.jsp");
				dispathcer.forward(request, response);
			}
			
			else if (action.contentEquals("alterar")) {
				
				String status = request.getParameter("status");
				Integer id = null;
				
				if (status.contentEquals("alterando")) {
					
					id = Integer.parseInt(request.getParameter("id"));
					//String texto = request.getParameter("texto");
					
					RequestDispatcher  dispathcer = request.getRequestDispatcher("view/alterar.jsp");
					dispathcer.forward(request, response);
				}
				
				else if (status.contentEquals("alterado")) {
					
					String novoTexto = request.getParameter("novoTexto");
					id = Integer.parseInt(request.getParameter("id"));
					
					Task tarefa = new Task();
					tarefa.setId(id);
					tarefa.setTarefa(novoTexto);
					
					dao.altera(tarefa);
					
					doGet(request, response);
				}
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
