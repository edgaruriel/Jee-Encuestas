package fmat.jee.projectQuiz.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;











import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

import org.eclipse.jdt.internal.compiler.codegen.Opcodes;

import fmat.jee.projectQuiz.model.dominio.Carpeta;
import fmat.jee.projectQuiz.model.dominio.Categoria;
import fmat.jee.projectQuiz.model.dominio.Encuesta;
import fmat.jee.projectQuiz.model.dominio.OpcionMultiple;
import fmat.jee.projectQuiz.model.dominio.Pregunta;
import fmat.jee.projectQuiz.model.dominio.Rol;
import fmat.jee.projectQuiz.model.dominio.TipoPregunta;
import fmat.jee.projectQuiz.model.dominio.Usuario;
import fmat.jee.projectQuiz.model.servicio.ServicioCarpeta;
import fmat.jee.projectQuiz.model.servicio.ServicioCategorias;
import fmat.jee.projectQuiz.model.servicio.ServicioEncuesta;
import fmat.jee.projectQuiz.model.servicio.ServicioPreguntas;
import fmat.jee.projectQuiz.model.servicio.ServicioRol;
import fmat.jee.projectQuiz.model.servicio.ServicioTipoPregunta;
import fmat.jee.projectQuiz.model.servicio.ServicioUsuario;

/**
 * Servlet implementation class ControlEncuesta
 */
