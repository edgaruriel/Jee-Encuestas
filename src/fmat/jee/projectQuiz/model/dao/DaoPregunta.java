package fmat.jee.projectQuiz.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;

import fmat.jee.projectQuiz.model.dominio.OpcionMultiple;
import fmat.jee.projectQuiz.model.dominio.Pregunta;
import fmat.jee.projectQuiz.model.dominio.TipoPregunta;

public class DaoPregunta extends AbstractDao<Pregunta>{

	@Override
	public boolean agregar(Pregunta entidad) throws SQLException {
		// TODO Auto-generated method stub
		Connection conexion;
		conexion = (Connection) AbstractDao.getConexion();		
		java.sql.Statement st = conexion.createStatement();
		int idTipoPregunta = entidad.getTipoPregunta().getId();
		String pregunta = entidad.getPregunta();
		int idEncuesta = entidad.getIdEncuesta();
		
		String Query = "INSERT INTO preguntas (TipoPregunta_id, pregunta, Encuesta_id) VALUES ('"+idTipoPregunta+"','"+pregunta+"', "+idEncuesta+")";
		st.executeUpdate(Query,java.sql.Statement.RETURN_GENERATED_KEYS);
		
		ResultSet r = st.getGeneratedKeys();
		int id = 0;
		if (r.first()){
			id = r.getInt(1);
		}
		
		//el tipo de pregunta es MULTIPLE 
		if(idTipoPregunta == 2){
			ArrayList<OpcionMultiple> opciones = entidad.getOpciones();
			for (OpcionMultiple opcion : opciones) {
				String opcionPregunta = opcion.getOpcion();				
				String QueryOpcion = "INSERT INTO opcionesmultiples (opcion, Preguntas_id) VALUES ('"+opcionPregunta+"','"+id+"')";
				st.executeUpdate(QueryOpcion);
			}
		}
		
		if(idTipoPregunta == 3){
			for(int i=1 ; i<=10; i++){
				String QueryOpcion = "INSERT INTO opcionesmultiples (opcion, Preguntas_id) VALUES ('"+i+"','"+id+"')";
				st.executeUpdate(QueryOpcion);
			}
		}
		boolean resultado = true;
		return resultado;
	}

	@Override				//Solo manda el id de la encuesta
	public boolean eliminar(String id) throws SQLException {
		// TODO Auto-generated method stub
		boolean resultado = true;
		Connection conexion;
		conexion = (Connection) AbstractDao.getConexion();	
		
		ArrayList<Pregunta> preguntas =	consultarTodos("Encuesta_id="+id);
		
		for (Pregunta pregunta : preguntas) {
			java.sql.Statement st = conexion.createStatement();
			String sqlR = "DELETE FROM respuesta WHERE Preguntas_id="+pregunta.getId();		
			String sqlOR = "DELETE FROM opcionmultiple_tiene_respuestas WHERE Preguntas_id="+pregunta.getId();
			String sqlO = "DELETE FROM opcionesmultiples WHERE Preguntas_id="+pregunta.getId();
			String sqlP = "DELETE FROM preguntas WHERE id="+pregunta.getId();
			st.executeUpdate(sqlR);
			st.executeUpdate(sqlOR);
			st.executeUpdate(sqlO);
			st.executeUpdate(sqlP);
		}
		
		return resultado;
	}

