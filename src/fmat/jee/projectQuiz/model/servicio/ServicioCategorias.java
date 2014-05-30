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
}
