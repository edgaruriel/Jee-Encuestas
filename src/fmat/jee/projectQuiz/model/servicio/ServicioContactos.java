package fmat.jee.projectQuiz.model.servicio;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import fmat.jee.projectQuiz.model.dao.DaoContacto;
import fmat.jee.projectQuiz.model.dominio.Contacto;
import fmat.jee.projectQuiz.model.dominio.Usuario;

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
	
	public boolean agregarContacto(Contacto contacto){
		
		DaoContacto dao = new DaoContacto();
		boolean resultado = false;
		try {
			if(dao.agregar(contacto)){
				resultado = true;
				
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