@WebServlet("/ControlEncuesta")
public class ControlEncuesta extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ArrayList<Pregunta> arrayPreguntas = null;
	int id;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControlEncuesta() {
        super();
        arrayPreguntas = new ArrayList<>();
        id = 0;
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
		String control = request.getParameter("tipo");
		
		switch(control){
		case "Agregar":
			agregar(request,response);
			break;
		case "Guardar":
			guardarEncuesta(request,response);
			break;
		case "GuardarPregunta":
			guardarPregunta(request,response);
			break;
		case "Eliminar":
			eliminarPregunta(request,response);
			break;
		case "Enlistar":
			Enlistar(request,response);
			break;
		case "VerReporte":
			verReporte(request,response);
			break;
		case "EliminarEncuesta":
			eliminarEncuesta(request,response);
			break;
		default:
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/index.jsp");
			dispatcher.forward(request, response);
			break;
		
		}
	}

	protected void eliminarEncuesta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServicioEncuesta servicioEncuesta = new ServicioEncuesta();
		int id = Integer.parseInt(request.getParameter("encuesta"));
		boolean resultado = servicioEncuesta.eliminarEncuesta(id);
		if(resultado){
			actualizarSession(request,response);
			response.sendRedirect("carpetas.jsp");
		}else{
			response.sendRedirect("indexCliente.jsp");
		}
	}
	
	protected void agregar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/crearEncuesta.jsp");
		HttpSession session = request.getSession(true);
		
		ServicioCategorias servicioCategorias = new ServicioCategorias();
		ArrayList<Categoria> categorias = servicioCategorias.obtenerCategorias();
		//System.out.println(categorias);
		session.setAttribute("categorias", categorias);
		
		ServicioCarpeta servicioCarpeta = new ServicioCarpeta();
		int id = Integer.parseInt(request.getParameter("id"));
		ArrayList<Carpeta> carpetas = servicioCarpeta.obtenerCarpetas(id);
		//System.out.println(carpetas);
		session.setAttribute("carpetas", carpetas);
		
		ServicioTipoPregunta servicioTipoPregunta = new ServicioTipoPregunta();
		ArrayList<TipoPregunta> tipopreguntas = servicioTipoPregunta.obtenerTipoPreguntas();
		//System.out.println(tipopreguntas);
		session.setAttribute("tipopreguntas", tipopreguntas);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		session.setAttribute("fechaActual", sdf.format(new Date()));
		
		if(dispatcher!=null){
			dispatcher.forward(request, response);
		}
	}
	
	protected void guardarEncuesta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/carpetas.jsp");
		HttpSession session = request.getSession(true);
		Encuesta encuesta = new Encuesta();
		
		if((ArrayList<Pregunta>)session.getAttribute("preguntas")!=null){
		
		ArrayList<Pregunta> preguntas = (ArrayList<Pregunta>)session.getAttribute("preguntas");
		String nombre = request.getParameter("nombre");
		int idCategoria = Integer.parseInt(request.getParameter("categoria"));
		ServicioCategorias servicioCategoria = new ServicioCategorias();
		Categoria categoria = servicioCategoria.obtenerDatos(idCategoria);
		int carpeta = Integer.parseInt(request.getParameter("carpeta"));
		String fechaActual = request.getParameter("fechaActual");
		String fechaTermino = "2014-06-25";
		
		int idUsuario = Integer.parseInt(request.getParameter("id"));
		ServicioUsuario servicioUsuario =  new ServicioUsuario();
		Usuario usuario = servicioUsuario.obtenerDatos(idUsuario);
		
		encuesta.setNombre(nombre);
		encuesta.setUsuario(usuario);
		encuesta.setFechaInicio(new Date());
		encuesta.setFechaFin(new Date());
		encuesta.setIdCarpeta(carpeta);
		encuesta.setCategoria(categoria);
		encuesta.setPreguntas(preguntas);
		
		ServicioEncuesta servicioEncuesta = new ServicioEncuesta();
		servicioEncuesta.crearEncuesta(encuesta);
		System.out.println(encuesta);
		
		actualizarSession(request, response);

			ArrayList<Pregunta> list = (ArrayList<Pregunta>)session.getAttribute("preguntas");
			
			Iterator<Pregunta> it = list.iterator();
			while (it.hasNext()) {
				it.next();
			    it.remove();
			}
			
			if(dispatcher!=null){
				dispatcher.forward(request, response);
			}
		}else{
			RequestDispatcher dispatcherError = request.getServletContext().getRequestDispatcher("/crearEncuesta.jsp");
			request.setAttribute("errorEncuesta", true);
			dispatcherError.forward(request, response);
		}

	}
	
	protected void guardarPregunta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/crearPregunta.jsp");
		HttpSession session = request.getSession(true);
		int idTipoPregunta = Integer.parseInt(request.getParameter("tipopregunta"));
		String pregunta = request.getParameter("pregunta");
		ServicioEncuesta servicioEncuesta = new ServicioEncuesta();
		int ultimaEncuesta = servicioEncuesta.obtenerUltimaEncuesta();
		
		Pregunta preg = new Pregunta();
		
		ServicioTipoPregunta servicioTipoPregunta = new ServicioTipoPregunta();
		TipoPregunta tipoPregunta = servicioTipoPregunta.obtenerDatos(idTipoPregunta);
		id+=1;
		preg.setId(id);
		preg.setIdEncuesta(ultimaEncuesta+1);
		preg.setPregunta(pregunta);
		preg.setTipoPregunta(tipoPregunta);
		
		if(idTipoPregunta==1){
			preg.setOpciones(null);
		}else if(idTipoPregunta==2){
			ArrayList<OpcionMultiple> arrayOpciones = new ArrayList<>();
			int numopciones = Integer.parseInt(request.getParameter("numopciones"));
			for (int i = 1; i <= numopciones; i++) {
				OpcionMultiple opcionMultiple = new OpcionMultiple();
				String opcion = request.getParameter("opcion"+i);
				//opcionMultiple.setIdPregunta(idPregunta);
				opcionMultiple.setOpcion(opcion);
				arrayOpciones.add(opcionMultiple);
			}
			preg.setOpciones(arrayOpciones);
			
		}else if(idTipoPregunta==3){
			preg.setOpciones(null);
		}
		
		System.out.println("Arreglo antes del add"+ arrayPreguntas);
		arrayPreguntas.add(preg);
		System.out.println("Arreglo despu�s del add"+ arrayPreguntas);
		
		session.setAttribute("preguntas", arrayPreguntas);
		
		ArrayList<Pregunta> pregun = (ArrayList<Pregunta>)session.getAttribute("preguntas");
		
		for (Pregunta pr : pregun) {
			System.out.println(pr.getId()+" "+pr.getPregunta());
			if(pr.getOpciones()!=null){
				for (OpcionMultiple op : pr.getOpciones()) {
					System.out.println(op.getOpcion());
				}
			}
		}
		
		if(dispatcher!=null){
			dispatcher.forward(request, response);
		}
		
		//System.out.println(session.getAttribute("preguntas"));

	}
	
	protected void eliminarPregunta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/crearPregunta.jsp");
		HttpSession session = request.getSession(true);

		int id = Integer.parseInt(request.getParameter("id"));
		System.out.println("Id a eliminar:"+id);
		
		ArrayList<Pregunta> list = (ArrayList<Pregunta>)session.getAttribute("preguntas");
		
		Iterator<Pregunta> it = list.iterator();
		while (it.hasNext()) {
		  Pregunta pregunta = it.next();
		  if (pregunta.getId()==id) {
		    it.remove();
		  }
		}
		
		ArrayList<Pregunta> pregun = (ArrayList<Pregunta>)session.getAttribute("preguntas");
		
		for (Pregunta pr : pregun) {
			System.out.println(pr.getId()+" "+pr.getPregunta());
			if(pr.getOpciones()!=null){
				for (OpcionMultiple op : pr.getOpciones()) {
					System.out.println(op.getOpcion());
				}
			}
		}
		
		if(dispatcher!=null){
			dispatcher.forward(request, response);
		}

	}
	
	protected void Enlistar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idCarpeta = request.getParameter("carpeta");
		
		HttpSession session = request.getSession(true);
		ArrayList<Carpeta> carpetas = new ArrayList<Carpeta>();
		carpetas = (ArrayList<Carpeta>) session.getAttribute("CARPETAS");
		Carpeta carpetaUsuario = new Carpeta();
		boolean bandera = false;
		int id = Integer.parseInt(idCarpeta);
		for (Carpeta carpeta : carpetas) {
			if(carpeta.getId() == id){
				bandera = true;
				carpetaUsuario = carpeta;
				break;
			}
		}
		
		if(bandera){
		//	ServicioEncuesta servicioEncuesta = new ServicioEncuesta();
		//	ArrayList<Encuesta> encuestas = new ArrayList<Encuesta>();
			
			
		//	encuestas = servicioEncuesta.obtenerEncuestasPor(id);
			session.setAttribute("CARPETA", carpetaUsuario);
			response.sendRedirect("listarEncuesta.jsp");
		}else{
			response.sendRedirect("index.jsp");
		}
		
	}
	
	protected void verReporte(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServicioEncuesta servicioEncuesta = new ServicioEncuesta();
		ServicioPreguntas servicioPregunta = new ServicioPreguntas();
		int id = Integer.parseInt(request.getParameter("encuesta"));
		Encuesta encuesta = servicioEncuesta.obtenerEncuestaPor(id);
		if(encuesta!=null){
			ArrayList<Pregunta> preguntas = new ArrayList<>();
			ArrayList<Respuesta> arreglo = new ArrayList<>();
			preguntas = encuesta.getPreguntas();
			int c = 1;
			for(Pregunta pregunta: preguntas){
				
				if(pregunta.getTipoPregunta().getId() == 1){
					System.out.println("Abierta " + pregunta.getPregunta() + "-Cantidad: " + servicioPregunta.respuestasAbiertas(pregunta));
					StringBuilder stringBuilder = new StringBuilder();
					for(int i = 0; i < servicioPregunta.respuestasAbiertas(pregunta).size();i++){
						 stringBuilder.append(servicioPregunta.respuestasAbiertas(pregunta).get(i).toString()+",  ");
					}
					String respuestasAb = stringBuilder.toString();
					arreglo.add(new Respuesta(c+".- " + pregunta.getPregunta(),respuestasAb));
				}else{
					if(pregunta.getTipoPregunta().getId() == 2){
						StringBuilder stringBuilder = new StringBuilder();
						for(int i = 0; i < pregunta.getOpciones().size(); i++){
							OpcionMultiple opcion = pregunta.getOpciones().get(i);
							
							int cantidad = servicioPregunta.obtenerRespuestasPorOpcion(opcion.getId());
							stringBuilder.append(opcion.getOpcion() + ":" + cantidad+",  ");
							System.out.println("Opcion " + opcion.getOpcion() + "-Cantidad: " + cantidad + " id:"+opcion.getId());
						}
						String respuestasAb = stringBuilder.toString();
						arreglo.add(new Respuesta(c+".- " + pregunta.getPregunta(),respuestasAb));
					}else{
						if(pregunta.getTipoPregunta().getId() == 3){
							StringBuilder stringBuilder = new StringBuilder();
							for(int i = 0; i < pregunta.getOpciones().size(); i++){
								OpcionMultiple opcion = pregunta.getOpciones().get(i);
								
								int cantidad = servicioPregunta.obtenerRespuestasPorOpcion(opcion.getId());
								stringBuilder.append(opcion.getOpcion() + ":" + cantidad + "  -");
								System.out.println("Opcion " + opcion.getOpcion() + "-Cantidad: " + cantidad + " id:"+opcion.getId());
							}
							String respuestasAb = stringBuilder.toString();
							arreglo.add(new Respuesta(c+".- " + pregunta.getPregunta(),respuestasAb));
						}else{
							System.out.println("Respuesta invalida");
						}
					}
				}
				c++;
			}
			
			try{
				JasperReport reporte = (JasperReport)JRLoader.loadObjectFromFile("C:\\Users\\user\\Documents\\Jee-Encuestas\\WebContent\\recursos\\reportes\\reporte.jasper");
				Map parametros = new HashMap<>();
				parametros.put("nombre", encuesta.getNombre());
				parametros.put("arreglo", arreglo);
				//JasperPrint print = JasperFillManager.fillReport(reporte, parametros, new JREmptyDataSource());
				JasperPrint print = JasperFillManager.fillReport(reporte, parametros, new JRBeanArrayDataSource(arreglo.toArray()));
				JasperViewer view = new JasperViewer(print);
				view.setTitle("Reporte: " + encuesta.getNombre());
				view.setVisible(true);
				
			}catch(JRException e){
				 e.printStackTrace();
			}
			//actualizarSession(request,response);
			//response.sendRedirect("carpetas.jsp");
		}else{
			response.sendRedirect("indexCliente.jsp");
		}
	}
	
	private void actualizarSession(HttpServletRequest request, HttpServletResponse response) throws IOException{
		ServicioCarpeta servicioCarpeta = new ServicioCarpeta();
		HttpSession session = request.getSession(true);
		Usuario usuario = (Usuario) session.getAttribute("USUARIO");
		int usuarioID = usuario.getId();
		ArrayList<Carpeta> nuevaLista = servicioCarpeta.obtenerCarpetas(usuarioID);
		
		session.removeAttribute("CARPETAS");
		session.setAttribute("CARPETAS", nuevaLista);
		
	}
	
	public class Respuesta{
		
		private String pregunta;
		private String reporte;
		
		public String getPregunta() {
			return pregunta;
		}

		public void setPregunta(String pregunta) {
			this.pregunta = pregunta;
		}

		public String getReporte() {
			return reporte;
		}

		public void setReporte(String reporte) {
			this.reporte = reporte;
		}

		public Respuesta(String pregunta, String reporte) {
			super();
			this.pregunta = pregunta;
			this.reporte = reporte;
		}
		
		public Respuesta getMe()
		 {
		  return this;
		 }
	}
	
}
