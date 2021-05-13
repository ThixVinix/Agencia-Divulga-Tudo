package br.capgemini.desafio.agencia.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.WindowConstants;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.capgemini.desafio.agencia.util.Constante;

public class ViewMenuInicial {

	@SuppressWarnings(value = "unused")
	private static final Logger LOGGER = LogManager.getLogger(ViewMenuInicial.class.getName());

	public JFrame frameMenuInicial;
//	private TelaPesquisaURL frameTelaPesquisaURL;
//	private TelaPesquisaArquivo frameTelaPesquisaArquivo;
	private ViewCriarAnuncio frameCriarAnuncio;
	private ViewVisualizarAnuncios frameVisualizarAnuncios;
	private ImageIcon imagem = new ImageIcon(getClass().getResource(Constante.IMAGEM_MENU_INICIAL_3));
	private JLabel imagemMenuInicial = new JLabel(imagem);

	/**
	 * Create the application.
	 */
	public ViewMenuInicial() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		frameMenuInicial = new JFrame();
		frameMenuInicial.setTitle(Constante.MENU_TITLE);
		frameMenuInicial.getContentPane().setBackground(Color.BLACK);
		frameMenuInicial.setBounds(100, 100, 1000, 500);
		frameMenuInicial.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frameMenuInicial.setResizable(false);
		frameMenuInicial.setLocationRelativeTo(null);

		JMenuBar menuBar = new JMenuBar();
		frameMenuInicial.setJMenuBar(menuBar);
		JMenuItem menuCriarAnuncio = new JMenuItem(Constante.MENU_CRIAR_ANUNCIO);
		menuCriarAnuncio.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, InputEvent.CTRL_DOWN_MASK));
		menuCriarAnuncio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							frameCriarAnuncio = new ViewCriarAnuncio(frameMenuInicial);
							frameCriarAnuncio.setVisible(true);
							frameMenuInicial.setVisible(false);
						} catch (Exception e) {
							LOGGER.catching(e);
						}
					}
				});
			}
		});
		menuBar.add(menuCriarAnuncio);

		JMenuItem menuVisualizarAnuncios = new JMenuItem(Constante.MENU_TITLE_VISUALIZAR);
		menuVisualizarAnuncios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameVisualizarAnuncios = new ViewVisualizarAnuncios(frameMenuInicial);
				frameVisualizarAnuncios.setVisible(true);
				frameMenuInicial.setVisible(false);
			}
		});
		menuVisualizarAnuncios.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, InputEvent.CTRL_DOWN_MASK));
		menuBar.add(menuVisualizarAnuncios);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0 };
		gridBagLayout.rowHeights = new int[] { 0 };
		gridBagLayout.columnWeights = new double[] { Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { Double.MIN_VALUE };
		frameMenuInicial.getContentPane().setLayout(gridBagLayout);

		frameMenuInicial.getContentPane().add(imagemMenuInicial);
	}

}
