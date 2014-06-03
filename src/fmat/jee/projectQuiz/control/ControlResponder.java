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

import fmat.jee.projectQuiz.model.dominio.Encuesta;
import fmat.jee.projectQuiz.model.dominio.OpcionMultiple;
import fmat.jee.projectQuiz.model.dominio.Pregunta;
import fmat.jee.projectQuiz.model.servicio.ServicioEncuesta;

/**
 * Servlet implementation class ControlResponder
 */
@WebServlet("/ControlResponder")
public class ControlResponder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControlResponder() {
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
		
		switch(control){
		case "ver":
			verEncuesta(request, response);
			break;
		case "agregar":
			agregarRespuestas(request,response);
			break;
		case "SoloLectura":
			verSoloLectura(request,response);
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
	
	public void verSoloLectura(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String idEncuesta = request.getParameter("encuesta");
		
		ServicioEncuesta servicioEncuesta = new ServicioEncuesta();
		Encuesta encuesta = servicioEncuesta.obtenerEncuestaPor(Integer.parseInt(idEncuesta));
		HttpSession session = request.getSession(true);
		session.setAttribute("ES_CLIENTE", true);
		session.setAttribute("VERENCUESTA", encuesta);
		response.sendRedirect("verEncuesta.jsp");
	}
	
	public void verEncuesta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String idEncuesta = request.getParameter("encuesta");
		String correoSolicitante = request.getParameter("correo");
		ServicioEncuesta servicioEncuesta = new ServicioEncuesta();
		
		Encuesta encuesta = servicioEncuesta.obtenerEncuestaPor(Integer.parseInt(idEncuesta));
		HttpSession session = request.getSession(true);
		session.setAttribute("VERENCUESTA", encuesta);
		session.setAttribute("SOLICITANTE",correoSolicitante);
		
		session.setAttribute("ES_CLIENTE", false);
		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/verEncuesta.jsp");
		dispatcher.forward(request, response);
		//response.sendRedirect("verEncuesta.jsp");
	}
	
	public void agregarRespuestas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		HttpSession session = request.getSession(true);
		Encuesta encuesta = (Encuesta) session.getAttribute("VERENCUESTA");
		//System.out.println("Correo: "+request.getParameter("correo"));
		System.out.println("dasdadadsas "+encuesta);
		ArrayList<Pregunta> preguntas = encuesta.getPreguntas();
		encuesta.setCorreo(request.getParameter("correo"));
		for (Pregunta pregunta : preguntas) {
			
			if(pregunta.getTipoPregunta().getTipo().equals("ABIERTA")){
				String respuesta = request.getParameter("pregunta"+pregunta.getId());
				pregunta.setRespuesta(respuesta);
				
			}else if (pregunta.getTipoPregunta().getTipo().equals("MULTIPLE")) {
				ArrayList<OpcionMultiple> opciones = pregunta.getOpciones();
				String respuestaOpcion = request.getParameter("opcion"+pregunta.getId());
				for(OpcionMultiple opcion: opciones){					
					if(opcion.getId() == Integer.parseInt(respuestaOpcion)){
						opcion.setRespuesta(true);
					}else{
						opcion.setRespuesta(false);
					}
				}				
			}else if(pregunta.getTipoPregunta().getTipo().equals("CALIFICACION")){
				ArrayList<OpcionMultiple> opciones = pregunta.getOpciones();
				String respuestaOpcion = request.getParameter("opcion"+pregunta.getId());
				for(OpcionMultiple opcion: opciones){					
					if(opcion.getId() == Integer.parseInt(respuestaOpcion)){
						opcion.setRespuesta(true);
					}else{
						opcion.setRespuesta(false);
					}
				}
				
			}
			
		}
		
		ServicioEncuesta servicioEncuesta = new ServicioEncuesta();
	boolean respuesta =	servicioEncuesta.agregarEncuestaContestada(encuesta);
		if(respuesta){
			request.setAttribute("correcto", true);
			RequestDispatcher dispatcher = 	request.getServletContext().getRequestDispatcher("/verEncuesta.jsp");
			dispatcher.forward(request, response);
			//response.sendRedirect("responderEncuesta.jsp");
		}else{
			request.setAttribute("error", true);
			RequestDispatcher dispatcher = 	request.getServletContext().getRequestDispatcher("/verEncuesta.jsp");
			dispatcher.forward(request, response);	
		//	response.sendRedirect("responderEncuesta.jsp");
		}
		//imprimirRespuestas(encuesta);
	}
	
	//solo para probar que si guarda las respuestas
	public void imprimirRespuestas(Encuesta encuesta){
		ArrayList<Pregunta> preguntas = encuesta.getPreguntas();
		for(Pregunta pregunta : preguntas){
			System.out.println("Pregunta: "+ pregunta.getPregunta());
			if(pregunta.getTipoPregunta().getTipo().equals("ABIERTA")){
				System.out.println("Respuesta: "+pregunta.getRespuesta());
			}else{
				for(OpcionMultiple opcion: pregunta.getOpciones()){
					if(opcion.isRespuesta()){
						System.out.println("Respuesta: "+opcion.getOpcion());
					}
				}
			}
			
		}
	}
	

}
