package fmat.jee.projectQuiz.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;

import fmat.jee.projectQuiz.model.Usuario;

public class DaoUsuario extends AbstractDao<Usuario>{

	@Override
	public boolean agregar(Usuario entidad) throws SQLException{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean eliminar(String condicion) throws SQLException{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean modificar(Usuario entidad) throws SQLException{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Usuario> consultarTodos(String condicion) throws SQLException{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuario consultar(String condicion) throws SQLException{
		// TODO Auto-generated method stub
		Connection conexion;
		conexion = (Connection) AbstractDao.getConexion();		
		java.sql.Statement st = conexion.createStatement();
		
		ResultSet rs = st.executeQuery("SELECT * FROM usuario WHERE "+condicion);
		Usuario usuario = new Usuario();
		if(rs.next()){		
			usuario.setId(rs.getInt("id"));
			usuario.setNombre(rs.getString("nombre"));
			usuario.setPrimerApellido(rs.getString("primerApellido"));
			usuario.setSegundoApellido(rs.getString("segundoApellido"));
			usuario.setCorreo(rs.getString("correo"));
			usuario.setNombreUsuario(rs.getString("nombreUsuario"));
			usuario.setContrasena(rs.getString("contrasena"));
			//usuario.setRol(rol);
		}else{
			usuario.setId(0);
		}
		return usuario;
	}

}
