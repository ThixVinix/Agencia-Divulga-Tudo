package br.capgemini.desafio.agencia.util;

import javax.swing.JOptionPane;

public class ViewUtil {

	private ViewUtil() {
	}
	
	public static void exibirMensagemInformativa(String mensagem) {
		JOptionPane.showMessageDialog(null, mensagem, "Informa��o", JOptionPane.INFORMATION_MESSAGE);
	}

	public static void exibirMensagemErro(String mensagem) {
		JOptionPane.showMessageDialog(null, mensagem, "Erro", JOptionPane.ERROR_MESSAGE);
	}

	public static void exibirMensagemAlerta(String mensagem) {
		JOptionPane.showMessageDialog(null, mensagem, "Aten��o", JOptionPane.WARNING_MESSAGE);
	}
	
}
