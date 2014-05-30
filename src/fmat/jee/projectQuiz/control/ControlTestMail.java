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
 * Servlet implementation class ControlTestMail
 */
@WebServlet("/ControlTestMail")
public class ControlTestMail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControlTestMail() {
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
		try {
			sendMail(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sendMail(HttpServletRequest request, HttpServletResponse response) throws Exception, ServletException, IOException{
		String uri=request.getRequestURI();		
		if(uri.equals("/ProyectoEncuestas/ControlTestMail"))
        {
			System.out.println("SI ENTRO");
            SendEmail sa=new SendEmail();
                        String to_mail =request.getParameter("email");
                        String body ="<html>"
                        		+ "<body>"
                        		+ "<table width=100%>"
                        		+ "<tr>"
                        		+ "<td>Bienvenido al sistema de encuestas gratis.</td>"
                        		+ "<td>Encuesta: <a href=\"http://localhost:8080/ProyectoEncuestas/ControlResponder?tipo=ver&&encuesta=1\">Encuesta</a></td>"                        		
                        		+ "</tr>"
                        		+ "</table>"
                        		+ "</body>"
                        		+ "</html>";
            sa.SendingEmail(to_mail,body);
            response.sendRedirect("pageHome.jsp");
        }else{
        	System.out.println("No ENTRO");
        }
	}

}
