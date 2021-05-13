package br.capgemini.desafio.agencia.controller;

import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.capgemini.desafio.agencia.banco.BancoDeDados;
import br.capgemini.desafio.agencia.dto.AnuncioDTO;
import br.capgemini.desafio.agencia.enums.ValidacaoAnuncioEnum;
import br.capgemini.desafio.agencia.model.Anuncio;
import br.capgemini.desafio.agencia.util.Constante;
import br.capgemini.desafio.agencia.util.Util;

public class AnuncioController {

	private static final Logger LOGGER = LogManager.getLogger(AnuncioController.class.getName());

	private Anuncio anuncio;

	public AnuncioController() {
		setAnuncio(new Anuncio());
	}

	public ValidacaoAnuncioEnum validarAnuncio(AnuncioDTO anuncioDto, boolean isCreate) {

		if (isCreate) {
			LOGGER.debug("Validando criação do anuncio...");
		} else {
			LOGGER.debug("Validando edição do anuncio...");
		}

		if (Util.isNullOrEmpty(anuncioDto.getNome())) {
			LOGGER.warn(ValidacaoAnuncioEnum.PREENCHIMENTO_OBRIGATORIO_NOME_ANUNCIO.getDescricao());
			return ValidacaoAnuncioEnum.PREENCHIMENTO_OBRIGATORIO_NOME_ANUNCIO;
		}

		if (Util.isNullOrEmpty(anuncioDto.getCliente())) {
			LOGGER.warn(ValidacaoAnuncioEnum.PREENCHIMENTO_OBRIGATORIO_NOME_CLIENTE.getDescricao());
			return ValidacaoAnuncioEnum.PREENCHIMENTO_OBRIGATORIO_NOME_CLIENTE;
		}

		if (Util.isNullOrEmpty(anuncioDto.getDataInicio())) {
			LOGGER.warn(ValidacaoAnuncioEnum.PREENCHIMENTO_OBRIGATORIO_DATA_INICIO.getDescricao());
			return ValidacaoAnuncioEnum.PREENCHIMENTO_OBRIGATORIO_DATA_INICIO;
		}

		if (Util.isNullOrEmpty(anuncioDto.getDataTermino())) {
			LOGGER.warn(ValidacaoAnuncioEnum.PREENCHIMENTO_OBRIGATORIO_DATA_TERMINO.getDescricao());
			return ValidacaoAnuncioEnum.PREENCHIMENTO_OBRIGATORIO_DATA_TERMINO;
		}

		if (Util.isNullOrEmpty(anuncioDto.getInvestimentoPorDia())) {
			LOGGER.warn(ValidacaoAnuncioEnum.PREENCHIMENTO_OBRIGATORIO_INVESTIMENTO_DIA.getDescricao());
			return ValidacaoAnuncioEnum.PREENCHIMENTO_OBRIGATORIO_INVESTIMENTO_DIA;
		}

		if (!Util.isDateValidDefault(anuncioDto.getDataInicio())) {
			LOGGER.warn(ValidacaoAnuncioEnum.DATA_INICIO_INVALIDA.getDescricao());
			return ValidacaoAnuncioEnum.DATA_INICIO_INVALIDA;
		}

		if (!Util.isDateValidDefault(anuncioDto.getDataTermino())) {
			LOGGER.warn(ValidacaoAnuncioEnum.DATA_TERMINO_INVALIDA.getDescricao());
			return ValidacaoAnuncioEnum.DATA_TERMINO_INVALIDA;
		}

		Date dataInicio = Util.getConverteStringToDate(anuncioDto.getDataInicio());
		Date dataTermino = Util.getConverteStringToDate(anuncioDto.getDataTermino());

		if (!Util.validatePrecedenciaDatas(dataInicio, dataTermino)) {
			LOGGER.warn(ValidacaoAnuncioEnum.DATA_PRECEDENCIA_INVALIDA.getDescricao());
			return ValidacaoAnuncioEnum.DATA_PRECEDENCIA_INVALIDA;
		}

		if (!Util.isNumerico(anuncioDto.getInvestimentoPorDia())) {
			LOGGER.warn(ValidacaoAnuncioEnum.VALOR_INVESTIMENTO_INCORRETO.getDescricao());
			return ValidacaoAnuncioEnum.VALOR_INVESTIMENTO_INCORRETO;
		}

		Double valorInvestido = Double.valueOf(anuncioDto.getInvestimentoPorDia());

		if (valorInvestido < 1d) {
			LOGGER.warn(ValidacaoAnuncioEnum.VALOR_LIMITE_INVALIDO.getDescricao());
			return ValidacaoAnuncioEnum.VALOR_LIMITE_INVALIDO;
		}

		LOGGER.info("Anúncio validado com sucesso.");
		if (isCreate) {
			return ValidacaoAnuncioEnum.SUCESSO_CRIACAO;
		} else {
			return ValidacaoAnuncioEnum.SUCESSO_EDICAO;
		}

	}

	public void preencherAnuncio(AnuncioDTO anuncioDto) {

		Date dataInicio = Util.getConverteStringToDate(anuncioDto.getDataInicio());
		Date dataTermino = Util.getConverteStringToDate(anuncioDto.getDataTermino());
		Double valorInvestido = Double.valueOf(anuncioDto.getInvestimentoPorDia());
		
		 List<Anuncio> anuncios = BancoDeDados.obterAnuncios();
		
		 Long id = 0L;
		for (int i = 0; i < anuncios.size(); i++) {
			if (anuncios.get(i).getId() > id) {
				id = anuncios.get(i).getId();
			}
		}
		getAnuncio().setId(++id);
		getAnuncio().setNome(Util.removeExceededWhiteSpace(anuncioDto.getNome().trim()));
		getAnuncio().setCliente(Util.removeExceededWhiteSpace(anuncioDto.getCliente().trim()));
		getAnuncio().setDataInicio(dataInicio);
		getAnuncio().setDataTermino(dataTermino);
		getAnuncio().setInvestimentoPorDia(valorInvestido);

	}
	
	public void criarAnuncio() {
		if (getAnuncio() != null) {
		BancoDeDados.adicionarAnuncio(getAnuncio());
		LOGGER.info(Constante.SUCESSO_CRIAR_ANUNCIO);
		}
		
	}
	
	public List<Anuncio> obterTodosAnuncios(){
		return BancoDeDados.obterAnuncios();
	}
	
	public Anuncio obterAnuncio(Long id) {
		return BancoDeDados.obterAnuncio(id);
	}
	
	public void editarAnuncio(AnuncioDTO anuncioDto) {
		Anuncio anuncio = new Anuncio();
		
		anuncio.setId(anuncioDto.getId());
		anuncio.setNome(anuncioDto.getNome());
		anuncio.setCliente(anuncioDto.getCliente());
		anuncio.setDataInicio(Util.getConverteStringToDate(anuncioDto.getDataInicio()));
		anuncio.setDataTermino(Util.getConverteStringToDate(anuncioDto.getDataTermino()));
		anuncio.setInvestimentoPorDia(Double.valueOf(anuncioDto.getInvestimentoPorDia()));
		
		BancoDeDados.alterarAnuncio(anuncio);
	}
	
	public void deletarAnuncio(Long id) {
		BancoDeDados.deletarAnuncio(id);
		LOGGER.info(Constante.SUCESSO_DELETAR_ANUNCIO);
	}

	public Anuncio getAnuncio() {
		return anuncio;
	}

	public void setAnuncio(Anuncio anuncio) {
		this.anuncio = anuncio;
	}

}
