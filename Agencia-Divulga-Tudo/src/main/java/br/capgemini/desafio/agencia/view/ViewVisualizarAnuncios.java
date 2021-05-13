package br.capgemini.desafio.agencia.view;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Panel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import br.capgemini.desafio.agencia.actions.ListenerDeletarAnuncio;
import br.capgemini.desafio.agencia.actions.ListenerViewEditar;
import br.capgemini.desafio.agencia.actions.ListenerVoltarVisualizarAnuncios;
import br.capgemini.desafio.agencia.calculator.Calculadora;
import br.capgemini.desafio.agencia.controller.AnuncioController;
import br.capgemini.desafio.agencia.model.Anuncio;
import br.capgemini.desafio.agencia.model.ResultadoProjecao;
import br.capgemini.desafio.agencia.util.Util;

public class ViewVisualizarAnuncios extends JFrame {

	private static final long serialVersionUID = -5448905658862121036L;
	private AnuncioController anuncioController;
	private ViewEditarAnuncio frameEditarAnuncio;

	public JPanel contentPane;
	public JFrame frameAnterior;
	public JTable table;
	public DefaultTableModel model;
	private JTextField textField;

	/**
	 * Create the frame.
	 */
	public ViewVisualizarAnuncios(JFrame frameAnterior) {
		setTitle("Visualizar An\u00FAncios");
		this.frameAnterior = frameAnterior;
		setAnuncioController(new AnuncioController());
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 1394, 600);
		setResizable(false);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 141, 1357, 314);
		contentPane.add(scrollPane);

		JLabel tituloTable = new JLabel("Tabela de An\u00FAncios");
		tituloTable.setFont(new Font("Tahoma", Font.BOLD, 30));
		tituloTable.setBounds(530, 11, 306, 119);
		contentPane.add(tituloTable);

		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "ID", "Nome do An\u00FAncio", "Cliente", "Data de In\u00EDcio", "Data de T\u00E9rmino",
						"Investimento p/ Dia", "Quantidade Max. Investido", "Quantidade Max. Visualiza\u00E7\u00E3o",
						"Quantidade Max. Cliques", "Quantidade Max. Compartilhamentos" }) {

			private static final long serialVersionUID = 1L;
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] { Long.class, String.class, String.class, String.class, String.class,
					String.class, String.class, String.class, String.class, String.class };

			@SuppressWarnings({ "rawtypes", "unchecked" })
			@Override
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false, false, false, false,
					false };

			@Override
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(41);
		table.getColumnModel().getColumn(1).setPreferredWidth(109);
		table.getColumnModel().getColumn(2).setPreferredWidth(72);
		table.getColumnModel().getColumn(3).setPreferredWidth(88);
		table.getColumnModel().getColumn(4).setPreferredWidth(105);
		table.getColumnModel().getColumn(5).setPreferredWidth(120);
		table.getColumnModel().getColumn(6).setPreferredWidth(152);
		table.getColumnModel().getColumn(7).setPreferredWidth(165);
		table.getColumnModel().getColumn(8).setPreferredWidth(142);
		table.getColumnModel().getColumn(9).setPreferredWidth(203);
		scrollPane.setViewportView(table);

		model = (DefaultTableModel) table.getModel();
		List<Anuncio> anuncios = getAnuncioController().obterTodosAnuncios();
		Calculadora calc = new Calculadora();
		Locale localeBR = new Locale("pt", "BR");
		// NumberFormat formatoDinheiro = NumberFormat.getCurrencyInstance(localeBR);
		NumberFormat formatoNumerico = NumberFormat.getNumberInstance(localeBR);

		for (Anuncio anuncio : anuncios) {
			ResultadoProjecao result = calc.calcularProjecaoAnuncio(anuncio.getInvestimentoPorDia(),
					anuncio.getDataInicio(), anuncio.getDataTermino());

			model.addRow(new Object[] { anuncio.getId(), anuncio.getNome(), anuncio.getCliente(),
					Util.getDataFormatada(anuncio.getDataInicio()), Util.getDataFormatada(anuncio.getDataTermino()),
					formatoNumerico.format(anuncio.getInvestimentoPorDia()), formatoNumerico.format(result.getTotalValorInvestido()),
					formatoNumerico.format(result.getQtdMaxVisualizacao()),
					formatoNumerico.format(result.getQtdMaxCliques()),
					formatoNumerico.format(result.getQtdMaxCompartilhamentos()) });

			calc.resetarAtributos();
		}

		Panel panel = new Panel();
		panel.setBounds(10, 489, 1357, 72);
		contentPane.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ListenerVoltarVisualizarAnuncios(this));
		panel.add(btnVoltar);

		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ListenerViewEditar(this));
		panel.add(btnEditar);

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ListenerDeletarAnuncio(this));
		panel.add(btnExcluir);

		JLabel lblFiltrar = new JLabel("Filtrar");
		lblFiltrar.setBounds(10, 74, 128, 14);
		contentPane.add(lblFiltrar);

		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				DefaultTableModel tabela = (DefaultTableModel) table.getModel();
				String search = textField.getText();
				TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(tabela);
				table.setRowSorter(tr);
				tr.setRowFilter(RowFilter.regexFilter(search));
			}
		});
		textField.setBounds(10, 99, 128, 31);
		contentPane.add(textField);
		textField.setColumns(10);

	}

	public AnuncioController getAnuncioController() {
		return anuncioController;
	}

	public void setAnuncioController(AnuncioController anuncioController) {
		this.anuncioController = anuncioController;
	}

	public ViewEditarAnuncio getFrameEditarAnuncio() {
		return frameEditarAnuncio;
	}

	public void setFrameEditarAnuncio(ViewEditarAnuncio frameEditarAnuncio) {
		this.frameEditarAnuncio = frameEditarAnuncio;
	}
}
