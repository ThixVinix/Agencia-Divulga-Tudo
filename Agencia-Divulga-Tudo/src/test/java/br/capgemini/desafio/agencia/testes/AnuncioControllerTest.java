package br.capgemini.desafio.agencia.testes;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.capgemini.desafio.agencia.controller.AnuncioController;
import br.capgemini.desafio.agencia.dto.AnuncioDTO;
import br.capgemini.desafio.agencia.enums.ValidacaoAnuncioEnum;

/**
 * Attention: it is necessary to run the test class in version
 * <strong>5</strong> of the JUNIT
 * 
 * @version JUNIT 5
 */
@DisplayName("Testes da classe: AnuncioController")
class AnuncioControllerTest {

	private static final Logger LOGGER = LogManager.getLogger(AnuncioControllerTest.class.getName());

	private static final String INITIALIZE_CONFIGURATION = "Inicializando configurações da classe de teste..."
			+ AnuncioController.class.getName() + "...\n";
	private static final String FINISH_CONFIGURATION = "Configurações da classe de testes finalizada com sucesso.\n";
	private static final String INITIALIZE_TEST = "Inicializando teste...";
	private static final String FINISH_TEST = "Teste finalizado com sucesso.\n";
	private static final String FINISH_ALL = "Todos os testes foram concluídos.\n";

	private static AnuncioController controller;

	private static AnuncioDTO anuncioNomeNull;
	private static AnuncioDTO anuncioClienteNull;
	private static AnuncioDTO anuncioDataInicioNull;
	private static AnuncioDTO anuncioDataTerminoNull;
	private static AnuncioDTO anuncioInvestimentoNull;
	private static AnuncioDTO anuncioDataInicioInvalida;
	private static AnuncioDTO anuncioDataTerminoInvalida;
	private static AnuncioDTO anuncioPrecedenciaInvalida;
	private static AnuncioDTO anuncioInvestimentoInvalido;
	private static AnuncioDTO anuncioInvestimentoNegativo;
	private static AnuncioDTO anuncioInvestimentoZerado;
	private static AnuncioDTO anuncioCompleto;

	@BeforeAll
	public static void inicializar() {
		LOGGER.info(INITIALIZE_CONFIGURATION);
		controller = new AnuncioController();
		povoarAnuncios();
		LOGGER.info(FINISH_CONFIGURATION);
	}

	@BeforeEach
	public void executarAntesDoTeste() {
		LOGGER.info(INITIALIZE_TEST);
	}

	@AfterEach
	public void executarDepoisDoTeste() {
		LOGGER.info(FINISH_TEST);
	}

	@AfterAll
	public static void finalizar() {
		LOGGER.info(FINISH_ALL);
	}

	/**
	 * Method to be tested: "{@link AnuncioController}.validarAnuncio"
	 */

	@DisplayName("Nome do anuncio = null")
	@Test
	void validarNomeNulo() {
		ValidacaoAnuncioEnum r = controller.validarAnuncio(anuncioNomeNull, true);
		assertEquals(ValidacaoAnuncioEnum.PREENCHIMENTO_OBRIGATORIO_NOME_ANUNCIO, r);
	}

	@DisplayName("Cliente = null")
	@Test
	void validarClienteNulo() {
		ValidacaoAnuncioEnum r = controller.validarAnuncio(anuncioClienteNull, true);
		assertEquals(ValidacaoAnuncioEnum.PREENCHIMENTO_OBRIGATORIO_NOME_CLIENTE, r);
	}

	@DisplayName("Data Inicio = null")
	@Test
	void validarDataInicioNula() {
		ValidacaoAnuncioEnum r = controller.validarAnuncio(anuncioDataInicioNull, true);
		assertEquals(ValidacaoAnuncioEnum.PREENCHIMENTO_OBRIGATORIO_DATA_INICIO, r);
	}

	@DisplayName("Data Termino = null")
	@Test
	void validarDataTerminoNula() {
		ValidacaoAnuncioEnum r = controller.validarAnuncio(anuncioDataTerminoNull, true);
		assertEquals(ValidacaoAnuncioEnum.PREENCHIMENTO_OBRIGATORIO_DATA_TERMINO, r);
	}

