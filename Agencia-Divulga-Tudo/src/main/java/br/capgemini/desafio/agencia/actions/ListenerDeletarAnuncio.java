package br.capgemini.desafio.agencia.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.capgemini.desafio.agencia.util.Constante;
import br.capgemini.desafio.agencia.util.ViewUtil;
import br.capgemini.desafio.agencia.view.ViewVisualizarAnuncios;

public class ListenerDeletarAnuncio implements ActionListener {

	private static final Logger LOGGER = LogManager.getLogger(ListenerDeletarAnuncio.class.getName());

	private ViewVisualizarAnuncios tela;

	public ListenerDeletarAnuncio(ViewVisualizarAnuncios tela) {
		this.tela = tela;
	}

	public void actionPerformed(ActionEvent e) {

		if (tela.table.getSelectedRow() == -1) {
			ViewUtil.exibirMensagemAlerta(Constante.SELECIONAR_LINHA_DELETAR);
			return;
		}

		Long idRemove = (Long) tela.table.getValueAt(tela.table.getSelectedRow(), 0);

		try {
			tela.getAnuncioController().deletarAnuncio(idRemove);
		} catch (Exception e2) {
			LOGGER.catching(e2);
			ViewUtil.exibirMensagemErro(Constante.ERRO_DELETAR_ANUNCIO);
			return;
		}

		tela.model.removeRow(tela.table.getSelectedRow());

		ViewUtil.exibirMensagemInformativa(Constante.SUCESSO_DELETAR_ANUNCIO);
	}

}
