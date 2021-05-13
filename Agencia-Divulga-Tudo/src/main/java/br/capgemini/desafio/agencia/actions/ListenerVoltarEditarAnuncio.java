package br.capgemini.desafio.agencia.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import br.capgemini.desafio.agencia.view.ViewEditarAnuncio;

public class ListenerVoltarEditarAnuncio implements ActionListener {

	private ViewEditarAnuncio tela;

	public ListenerVoltarEditarAnuncio(ViewEditarAnuncio tela) {
		this.tela = tela;
	}

	public void actionPerformed(ActionEvent e) {
		tela.setVisible(false);
		tela.contentPane.resetKeyboardActions();
		tela.frameAnterior.setVisible(true);
		tela.dispose();
	}

}
