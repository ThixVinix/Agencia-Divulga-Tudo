package br.capgemini.desafio.agencia;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.capgemini.desafio.agencia.util.LayoutPersonalizado;
import br.capgemini.desafio.agencia.view.ViewMenuInicial;

/**
 * Programming Challenge - CAPGEMINI ACADEMY - Part 2
 * 
 * @author THIAGO VINICIUS DE ALMEIDA SOUZA
 * @since 11/05/2021
 */
public class Main {

	private static final Logger LOGGER = LogManager.getLogger(Main.class.getName());

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ViewMenuInicial window = new ViewMenuInicial();
			LayoutPersonalizado.determinarLayoutPersonalizado("Nimbus");
			window.initialize();
			window.frameMenuInicial.setVisible(true);
		} catch (Exception e) {
			LOGGER.catching(e);
		}
	}

}
