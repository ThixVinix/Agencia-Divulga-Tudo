package br.capgemini.desafio.agencia.calculator;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import br.capgemini.desafio.agencia.model.ResultadoProjecao;

public class Calculadora {

	private Double visualizacoes;
	private Double totalCliques;
	private Double compartilhamentos;
	private Double visualizacaoDeCompartilhamento;
	private Long totalCompSeq;
	private Double totalVisualizacao;

	private ResultadoProjecao result;

	public Calculadora() {
		this.visualizacoes = 0d;
		this.totalCliques = 0d;
		this.compartilhamentos = 0d;
		this.visualizacaoDeCompartilhamento = 0d;
		this.totalCompSeq = 0L;
		this.totalVisualizacao = 0d;
		setResult(new ResultadoProjecao());
	}

	public ResultadoProjecao calcularProjecaoAnuncio(Double valorInvestido, Date dataInicio, Date dataTermino) {

		long diffMilliseconds = Math.abs(dataTermino.getTime() - dataInicio.getTime());
		long dias = TimeUnit.DAYS.convert(diffMilliseconds, TimeUnit.MILLISECONDS);

		Double totalValorInvestido;
		if (dias == 0l) {
			totalValorInvestido = valorInvestido * 1;
		} else {
			totalValorInvestido = valorInvestido * dias;
		}

		visualizacoes = calcularVisualizacoes(totalValorInvestido);
		totalCliques = calcularCliques(visualizacoes);
		compartilhamentos = calcularCompartilhamentos(totalCliques);
		visualizacaoDeCompartilhamento = calcularVisualizacoesCompartilhadasNaoSequenciais(compartilhamentos);
		totalCompSeq = calcularVisualizacoesCompartilhadasSequenciais(visualizacaoDeCompartilhamento);

		totalVisualizacao = visualizacoes + visualizacaoDeCompartilhamento + totalCompSeq;

		getResult().setQtdMaxCliques(totalCliques.longValue());
		getResult().setQtdMaxCompartilhamentos(visualizacaoDeCompartilhamento.longValue() + totalCompSeq.longValue());
		getResult().setTotalValorInvestido(totalValorInvestido);
		getResult().setQtdMaxVisualizacao(totalVisualizacao.longValue());

		return getResult();
	}

	/**
	 * To know the number of views, we must know the amount of the value invested.
	 * Because for every 30 people who see the original AD (no shared) is invested
	 * 1,00 real.
	 */
	private Double calcularVisualizacoes(Double valorInvestido) {
		Double visu = (valorInvestido * 30) / 1;
		return (double) Math.round(visu);
	}

	/**
	 * The click depends on the visualization. Depending on the amount invested, the
	 * number of clicks may increase or not. It is known that for every 20 people
	 * who click on the ad 3 share on social networks.
	 */
	private Double calcularCliques(Double visualizacao) {
		Double cliques = (double) Math.round(((visualizacao * 12) / 100));
		return cliques;
	}

	/**
	 * To know the number of shares, we must know the amount of shares clicks. For
	 * every 20 people who click on the ad 3 share in the social networks.
	 */
	private Double calcularCompartilhamentos(Double clique) {
		Double comp = (double) Math.round(((clique * 3) / 20));
		return comp;
	}

	/**
	 * To know the number of views (shared), we must know the number of shares.
	 * Because, for each share on the networks generates 40 new views.
	 */
	private Double calcularVisualizacoesCompartilhadasNaoSequenciais(Double compartilhamento) {
		Double visualizacoesDeCompartilhamento;
		visualizacoesDeCompartilhamento = (compartilhamento * 40) / 1;
		return visualizacoesDeCompartilhamento;
	}

	private Long calcularVisualizacoesCompartilhadasSequenciais(Double visualizacaoDeCompartilhamento) {
		Long totalCompartilhamentosSequenciais = 0L;
		for (int i = 0; i < visualizacaoDeCompartilhamento.intValue(); i++) {
			int sequenciaCompartilhada = aleatorizarSequenciaCompartilhamentos();
			totalCompartilhamentosSequenciais += (sequenciaCompartilhada - 1);
		}
		return totalCompartilhamentosSequenciais;
	}

	private int aleatorizarSequenciaCompartilhamentos() {
		int sequenciaCompartilhada = 0;
		Random rand = new Random();
		sequenciaCompartilhada = (rand.nextInt(4) + 1);
		return sequenciaCompartilhada;
	}

	public void resetarAtributos() {
		this.visualizacoes = 0d;
		this.totalCliques = 0d;
		this.compartilhamentos = 0d;
		this.visualizacaoDeCompartilhamento = 0d;
		this.totalCompSeq = 0L;
		this.totalVisualizacao = 0d;
		setResult(new ResultadoProjecao());
	}

	public Double getVisualizacoes() {
		return visualizacoes;
	}

	public void setVisualizacoes(Double visualizacoes) {
		this.visualizacoes = visualizacoes;
	}

	public Double getTotalCliques() {
		return totalCliques;
	}

	public void setTotalCliques(Double totalCliques) {
		this.totalCliques = totalCliques;
	}

	public Double getCompartilhamentos() {
		return compartilhamentos;
	}

	public void setCompartilhamentos(Double compartilhamentos) {
		this.compartilhamentos = compartilhamentos;
	}

	public Double getVisualizacaoDeCompartilhamento() {
		return visualizacaoDeCompartilhamento;
	}

	public void setVisualizacaoDeCompartilhamento(Double visualizacaoDeCompartilhamento) {
		this.visualizacaoDeCompartilhamento = visualizacaoDeCompartilhamento;
	}

	public Long getTotalCompSeq() {
		return totalCompSeq;
	}

	public void setTotalCompSeq(Long totalCompSeq) {
		this.totalCompSeq = totalCompSeq;
	}

	public Double getTotalVisualizacao() {
		return totalVisualizacao;
	}

	public void setTotalVisualizacao(Double totalVisualizacao) {
		this.totalVisualizacao = totalVisualizacao;
	}

	public ResultadoProjecao getResult() {
		return result;
	}

	public void setResult(ResultadoProjecao result) {
		this.result = result;
	}

}