	@DisplayName("Valor investido = null")
	@Test
	void validarInvestimentoNulo() {
		ValidacaoAnuncioEnum r = controller.validarAnuncio(anuncioInvestimentoNull, true);
		assertEquals(ValidacaoAnuncioEnum.PREENCHIMENTO_OBRIGATORIO_INVESTIMENTO_DIA, r);
	}

	@DisplayName("Data Inicio invalida")
	@Test
	void validarDataInicioInvalida() {
		ValidacaoAnuncioEnum r = controller.validarAnuncio(anuncioDataInicioInvalida, true);
		assertEquals(ValidacaoAnuncioEnum.DATA_INICIO_INVALIDA, r);
	}

	@DisplayName("Data Termino invalida")
	@Test
	void validarDataTerminoInvalida() {
		ValidacaoAnuncioEnum r = controller.validarAnuncio(anuncioDataTerminoInvalida, true);
		assertEquals(ValidacaoAnuncioEnum.DATA_TERMINO_INVALIDA, r);
	}

	@DisplayName("Precedencia incorreta")
	@Test
	void validarPrecedenciaIncorreta() {
		ValidacaoAnuncioEnum r = controller.validarAnuncio(anuncioPrecedenciaInvalida, true);
		assertEquals(ValidacaoAnuncioEnum.DATA_PRECEDENCIA_INVALIDA, r);
	}

	@DisplayName("Valor investido invalido")
	@Test
	void validarInvestimentoInvalido() {
		ValidacaoAnuncioEnum r = controller.validarAnuncio(anuncioInvestimentoInvalido, true);
		assertEquals(ValidacaoAnuncioEnum.VALOR_INVESTIMENTO_INCORRETO, r);
	}

	@DisplayName("Valor investido negativo")
	@Test
	void validarInvestimentoNegativo() {
		ValidacaoAnuncioEnum r = controller.validarAnuncio(anuncioInvestimentoNegativo, true);
		assertEquals(ValidacaoAnuncioEnum.VALOR_INVESTIMENTO_INCORRETO, r);
	}

	@DisplayName("Valor investido zerado")
	@Test
	void validarInvestimentoZerado() {
		ValidacaoAnuncioEnum r = controller.validarAnuncio(anuncioInvestimentoZerado, true);
		assertEquals(ValidacaoAnuncioEnum.VALOR_LIMITE_INVALIDO, r);
	}

	@DisplayName("Anuncio completo - Criação")
	@Test
	void validarAnuncioCompletoCriacao() {
		ValidacaoAnuncioEnum r = controller.validarAnuncio(anuncioCompleto, true);
		assertEquals(ValidacaoAnuncioEnum.SUCESSO_CRIACAO, r);
	}

	@DisplayName("Anuncio completo - Edição")
	@Test
	void validarAnuncioCompletoEdicao() {
		ValidacaoAnuncioEnum r = controller.validarAnuncio(anuncioCompleto, false);
		assertEquals(ValidacaoAnuncioEnum.SUCESSO_EDICAO, r);
	}

