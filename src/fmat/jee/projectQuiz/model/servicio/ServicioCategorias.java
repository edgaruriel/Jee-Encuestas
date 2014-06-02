package fmat.jee.projectQuiz.model.servicio;

import java.sql.SQLException;
import java.util.ArrayList;

import fmat.jee.projectQuiz.model.dao.DaoCarpeta;
import fmat.jee.projectQuiz.model.dao.DaoCategoria;
import fmat.jee.projectQuiz.model.dao.DaoUsuario;
import fmat.jee.projectQuiz.model.dominio.Carpeta;
import fmat.jee.projectQuiz.model.dominio.Categoria;
import fmat.jee.projectQuiz.model.dominio.Usuario;

public class ServicioCategorias {
	
	public ArrayList<Categoria> obtenerCategorias(){
		DaoCategoria daoCategoria = new DaoCategoria();
		ArrayList<Categoria> categorias = null;
		try {
			categorias = daoCategoria.consultarTodos("");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return categorias;
	}
	
	public Categoria obtenerDatos(int id) {
		DaoCategoria dao = new DaoCategoria();
		Categoria categoria = null;
		String s = String.valueOf(id);
		try{
			categoria = dao.consultar("categorias.id="+s);
		}catch(Exception e){
			e.printStackTrace();
		}
		return categoria;
	}
	
	public boolean agregarCategoria(Categoria categoria){
		DaoCategoria dao = new DaoCategoria();
		boolean resultado = false;
		
		try {
			if(dao.agregar(categoria)){
				resultado = true;
			}else{
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultado= false;
		}
		return resultado;
	}
	
	public Categoria obtenerCategoriaId(int id){
		DaoCategoria dao = new DaoCategoria();
		String condicion = "id="+id;
		Categoria categoria = null;
		
		try {
			categoria = dao.consultar(condicion);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return categoria;
	}
	
	public boolean actualizarCategoria(Categoria categoria){
		DaoCategoria dao = new DaoCategoria();
		boolean resultado = false;
		
		try {
			resultado = dao.modificar(categoria);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultado = false;
		}
		
		return resultado;
	}
	
	public boolean eliminarCategoria(int id){
		DaoCategoria dao = new DaoCategoria();
		boolean resultado = false;
		String condicion = "id="+id;
		
		try {
			resultado = dao.eliminar(condicion);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultado;
	}
}
