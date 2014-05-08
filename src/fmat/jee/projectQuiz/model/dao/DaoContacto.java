package fmat.jee.projectQuiz.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;

import fmat.jee.projectQuiz.model.Contacto;

public class DaoContacto extends AbstractDao<Contacto>{

	@Override
	public boolean agregar(Contacto entidad) throws SQLException {
		// TODO Auto-generated method stub
		Connection conexion;
		conexion = (Connection) AbstractDao.getConexion();		
		java.sql.Statement st = conexion.createStatement();
		String nombre = entidad.getNombre();
		String correo = entidad.getCorreo();
		int usuarioId = entidad.getUsuarioId();
		String Query = "INSERT INTO contactos (nombre, correo, Usuario_id) VALUES ('"+nombre+"','"+correo+"', "+usuarioId+")";
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
		String sql = "DELETE FROM contactos WHERE "+condicion;
		st.executeUpdate(sql);
		return resultado;
	}

	@Override
	public boolean modificar(Contacto entidad) throws SQLException {
		// TODO Auto-generated method stub
	//	System.out.print("Inicio de modificar dao");
		Connection conexion;
		conexion = (Connection) AbstractDao.getConexion();		
		java.sql.Statement st = conexion.createStatement();
		int id = entidad.getId();
		String nombre = entidad.getNombre();
		String correo = entidad.getCorreo();
		boolean resultado = true;
		String sql = "UPDATE contactos SET nombre='" +nombre + "', correo='" + correo+ "' WHERE id= '"+ id+"'";
		st.executeUpdate(sql);
	//	System.out.print("Fin de modificar dao");
		return resultado;
	}

	@Override
	public ArrayList<Contacto> consultarTodos(String condicion)
			throws SQLException {
		// TODO Auto-generated method stub
		
		Connection conexion;
		conexion = (Connection) AbstractDao.getConexion();		
		java.sql.Statement st = conexion.createStatement();
		String Query = "SELECT * FROM contactos WHERE "+condicion;
		ResultSet rs = st.executeQuery(Query);
		ArrayList<Contacto> contactos = new ArrayList<Contacto>();
		while(rs.next()){
			Contacto contacto = new Contacto();
			contacto.setId(rs.getInt("id"));
			contacto.setNombre(rs.getString("nombre"));
			contacto.setCorreo(rs.getString("correo"));
			contactos.add(contacto);
		}
		
		return contactos;
	}

	@Override
	public Contacto consultar(String condicion) throws SQLException {
		// TODO Auto-generated method stub
		Connection conexion;
		conexion = (Connection) AbstractDao.getConexion();		
		java.sql.Statement st = conexion.createStatement();
		String Query = "SELECT * FROM contactos WHERE "+condicion;
		ResultSet rs = st.executeQuery(Query);
		Contacto contacto = new Contacto();
	
		if(rs.next()){
			contacto.setId(rs.getInt("id"));
			contacto.setNombre(rs.getString("nombre"));
			contacto.setCorreo(rs.getString("correo"));
		}
		
		return contacto;
	}

}
