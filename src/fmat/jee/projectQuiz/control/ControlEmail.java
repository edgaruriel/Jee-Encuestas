package fmat.jee.projectQuiz.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.mail.*;
import javax.mail.internet.*;

import com.sun.xml.internal.fastinfoset.sax.Properties;

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
		try {
		String mensaje = configurarMensaje(request,response);
						SendingEmail(request,response, mensaje);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected String configurarMensaje(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//String uri=request.getRequestURI();		
	//	if(uri.equals(request.getContextPath()+"/ControlEmail"))
     //   {
			//System.out.println("SI ENTRO");
         //   SendEmail sa=new SendEmail();
                      //  String to_mail =request.getParameter("email");
                        String body ="<html>"
                        		+ "<body>"
                        		+ "<table width=100%>"
                        		+ "<tr>"
                        		+ "<td>Bienvenido al sistema de encuestas gratis.</td>"
                        		+ "</tr>"
                        		+ "<tr>"
                        		+ "<td>Encuesta: <a href=\"http://localhost:8080/"+request.getContextPath()+"/ControlResponder?tipo=ver&&encuesta=1\">Encuesta</a></td>"                        		
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
	
	 public void SendingEmail(HttpServletRequest request, HttpServletResponse response, String body) throws ServletException, IOException, AddressException, MessagingException
	    {
		 		String to_mail =request.getParameter("email");
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
	             String[] to = {to_mail}; // To Email address
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

	             message.setContent(body,"text/html");
	             Transport transport = session.getTransport("smtp");
	             transport.connect(host, from, pass);
	             transport.sendMessage(message, message.getAllRecipients());
	                 transport.close();
	                 
	                 response.sendRedirect("pageHome.jsp");
	        }

}
