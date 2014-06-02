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

import fmat.jee.projectQuiz.model.dominio.Usuario;
import fmat.jee.projectQuiz.model.dominio.Carpeta;
import fmat.jee.projectQuiz.model.servicio.ServicioCarpeta;

/**
 * Servlet implementation class ControlCarpeta
 */
@WebServlet("/ControlCarpeta")
public class ControlCarpeta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControlCarpeta() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doIt(request,response);
	}

	protected void doIt(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String control = request.getParameter("tipo");
		
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
		case "Cargar":
			cargar(request,response);
			break;
		default:
			paginaDefault(request,response);
			break;		
		}
		
	}
	
	public void paginaDefault(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/indexCliente.jsp");
		dispatcher.forward(request, response);
	}
	
	public void agregar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String nombre = request.getParameter("nombre");
		String usuarioID = request.getParameter("usuarioId");
		
		Carpeta carpeta = new Carpeta();
		carpeta.setNombre(nombre);
		carpeta.setIdUsuario(Integer.parseInt(usuarioID));
		
		ServicioCarpeta servicioCarpeta = new ServicioCarpeta();
	boolean respuesta =	servicioCarpeta.agregarCarpeta(carpeta);
		if(respuesta){
			actualizarSession(request,response);
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/carpetas.jsp");
			dispatcher.forward(request, response);
		}else{
			RequestDispatcher dispatcherMal = request.getServletContext().getRequestDispatcher("/crearCarpeta.jsp");
			dispatcherMal.forward(request, response);
		}
		
	}
	
	public void eliminar(HttpServletRequest request, HttpServletResponse response){
		
	}
	
	public void editar(HttpServletRequest request, HttpServletResponse response){
		
	}
	
	public void actualizar(HttpServletRequest request, HttpServletResponse response){
		
	}
	
	public void cargar(HttpServletRequest request, HttpServletResponse response) throws IOException{
		HttpSession session = request.getSession(true);
		Usuario usuario = (Usuario) session.getAttribute("USUARIO");
		int idUsuario = usuario.getId();
		
		ServicioCarpeta servicioCarpeta = new ServicioCarpeta();
		ArrayList<Carpeta> carpetas = new ArrayList<Carpeta>();
		carpetas = servicioCarpeta.obtenerCarpetas(idUsuario);
		
		session.setAttribute("CARPETAS", carpetas);
		
		response.sendRedirect("carpetas.jsp");
		
	}
	
	private void actualizarSession(HttpServletRequest request, HttpServletResponse response) throws IOException{
		ServicioCarpeta servicioCarpeta = new ServicioCarpeta();
		String usuarioID = request.getParameter("usuarioId");
		ArrayList<Carpeta> nuevaLista = servicioCarpeta.obtenerCarpetas(Integer.parseInt(usuarioID));
		HttpSession session = request.getSession(true);
		session.removeAttribute("CARPETAS");
		session.setAttribute("CARPETAS", nuevaLista);
		
	}
	
}
