package fmat.jee.projectQuiz.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;

import fmat.jee.projectQuiz.model.Rol;
import fmat.jee.projectQuiz.model.Usuario;
import fmat.jee.projectQuiz.model.servicio.ServicioRol;

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
		st.executeUpdate("INSERT INTO usuario (nombre, primerApellido, segundoApellido, nombreUsuario, contrasena, correo, Rol_id) VALUES ('"+nombre+"','"+pApellido+"','"+sApellido+"','"+nombreUsuario+"','"+contrasenia+"','"+correo+"',"+id+")");
		resultado=true;
		}catch(Exception e){
			e.printStackTrace();
			resultado=false;
		}
		return resultado;
	}

	@Override
	public boolean eliminar(String condicion) throws SQLException{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean modificar(Usuario usuario) throws SQLException{
		// TODO Auto-generated method stub
		Connection conexion;
		conexion = (Connection) AbstractDao.getConexion();		
		java.sql.Statement st = conexion.createStatement();
		int idUsuario = usuario.getId();
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
		st.executeUpdate("UPDATE usuario SET nombre='"+nombre+"',primerApellido='"+pApellido+"', segundoApellido='"+sApellido+"', nombreUsuario='"+nombreUsuario+"', contrasena='"+contrasenia+"', correo='"+correo+"', Rol_id='"+id+"' WHERE id='"+idUsuario+"'");
		resultado=true;
		}catch(Exception e){
			e.printStackTrace();
			resultado=false;
		}
		return resultado;
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
			ServicioRol servicioRol = new ServicioRol();
			Rol rol = servicioRol.obtenerRol(rs.getString("Rol_id"));
			usuario.setRol(rol);
		}else{
			usuario.setId(0);
		}
		return usuario;
	}

}
