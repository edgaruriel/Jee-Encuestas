package fmat.jee.projectQuiz.model.servicio;

import java.sql.SQLException;
import java.util.ArrayList;

import fmat.jee.projectQuiz.model.dao.DaoEncuesta;
import fmat.jee.projectQuiz.model.dominio.Encuesta;

public class ServicioEncuesta {
	public boolean crearEncuesta(Encuesta encuesta){
		DaoEncuesta dao = new DaoEncuesta();
		boolean resultado=false;
		try{
			if(dao.agregar(encuesta)){
				resultado=true;
			}
		}catch(SQLException e){
			System.out.println("Error en crearEncuesta");
			e.printStackTrace();
			resultado=false;
		}
		return resultado;
	}
	
	public int obtenerUltimaEncuesta(){
		DaoEncuesta dao = new DaoEncuesta();
		int ultima = 0;
		try{
			if(dao.obtenerUltimoId()!=0){
				ultima = dao.obtenerUltimoId();
			}
		}catch(SQLException e){
			System.out.println("Error en obtener ultimo id");
			e.printStackTrace();
			ultima=0;
		}
		return ultima;
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
	
	public ArrayList<Encuesta> obtenerEncuestasPor(int idCarpeta){
		DaoEncuesta daoEncuesta = new DaoEncuesta();
		ArrayList<Encuesta> encuesta = new ArrayList<Encuesta>();
		String condicion = "CarpetasPersonal_id ="+ idCarpeta;
		
		try {
			encuesta =	daoEncuesta.consultarTodos(condicion);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			encuesta = null;
		}
		return encuesta;
	}
	
}
