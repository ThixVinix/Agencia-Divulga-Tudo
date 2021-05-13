package br.capgemini.desafio.agencia.enums;

public enum ValidacaoAnuncioEnum {

	SUCESSO_CRIACAO("Anúncio criado com sucesso!"), SUCESSO_EDICAO("Anúncio editado com sucesso!"),
	SUCESSO_EXCLUSAO("Anúncio deletado com sucesso!"),
	PREENCHIMENTO_OBRIGATORIO_NOME_ANUNCIO("Preencha o campo \"Nome do Anúncio\"."),
	PREENCHIMENTO_OBRIGATORIO_NOME_CLIENTE("Preencha o campo \"Cliente\"."),
	PREENCHIMENTO_OBRIGATORIO_DATA_INICIO("Preencha o campo \"Data de Início\"."),
	PREENCHIMENTO_OBRIGATORIO_DATA_TERMINO("Preencha o campo \"Data de Término\"."),
	PREENCHIMENTO_OBRIGATORIO_INVESTIMENTO_DIA("Preencha o campo \"Investimento por Dia\"."),
	VALOR_INVESTIMENTO_INCORRETO("O valor digitado no campo \"Investimento por Dia\" é inválido."),
	VALOR_LIMITE_INVALIDO("Limite mínimo permitido para o campo \"Investimento por Dia\" é de 1 real."),
	DATA_INICIO_INVALIDA("A \"Data de Início\" está incorreta. Digite uma data válida"),
	DATA_TERMINO_INVALIDA("A \"Data de Término\" está incorreta. Digite uma data válida"),
	DATA_PRECEDENCIA_INVALIDA("A \"Data de Término\" não deve ser inferior à \"Data de Início\".");

	private String descricao;

	private ValidacaoAnuncioEnum(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
