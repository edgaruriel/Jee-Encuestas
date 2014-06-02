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

import fmat.jee.projectQuiz.model.dominio.Categoria;
import fmat.jee.projectQuiz.model.dominio.Usuario;
import fmat.jee.projectQuiz.model.servicio.ServicioCategorias;

/**
 * Servlet implementation class ControlCategoria
 */
@WebServlet("/ControlCategoria")
public class ControlCategoria extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControlCategoria() {
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
		doIt(request, response);
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
			break;
		case "Cargar":
			cargar(request, response);
			break;
		default:
			paginaDefault(request,response);
			break;
			
		}
	}
	
	public void paginaDefault(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/categorias.jsp");
		dispatcher.forward(request, response);
	}
	
	public void agregar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		ServicioCategorias servicioCategorias = new ServicioCategorias();
		String nombreCategoria = request.getParameter("nombre");
		
		Categoria categoria = new Categoria();
		categoria.setNombre(nombreCategoria);
		
		boolean respuesta = servicioCategorias.agregarCategoria(categoria);
		if(respuesta){
			actualizarSession(request,response);
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/categorias.jsp");
			dispatcher.forward(request, response);
		}else{
			RequestDispatcher dispatcherMal = request.getServletContext().getRequestDispatcher("/crearCategoria.jsp");
			dispatcherMal.forward(request, response);
		}
		
	}
	public void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		ServicioCategorias servicioCategorias = new ServicioCategorias();
		int id = Integer.parseInt(request.getParameter("id"));
		Categoria categoria = servicioCategorias.obtenerCategoriaId(id);
		HttpSession session = request.getSession(true);
		session.setAttribute("categoria", categoria);
		
		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/editarCategoria.jsp");
		dispatcher.forward(request, response);
	}
	
	public void actualizar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		ServicioCategorias servicioCategorias = new ServicioCategorias();
		Categoria categoria = new Categoria();
		categoria.setId(Integer.parseInt(request.getParameter("id")));
		categoria.setNombre(request.getParameter("nombre"));
		if(servicioCategorias.actualizarCategoria(categoria)){
			actualizarSession(request,response);
		}
		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/categorias.jsp");
		dispatcher.forward(request, response);
		
	}
		
	
	public void cargar(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		ServicioCategorias servicioCategorias = new ServicioCategorias();
		ArrayList<Categoria> categorias = new ArrayList<Categoria>();
		categorias = servicioCategorias.obtenerCategorias();
		HttpSession session = request.getSession(true);
		session.setAttribute("CATEGORIAS", categorias);
		response.sendRedirect("categorias.jsp");
		
	}
	
	private void actualizarSession(HttpServletRequest request, HttpServletResponse response) throws IOException{
		ServicioCategorias servicioCategorias = new ServicioCategorias();
		ArrayList<Categoria> categorias = new ArrayList<Categoria>();
		categorias = servicioCategorias.obtenerCategorias();
		HttpSession session = request.getSession(true);
		session.removeAttribute("CATEGORIAS");
		session.setAttribute("CATEGORIAS", categorias);
	}
		
		
		
		
		

}
