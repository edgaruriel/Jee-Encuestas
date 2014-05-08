package fmat.jee.projectQuiz.model.servicio;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import fmat.jee.projectQuiz.model.Contacto;
import fmat.jee.projectQuiz.model.Usuario;
import fmat.jee.projectQuiz.model.dao.DaoContacto;

public class ServicioContactos {

	
	
	public ArrayList<Contacto> obtenerContactos(int id){
		DaoContacto dao = new DaoContacto();
		ArrayList<Contacto> contactos = null;
		
		try {
			contactos =	dao.consultarTodos("Usuario_id = " + id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return contactos;
	}
	
	public boolean agregarContacto(HttpServletRequest request){
		String usuarioID = request.getParameter("usuarioId");
		String nombre = request.getParameter("nombre");
		String correo = request.getParameter("correo");
		
		Contacto contacto = new Contacto();
		contacto.setNombre(nombre);
		contacto.setCorreo(correo);
		contacto.setUsuarioId(Integer.parseInt(usuarioID));
		
		DaoContacto dao = new DaoContacto();
		boolean resultado = false;
		try {
			if(dao.agregar(contacto)){
				resultado = true;
				ArrayList<Contacto> nuevaLista = obtenerContactos(Integer.parseInt(usuarioID));
				HttpSession session = request.getSession(true);
				Usuario usuario = (Usuario) session.getAttribute("USUARIO");
				usuario.setContactos(nuevaLista);
				session.setAttribute("USUARIO", usuario);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultado = false;
		}
		
		return resultado;
	}
	
	public Contacto obtenerContactoId(int id){
		DaoContacto dao = new DaoContacto();
		String condicion = "id="+id;
		Contacto contacto = null;
		try {
			contacto = dao.consultar(condicion);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return contacto;
	}
	
	public boolean actualizarContacto(Contacto contacto){
		DaoContacto dao = new DaoContacto();
		boolean resultado = false;		
		try {
			//System.out.print("Servicio contacto");
			resultado = dao.modificar(contacto);			
			resultado = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultado = false;	
		}
		
		return resultado;
	}
	
	public boolean eliminarContacto(int id){
		DaoContacto dao = new DaoContacto();
		boolean resultado = false;
		String condicion = "id="+id;
			try {
				resultado = dao.eliminar(condicion);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				resultado = false;
			}
		return resultado;
	}
}
