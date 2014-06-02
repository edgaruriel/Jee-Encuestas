package fmat.jee.projectQuiz.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.mail.*;
import javax.mail.internet.*;

import com.sun.xml.internal.fastinfoset.sax.Properties;

import fmat.jee.projectQuiz.model.dominio.Administrador;
import fmat.jee.projectQuiz.model.dominio.Contacto;
import fmat.jee.projectQuiz.model.dominio.Encuesta;
import fmat.jee.projectQuiz.model.dominio.Usuario;
import fmat.jee.projectQuiz.model.servicio.ServicioEncuesta;
import fmat.jee.projectQuiz.model.servicio.ServicioUsuario;

/**
 * Servlet implementation class ControlEmail
 */
@WebServlet("/ControlEmail")
public class ControlEmail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControlEmail() {
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
		case "seleccionarEncuesta":
			seleccionarEncuesta(request, response);
			break;
		case "enviar":
			//imprimirContactos(request, response);
				//String mensaje = configurarMensaje(request,response);
				controlEnvio(request,response);			
			break;
		case "seleccionarUsuario":
			seleccionarUsuario(request,response);
			break;
		case "mensajeUsuario":
			mensajeUsuario(request,response);
			break;
		default:
			break;
		
		}
		
		
	}
	
	protected void mensajeUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String mensaje = configurarMensajeUsuario(request, response);
		HttpSession session = request.getSession(true);
		Usuario usuario = (Usuario) session.getAttribute("SELECCION_USUARIO");
		try {
			enviarEmail(usuario.getCorreo(),mensaje);
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		response.sendRedirect("usuarios.jsp");
	}
	
	protected void seleccionarUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String nombreUsuario = request.getParameter("usuario");
		HttpSession session = request.getSession(true);
		ServicioUsuario servicioUsuario = new ServicioUsuario();
		Usuario usuario = servicioUsuario.obtenerUsuarioPorNombre(nombreUsuario);
		session.setAttribute("SELECCION_USUARIO", usuario);
		response.sendRedirect("enviarMensaje.jsp");
	}
	
	protected String configurarMensajeUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String mensaje = request.getParameter("mensaje");
		HttpSession session = request.getSession(true);
		Usuario usuario = (Usuario) session.getAttribute("SELECCION_USUARIO");
		Administrador administrador = (Administrador) session.getAttribute("ADMIN");
		                String body ="<html>"
                        		+ "<body>"
                        		+ "<table width=100%>"
                        		+ "<tr>"
                        		+ "<td>Del Administrador: "+administrador.getNombre()+" "+administrador.getPrimerApellido()+" "+administrador.getSegundoApellido()+"</td>"
                        		+ "</tr>"
                        		+ "<tr>"
                        		+ "<td>Para: "+usuario.getNombre()+" "+usuario.getPrimerApellido()+" "+usuario.getSegundoApellido()+"</td>"
                        		+ "</tr>"
                        		+ "<tr>"
                        		+ "<td><label>Mensaje:</label></td>"
                        		+ "</tr>"
                        		+ "<tr>"
                        		+ mensaje
                        		+ "</tr>"
                        		+ "</table>"
                        		+ "</body>"
                        		+ "</html>";
      //      sa.SendingEmail(to_mail,body);
      //      response.sendRedirect("pageHome.jsp");
     //   }else{
     //   	System.out.println("No ENTRO");
     //   }
        return body;
	}
	
	public void imprimirContactos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		HttpSession session = request.getSession(true);
		Usuario usuario = (Usuario) session.getAttribute("USUARIO");
		ArrayList<Contacto> contactos = usuario.getContactos();
		System.out.println("seleccionados: ");
		for (Contacto contacto : contactos) {
			System.out.println("SELECCIONADOS: "+request.getParameter("contacto"+contacto.getId()));
		}
		
	}
	
	protected void seleccionarEncuesta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		int idEncuesta = Integer.parseInt(request.getParameter("encuesta"));
		HttpSession session = request.getSession(true);
		ServicioEncuesta servicioEncuesta = new ServicioEncuesta();
		Encuesta encuesta = servicioEncuesta.obtenerEncuestaPor(idEncuesta);
		session.setAttribute("SELECCION_ENCUESTA", encuesta);
		response.sendRedirect("enviarEncuesta.jsp");
	}
	
	protected String configurarMensajeEncuesta(HttpServletRequest request, HttpServletResponse response, String correo) throws ServletException, IOException{
		//String uri=request.getRequestURI();		
	//	if(uri.equals(request.getContextPath()+"/ControlEmail"))
     //   {
			//System.out.println("SI ENTRO");
         //   SendEmail sa=new SendEmail();
                      //  String to_mail =request.getParameter("email");
		HttpSession session = request.getSession(true);
		Encuesta encuesta = (Encuesta) session.getAttribute("SELECCION_ENCUESTA");
		Usuario usuario = (Usuario) session.getAttribute("USUARIO");
		                String body ="<html>"
                        		+ "<body>"
                        		+ "<table width=100%>"
                        		+ "<tr>"
                        		+ "<td>El usuario: "+usuario.getNombre()+" "+usuario.getPrimerApellido()+" "+usuario.getSegundoApellido()+"."
                        		+ " Le invito a contestar una de sus encuestas</td>"
                        		+ "</tr>"
                        		+ "<tr>"
                        		+ "<td>Encuesta "+encuesta.getNombre()+": <a href=\"http://localhost:8080/"+request.getContextPath()+"/ControlResponder?tipo=ver&&encuesta="+encuesta.getId()+"&&correo="+correo+"\">Encuesta</a></td>"                        		
                        		+ "</tr>"
                        		+ "</table>"
                        		+ "</body>"
                        		+ "</html>";
      //      sa.SendingEmail(to_mail,body);
      //      response.sendRedirect("pageHome.jsp");
     //   }else{
     //   	System.out.println("No ENTRO");
     //   }
        return body;
	}
	
	protected void controlEnvio(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

		HttpSession session = request.getSession(true);
		Usuario usuario = (Usuario) session.getAttribute("USUARIO");
		ArrayList<Contacto> contactos = usuario.getContactos();
		
		for (Contacto contacto : contactos) {
			
			if(request.getParameter("contacto"+contacto.getId()) != null){
			//	System.out.println("SELECCIONADOS: " +request.getParameter("contacto"+contacto.getId()));
			String mensaje = configurarMensajeEncuesta(request,response,request.getParameter("contacto"+contacto.getId()));
				try {
					enviarEmail(request.getParameter("contacto"+contacto.getId()), mensaje);
				} catch (MessagingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				
			}
		}
		
		String nuevosCorreos = request.getParameter("email");
		StringTokenizer tokens = new StringTokenizer(nuevosCorreos, ",");
		//System.out.println("correos del input: ");
		
		String valido = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		
		while (tokens.hasMoreElements()) {
			//System.out.println(tokens.nextToken());
			Pattern pattern = Pattern.compile(valido);
			String correo = tokens.nextToken();
			Matcher matcher = pattern.matcher(correo);
			if(matcher.matches()){
				//System.out.println(correo);
		String mensaje = configurarMensajeEncuesta(request,response,correo);
				try {
					enviarEmail(correo,mensaje);
				} catch (MessagingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			//enviarEmail(tokens.nextToken(),body);
		}
		
		response.sendRedirect("listarEncuesta.jsp");	
	}
	
	 protected void enviarEmail(String correo, String mensaje) throws AddressException, MessagingException
	    {
		 		//String to_mail =request.getParameter("email");
	             String host ="smtp.gmail.com";
	             String from ="proyecto.encuestajee@gmail.com";  //Your mail id
	             String pass ="Jee12345";   // Your Password
	             java.util.Properties props = System.getProperties();
	             props.put("mail.smtp.starttls.enable", "true"); // added this line
	             props.put("mail.smtp.host", host);
	             props.put("mail.smtp.user", from);
	             props.put("mail.smtp.password", pass);
	             props.put("mail.smtp.port", "25");
	             props.put("mail.smtp.auth", "true");
	             String[] to = {correo}; // To Email address
	             Session session = Session.getDefaultInstance(props, null);
	             MimeMessage message = new MimeMessage(session);
	             message.setFrom(new InternetAddress(from));
	             InternetAddress[] toAddress = new InternetAddress[to.length];        
	             // To get the array of addresses
	              for( int i=0; i < to.length; i++ )
	              { // changed from a while loop
	                  toAddress[i] = new InternetAddress(to[i]);
	              }
	             System.out.println(Message.RecipientType.TO);
	             for( int j=0; j < toAddress.length; j++)
	             { // changed from a while loop
	             message.addRecipient(Message.RecipientType.TO, toAddress[j]);
	             }
	             message.setSubject("Email from SciArchives");

	             message.setContent(mensaje,"text/html");
	             Transport transport = session.getTransport("smtp");
	             transport.connect(host, from, pass);
	             transport.sendMessage(message, message.getAllRecipients());
	                 transport.close();
	                 
	                
	        }

}
