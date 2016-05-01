package br.com.dbcorp.calculo.cartao;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="maquinas")
public class Maquinas {
	
	private List<Maquina> maquina;
	
    @XmlElement(name="maquina")
	public List<Maquina> getMaquina() {
		return maquina;
	}
	public void setMaquina(List<Maquina> maquina) {
		this.maquina = maquina;
	}
}
