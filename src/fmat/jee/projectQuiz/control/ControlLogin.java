package fmat.jee.projectQuiz.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fmat.jee.projectQuiz.model.servicio.ServicioUsuario;

/**
 * Servlet implementation class ControlLogin
 */
@WebServlet("/ControlLogin")
public class ControlLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControlLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		validarUsuario(request, response);
	}

	protected void validarUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		String usuario = request.getParameter("usuario");
		String contrasenia = request.getParameter("contrasenia");	
		
		ServicioUsuario servicio = new ServicioUsuario();
		boolean respuesta = false;
		respuesta = servicio.validarUsuario(usuario, contrasenia);
		
		if(respuesta){		
			response.sendRedirect("firstPage.jsp");
		}else{
			request.setAttribute("error", true);
			RequestDispatcher dispatcher = 	request.getServletContext().getRequestDispatcher("/index.jsp");
			dispatcher.forward(request, response);	
		}
	}
}
