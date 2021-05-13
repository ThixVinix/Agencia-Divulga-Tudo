package br.capgemini.desafio.agencia.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.capgemini.desafio.agencia.model.Anuncio;
import br.capgemini.desafio.agencia.util.Constante;
import br.capgemini.desafio.agencia.util.ViewUtil;
import br.capgemini.desafio.agencia.view.ViewEditarAnuncio;
import br.capgemini.desafio.agencia.view.ViewVisualizarAnuncios;

public class ListenerViewEditar implements ActionListener {

	private static final Logger LOGGER = LogManager.getLogger(ListenerViewEditar.class.getName());
	
	private ViewVisualizarAnuncios tela;
	
	public ListenerViewEditar(ViewVisualizarAnuncios tela) {
		this.tela = tela;
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if (tela.table.getSelectedRow() == -1) {
			ViewUtil.exibirMensagemAlerta(Constante.SELECIONAR_LINHA_EDITAR);
			return;
		}

		Long idEdit = (Long) tela.table.getValueAt(tela.table.getSelectedRow(), 0);
		
		Anuncio anuncio = tela.getAnuncioController().obterAnuncio(idEdit);
		
		if (anuncio == null) {
			ViewUtil.exibirMensagemErro("O anúncio não pode ser editado, pois já foi removido.\nSelecione outro registro.");
			return;
		}
		
		try {
			ViewEditarAnuncio viewEdit = new ViewEditarAnuncio(tela, tela.frameAnterior, idEdit);
			viewEdit.setVisible(true);
			tela.setVisible(false);
		} catch (ParseException e1) {
			ViewUtil.exibirMensagemErro("Erro ao renderizar tela de edição");
			LOGGER.catching(e1);
		}
		
	}

}
