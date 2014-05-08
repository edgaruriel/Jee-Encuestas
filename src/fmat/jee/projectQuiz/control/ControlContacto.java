package fmat.jee.projectQuiz.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fmat.jee.projectQuiz.model.Contacto;
import fmat.jee.projectQuiz.model.Usuario;
import fmat.jee.projectQuiz.model.servicio.ServicioContactos;

/**
 * Servlet implementation class ControlContacto
 */
@WebServlet("/ControlContacto")
public class ControlContacto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControlContacto() {
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
		doIt(request, response);
	}

	protected void doIt(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	
		String control = request.getParameter("tipo");
		String jsp = "/pageHome.jsp";
	
		
		switch(control){
		case "Agregar":
			agregar(request);
			jsp ="/contactos.jsp";
			break;
		case "Editar":
			editar(request);
			jsp ="/editarContacto.jsp";
			break;
		case "Actualizar":
			actualizar(request);
			jsp = "/contactos.jsp";
			break;
		case "Eliminar":
			eliminar(request);
			jsp = "/contactos.jsp";
			break;
		default:
			jsp = "/pageHome.jsp";
			break;
		
		}
		
		
		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(jsp);
		dispatcher.forward(request, response);
	}
	
	public void agregar(HttpServletRequest request){
		ServicioContactos servicioContactos = new ServicioContactos();
	}
	
	public void editar(HttpServletRequest request){
		ServicioContactos servicioContactos = new ServicioContactos();
		HttpSession session = request.getSession(true);
		int id = Integer.parseInt(request.getParameter("id"));
		Contacto contacto = servicioContactos.obtenerContactoId(id);		
		session.setAttribute("contacto", contacto);
	}
	
	public void actualizar(HttpServletRequest request){
		ServicioContactos servicioContactos = new ServicioContactos();
		Contacto contacto = new Contacto();
		contacto.setId(Integer.parseInt(request.getParameter("id")));
		contacto.setNombre(request.getParameter("nombre"));
		contacto.setCorreo(request.getParameter("correo"));
		if(servicioContactos.actualizarContacto(contacto)){
			actualizarContactoSession(request);
		}else{
			
		}
		
	}
	
	public void eliminar(HttpServletRequest request){
		boolean respuesta = false;
		ServicioContactos servicioContactos = new ServicioContactos();
		respuesta =	servicioContactos.eliminarContacto(Integer.parseInt(request.getParameter("id")));
		if(respuesta){
			actualizarContactoSession(request);
		}
	}
	
	private void actualizarContactoSession(HttpServletRequest request){
		ServicioContactos servicioContactos = new ServicioContactos();
		HttpSession session = request.getSession(true);
		Usuario usuario = (Usuario) session.getAttribute("USUARIO");
		
		ArrayList<Contacto> nuevaLista = servicioContactos.obtenerContactos(usuario.getId());
		usuario.setContactos(nuevaLista);
		session.setAttribute("USUARIO", usuario);
		session.removeAttribute("contacto");
	}
}
