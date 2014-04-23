package fmat.jee.projectQuiz.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;

import fmat.jee.projectQuiz.model.Rol;
import fmat.jee.projectQuiz.model.Usuario;

public class DaoRol extends AbstractDao<Rol>{

	@Override
	public boolean agregar(Rol entidad) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean eliminar(String condicion) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean modificar(Rol entidad) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Rol> consultarTodos(String condicion) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rol consultar(String condicion) throws SQLException {
		// TODO Auto-generated method stub
		Connection conexion;
		conexion = (Connection) AbstractDao.getConexion();		
		java.sql.Statement st = conexion.createStatement();
		
		ResultSet rs = st.executeQuery("SELECT * FROM rol WHERE "+condicion);
		Rol rol = new Rol();
		if(rs.next()){
			rol.setId(rs.getInt("id"));
			rol.setNombre(rs.getString("nombre"));
		}else{
			rol.setId(0);
		}
		return rol;
	}

}
