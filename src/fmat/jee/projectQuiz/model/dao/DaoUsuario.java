package fmat.jee.projectQuiz.model.dao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;




import com.mysql.jdbc.Connection;

import fmat.jee.projectQuiz.model.dominio.Rol;
import fmat.jee.projectQuiz.model.dominio.Usuario;
import fmat.jee.projectQuiz.model.servicio.ServicioRol;

public class DaoUsuario extends AbstractDao<Usuario>{
	
	public boolean existeRegistro(String condicion) throws SQLException{
		Connection conexion;
		conexion = (Connection) AbstractDao.getConexion();		
		java.sql.Statement st = conexion.createStatement();
		String sql = "SELECT * FROM usuario WHERE nombreUsuario = '"+condicion+"'";
		ResultSet rs = st.executeQuery(sql);
		if(rs.next()){
			return true;
		}else{
			return false;
		}
	}

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
		if(existeRegistro(nombreUsuario)){
			resultado=false;
		}else{
		
		try{
		st.executeUpdate("INSERT INTO usuario (nombre, primerApellido, segundoApellido, nombreUsuario, contrasena, correo, Rol_id) VALUES ('"+nombre+"','"+pApellido+"','"+sApellido+"','"+nombreUsuario+"','"+contrasenia+"','"+correo+"',"+id+")");
		resultado=true;
		}catch(Exception e){
			e.printStackTrace();
			resultado=false;
		}
		}
		return resultado;
	}

	@Override
	public boolean eliminar(String condicion) throws SQLException{
		// TODO Auto-generated method stub
		boolean resultado = true;
		Connection conexion;
		conexion = (Connection) AbstractDao.getConexion();		
		
		
		DaoCarpeta daoCarpeta = new DaoCarpeta();
		String[] parametros = condicion.split("=");
		String condicion2 = "Usuario_id="+parametros[1];		
		/*for (int i = 0; i < parametros.length; i++) {
			System.out.println("Parametros: "+parametros[i]);
		}*/		
		daoCarpeta.eliminar(condicion2);
		
		DaoContacto daoContacto = new DaoContacto();
		daoContacto.eliminar(condicion2);
		String sql2 = "DELETE FROM usuario WHERE "+condicion;
		java.sql.Statement st2 = conexion.createStatement();
		st2.executeUpdate(sql2);
		return resultado;
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
		Connection conexion;
		conexion = (Connection) AbstractDao.getConexion();		
		java.sql.Statement st = conexion.createStatement();
		String Query = "SELECT * FROM usuario WHERE "+condicion;
		ResultSet rs = st.executeQuery(Query);
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		while(rs.next()){
			Usuario usuario = new Usuario();
			usuario.setId(rs.getInt("id"));
			usuario.setNombre(rs.getString("nombre"));
			usuario.setPrimerApellido(rs.getString("primerApellido"));
			usuario.setSegundoApellido(rs.getString("segundoApellido"));
			usuario.setCorreo(rs.getString("correo"));
			usuario.setNombreUsuario(rs.getString("nombreUsuario"));
			usuario.setContrasena(rs.getString("contrasena"));
			ServicioRol servicioRol = new ServicioRol();
			Rol rol = servicioRol.obtenerRolPorId(rs.getInt("Rol_id"));
			usuario.setRol(rol);
			usuarios.add(usuario);
		}
		
		return usuarios;
	}

	@Override
	public Usuario consultar(String condicion) throws SQLException{
		// TODO Auto-generated method stub
		Connection conexion;
		conexion = (Connection) AbstractDao.getConexion();		
		java.sql.Statement st = conexion.createStatement();
		String Query = "SELECT * FROM usuario WHERE "+condicion;
		ResultSet rs = st.executeQuery(Query);
		Usuario usuario = new Usuario();
		
		if(rs.next()){
			//System.out.print(Query);
			usuario.setId(rs.getInt("id"));
			usuario.setNombre(rs.getString("nombre"));
			usuario.setPrimerApellido(rs.getString("primerApellido"));
			usuario.setSegundoApellido(rs.getString("segundoApellido"));
			
			usuario.setCorreo(rs.getString("correo"));
			usuario.setNombreUsuario(rs.getString("nombreUsuario"));
			usuario.setContrasena(rs.getString("contrasena"));
			ServicioRol servicioRol = new ServicioRol();
			Rol rol = servicioRol.obtenerRolPorId(rs.getInt("Rol_id"));
			usuario.setRol(rol);
	
		}else{
			//System.out.print(Query);
			usuario.setId(0);
		}
		return usuario;
	}

}
