package fmat.jee.projectQuiz.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;

import fmat.jee.projectQuiz.model.dominio.Carpeta;
import fmat.jee.projectQuiz.model.dominio.Categoria;

public class DaoCategoria extends AbstractDao<Categoria>{

	@Override
	public boolean agregar(Categoria entidad) throws SQLException {
		// TODO Auto-generated method stub
		Connection conexion;
		conexion = (Connection) AbstractDao.getConexion();		
		java.sql.Statement st = conexion.createStatement();
		String nombre = entidad.getNombre();
		String Query = "INSERT INTO categorias (nombre) VALUES ('"+nombre+"')";
		boolean resultado = true;
		st.executeUpdate(Query);
		return resultado;
	}

	@Override
	public boolean eliminar(String condicion) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean modificar(Categoria entidad) throws SQLException {
		// TODO Auto-generated method stub
		Connection conexion;
		conexion = (Connection) AbstractDao.getConexion();		
		java.sql.Statement st = conexion.createStatement();
		int id = entidad.getId();
		String nombre = entidad.getNombre();
		String Query = "UPDATE categorias SET nombre='" +nombre + "' WHERE id= '"+ id+"'";
		boolean resultado = true;
		st.executeUpdate(Query);
		return resultado;
	}

	@Override
	public ArrayList<Categoria> consultarTodos(String condicion)
			throws SQLException {
		// TODO Auto-generated method stub
		Connection conexion;
		conexion = (Connection) AbstractDao.getConexion();		
		java.sql.Statement st = conexion.createStatement();
		String Query = "SELECT * FROM categorias";
		ResultSet rs = st.executeQuery(Query);
		ArrayList<Categoria> categorias = new ArrayList<Categoria>();
		while(rs.next()){
			Categoria categoria = new Categoria();
			categoria.setId(rs.getInt("id"));
			categoria.setNombre(rs.getString("nombre"));
			categorias.add(categoria);			
		}	
		return categorias;
	}

	@Override
	public Categoria consultar(String condicion) throws SQLException {
		// TODO Auto-generated method stub
		Connection conexion;
		conexion = (Connection) AbstractDao.getConexion();		
		java.sql.Statement st = conexion.createStatement();
		String Query = "SELECT * FROM categorias WHERE "+condicion;
		ResultSet rs = st.executeQuery(Query);
		
		Categoria categoria = new Categoria();
		if(rs.next()){
			categoria.setId(rs.getInt("id"));
			categoria.setNombre(rs.getString("nombre"));
		}
		return categoria;
	}

}
