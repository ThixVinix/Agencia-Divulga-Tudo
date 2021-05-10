package br.capgemini.desafio.agencia.view;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.capgemini.desafio.agencia.actions.ListenerCriarAnuncio;
import br.capgemini.desafio.agencia.actions.ListenerVoltar;

public class ViewCriarAnuncio extends JFrame {

	private static final long serialVersionUID = 4642741144261760853L;
	public JPanel contentPane;
	public JFrame frameAnterior;
	private JTextField nomeAnuncio;
	private JTextField nomeCliente;
	private JTextField investimentoDia;

	/**
	 * Create the frame.
	 */
	public ViewCriarAnuncio(JFrame frameAnterior) {
		this.frameAnterior = frameAnterior;
		setTitle("Criar An\u00FAncio");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

		JFormattedTextField formattedDataInicio = new JFormattedTextField();
		formattedDataInicio.setBounds(10, 160, 160, 26);
		contentPane.add(formattedDataInicio);

		JLabel lblDataTermino = new JLabel("Data de T\u00E9rmino");
		lblDataTermino.setBounds(209, 135, 160, 14);
		contentPane.add(lblDataTermino);

		JFormattedTextField formattedDataTermino = new JFormattedTextField();
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
		btnVoltar.addActionListener(new ListenerVoltar(this));
		contentPane.add(btnVoltar);

	}
}
