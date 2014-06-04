package fmat.jee.projectQuiz.model.servicio;

import java.sql.SQLException;
import java.util.ArrayList;

import fmat.jee.projectQuiz.model.dao.DaoEncuesta;
import fmat.jee.projectQuiz.model.dao.DaoPregunta;
import fmat.jee.projectQuiz.model.dominio.Encuesta;
import fmat.jee.projectQuiz.model.dominio.Pregunta;

public class ServicioPreguntas {

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
	
	public int obtenerRespuestasPorOpcion(int id){
		DaoPregunta dao = new DaoPregunta();
		int cantidad = 0;
		try{
			if(dao.consultarRespuestas("opr.OpcionesMultiples_id = "+ id)>0){
				cantidad = dao.consultarRespuestas("opr.OpcionesMultiples_id = "+ id);
			}
		}catch(SQLException e){
			System.out.println("Error en obtener ultimo id");
			e.printStackTrace();
			cantidad=0;
		}
		return cantidad;
	}
	
	public ArrayList<String> respuestasAbiertas(Pregunta pregunta){
		DaoPregunta dao = new DaoPregunta();
		ArrayList<String> preguntas = new ArrayList<>();
		try{
			if(dao.consultarRespuestasAbiertas("r.`Preguntas_id` = "+ pregunta.getId()).size()>0){
				preguntas = dao.consultarRespuestasAbiertas("r.`Preguntas_id` = "+ pregunta.getId());
			}
		}catch(SQLException e){
			System.out.println("Error en obtener ultimo id");
			e.printStackTrace();
		}
		return preguntas;
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
			daoEncuesta.agregarRespuestas(encuesta);
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
	
	public boolean eliminarEncuesta(int id){
		DaoEncuesta daoEncuesta = new DaoEncuesta();
		String condicion = "id="+id;
		boolean resultado = false;
		try {
			daoEncuesta.eliminar(condicion);
			resultado = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultado = false;
		}
		
		return resultado;
	}
}
