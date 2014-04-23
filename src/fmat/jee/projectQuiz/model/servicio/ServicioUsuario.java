package fmat.jee.projectQuiz.model.servicio;

import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import fmat.jee.projectQuiz.model.Usuario;
import fmat.jee.projectQuiz.model.dao.DaoUsuario;

public class ServicioUsuario {
	
	
	public boolean validarUsuario(String usuario, String contrasenia){
		boolean resp = false;
		DaoUsuario dao = new DaoUsuario();
		String condicion = "usuario.nombreUsuario = '"+ usuario +"' AND usuario.contrasena = '"+ contrasenia +"'";
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
		try{
			dao.agregar(usuario);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return true;
	}
}
