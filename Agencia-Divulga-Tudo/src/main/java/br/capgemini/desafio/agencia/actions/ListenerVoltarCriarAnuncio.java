package br.capgemini.desafio.agencia.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import br.capgemini.desafio.agencia.view.ViewCriarAnuncio;

public class ListenerVoltarCriarAnuncio implements ActionListener {

	private ViewCriarAnuncio tela;

	public ListenerVoltarCriarAnuncio(ViewCriarAnuncio tela) {
		this.tela = tela;
	}

	public void actionPerformed(ActionEvent e) {
		tela.setVisible(false);
		tela.contentPane.resetKeyboardActions();
		tela.frameAnterior.setVisible(true);
	}

}
