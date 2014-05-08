package fmat.jee.projectQuiz.model.servicio;

import java.sql.SQLException;

import fmat.jee.projectQuiz.model.Rol;
import fmat.jee.projectQuiz.model.dao.DaoRol;

public class ServicioRol {

	public Rol obtenerRolPorId(int tipoUsuario){
		DaoRol dao = new DaoRol();
		String condicion = "rol.id = '"+tipoUsuario+"'";
		Rol rol = null;
		try{
			rol = dao.consultar(condicion);
		}catch(SQLException e){
			
		}
		return rol;
	}
}
