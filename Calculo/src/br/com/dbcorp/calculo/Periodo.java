package br.com.dbcorp.calculo;

import java.util.HashMap;
import java.util.Map;

import br.com.dbcorp.calculo.cartao.Maquinas;

public class Periodo {
	
	private double totGeral;
	private double totDinheiro;
	private double totCartao;
	private Map<String, Double> valoresTipo = new HashMap<>();
	private Map<String, Double> taxasTipo = new HashMap<>();
	private Map<String, Integer> qtdTipo = new HashMap<>();
	private Maquinas maquinas;
	
	public void limpar() {
		this.valoresTipo.clear();
		this.taxasTipo.clear();
		this.qtdTipo.clear();
	}
	
	public Double getTotGeral() {
		return totGeral;
	}
	public void setTotGeral(Double totGeral) {
		this.totGeral = totGeral;
	}
	public Double getTotDinheiro() {
		return totDinheiro;
	}
	public void setTotDinheiro(Double totDinheiro) {
		this.totDinheiro = totDinheiro;
	}
	public Double getTotCartao() {
		return totCartao;
	}
	public void setTotCartao(Double totCartao) {
		this.totCartao = totCartao;
	}
	public Map<String, Double> getValoresTipo() {
		return valoresTipo;
	}
	public void setValoresTipo(Map<String, Double> valoresTipo) {
		this.valoresTipo = valoresTipo;
	}
	public Map<String, Double> getTaxasTipo() {
		return taxasTipo;
	}
	public void setTaxasTipo(Map<String, Double> taxasTipo) {
		this.taxasTipo = taxasTipo;
	}
	public Map<String, Integer> getQtdTipo() {
		return qtdTipo;
	}
	public void setQtdTipo(Map<String, Integer> qtdTipo) {
		this.qtdTipo = qtdTipo;
	}
	public Maquinas getMaquinas() {
		return maquinas;
	}
	public void setMaquinas(Maquinas maquinas) {
		this.maquinas = maquinas;
	}
}