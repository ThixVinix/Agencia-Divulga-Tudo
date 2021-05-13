package br.capgemini.desafio.agencia.dto;

public class AnuncioDTO {

	private Long id;
	
	private String nome;

	private String cliente;

	private String dataInicio;

	private String dataTermino;

	private String investimentoPorDia;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(String dataInicio) {
		this.dataInicio = dataInicio;
	}

	public String getDataTermino() {
		return dataTermino;
	}

	public void setDataTermino(String dataTermino) {
		this.dataTermino = dataTermino;
	}

	public String getInvestimentoPorDia() {
		return investimentoPorDia;
	}

	public void setInvestimentoPorDia(String investimentoPorDia) {
		this.investimentoPorDia = investimentoPorDia;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
