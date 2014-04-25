package fmat.jee.projectQuiz.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fmat.jee.projectQuiz.model.Usuario;
import fmat.jee.projectQuiz.model.servicio.ServicioUsuario;

/**
 * Servlet implementation class editarCuenta
 */
@WebServlet("/editarCuenta")
public class editarCuenta extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doIt(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doIt(request, response);
	}
	
	protected void doIt(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ServicioUsuario servicio = new ServicioUsuario();
		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/editarCuenta.jsp");
		HttpSession session = request.getSession(true);
		try{
		Usuario usuario = servicio.obtenerDatos(1);
		session.setAttribute("nombre", usuario.getNombre());
		session.setAttribute("pApellido", usuario.getPrimerApellido());
		session.setAttribute("sApellido", usuario.getSegundoApellido());
		session.setAttribute("nombreUsuario", usuario.getNombreUsuario());
		session.setAttribute("contrasena", usuario.getContrasena());
		session.setAttribute("correo", usuario.getCorreo());
		session.setAttribute("rol", usuario.getRol().getNombre());
		if(dispatcher!=null){
			dispatcher.forward(request, response);
		}
		}catch(Exception e){
			e.printStackTrace();
		}

	}

}
