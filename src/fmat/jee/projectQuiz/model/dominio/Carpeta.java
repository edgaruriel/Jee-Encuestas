package fmat.jee.projectQuiz.model.dominio;

import java.util.ArrayList;

public class Carpeta {
	private int id;
	private String nombre;
	private int idUsuario;
	private ArrayList<Encuesta> encuestas;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
		
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public ArrayList<Encuesta> getEncuestas() {
		return encuestas;
	}
	public void setEncuestas(ArrayList<Encuesta> encuestas) {
		this.encuestas = encuestas;
	}
	
	
}
