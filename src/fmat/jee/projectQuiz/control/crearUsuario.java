package fmat.jee.projectQuiz.control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fmat.jee.projectQuiz.model.Rol;
import fmat.jee.projectQuiz.model.Usuario;
import fmat.jee.projectQuiz.model.dao.DaoRol;
import fmat.jee.projectQuiz.model.servicio.ServicioRol;
import fmat.jee.projectQuiz.model.servicio.ServicioUsuario;

/**
 * Servlet implementation class crearUsuario
 */
@WebServlet("/crearUsuario")
public class crearUsuario extends HttpServlet {
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
		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/index.jsp");
		
		String nombre = request.getParameter("nombre");
		String pApellido = request.getParameter("pApellido");
		String sApellido = request.getParameter("sApellido");
		String nombreUsuario = request.getParameter("nombreUsuario");
		String contrasenia = request.getParameter("contrasenia");
		String correo = request.getParameter("correo");
		String tipoUsuario = request.getParameter("tipoUsuario");
		ServicioRol servicioRol = new ServicioRol();
		Rol nuevoRol = servicioRol.obtenerRolPorId(Integer.parseInt(tipoUsuario));
		
		Usuario nuevoUsuario = new Usuario(nombre,pApellido,sApellido,nombreUsuario,contrasenia,correo,nuevoRol);
		ServicioUsuario servicio = new ServicioUsuario();
		if(servicio.crearUsuario(nuevoUsuario)){
			if(dispatcher!=null){
				dispatcher.forward(request, response);
			}
		}else{
			RequestDispatcher dispatcherMal = request.getServletContext().getRequestDispatcher("/crearUsuario.jsp");
			if(dispatcherMal!=null){
				dispatcherMal.forward(request, response);
			}
		}
	}
	

}
