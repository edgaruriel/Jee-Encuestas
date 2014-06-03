package fmat.jee.projectQuiz.filtro;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class Filtro
 */
@WebFilter(urlPatterns = {"/*"})
public class Filtro implements Filter {
	 private FilterConfig config = null;
	 

		/**
		 * @see Filter#init(FilterConfig)
		 */
		public void init(FilterConfig config) throws ServletException {
			this.config = config;
			System.out.println("iniciado el filtro");
		}
		
	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("filtro borrado");
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		//System.out.println("entró a doFilter");
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		HttpSession session= req.getSession();
		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/index.jsp");
		
		if(session.getAttribute("USUARIO")!=null){
			chain.doFilter(request, response);
		}else if(session.getAttribute("ADMIN")!=null){
			chain.doFilter(request, response);
		}else{
			System.out.println("Sesion nula");
			String path = ((HttpServletRequest) request).getRequestURI();
			
			//System.out.println(path);
			if (path.endsWith("/crearUsuario.jsp")||path.endsWith("/ControlLogin")||path.endsWith("/ControlUsuario")||path.endsWith("/ControlResponder")) {
				//System.out.println("crearUsuario.jsp");
			    chain.doFilter(request, response); // Just continue chain.
			}else{
				if(dispatcher!=null){
					dispatcher.forward(request, response);	
				}
			}
		}
		
	}

}
