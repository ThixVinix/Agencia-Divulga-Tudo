package br.capgemini.desafio.agencia.enums;

public enum ValidacaoAnuncioEnum {

	SUCESSO_CRIACAO("An�ncio criado com sucesso!"), SUCESSO_EDICAO("An�ncio editado com sucesso!"),
	SUCESSO_EXCLUSAO("An�ncio deletado com sucesso!"),
	PREENCHIMENTO_OBRIGATORIO_NOME_ANUNCIO("Preencha o campo \"Nome do An�ncio\"."),
	PREENCHIMENTO_OBRIGATORIO_NOME_CLIENTE("Preencha o campo \"Cliente\"."),
	PREENCHIMENTO_OBRIGATORIO_DATA_INICIO("Preencha o campo \"Data de In�cio\"."),
	PREENCHIMENTO_OBRIGATORIO_DATA_TERMINO("Preencha o campo \"Data de T�rmino\"."),
	PREENCHIMENTO_OBRIGATORIO_INVESTIMENTO_DIA("Preencha o campo \"Investimento por Dia\"."),
	VALOR_INVESTIMENTO_INCORRETO("O valor digitado no campo \"Investimento por Dia\" � inv�lido."),
	VALOR_LIMITE_INVALIDO("Limite m�nimo permitido para o campo \"Investimento por Dia\" � de 1 real."),
	DATA_INICIO_INVALIDA("A \"Data de In�cio\" est� incorreta. Digite uma data v�lida"),
	DATA_TERMINO_INVALIDA("A \"Data de T�rmino\" est� incorreta. Digite uma data v�lida"),
	DATA_PRECEDENCIA_INVALIDA("A \"Data de T�rmino\" n�o deve ser inferior � \"Data de In�cio\".");

	private String descricao;

	private ValidacaoAnuncioEnum(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
