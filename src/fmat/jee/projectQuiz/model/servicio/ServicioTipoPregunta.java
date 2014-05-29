package fmat.jee.projectQuiz.model.servicio;

import java.sql.SQLException;
import java.util.ArrayList;

import fmat.jee.projectQuiz.model.dao.DaoCarpeta;
import fmat.jee.projectQuiz.model.dao.DaoCategoria;
import fmat.jee.projectQuiz.model.dao.DaoTipoPregunta;
import fmat.jee.projectQuiz.model.dao.DaoUsuario;
import fmat.jee.projectQuiz.model.dominio.Carpeta;
import fmat.jee.projectQuiz.model.dominio.Categoria;
import fmat.jee.projectQuiz.model.dominio.TipoPregunta;
import fmat.jee.projectQuiz.model.dominio.Usuario;

public class ServicioTipoPregunta {
	
	public ArrayList<TipoPregunta> obtenerTipoPreguntas(){
		DaoTipoPregunta daoTipoPregunta = new DaoTipoPregunta();
		ArrayList<TipoPregunta> tipopreguntas = null;
		try {
			tipopreguntas = daoTipoPregunta.consultarTodos("");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tipopreguntas;
	}
	
	public TipoPregunta obtenerDatos(int id) {
		DaoTipoPregunta dao = new DaoTipoPregunta();
		TipoPregunta tipopregunta = null;
		String s = String.valueOf(id);
		try{
			tipopregunta = dao.consultar("tipopregunta.id="+s);
		}catch(Exception e){
			e.printStackTrace();
		}
		return tipopregunta;
	}
}
