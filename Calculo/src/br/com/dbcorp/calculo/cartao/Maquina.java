package br.com.dbcorp.calculo.cartao;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class Maquina {

	private String nome;
	private List<Tipo> tipos;
	
	@XmlAttribute
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@XmlElement(name="tipo")
	public List<Tipo> getTipos() {
		return tipos;
	}
	public void setTipos(List<Tipo> tipos) {
		this.tipos = tipos;
	}
}
