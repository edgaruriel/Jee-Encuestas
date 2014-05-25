package fmat.jee.projectQuiz.model.dominio;

import java.sql.Date;
import java.util.ArrayList;

public class Encuesta {
	private int id;
	private String nombre;
	private Date fechaInicio;
	private Date fechaFin;
	private Usuario usuario;
	private Categoria categoria;
	private int idCarpeta;
	private ArrayList<Pregunta> preguntas;
	
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
	
	
	
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Date getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public int getIdCarpeta() {
		return idCarpeta;
	}
	public void setIdCarpeta(int idCarpeta) {
		this.idCarpeta = idCarpeta;
	}
	public ArrayList<Pregunta> getPreguntas() {
		return preguntas;
	}
	public void setPreguntas(ArrayList<Pregunta> preguntas) {
		this.preguntas = preguntas;
	}
	
	
	
}
