package br.capgemini.desafio.agencia.model;

public class ResultadoProjecao {

	private Double totalValorInvestido;

	private Long qtdMaxVisualizacao;

	private Long qtdMaxCliques;

	private Long qtdMaxCompartilhamentos;

	public Double getTotalValorInvestido() {
		return totalValorInvestido;
	}

	public void setTotalValorInvestido(Double totalValorInvestido) {
		this.totalValorInvestido = totalValorInvestido;
	}

	public Long getQtdMaxVisualizacao() {
		return qtdMaxVisualizacao;
	}

	public void setQtdMaxVisualizacao(Long qtdMaxVisualizacao) {
		this.qtdMaxVisualizacao = qtdMaxVisualizacao;
	}

	public Long getQtdMaxCliques() {
		return qtdMaxCliques;
	}

	public void setQtdMaxCliques(Long qtdMaxCliques) {
		this.qtdMaxCliques = qtdMaxCliques;
	}

	public Long getQtdMaxCompartilhamentos() {
		return qtdMaxCompartilhamentos;
	}

	public void setQtdMaxCompartilhamentos(Long qtdMaxCompartilhamentos) {
		this.qtdMaxCompartilhamentos = qtdMaxCompartilhamentos;
	}

}
