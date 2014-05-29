package fmat.jee.projectQuiz.control;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fmat.jee.projectQuiz.model.dominio.Contacto;
import fmat.jee.projectQuiz.model.dominio.Rol;
import fmat.jee.projectQuiz.model.dominio.Usuario;
import fmat.jee.projectQuiz.model.servicio.ServicioContactos;
import fmat.jee.projectQuiz.model.servicio.ServicioRol;
import fmat.jee.projectQuiz.model.servicio.ServicioUsuario;

/**
 * Servlet implementation class ControlUsuario
 */
@WebServlet("/ControlUsuario")
public class ControlUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControlUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }

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
		String control = request.getParameter("tipo");
		String jsp = "/pageHome.jsp";
	
		
		switch(control){
		case "Agregar":
			agregar(request,response);
			break;
		case "Editar":
			editar(request,response);
			break;
		case "Actualizar":
			actualizar(request,response);
			break;
		case "Eliminar":
			eliminar(request,response);
			break;
		default:
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/index.jsp");
			dispatcher.forward(request, response);
			break;
		
		}
	}
	
	protected void agregar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
	
	protected void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ServicioUsuario servicio = new ServicioUsuario();
		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/editarCuenta.jsp");
		//HttpSession session = request.getSession(true);
		try{
		/*int id = Integer.parseInt(request.getParameter("id"));
		Usuario usuario = servicio.obtenerDatos(id);
		session.setAttribute("nombre", usuario.getNombre());
		session.setAttribute("pApellido", usuario.getPrimerApellido());
		session.setAttribute("sApellido", usuario.getSegundoApellido());
		session.setAttribute("nombreUsuario", usuario.getNombreUsuario());
		session.setAttribute("contrasena", usuario.getContrasena());
		session.setAttribute("correo", usuario.getCorreo());
		session.setAttribute("rol", usuario.getRol().getNombre());*/
		if(dispatcher!=null){
			dispatcher.forward(request, response);
		}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	protected void actualizar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/pageHome.jsp");
		
		String nombre = request.getParameter("nombre");
		String pApellido = request.getParameter("pApellido");
		String sApellido = request.getParameter("sApellido");
		String nombreUsuario = request.getParameter("nombreUsuario");
		String contrasenia = request.getParameter("contrasenia");
		String correo = request.getParameter("correo");
		String tipoUsuario = request.getParameter("tipoUsuario");
		ServicioRol servicioRol = new ServicioRol();
		Rol nuevoRol = servicioRol.obtenerRolPorId(Integer.parseInt(tipoUsuario));
		int id = Integer.parseInt(request.getParameter("id"));
		
		Usuario nuevoUsuario = new Usuario(id,nombre,pApellido,sApellido,nombreUsuario,contrasenia,correo,nuevoRol);
		ServicioUsuario servicio = new ServicioUsuario();
		if(servicio.actualizarUsuario(nuevoUsuario)){
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
	
	protected void eliminar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/index.jsp");
		
		boolean respuesta = false;
		ServicioUsuario servicio = new ServicioUsuario();
		respuesta =	servicio.eliminarUsuario(Integer.parseInt(request.getParameter("id")));
		if(respuesta){
			if(dispatcher!=null){
				dispatcher.forward(request, response);
			}
		}else{
			RequestDispatcher dispatcherMal = request.getServletContext().getRequestDispatcher("/pageHome.jsp");
			if(dispatcherMal!=null){
				dispatcherMal.forward(request, response);
			}
		}
	}
}
