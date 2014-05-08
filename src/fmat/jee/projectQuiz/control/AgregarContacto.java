package fmat.jee.projectQuiz.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fmat.jee.projectQuiz.model.servicio.ServicioContactos;

/**
 * Servlet implementation class AgregarContacto
 */
@WebServlet("/AgregarContacto")
public class AgregarContacto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AgregarContacto() {
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
	
		ServicioContactos servicioContactos = new ServicioContactos();
	boolean respuesta =	servicioContactos.agregarContacto(request);
		if(respuesta){
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/contactos.jsp");
			dispatcher.forward(request, response);
		}else{
			RequestDispatcher dispatcherMal = request.getServletContext().getRequestDispatcher("/crearContacto.jsp");
			dispatcherMal.forward(request, response);
		}
	}

}