	private static void povoarAnuncios() {
		anuncioNomeNull = new AnuncioDTO();
		anuncioNomeNull.setNome(null);
		anuncioNomeNull.setCliente("Cliente A");
		anuncioNomeNull.setDataInicio("01/01/2019");
		anuncioNomeNull.setDataTermino("01/01/2020");
		anuncioNomeNull.setInvestimentoPorDia("152.50");

		anuncioClienteNull = new AnuncioDTO();
		anuncioClienteNull.setNome("Anúncio A");
		anuncioClienteNull.setCliente(null);
		anuncioClienteNull.setDataInicio("01/01/2019");
		anuncioClienteNull.setDataTermino("01/01/2020");
		anuncioClienteNull.setInvestimentoPorDia("152.50");

		anuncioDataInicioNull = new AnuncioDTO();
		anuncioDataInicioNull.setNome("Anúncio A");
		anuncioDataInicioNull.setCliente("Cliente A");
		anuncioDataInicioNull.setDataInicio(null);
		anuncioDataInicioNull.setDataTermino("01/01/2020");
		anuncioDataInicioNull.setInvestimentoPorDia("152.50");

		anuncioDataTerminoNull = new AnuncioDTO();
		anuncioDataTerminoNull.setNome("Anúncio A");
		anuncioDataTerminoNull.setCliente("Cliente A");
		anuncioDataTerminoNull.setDataInicio("01/01/2019");
		anuncioDataTerminoNull.setDataTermino(null);
		anuncioDataTerminoNull.setInvestimentoPorDia("152.50");

		anuncioInvestimentoNull = new AnuncioDTO();
		anuncioInvestimentoNull.setNome("Anúncio A");
		anuncioInvestimentoNull.setCliente("Cliente A");
		anuncioInvestimentoNull.setDataInicio("01/01/2019");
		anuncioInvestimentoNull.setDataTermino("01/01/2020");
		anuncioInvestimentoNull.setInvestimentoPorDia(null);

		anuncioDataInicioInvalida = new AnuncioDTO();
		anuncioDataInicioInvalida.setNome("Anúncio A");
		anuncioDataInicioInvalida.setCliente("Cliente A");
		anuncioDataInicioInvalida.setDataInicio("DATA_INVALIDA");
		anuncioDataInicioInvalida.setDataTermino("01/01/2020");
		anuncioDataInicioInvalida.setInvestimentoPorDia("152.50");

		anuncioDataTerminoInvalida = new AnuncioDTO();
		anuncioDataTerminoInvalida.setNome("Anúncio A");
		anuncioDataTerminoInvalida.setCliente("Cliente A");
		anuncioDataTerminoInvalida.setDataInicio("01/01/2019");
		anuncioDataTerminoInvalida.setDataTermino("DATA_INVALIDA");
		anuncioDataTerminoInvalida.setInvestimentoPorDia("152.50");

		anuncioPrecedenciaInvalida = new AnuncioDTO();
		anuncioPrecedenciaInvalida.setNome("Anúncio A");
		anuncioPrecedenciaInvalida.setCliente("Cliente A");
		anuncioPrecedenciaInvalida.setDataInicio("01/01/2020");
		anuncioPrecedenciaInvalida.setDataTermino("01/01/2019");
		anuncioPrecedenciaInvalida.setInvestimentoPorDia("152.50");

		anuncioInvestimentoInvalido = new AnuncioDTO();
		anuncioInvestimentoInvalido.setNome("Anúncio A");
		anuncioInvestimentoInvalido.setCliente("Cliente A");
		anuncioInvestimentoInvalido.setDataInicio("01/01/2019");
		anuncioInvestimentoInvalido.setDataTermino("01/01/2020");
		anuncioInvestimentoInvalido.setInvestimentoPorDia("INVESTIMENTO_INVALIDO");

		anuncioInvestimentoNegativo = new AnuncioDTO();
		anuncioInvestimentoNegativo.setNome("Anúncio A");
		anuncioInvestimentoNegativo.setCliente("Cliente A");
		anuncioInvestimentoNegativo.setDataInicio("01/01/2019");
		anuncioInvestimentoNegativo.setDataTermino("01/01/2020");
		anuncioInvestimentoNegativo.setInvestimentoPorDia("-1");

		anuncioInvestimentoZerado = new AnuncioDTO();
		anuncioInvestimentoZerado.setNome("Anúncio A");
		anuncioInvestimentoZerado.setCliente("Cliente A");
		anuncioInvestimentoZerado.setDataInicio("01/01/2019");
		anuncioInvestimentoZerado.setDataTermino("01/01/2020");
		anuncioInvestimentoZerado.setInvestimentoPorDia("0");

		anuncioCompleto = new AnuncioDTO();
		anuncioCompleto.setNome("Anúncio A");
		anuncioCompleto.setCliente("Cliente A");
		anuncioCompleto.setDataInicio("01/01/2019");
		anuncioCompleto.setDataTermino("01/01/2020");
		anuncioCompleto.setInvestimentoPorDia("152.50");
	}

}
