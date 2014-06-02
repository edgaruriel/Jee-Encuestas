package fmat.jee.projectQuiz.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fmat.jee.projectQuiz.model.dominio.Administrador;
import fmat.jee.projectQuiz.model.dominio.Pregunta;
import fmat.jee.projectQuiz.model.dominio.Usuario;
import fmat.jee.projectQuiz.model.servicio.ServicioContactos;
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
		String control = request.getParameter("tipo");
		
		switch(control){
		case "LogIn":
			LogIn(request, response);
			break;
		case "LogOut":
			LogOut(request, response);
			break;
		default:
			paginaDefault(request,response);
			break;
		}
		
		
	}
	
	public void paginaDefault(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/index.jsp");
		dispatcher.forward(request, response);
	}

	protected boolean validarUsuario(String usuario, String contrasenia){
		
		boolean respuesta = false;
		ServicioUsuario servicioUsuario = new ServicioUsuario();
		
		respuesta = servicioUsuario.validarUsuario(usuario, contrasenia);
		
		return respuesta;
	}
	
	public void LogIn(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		response.setContentType("text/html");
		String usuario = request.getParameter("usuario");
		String contrasenia = request.getParameter("contrasenia");	
	
		boolean respuesta = validarUsuario(usuario, contrasenia);
		if(respuesta){
			ServicioUsuario servicioUsuario = new ServicioUsuario();
			ServicioContactos servicioContactos = new ServicioContactos();
			
			Usuario usuarioLogin =	servicioUsuario.obtenerUsuarioPorNombre(usuario);
			HttpSession session = request.getSession(true);
			if(usuarioLogin.getRol().getNombre().equals("CLIENTE")){
				usuarioLogin.setContactos(servicioContactos.obtenerContactos(usuarioLogin.getId()));
				RequestDispatcher dispatcher = 	request.getServletContext().getRequestDispatcher("/indexCliente.jsp");
				session.setAttribute("USUARIO", usuarioLogin);
				System.out.println(session.getAttribute("USUARIO"));
				session.setAttribute("preguntas", null);
				dispatcher.forward(request, response);
			}else if (usuarioLogin.getRol().getNombre().equals("ADMINISTRADOR")) {
				Administrador admin = crearAdmin(usuarioLogin);
				RequestDispatcher dispatcher = 	request.getServletContext().getRequestDispatcher("/indexAdmin.jsp");
				admin.setUsuarios(servicioUsuario.obtenerUsuarios());				
				session.setAttribute("ADMIN", admin);
				session.setAttribute("preguntas", null);
				dispatcher.forward(request, response);
			}
			
			}else{
				request.setAttribute("error", true);
				RequestDispatcher dispatcher = 	request.getServletContext().getRequestDispatcher("/index.jsp");
				dispatcher.forward(request, response);	
			}
		
	}
	
	private Administrador crearAdmin(Usuario usuario){
		Administrador admin = new Administrador();
		admin.setId(usuario.getId());
		admin.setNombre(usuario.getNombre());
		admin.setPrimerApellido(usuario.getPrimerApellido());
		admin.setSegundoApellido(usuario.getSegundoApellido());
		admin.setNombreUsuario(usuario.getNombreUsuario());
		admin.setContrasena(usuario.getContrasena());
		admin.setRol(usuario.getRol());
		admin.setCorreo(usuario.getCorreo());
		
		return admin;
	}
	
	private void logOut(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		HttpSession session = request.getSession(false);
		if((ArrayList<Pregunta>)session.getAttribute("preguntas")!=null){
			ArrayList<Pregunta> list = (ArrayList<Pregunta>)session.getAttribute("preguntas");
			
			Iterator<Pregunta> it = list.iterator();
			while (it.hasNext()) {
				it.next();
			    it.remove();
			}
		}
		
		session.invalidate();
		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/index.jsp");
		dispatcher.forward(request, response);
	}
	
	public void LogOut(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		HttpSession session = request.getSession(false);
		if(session.getAttribute("USUARIO")!=null){
			logOut(request, response);
		}else if(session.getAttribute("ADMIN")!=null){
			logOut(request, response);
		}else{
			RequestDispatcher dispatcherMal = request.getServletContext().getRequestDispatcher("/index.jsp");
			dispatcherMal.forward(request, response);
		}
	}
}
