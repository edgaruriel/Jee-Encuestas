package fmat.jee.projectQuiz.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;

import fmat.jee.projectQuiz.model.dominio.Categoria;
import fmat.jee.projectQuiz.model.dominio.Rol;
import fmat.jee.projectQuiz.model.dominio.TipoPregunta;
import fmat.jee.projectQuiz.model.dominio.Usuario;

public class DaoTipoPregunta extends AbstractDao<TipoPregunta>{

	@Override
	public boolean eliminar(String condicion) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public ArrayList<TipoPregunta> consultarTodos(String condicion) throws SQLException {
		// TODO Auto-generated method stub
		Connection conexion;
		conexion = (Connection) AbstractDao.getConexion();		
		java.sql.Statement st = conexion.createStatement();
		String Query = "SELECT * FROM tipopregunta";
		ResultSet rs = st.executeQuery(Query);
		ArrayList<TipoPregunta> tipopreguntas = new ArrayList<TipoPregunta>();
		while(rs.next()){
			TipoPregunta tipo = new TipoPregunta();
			tipo.setId(rs.getInt("id"));
			tipo.setTipo(rs.getString("tipo"));
			tipopreguntas.add(tipo);			
		}	
		return tipopreguntas;
	}

	@Override
	public TipoPregunta consultar(String condicion) throws SQLException {
		// TODO Auto-generated method stub
		Connection conexion;
		conexion = (Connection) AbstractDao.getConexion();		
		java.sql.Statement st = conexion.createStatement();
		
		ResultSet rs = st.executeQuery("SELECT * FROM tipopregunta WHERE "+condicion);
		TipoPregunta tipopregunta = new TipoPregunta();
		if(rs.next()){
			tipopregunta.setId(rs.getInt("id"));
			tipopregunta.setTipo(rs.getString("tipo"));
		}else{
			tipopregunta.setId(0);
		}
		return tipopregunta;
	}

	@Override
	public boolean agregar(TipoPregunta entidad) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean modificar(TipoPregunta entidad) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

}
