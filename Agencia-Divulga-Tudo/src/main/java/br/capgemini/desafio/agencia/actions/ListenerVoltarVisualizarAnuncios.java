package br.capgemini.desafio.agencia.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import br.capgemini.desafio.agencia.view.ViewVisualizarAnuncios;

public class ListenerVoltarVisualizarAnuncios implements ActionListener {

	private ViewVisualizarAnuncios tela;

	public ListenerVoltarVisualizarAnuncios(ViewVisualizarAnuncios tela) {
		this.tela = tela;
	}

	public void actionPerformed(ActionEvent e) {
		tela.setVisible(false);
		tela.contentPane.resetKeyboardActions();
		tela.frameAnterior.setVisible(true);
	}

}
