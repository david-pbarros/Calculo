package br.com.dbcorp.calculo.cartao;

import javax.swing.DefaultListModel;
import javax.xml.bind.annotation.XmlElement;

public class Tipo {

	private String nome;
	private double taxa;
	private DefaultListModel<String> listaValores = new DefaultListModel<>();
	
	@XmlElement(name="nome")
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@XmlElement(name="taxa")
	public double getTaxa() {
		return taxa;
	}
	public void setTaxa(double taxa) {
		this.taxa = taxa;
	}
	
	public DefaultListModel<String> getListaValores() {
		return listaValores;
	}
	public void setListaValores(DefaultListModel<String> listaValores) {
		this.listaValores = listaValores;
	}
}
