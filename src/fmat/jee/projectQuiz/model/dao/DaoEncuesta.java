package fmat.jee.projectQuiz.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.mysql.jdbc.Connection;

import fmat.jee.projectQuiz.model.dominio.Categoria;
import fmat.jee.projectQuiz.model.dominio.Encuesta;
import fmat.jee.projectQuiz.model.dominio.Pregunta;
import fmat.jee.projectQuiz.model.dominio.Rol;
import fmat.jee.projectQuiz.model.dominio.Usuario;

public class DaoEncuesta extends AbstractDao<Encuesta>{

	@Override
	public boolean agregar(Encuesta encuesta) throws SQLException {
		// TODO Auto-generated method stub
		Connection conexion;
		conexion = (Connection) AbstractDao.getConexion();		
		java.sql.Statement st = conexion.createStatement();
		String nombre=encuesta.getNombre();
		Categoria categoria = encuesta.getCategoria();
		int idCategoria = categoria.getId();
		int carpeta=encuesta.getIdCarpeta();
		Date fechaInicio=encuesta.getFechaInicio();
		Date fechaFin=encuesta.getFechaFin();
		Usuario usuario = encuesta.getUsuario();
		int idUsuario = usuario.getId();
		ArrayList<Pregunta> arrayPreguntas = encuesta.getPreguntas();
		boolean resultado=false;
		try{
		st.executeUpdate("INSERT INTO encuesta (nombre, fechaInicio, fechaFin, Usuario_id, CarpetasPersonal_id, Categorias_id) VALUES ('"+nombre+"','2014-05-28','2014-05-31',"+idUsuario+","+carpeta+","+idCategoria+")",java.sql.Statement.RETURN_GENERATED_KEYS);
		
		ResultSet r = st.getGeneratedKeys();
		int id = 0;
		DaoPregunta daopregunta = new DaoPregunta();
		if (r.first()){
			id = r.getInt(1);
			for (Pregunta pregunta : arrayPreguntas) {
				pregunta.setIdEncuesta(id);
				daopregunta.agregar(pregunta);
			}
		}
		resultado=true;
		}catch(Exception e){
			e.printStackTrace();
			resultado=false;
		}
		return resultado;
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
	
	public int obtenerUltimoId() throws SQLException{
		Connection conexion;
		conexion = (Connection) AbstractDao.getConexion();		
		java.sql.Statement st = conexion.createStatement();
		String Query = "SELECT id FROM encuesta WHERE id=(SELECT max(id) FROM encuesta)";
		ResultSet rs = st.executeQuery(Query);
		if(rs.next()){
			return rs.getInt("id");
		}else{
		return 0;
		}
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
			
			encuesta.setIdCarpeta(rs.getInt("CarpetasPersonal_id"));
			
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
			
			encuesta.setIdCarpeta(rs.getInt("CarpetasPersonal_id"));
			
			DaoPregunta daoPregunta = new DaoPregunta();
			ArrayList<Pregunta> preguntas;
			String condicionPregunta = "Encuesta_id"+rs.getInt("id");
			preguntas = daoPregunta.consultarTodos(condicionPregunta);
			
			encuesta.setPreguntas(preguntas);
			
		}
		
		return encuesta;
	}

}
