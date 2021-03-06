package fmat.jee.projectQuiz.model.servicio;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import fmat.jee.projectQuiz.model.dao.DaoContacto;
import fmat.jee.projectQuiz.model.dao.DaoUsuario;
import fmat.jee.projectQuiz.model.dominio.Contacto;
import fmat.jee.projectQuiz.model.dominio.Usuario;

public class ServicioUsuario {
	
	
	public boolean validarUsuario(String usuario, String contrasenia){
		boolean resp = false;
		DaoUsuario dao = new DaoUsuario();
		String condicion = "nombreUsuario = '"+ usuario +"' AND contrasena = '"+ contrasenia +"'";
		Usuario usuaro;
		try {
			usuaro = dao.consultar(condicion);
			int id = usuaro.getId();
			if(id!=0){
				resp = true;
				
			}else{
				resp = false;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resp;
	}
	
	
	public boolean crearUsuario(Usuario usuario){
		DaoUsuario dao = new DaoUsuario();
		boolean resultado=false;
		try{
			if(dao.agregar(usuario)){
				resultado=true;
			}else{
				resultado = false;
			}
		}catch(SQLException e){
			System.out.println("Error en crearUsuario");
			e.printStackTrace();
			resultado=false;
		}
		return resultado;
	}
	
	public boolean actualizarUsuario(Usuario usuario){
		DaoUsuario dao = new DaoUsuario();
		boolean resultado=false;
		try{
			if(dao.modificar(usuario)){
				resultado=true;
			}
		}catch(SQLException e){
			System.out.println("Error en modifUsuario");
			e.printStackTrace();
			resultado=false;
		}
		return resultado;
	}
	
	public Usuario obtenerDatos(int id) {
		DaoUsuario dao = new DaoUsuario();
		Usuario usuario = null;
		String s = String.valueOf(id);
		try{
		usuario = dao.consultar("usuario.id="+s);
		}catch(Exception e){
			e.printStackTrace();
		}
		return usuario;
	}
	
	public Usuario obtenerUsuarioPorNombre(String nombre){
		DaoUsuario dao = new DaoUsuario();
		Usuario usuario = null;	
		try{
		usuario = dao.consultar("usuario.nombreUsuario= '"+nombre +"'");		
		}catch(Exception e){
			e.printStackTrace();
		}
		return usuario;
	}
	
	public boolean eliminarUsuario(int id){
		DaoUsuario dao = new DaoUsuario();
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
	
	public ArrayList<Usuario> obtenerUsuarios(){
		DaoUsuario dao = new DaoUsuario();
		ArrayList<Usuario> usuarios = null;
		try {
			usuarios = dao.consultarTodos("Rol_id ="+1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return usuarios;
	}

}
