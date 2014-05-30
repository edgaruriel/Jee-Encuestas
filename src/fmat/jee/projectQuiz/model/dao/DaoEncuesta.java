package fmat.jee.projectQuiz.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;

import fmat.jee.projectQuiz.model.dominio.Encuesta;
import fmat.jee.projectQuiz.model.dominio.OpcionMultiple;
import fmat.jee.projectQuiz.model.dominio.Pregunta;

public class DaoEncuesta extends AbstractDao<Encuesta>{

	@Override
	public boolean agregar(Encuesta entidad) throws SQLException {
		// TODO Auto-generated method stub
		Connection conexion;
		conexion = (Connection) AbstractDao.getConexion();
		
		String correo = entidad.getCorreo();
		int idEncuesta = entidad.getId();
		
		ArrayList<Pregunta> preguntas = entidad.getPreguntas();
		for(Pregunta pregunta : preguntas){
			java.sql.Statement st = conexion.createStatement();
			int idPregunta = pregunta.getId();
		//	System.out.println("Pregunta: "+ pregunta.getPregunta());
			if(pregunta.getTipoPregunta().getTipo().equals("ABIERTA")){
		//		System.out.println("Respuesta: "+pregunta.getRespuesta());
			String respuesta = pregunta.getRespuesta();
			
			String Query = "INSERT INTO respuesta (Preguntas_id, respuesta) VALUES ("+idPregunta+",'"+respuesta+"')";
			st.executeUpdate(Query);
			}else{
				for(OpcionMultiple opcion: pregunta.getOpciones()){
					if(opcion.isRespuesta()){
					int idOpcionRespuesta = opcion.getId();
					String Query = "INSERT INTO opcionmultiple_tiene_respuestas (OpcionesMultiples_id, Preguntas_id) VALUES ("+idOpcionRespuesta+","+idPregunta+")";
					st.executeUpdate(Query);
					
					
		//				System.out.println("Respuesta: "+opcion.getOpcion());
					}
				}
			}
		}
		
		java.sql.Statement st = conexion.createStatement();
		String Query = "INSERT INTO contestados (correo, Encuesta_id) VALUES ('"+correo+"',"+idEncuesta+")";
		st.executeUpdate(Query);
		
		return true;
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
			String condicionPregunta = "Encuesta_id="+rs.getInt("id");
			preguntas = daoPregunta.consultarTodos(condicionPregunta);
			
			encuesta.setPreguntas(preguntas);
			
		}
		
		return encuesta;
	}
	
	public boolean consultarContestado(String condicion) throws SQLException{
		Connection conexion;
		conexion = (Connection) AbstractDao.getConexion();		
		java.sql.Statement st = conexion.createStatement();
		String Query = "SELECT * FROM contestados WHERE "+condicion;
		ResultSet rs = st.executeQuery(Query);
		boolean respuesta = false;
		
		if(rs.next()){
			respuesta = true;
		}else{
			respuesta = false;
		}
		
		return respuesta;
	}

}
