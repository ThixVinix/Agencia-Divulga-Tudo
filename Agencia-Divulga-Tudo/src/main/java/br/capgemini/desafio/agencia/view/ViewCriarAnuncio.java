package br.capgemini.desafio.agencia.view;

import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import br.capgemini.desafio.agencia.actions.ListenerCriarAnuncio;
import br.capgemini.desafio.agencia.actions.ListenerVoltarCriarAnuncio;
import br.capgemini.desafio.agencia.controller.AnuncioController;

public class ViewCriarAnuncio extends JFrame {

	private static final long serialVersionUID = 4642741144261760853L;
	private AnuncioController anuncioController;

	public JPanel contentPane;
	public JFrame frameAnterior;
	public JTextField nomeAnuncio;
	public JTextField nomeCliente;
	public JTextField investimentoDia;
	public JFormattedTextField formattedDataInicio;
	public JFormattedTextField formattedDataTermino;

	/**
	 * Create the frame.
	 * @throws ParseException 
	 */
	public ViewCriarAnuncio(JFrame frameAnterior) throws ParseException {
		this.frameAnterior = frameAnterior;
		setAnuncioController(new AnuncioController());

		setTitle("Criar An\u00FAncio");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 343);
		setResizable(false);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNomeAnuncio = new JLabel("Nome do an\u00FAncio");
		lblNomeAnuncio.setBounds(10, 11, 122, 14);
		contentPane.add(lblNomeAnuncio);

		nomeAnuncio = new JTextField();
		nomeAnuncio.setBounds(10, 36, 359, 26);
		contentPane.add(nomeAnuncio);
		nomeAnuncio.setColumns(10);

		JLabel lblCliente = new JLabel("Cliente");
		lblCliente.setBounds(10, 73, 122, 14);
		contentPane.add(lblCliente);

		nomeCliente = new JTextField();
		nomeCliente.setBounds(10, 98, 359, 26);
		contentPane.add(nomeCliente);
		nomeCliente.setColumns(10);

		JLabel lblDataInicio = new JLabel("Data de In\u00EDcio");
		lblDataInicio.setBounds(10, 135, 160, 14);
		contentPane.add(lblDataInicio);

		formattedDataInicio = new JFormattedTextField(new MaskFormatter("##/##/####"));
		formattedDataInicio.setFocusLostBehavior(JFormattedTextField.COMMIT);
		formattedDataInicio.setBounds(10, 160, 160, 26);
		contentPane.add(formattedDataInicio);

		JLabel lblDataTermino = new JLabel("Data de T\u00E9rmino");
		lblDataTermino.setBounds(209, 135, 160, 14);
		contentPane.add(lblDataTermino);

		formattedDataTermino = new JFormattedTextField(new MaskFormatter("##/##/####"));
		formattedDataTermino.setFocusLostBehavior(JFormattedTextField.COMMIT);
		formattedDataTermino.setBounds(209, 160, 160, 26);
		contentPane.add(formattedDataTermino);

		JLabel lblInvestimentoDia = new JLabel("Investimento por Dia");
		lblInvestimentoDia.setBounds(10, 197, 122, 14);
		contentPane.add(lblInvestimentoDia);

		investimentoDia = new JTextField();
		investimentoDia.setBounds(10, 222, 359, 26);
		investimentoDia.setColumns(10);
		contentPane.add(investimentoDia);

		JButton btnCriarAnuncio = new JButton("Criar");
		btnCriarAnuncio.setBounds(209, 274, 89, 23);
		btnCriarAnuncio.addActionListener(new ListenerCriarAnuncio(this));
		contentPane.add(btnCriarAnuncio);

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setBounds(81, 274, 89, 23);
		btnVoltar.addActionListener(new ListenerVoltarCriarAnuncio(this));
		contentPane.add(btnVoltar);

	}

	public AnuncioController getAnuncioController() {
		return anuncioController;
	}

	public void setAnuncioController(AnuncioController anuncioController) {
		this.anuncioController = anuncioController;
	}

}