	@Override
	public boolean modificar(Pregunta entidad) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}
	

	@Override
	public ArrayList<Pregunta> consultarTodos(String condicion)
			throws SQLException {
		// TODO Auto-generated method stub
		Connection conexion;
		conexion = (Connection) AbstractDao.getConexion();		
		java.sql.Statement st = conexion.createStatement();
		String Query = "SELECT * FROM preguntas WHERE "+condicion;
		
		ResultSet rs = st.executeQuery(Query);
		ArrayList<Pregunta> preguntas = new ArrayList<Pregunta>();
		while(rs.next()){
			Pregunta pregunta = new Pregunta();
			pregunta.setId(rs.getInt("id"));
			pregunta.setIdEncuesta(rs.getInt("Encuesta_id"));
			pregunta.setPregunta(rs.getString("pregunta"));
			int idTipoPregunta = rs.getInt("TipoPregunta_id");
			String QueryT = "SELECT * FROM tipopregunta WHERE id="+idTipoPregunta;
			java.sql.Statement stt = conexion.createStatement();
			ResultSet r = stt.executeQuery(QueryT);
			if(r.next()){
				TipoPregunta tipoPregunta = new TipoPregunta();
				tipoPregunta.setId(r.getInt("id"));
				tipoPregunta.setTipo(r.getString("tipo"));
				pregunta.setTipoPregunta(tipoPregunta);
			}else{
				TipoPregunta tipoPregunta = new TipoPregunta();
				tipoPregunta = null;		
				pregunta.setTipoPregunta(tipoPregunta);
			}
			
			//int tipoP = rs.getInt("TipoPregunta_id");
			//Abierta
			if(idTipoPregunta == 1){
				ArrayList<OpcionMultiple> opciones = new ArrayList<OpcionMultiple>();
				opciones = null;
				pregunta.setOpciones(opciones);
				//Multiple
			}else if (idTipoPregunta == 2) {
				ArrayList<OpcionMultiple> opciones = new ArrayList<OpcionMultiple>();
				String QueryOpcion = "SELECT * FROM opcionesmultiples WHERE Preguntas_id ="+rs.getInt("id");
				java.sql.Statement sttt = conexion.createStatement();
				ResultSet rss = sttt.executeQuery(QueryOpcion);
				while(rss.next()){
					OpcionMultiple opcionMultiple = new OpcionMultiple();
					opcionMultiple.setId(rss.getInt("id"));
					opcionMultiple.setOpcion(rss.getString("opcion"));
					opcionMultiple.setIdPregunta(rss.getInt("Preguntas_id"));
					opciones.add(opcionMultiple);
				}
				pregunta.setOpciones(opciones);
				//Calificación
			}else if (idTipoPregunta == 3) {
				ArrayList<OpcionMultiple> opciones = new ArrayList<OpcionMultiple>();
				String QueryOpcion = "SELECT * FROM opcionesmultiples WHERE Preguntas_id ="+rs.getInt("id");
				java.sql.Statement stttt = conexion.createStatement();
				ResultSet rss = stttt.executeQuery(QueryOpcion);
				while(rss.next()){
					OpcionMultiple opcionMultiple = new OpcionMultiple();
					opcionMultiple.setId(rss.getInt("id"));
					opcionMultiple.setOpcion(rss.getString("opcion"));
					opcionMultiple.setIdPregunta(rss.getInt("Preguntas_id"));
					opciones.add(opcionMultiple);
				}
				pregunta.setOpciones(opciones);
			}
			
			
			preguntas.add(pregunta);
		}
		
		return preguntas;
	}
	
	public ArrayList<Pregunta> consultarTodosContestado(String condicion){
		
		return null;
	}

	@Override
	public Pregunta consultar(String condicion) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public int consultarRespuestas(String condicion) throws SQLException {
		// TODO Auto-generated method stub
		int cantidad = 0;
		Connection conexion;
		conexion = (Connection) AbstractDao.getConexion();		
		java.sql.Statement st = conexion.createStatement();
		String Query = "SELECT * FROM opcionmultiple_tiene_respuestas AS opr WHERE "+condicion;
		
		ResultSet rs = st.executeQuery(Query);
		while(rs.next()){
			cantidad++;
		}

		return cantidad;
	}
	
	public ArrayList<String> consultarRespuestasAbiertas(String condicion) throws SQLException {
		// TODO Auto-generated method stub
		Connection conexion;
		conexion = (Connection) AbstractDao.getConexion();		
		java.sql.Statement st = conexion.createStatement();
		ArrayList<String> respuestas = new ArrayList<>();
		String Query = "SELECT * FROM respuesta AS r WHERE "+condicion;
		
		ResultSet rs = st.executeQuery(Query);
		while(rs.next()){
			respuestas.add(rs.getString("respuesta"));
		}
		return respuestas;
	}

}
