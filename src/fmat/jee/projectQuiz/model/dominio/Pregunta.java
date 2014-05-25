package fmat.jee.projectQuiz.model.dominio;

import java.util.ArrayList;

public class Pregunta {
	private int id;
	private TipoPregunta tipoPregunta;
	private String pregunta;
	private int idEncuesta;
	private ArrayList<OpcionMultiple> opciones;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public TipoPregunta getTipoPregunta() {
		return tipoPregunta;
	}
	public void setTipoPregunta(TipoPregunta tipoPregunta) {
		this.tipoPregunta = tipoPregunta;
	}
	public String getPregunta() {
		return pregunta;
	}
	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}
	public int getIdEncuesta() {
		return idEncuesta;
	}
	public void setIdEncuesta(int idEncuesta) {
		this.idEncuesta = idEncuesta;
	}
	public ArrayList<OpcionMultiple> getOpciones() {
		return opciones;
	}
	public void setOpciones(ArrayList<OpcionMultiple> opciones) {
		this.opciones = opciones;
	}
	
	
}
