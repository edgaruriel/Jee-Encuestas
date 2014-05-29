package fmat.jee.projectQuiz.model.servicio;

import java.sql.SQLException;

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
}
