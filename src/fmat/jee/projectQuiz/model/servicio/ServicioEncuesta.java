package fmat.jee.projectQuiz.model.servicio;

import java.sql.SQLException;

import fmat.jee.projectQuiz.model.dao.DaoEncuesta;
import fmat.jee.projectQuiz.model.dominio.Encuesta;

public class ServicioEncuesta {
	
	public Encuesta obtenerEncuestaPor(int id){
		DaoEncuesta daoEncuesta = new DaoEncuesta();
		Encuesta encuesta = null;
		
		try {
			encuesta = daoEncuesta.consultar("id="+id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return encuesta;
	}
	
	public boolean agregarEncuestaContestada(Encuesta encuesta){
		DaoEncuesta daoEncuesta = new DaoEncuesta();
	boolean	respuesta = false;

	if(!validarEncuestaPor(encuesta.getCorreo())){
		try {
			daoEncuesta.agregar(encuesta);
			respuesta = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			respuesta = false;
		}
	}	
		return respuesta;
	}
	
	public boolean validarEncuestaPor(String correo){
		DaoEncuesta daoEncuesta = new DaoEncuesta();
		boolean respuesta = false;
		try {
			respuesta =	daoEncuesta.consultarContestado("correo ='"+correo+"'");
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return respuesta;
	}

}
