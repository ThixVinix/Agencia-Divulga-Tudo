package br.capgemini.desafio.agencia.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.capgemini.desafio.agencia.dto.AnuncioDTO;
import br.capgemini.desafio.agencia.enums.ValidacaoAnuncioEnum;
import br.capgemini.desafio.agencia.util.ViewUtil;
import br.capgemini.desafio.agencia.view.ViewCriarAnuncio;

public class ListenerCriarAnuncio implements ActionListener {

	private static final Logger LOGGER = LogManager.getLogger(ListenerCriarAnuncio.class.getName());

	private ViewCriarAnuncio tela;

	public ListenerCriarAnuncio(ViewCriarAnuncio tela) {
		this.tela = tela;
	}

	public void actionPerformed(ActionEvent e) {

		AnuncioDTO anuncioDto = new AnuncioDTO();

		anuncioDto.setNome(tela.nomeAnuncio.getText());
		anuncioDto.setCliente(tela.nomeCliente.getText());
		anuncioDto.setDataInicio(tela.formattedDataInicio.getText());
		anuncioDto.setDataTermino(tela.formattedDataTermino.getText());
		anuncioDto.setInvestimentoPorDia(tela.investimentoDia.getText());

		boolean isValid = validarAnuncio(anuncioDto);

		if (!isValid) 
			return;
		
		tela.getAnuncioController().preencherAnuncio(anuncioDto);
		
		tela.getAnuncioController().criarAnuncio();
		
		ViewUtil.exibirMensagemInformativa(ValidacaoAnuncioEnum.SUCESSO_CRIACAO.getDescricao());
		
		tela.setVisible(false);
		tela.contentPane.resetKeyboardActions();
		tela.frameAnterior.setVisible(true);
	}

	private boolean validarAnuncio(AnuncioDTO anuncioDto) {

		ValidacaoAnuncioEnum validacaoAnuncio;

		validacaoAnuncio = tela.getAnuncioController().validarAnuncio(anuncioDto, true);

		if (!(validacaoAnuncio == ValidacaoAnuncioEnum.SUCESSO_CRIACAO
				|| validacaoAnuncio == ValidacaoAnuncioEnum.SUCESSO_EDICAO)) {
			switch (validacaoAnuncio) {
			case DATA_INICIO_INVALIDA:
			case DATA_TERMINO_INVALIDA:
			case DATA_PRECEDENCIA_INVALIDA:
			case VALOR_INVESTIMENTO_INCORRETO:
			case VALOR_LIMITE_INVALIDO:
				ViewUtil.exibirMensagemErro(validacaoAnuncio.getDescricao());
				break;
			case PREENCHIMENTO_OBRIGATORIO_NOME_ANUNCIO:
			case PREENCHIMENTO_OBRIGATORIO_NOME_CLIENTE:
			case PREENCHIMENTO_OBRIGATORIO_DATA_INICIO:
			case PREENCHIMENTO_OBRIGATORIO_DATA_TERMINO:
			case PREENCHIMENTO_OBRIGATORIO_INVESTIMENTO_DIA:
				ViewUtil.exibirMensagemAlerta(validacaoAnuncio.getDescricao());
				break;
			default:
				LOGGER.warn("Valida??o indefinida");
			}

			return false;
		}

		return true;
	}

}
