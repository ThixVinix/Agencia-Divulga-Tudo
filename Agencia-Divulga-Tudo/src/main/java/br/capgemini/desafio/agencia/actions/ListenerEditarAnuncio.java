package br.capgemini.desafio.agencia.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.capgemini.desafio.agencia.dto.AnuncioDTO;
import br.capgemini.desafio.agencia.enums.ValidacaoAnuncioEnum;
import br.capgemini.desafio.agencia.util.ViewUtil;
import br.capgemini.desafio.agencia.view.ViewEditarAnuncio;
import br.capgemini.desafio.agencia.view.ViewVisualizarAnuncios;

public class ListenerEditarAnuncio implements ActionListener {

	private static final Logger LOGGER = LogManager.getLogger(ListenerEditarAnuncio.class.getName());

	private ViewEditarAnuncio tela;

	public ListenerEditarAnuncio(ViewEditarAnuncio tela) {
		this.tela = tela;
	}

	public void actionPerformed(ActionEvent e) {

		AnuncioDTO anuncioDto = new AnuncioDTO();

		anuncioDto.setId(tela.idEdit);
		anuncioDto.setNome(tela.nomeAnuncio.getText());
		anuncioDto.setCliente(tela.nomeCliente.getText());
		anuncioDto.setDataInicio(tela.formattedDataInicio.getText());
		anuncioDto.setDataTermino(tela.formattedDataTermino.getText());
		anuncioDto.setInvestimentoPorDia(tela.investimentoDia.getText());

		boolean isValid = validarAnuncio(anuncioDto);

		if (!isValid)
			return;


		tela.getAnuncioController().editarAnuncio(anuncioDto);

		ViewUtil.exibirMensagemInformativa(ValidacaoAnuncioEnum.SUCESSO_EDICAO.getDescricao());

		tela.setVisible(false);
		tela.contentPane.resetKeyboardActions();
		tela.frameAnterior = new ViewVisualizarAnuncios(tela.frameInicial);
		tela.frameAnterior.setVisible(true);
		tela.dispose();
	}

	private boolean validarAnuncio(AnuncioDTO anuncioDto) {

		ValidacaoAnuncioEnum validacaoAnuncio;

		validacaoAnuncio = tela.getAnuncioController().validarAnuncio(anuncioDto, false);

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
				LOGGER.warn("Validação indefinida");
			}

			return false;
		}

		return true;
	}

}
