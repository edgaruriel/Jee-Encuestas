package fmat.jee.projectQuiz.model.servicio;

import java.sql.SQLException;
import java.util.ArrayList;

import fmat.jee.projectQuiz.model.dao.DaoCarpeta;
import fmat.jee.projectQuiz.model.dominio.Carpeta;

public class ServicioCarpeta {
	
	
	public ArrayList<Carpeta> obtenerCarpetas(int usuarioId){
		DaoCarpeta daoCarpeta = new DaoCarpeta();
		ArrayList<Carpeta> carpetas = null;
		try {
			carpetas = daoCarpeta.consultarTodos("Usuario_id = " + usuarioId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return carpetas;
	}
	
	public boolean agregarCarpeta(Carpeta carpeta){
		DaoCarpeta daoCarpeta = new DaoCarpeta();
		boolean resultado = false;
		
		try {
			if(daoCarpeta.agregar(carpeta)){
				resultado = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultado = false;
		}		
		return resultado;
	}
}
