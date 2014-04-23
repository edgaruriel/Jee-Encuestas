package fmat.jee.projectQuiz.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;

import fmat.jee.projectQuiz.model.Rol;
import fmat.jee.projectQuiz.model.Usuario;

public class DaoUsuario extends AbstractDao<Usuario>{

	@Override
	public boolean agregar(Usuario usuario) throws SQLException{
		// TODO Auto-generated method stub
		Connection conexion;
		conexion = (Connection) AbstractDao.getConexion();		
		java.sql.Statement st = conexion.createStatement();
		String nombre=usuario.getNombre();
		String pApellido=usuario.getPrimerApellido();
		String sApellido=usuario.getSegundoApellido();
		String nombreUsuario=usuario.getNombreUsuario();
		String contrasenia=usuario.getContrasena();
		String correo = usuario.getCorreo();
		Rol tipoUsuario=usuario.getRol();
		int id = tipoUsuario.getId();
		System.out.println(nombre+pApellido+sApellido+nombreUsuario+contrasenia+correo+tipoUsuario.getNombre());
		boolean resultado=false;
		try{
		ResultSet rs = st.executeQuery("INSERT INTO usuario (nombre, primerApellido, segundoApellido, nombreUsuario, contrasena, correo, Rol_id) VALUES ('"+nombre+"','"+pApellido+"','"+sApellido+"','"+nombreUsuario+"','"+contrasenia+"','"+correo+"','"+id+"')");
		if(rs.next()){
			resultado= true;
		}else{
			resultado= false;
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		return resultado;
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
