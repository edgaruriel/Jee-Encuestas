package fmat.jee.projectQuiz.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;

import fmat.jee.projectQuiz.model.dominio.Encuesta;
import fmat.jee.projectQuiz.model.dominio.Pregunta;

public class DaoEncuesta extends AbstractDao<Encuesta>{

	@Override
	public boolean agregar(Encuesta entidad) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean eliminar(String condicion) throws SQLException {
		// TODO Auto-generated method stub
		boolean resultado = true;
		Connection conexion;
		conexion = (Connection) AbstractDao.getConexion();		
		java.sql.Statement st = conexion.createStatement();
		
		ArrayList<Encuesta> encuesta = consultarTodos(condicion);
		DaoPregunta daoPregunta = new DaoPregunta();
		for (int i = 0; i < encuesta.size(); i++) {
			
			daoPregunta.eliminar(Integer.toString(encuesta.get(i).getId()));
		}
		
		String sql = "DELETE FROM encuesta WHERE "+condicion;
		st.executeUpdate(sql);
		return resultado;
	}

	@Override
	public boolean modificar(Encuesta entidad) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Encuesta> consultarTodos(String condicion)
			throws SQLException {
		// TODO Auto-generated method stub
		Connection conexion;
		conexion = (Connection) AbstractDao.getConexion();		
		java.sql.Statement st = conexion.createStatement();
		String Query = "SELECT * FROM encuesta WHERE "+condicion;
		ResultSet rs = st.executeQuery(Query);
		
		ArrayList<Encuesta> encuestas = new ArrayList<Encuesta>();
				
		while(rs.next()){
			Encuesta encuesta = new Encuesta();
			encuesta.setId(rs.getInt("id"));
			encuesta.setNombre(rs.getString("nombre"));
			encuesta.setFechaInicio(rs.getDate("fechaInicio"));
			encuesta.setFechaFin(rs.getDate("fechaFin"));
			
			DaoUsuario daoUsuario = new DaoUsuario();
			encuesta.setUsuario(daoUsuario.consultar("id="+rs.getInt("Usuario_id")));
			
			DaoCategoria daoCategoria = new DaoCategoria();
			String condicionCategoria = "id="+rs.getInt("Categorias_id");
			encuesta.setCategoria(daoCategoria.consultar(condicionCategoria));
			
			encuesta.setIdCarpeta(rs.getInt("CapetasPersonal_id"));
			
			DaoPregunta daoPregunta = new DaoPregunta();
			ArrayList<Pregunta> preguntas;
			String condicionPregunta = "Encuesta_id="+rs.getInt("id");
			preguntas = daoPregunta.consultarTodos(condicionPregunta);
			
			encuesta.setPreguntas(preguntas);
			encuestas.add(encuesta);
		}
		
		return encuestas;
	}

	@Override
	public Encuesta consultar(String condicion) throws SQLException {
		// TODO Auto-generated method stub
		Connection conexion;
		conexion = (Connection) AbstractDao.getConexion();		
		java.sql.Statement st = conexion.createStatement();
		String Query = "SELECT * FROM encuesta WHERE "+condicion;
		ResultSet rs = st.executeQuery(Query);
		Encuesta encuesta = new Encuesta();
		
		if(rs.next()){
			encuesta.setId(rs.getInt("id"));
			encuesta.setNombre(rs.getString("nombre"));
			encuesta.setFechaInicio(rs.getDate("fechaInicio"));
			encuesta.setFechaFin(rs.getDate("fechaFin"));
			
			DaoUsuario daoUsuario = new DaoUsuario();
			encuesta.setUsuario(daoUsuario.consultar("id="+rs.getInt("Usuario_id")));
			
			DaoCategoria daoCategoria = new DaoCategoria();
			String condicionCategoria = "id="+rs.getInt("Categorias_id");
			encuesta.setCategoria(daoCategoria.consultar(condicionCategoria));
			
			encuesta.setIdCarpeta(rs.getInt("CapetasPersonal_id"));
			
			DaoPregunta daoPregunta = new DaoPregunta();
			ArrayList<Pregunta> preguntas;
			String condicionPregunta = "Encuesta_id"+rs.getInt("id");
			preguntas = daoPregunta.consultarTodos(condicionPregunta);
			
			encuesta.setPreguntas(preguntas);
			
		}
		
		return encuesta;
	}

}
