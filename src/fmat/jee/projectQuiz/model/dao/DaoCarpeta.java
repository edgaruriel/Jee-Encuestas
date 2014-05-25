package fmat.jee.projectQuiz.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;

import fmat.jee.projectQuiz.model.dominio.Carpeta;
import fmat.jee.projectQuiz.model.dominio.Encuesta;

public class DaoCarpeta extends AbstractDao<Carpeta>{

	@Override
	public boolean agregar(Carpeta entidad) throws SQLException {
		// TODO Auto-generated method stub
		Connection conexion;
		conexion = (Connection) AbstractDao.getConexion();		
		java.sql.Statement st = conexion.createStatement();
		String nombre = entidad.getNombre();
		String idUsuario = Integer.toString(entidad.getIdUsuario());
		String Query = "INSERT INTO capetaspersonal (nombre, Usuario_id) VALUES ('"+nombre+"', "+idUsuario+")";
		boolean resultado = false;		
		try{
			st.executeUpdate(Query);
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
		String [] condicionArray = condicion.split("=");
		int idCarpeta = Integer.parseInt(condicionArray[1]);
		String condicionEliminarEncuesta = "CapetasPersonal_id="+idCarpeta;
		
		DaoEncuesta daoEncuesta = new DaoEncuesta();
		daoEncuesta.eliminar(condicionEliminarEncuesta);
		
		//String sqlE = "DELETE FROM encuesta WHERE CapetasPersonal_id"+idCarpeta;		
		String sqlC = "DELETE FROM carpetaspersonal WHERE "+condicion;
		//st.executeUpdate(sqlE);
		st.executeUpdate(sqlC);
		return resultado;
	}

	@Override
	public boolean modificar(Carpeta entidad) throws SQLException {
		// TODO Auto-generated method stub
		Connection conexion;
		conexion = (Connection) AbstractDao.getConexion();		
		java.sql.Statement st = conexion.createStatement();
		int id = entidad.getId();
		String nombre = entidad.getNombre();
		boolean resultado = true;
		String sql = "UPDATE carpetaspersonal SET nombre='" +nombre + "' WHERE id= '"+ id+"'";
		st.executeUpdate(sql);	
		return resultado;
		
	}

	@Override
	public ArrayList<Carpeta> consultarTodos(String condicion)
			throws SQLException {
		// TODO Auto-generated method stub
		Connection conexion;
		conexion = (Connection) AbstractDao.getConexion();		
		java.sql.Statement st = conexion.createStatement();
		String Query = "SELECT * FROM capetaspersonal WHERE "+condicion;
		ResultSet rs = st.executeQuery(Query);
		ArrayList<Carpeta> carpetas = new ArrayList<Carpeta>();
		while(rs.next()){
			Carpeta carpeta = new Carpeta();
			carpeta.setId(rs.getInt("id"));
			carpeta.setNombre(rs.getString("nombre"));
			carpeta.setIdUsuario(rs.getInt("Usuario_id"));
			DaoEncuesta daoEncuesta = new DaoEncuesta();
			String condicionEncuesta = "CapetasPersonal_id="+rs.getInt("id");
			carpeta.setEncuestas(daoEncuesta.consultarTodos(condicionEncuesta));
			
			carpetas.add(carpeta);			
		}
		
		return carpetas;
	}

	@Override
	public Carpeta consultar(String condicion) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
