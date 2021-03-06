package br.com.dbcorp.calculo;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import br.com.dbcorp.calculo.cartao.Maquina;
import br.com.dbcorp.calculo.cartao.Maquinas;
import br.com.dbcorp.calculo.cartao.Tipo;

public class InicialUI extends InternalUI implements FocusListener, ActionListener, KeyListener, ItemListener {
	private static final long serialVersionUID = 7068067565699504965L;
	
	private JTextField txTotalPeriodo;
	private JTextField txTotalDinheiroPeriodo;
	private JTextField txTotalCartaoPeriodo;
	private JList<String> vlTpCartaoListPeriodo;
	private JList<String> taxasListPeriodo;
	private JList<Integer> qtdCartaoListPeriodo;
	private JList<String> tpCartaoListPeriodo;

	private JTextField txTotal;
	private JTextField txTotalDinheiro;
	private JTextField txTotalCartao;
	private JTextField txCartao;
	private JTextField txCincoCent;
	private JTextField txDezCent;
	private JTextField txVinteCincoCent;
	private JTextField txCinqCent;
	private JTextField txUm;
	private JTextField txDois;
	private JTextField txCinco;
	private JTextField txDez;
	private JTextField txVinte;
	private JTextField txCinquenta;
	private JTextField txCem;
	private JTextField txSub;
	private JList<String> cartaoList;
	private JList<String> tpCartaoList;
	private JList<String> vlTpCartaoList;
	private JList<String> taxasList;
	private JList<Integer> qtdCartaoList;

	private JComboBox<String> cbMaquina;
	private JComboBox<String> cbTipoCartao;
	private JComboBox<String> cbPeriodo;
	
	private JButton btnRemoverCar;
	private JButton btnAddSub;
	
	private double totalDinheiro;
	private double totalCartao;
	private double totalGeral;
	private JButton btnRemover;
	
	private DecimalFormat decFormat;
	
	private Maquina maquinaSelecionada;
	private Set<String> tiposCartoes;
	
	private Map<JTextField, JLabel> porValor;
	private Map<JTextField, JLabel> paraMil;
	private Map<JTextField, Double> campoValor;
	
	private List<Periodo> periodos;
	private Periodo periodoSelecionado;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public InicialUI() {
		this.setPreferredSize(new Dimension(824, 768));
		
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		this.decFormat = new DecimalFormat("##,##0.00");
		
		this.periodos = new ArrayList<>();
		this.periodos.add(new Periodo());
		this.periodos.add(new Periodo());
		
		this.preparaCartoes();
		
		this.tiposCartoes = new HashSet<>();
		String[] nomeMaquina = new String[this.periodos.get(0).getMaquinas().getMaquina().size()+1];
		nomeMaquina[0] = "Selecione...";
		
		for (int i = 0; i < this.periodos.get(0).getMaquinas().getMaquina().size(); i++) {
			nomeMaquina[i + 1] = this.periodos.get(0).getMaquinas().getMaquina().get(i).getNome();
			
			for (Tipo tipo : this.periodos.get(0).getMaquinas().getMaquina().get(i).getTipos()) {
				this.tiposCartoes.add(tipo.getNome());
			}
		}
		
		JPanel headPanel = new JPanel();
		headPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("center:default"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(378dlu;default):grow"),},
			new RowSpec[] {
				RowSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),}));
		
		String[] pediodo = {"Manh�", "Tarde"};
		this.cbPeriodo = new JComboBox<>(pediodo);
		this.cbPeriodo.addItemListener(this);
		
		JPanel totaisPanel = new JPanel();
		totaisPanel.setBorder(new TitledBorder(null, "Totais Gerais", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		totaisPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),}));
		
		
		
		this.txTotal = new JTextField("0,00");
		this.txTotalDinheiro = new JTextField("0,00");
		this.txTotalCartao = new JTextField("0,00");
		this.tpCartaoList = new JList(this.tiposCartoes.toArray());
		this.vlTpCartaoList = new JList(new DefaultListModel<String>());
		this.taxasList = new JList(new DefaultListModel<String>());
		this.qtdCartaoList = new JList(new DefaultListModel<Integer>());

		this.txTotal.setEditable(false);
		this.txTotalDinheiro.setEditable(false);
		this.txTotalCartao.setEditable(false);
		this.tpCartaoList.setEnabled(false);
		
		totaisPanel.add(new JLabel("Total Geral:"), "2, 2, right, default");
		totaisPanel.add(this.txTotal, "4, 2, fill, default");
		totaisPanel.add(new JLabel("Total Dinheiro"), "6, 2, right, default");
		totaisPanel.add(this.txTotalDinheiro, "8, 2, fill, default");
		totaisPanel.add(new JLabel("Total Cart\u00E3o:"), "10, 2, right, default");
		totaisPanel.add(this.txTotalCartao, "12, 2, fill, default");
		totaisPanel.add(this.tpCartaoList, "2, 4, right, fill");
		totaisPanel.add(this.vlTpCartaoList, "4, 4, fill, fill");
		totaisPanel.add(new JLabel("Taxas:"), "6, 4, right, default");
		totaisPanel.add(this.taxasList, "8, 4, fill, fill");
		totaisPanel.add(new JLabel("Qtd. Cart\u00F5es:"), "10, 4, right, default");
		totaisPanel.add(this.qtdCartaoList, "12, 4, fill, fill");
		///// fim totais gerais
		
		JPanel totaisPeriodoPanel = new JPanel();
		totaisPeriodoPanel.setBorder(new TitledBorder(null, "Totais Per�odo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		totaisPeriodoPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),}));
		
		this.txTotalPeriodo = new JTextField("0,00");
		this.txTotalDinheiroPeriodo = new JTextField("0,00");
		this.txTotalCartaoPeriodo = new JTextField("0,00");
		this.vlTpCartaoListPeriodo = new JList(new DefaultListModel<String>());
		this.taxasListPeriodo = new JList(new DefaultListModel<String>());
		this.qtdCartaoListPeriodo = new JList(new DefaultListModel<Integer>());
		this.tpCartaoListPeriodo = new JList(this.tiposCartoes.toArray());

		this.txTotalPeriodo.setEditable(false);
		this.txTotalDinheiroPeriodo.setEditable(false);
		this.txTotalCartaoPeriodo.setEditable(false);
		this.tpCartaoListPeriodo.setEnabled(false);
		
		totaisPeriodoPanel.add(new JLabel("Total Geral:"), "2, 2, right, default");
		totaisPeriodoPanel.add(this.txTotalPeriodo, "4, 2, fill, default");
		totaisPeriodoPanel.add(new JLabel("Total Dinheiro"), "6, 2, right, default");
		totaisPeriodoPanel.add(this.txTotalDinheiroPeriodo, "8, 2, fill, default");
		totaisPeriodoPanel.add(new JLabel("Total Cart\u00E3o:"), "10, 2, right, default");
		totaisPeriodoPanel.add(this.txTotalCartaoPeriodo, "12, 2, fill, default");
		totaisPeriodoPanel.add(this.tpCartaoListPeriodo, "2, 4, right, fill");
		totaisPeriodoPanel.add(this.vlTpCartaoListPeriodo, "4, 4, fill, fill");
		totaisPeriodoPanel.add(new JLabel("Taxas:"), "6, 4, right, default");
		totaisPeriodoPanel.add(this.taxasListPeriodo, "8, 4, fill, fill");
		totaisPeriodoPanel.add(new JLabel("Qtd. Cart\u00F5es:"), "10, 4, right, default");
		totaisPeriodoPanel.add(this.qtdCartaoListPeriodo, "12, 4, fill, fill");
		
		headPanel.add(totaisPeriodoPanel, "1, 5, 6, 1, fill, fill");
		headPanel.add(totaisPanel, "1, 1, 6, 1, fill, default");
		headPanel.add(new JLabel("Per\u00EDodo:"), "2, 3, right, default");
		headPanel.add(this.cbPeriodo, "4, 3, fill, default");
		
		JPanel cartaoPanel = new JPanel();
		cartaoPanel.setBorder(new TitledBorder(null, "Cart\u00E3o", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		cartaoPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(100dlu;default):grow"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		this.txCartao = new JTextField("0,00");
		this.cartaoList = new JList<String>(new DefaultListModel<String>());
		this.btnRemoverCar = new JButton("Remover");
		this.cbMaquina = new JComboBox(nomeMaquina);
		this.cbTipoCartao = new JComboBox();
		
		this.txCartao.addKeyListener(this);
		this.btnRemoverCar.addActionListener(this);
		this.cbMaquina.addItemListener(this);
		this.cbTipoCartao.addItemListener(this);

		cartaoPanel.add(new JLabel("Maquina:"), "2, 2, right, default");
		cartaoPanel.add(this.cbMaquina, "4, 2, fill, default");
		cartaoPanel.add(new JLabel("Tipo:"), "2, 4, right, default");
		cartaoPanel.add(this.cbTipoCartao, "4, 4, fill, default");
		cartaoPanel.add(new JLabel("Valor:"), "2, 6, right, default");
		cartaoPanel.add(this.txCartao, "4, 6, fill, default");
		cartaoPanel.add(this.cartaoList, "2, 8, 3, 1, fill, fill");
		cartaoPanel.add(this.btnRemoverCar, "2, 10, 3, 1");
		
		JPanel dinheiroPanel = new JPanel();
		dinheiroPanel.setBorder(new TitledBorder(null, "Dinheiro", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		dinheiroPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		this.txCincoCent = new JTextField("0");
		this.txDezCent = new JTextField("0");
		this.txVinteCincoCent = new JTextField("0");
		this.txCinqCent = new JTextField("0");
		this.txUm = new JTextField("0");
		this.txDois = new JTextField("0");
		this.txCinco = new JTextField("0");
		this.txDez = new JTextField("0");
		this.txVinte = new JTextField("0");
		this.txCinquenta = new JTextField("0");
		this.txCem = new JTextField("0");
		this.txSub = new JTextField("0,00");
		
		this.txCincoCent.addFocusListener(this);
		this.txDezCent.addFocusListener(this);
		this.txVinteCincoCent.addFocusListener(this);
		this.txCinqCent.addFocusListener(this);
		this.txUm.addFocusListener(this);
		this.txDois.addFocusListener(this);
		this.txCinco.addFocusListener(this);
		this.txDez.addFocusListener(this);
		this.txVinte.addFocusListener(this);
		this.txCinquenta.addFocusListener(this);
		this.txCem.addFocusListener(this);
		
		JPanel btnPanel = new JPanel();

		this.btnAddSub = new JButton("Adiciona Total");
		this.btnRemover = new JButton("Remover Total");
		
		this.btnAddSub.setHorizontalAlignment(SwingConstants.LEFT);
		this.btnRemover.setHorizontalAlignment(SwingConstants.RIGHT);
		
		this.btnAddSub.addActionListener(this);
		this.btnRemover.addActionListener(this);
		
		btnPanel.add(btnAddSub);
		btnPanel.add(btnRemover);
		
		JPanel notaPanel = new JPanel();
		notaPanel.setBorder(new TitledBorder(null, "Totais de notas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		notaPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("center:max(10dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(10dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(10dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(10dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(10dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(10dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(10dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(10dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(10dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(10dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("right:max(10dlu;default)"),},
			new RowSpec[] {
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JLabel lbTotCincoCent = new JLabel("0");
		JLabel lbTotDezCent = new JLabel("0");
		JLabel lbTotVinteCincoCent = new JLabel("0");
		JLabel lbTotCinquentaCent = new JLabel("0");
		JLabel lblTotUm = new JLabel("0");
		JLabel lblTotDois = new JLabel("0");
		lblTotDois.setHorizontalAlignment(SwingConstants.CENTER);
		JLabel lbTotCinco = new JLabel("0");
		JLabel lbTotDez = new JLabel("0");
		JLabel lbTotVinte = new JLabel("0");
		JLabel lbTotCinquenta = new JLabel("0");
		JLabel lbTotCem = new JLabel("0");

		Font fonte = new Font("Tahoma", Font.BOLD | Font.ITALIC, 11);
		
		lbTotCincoCent.setFont(fonte);
		lbTotDezCent.setFont(fonte);
		lbTotVinteCincoCent.setFont(fonte);
		lbTotCinquentaCent.setFont(fonte);
		lblTotUm.setFont(fonte);
		lblTotDois.setFont(fonte);
		lbTotCinco.setFont(fonte);
		lbTotDez.setFont(fonte);
		lbTotVinte.setFont(fonte);
		lbTotCinquenta.setFont(fonte);
		lbTotCem.setFont(fonte);
		
		notaPanel.add(new JLabel("0,05:"), "1, 1, left, top");
		notaPanel.add(lbTotCincoCent, "3, 1, center, default");
		notaPanel.add(new JLabel("0,10:"), "5, 1");
		notaPanel.add(lbTotDezCent, "7, 1");
		notaPanel.add(new JLabel("0,25:"), "9, 1");
		notaPanel.add(lbTotVinteCincoCent, "11, 1");
		notaPanel.add(new JLabel("0,50:"), "13, 1");
		notaPanel.add(lbTotCinquentaCent, "15, 1");
		notaPanel.add(new JLabel("1,00:"), "17, 1");
		notaPanel.add(lblTotUm, "19, 1");
		notaPanel.add(new JLabel("2,00:"), "1, 3");
		notaPanel.add(lblTotDois, "3, 3, center, default");
		notaPanel.add(new JLabel("5,00:"), "5, 3");
		notaPanel.add(lbTotCinco, "7, 3");
		notaPanel.add(new JLabel("10,00:"), "9, 3");
		notaPanel.add(lbTotDez, "11, 3");
		notaPanel.add(new JLabel("20,00:"), "13, 3");
		notaPanel.add(lbTotVinte, "15, 3");
		notaPanel.add(new JLabel("50,00:"), "17, 3");
		notaPanel.add(lbTotCinquenta, "19, 3");
		notaPanel.add(new JLabel("100,00:"), "21, 3");
		notaPanel.add(lbTotCem, "23, 3");
		
		JPanel milPanel = new JPanel();
		milPanel.setBorder(new TitledBorder(null, "Para Mil", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		milPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("center:max(10dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(10dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(10dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(10dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(10dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(10dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(10dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(10dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(10dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(10dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("right:max(10dlu;default)"),},
			new RowSpec[] {
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JLabel lbMilTotCincoCent = new JLabel("20000");
		JLabel lbMilTotDezCent = new JLabel("10000");
		JLabel lbMilTotVinteCincoCent = new JLabel("4000");
		JLabel lbMilTotCinquentaCent = new JLabel("2000");
		JLabel lbMilTotUm = new JLabel("1000");
		JLabel lbMilTotDois = new JLabel("500");
		JLabel lbMilTotCinco = new JLabel("200");
		JLabel lbMilTotDez = new JLabel("100");
		JLabel lbMilTotVinte = new JLabel("50");
		JLabel lbMilTotCinquenta = new JLabel("20");
		JLabel lbMilTotCem = new JLabel("10");

		lbMilTotCincoCent.setFont(fonte);
		lbMilTotDezCent.setFont(fonte);
		lbMilTotVinteCincoCent.setFont(fonte);
		lbMilTotCinquentaCent.setFont(fonte);
		lbMilTotUm.setFont(fonte);
		lbMilTotDois.setFont(fonte);
		lbMilTotCinco.setFont(fonte);
		lbMilTotDez.setFont(fonte);
		lbMilTotVinte.setFont(fonte);
		lbMilTotCinquenta.setFont(fonte);
		lbMilTotCem.setFont(fonte);
		
		milPanel.add(new JLabel("0,05:"), "1, 1, left, top");
		milPanel.add(lbMilTotCincoCent, "3, 1, center, default");
		milPanel.add(new JLabel("0,10:"), "5, 1");
		milPanel.add(lbMilTotDezCent, "7, 1");
		milPanel.add(new JLabel("0,25:"), "9, 1");
		milPanel.add(lbMilTotVinteCincoCent, "11, 1");
		milPanel.add(new JLabel("0,50:"), "13, 1");
		milPanel.add(lbMilTotCinquentaCent, "15, 1");
		milPanel.add(new JLabel("1,00:"), "17, 1");
		milPanel.add(lbMilTotUm, "19, 1");
		milPanel.add(new JLabel("2,00:"), "1, 3");
		milPanel.add(lbMilTotDois, "3, 3, center, default");
		milPanel.add(new JLabel("5,00:"), "5, 3");
		milPanel.add(lbMilTotCinco, "7, 3");
		milPanel.add(new JLabel("10,00:"), "9, 3");
		milPanel.add(lbMilTotDez, "11, 3");
		milPanel.add(new JLabel("20,00:"), "13, 3");
		milPanel.add(lbMilTotVinte, "15, 3");
		milPanel.add(new JLabel("50,00:"), "17, 3");
		milPanel.add(lbMilTotCinquenta, "19, 3");
		milPanel.add(new JLabel("100,00:"), "21, 3");
		milPanel.add(lbMilTotCem, "23, 3");
		
		this.porValor = new HashMap<>();
		this.porValor.put(this.txCincoCent, lbTotCincoCent);
		this.porValor.put(this.txDezCent, lbTotDezCent);
		this.porValor.put(this.txVinteCincoCent, lbTotVinteCincoCent);
		this.porValor.put(this.txCinqCent, lbTotCinquentaCent);
		this.porValor.put(this.txUm, lblTotUm);
		this.porValor.put(this.txDois, lblTotDois);
		this.porValor.put(this.txCinco, lbTotCinco);
		this.porValor.put(this.txDez, lbTotDez);
		this.porValor.put(this.txVinte, lbTotVinte);
		this.porValor.put(this.txCinquenta, lbTotCinquenta);
		this.porValor.put(this.txCem, lbTotCem);
		
		this.paraMil = new HashMap<>();
		this.paraMil.put(this.txCincoCent, lbMilTotCincoCent);
		this.paraMil.put(this.txDezCent, lbMilTotDezCent);
		this.paraMil.put(this.txVinteCincoCent, lbMilTotVinteCincoCent);
		this.paraMil.put(this.txCinqCent, lbMilTotCinquentaCent);
		this.paraMil.put(this.txUm, lbMilTotUm);
		this.paraMil.put(this.txDois, lbMilTotDois);
		this.paraMil.put(this.txCinco, lbMilTotCinco);
		this.paraMil.put(this.txDez, lbMilTotDez);
		this.paraMil.put(this.txVinte, lbMilTotVinte);
		this.paraMil.put(this.txCinquenta, lbMilTotCinquenta);
		this.paraMil.put(this.txCem, lbMilTotCem);
		
		this.campoValor = new HashMap<>();
		this.campoValor.put(this.txCincoCent, 0.05);
		this.campoValor.put(this.txDezCent, 0.10);
		this.campoValor.put(this.txVinteCincoCent, 0.25);
		this.campoValor.put(this.txCinqCent, 0.50);
		this.campoValor.put(this.txUm, 1.0);
		this.campoValor.put(this.txDois, 2.0);
		this.campoValor.put(this.txCinco, 5.0);
		this.campoValor.put(this.txDez, 10.0);
		this.campoValor.put(this.txVinte, 20.0);
		this.campoValor.put(this.txCinquenta, 50.0);
		this.campoValor.put(this.txCem, 100.0);
		
		dinheiroPanel.add(new JLabel("0,05:"), "2, 2, right, default");
		dinheiroPanel.add(this.txCincoCent, "4, 2, fill, top");
		dinheiroPanel.add(new JLabel("0,10:"), "6, 2, right, default");
		dinheiroPanel.add(this.txDezCent, "8, 2, fill, default");
		dinheiroPanel.add(new JLabel("0,25:"), "10, 2, right, default");
		dinheiroPanel.add(this.txVinteCincoCent, "12, 2, fill, default");
		dinheiroPanel.add(new JLabel("0,50:"), "2, 4, right, default");
		dinheiroPanel.add(this.txCinqCent, "4, 4, fill, default");
		dinheiroPanel.add(new JLabel("1,00:"), "6, 4, right, default");
		dinheiroPanel.add(this.txUm, "8, 4, fill, top");
		dinheiroPanel.add(new JLabel("2,00:"), "10, 4, right, default");
		dinheiroPanel.add(this.txDois, "12, 4, fill, default");
		dinheiroPanel.add(new JLabel("5,00:"), "2, 6, right, default");
		dinheiroPanel.add(this.txCinco, "4, 6, fill, default");
		dinheiroPanel.add(new JLabel("10,00:"), "6, 6, right, default");
		dinheiroPanel.add(this.txDez, "8, 6, fill, default");
		dinheiroPanel.add(new JLabel("20,00:"), "10, 6, right, default");
		dinheiroPanel.add(this.txVinte, "12, 6, fill, default");
		dinheiroPanel.add(new JLabel("50,00:"), "2, 8, right, default");
		dinheiroPanel.add(this.txCinquenta, "4, 8, fill, default");
		dinheiroPanel.add(new JLabel("100,00:"), "6, 8, right, default");
		dinheiroPanel.add(this.txCem, "8, 8, fill, default");
		dinheiroPanel.add(new JLabel("SubTotal:"), "2, 12, right, default");
		dinheiroPanel.add(this.txSub, "4, 12, 5, 1, fill, default");
		dinheiroPanel.add(btnPanel, "10, 12, 3, 1, fill, fill");
		dinheiroPanel.add(notaPanel, "2, 14, 11, 1, fill, fill");
		dinheiroPanel.add(milPanel, "2, 16, 11, 1, fill, fill");
		
		this.setPeriodo();
		
		this.inicializaListaCartoes();
		
		getContentPane().add(headPanel, BorderLayout.NORTH);
		getContentPane().add(cartaoPanel, BorderLayout.WEST);
		getContentPane().add(dinheiroPanel, BorderLayout.CENTER);
		
		setVisible(true);
	}

	@Override
	public void reset() {
		
	}

	//FocusListener
	@Override
	public void focusGained(FocusEvent event) {}

	//FocusListener
	@Override
	public void focusLost(FocusEvent event) {
		double subTotal = 0.0;
		
		for (JTextField field : this.campoValor.keySet()) {
			subTotal += Long.parseLong(field.getText()) * this.campoValor.get(field);
		}
		
		this.txSub.setText(this.decFormat.format(subTotal));
		
		//para mil
		double faltante = 1000.0 - subTotal;
		
		for (JTextField field : this.paraMil.keySet()) {
			Double temp = faltante / this.campoValor.get(field);
			this.paraMil.get(field).setText(Integer.toString(temp.intValue()));
		}
	}

	//ActionListener
	@Override
	public void actionPerformed(ActionEvent event) {
		double subTotal = 0d;
		
		try {
			subTotal = this.decFormat.parse(this.txSub.getText()).doubleValue();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		if (event.getSource() == this.btnAddSub) {
			this.totalDinheiro += subTotal;
			this.totalGeral += subTotal;
			this.periodoSelecionado.setTotDinheiro(this.periodoSelecionado.getTotDinheiro() + subTotal);
			this.periodoSelecionado.setTotGeral(this.periodoSelecionado.getTotGeral() + subTotal);
			
			this.txTotalDinheiro.setText(this.decFormat.format(this.totalDinheiro));
			this.txTotal.setText(this.decFormat.format(this.totalGeral));
			this.txTotalDinheiroPeriodo.setText(this.decFormat.format(this.periodoSelecionado.getTotDinheiro()));
			this.txTotalPeriodo.setText(this.decFormat.format(this.periodoSelecionado.getTotGeral()));
			
			for (JTextField field : this.porValor.keySet()) {
				Integer total = Integer.parseInt(this.porValor.get(field).getText()) + Integer.parseInt(field.getText());
				
				this.porValor.get(field).setText(total.toString());
			}
			
			this.resetDinheiro();
		
		} else if (event.getSource() == this.btnRemover) {
			this.totalDinheiro -= subTotal;
			this.totalGeral -= subTotal;
			this.periodoSelecionado.setTotDinheiro(this.periodoSelecionado.getTotDinheiro() - subTotal);
			this.periodoSelecionado.setTotGeral(this.periodoSelecionado.getTotGeral() - subTotal);
			
			this.txTotalDinheiro.setText(this.decFormat.format(this.totalDinheiro));
			this.txTotal.setText(this.decFormat.format(this.totalGeral));
			this.txTotalDinheiroPeriodo.setText(this.decFormat.format(this.periodoSelecionado.getTotDinheiro()));
			this.txTotalPeriodo.setText(this.decFormat.format(this.periodoSelecionado.getTotGeral()));
			
			for (JTextField field : this.porValor.keySet()) {
				Integer total = Integer.parseInt(this.porValor.get(field).getText()) - Integer.parseInt(field.getText());
				
				this.porValor.get(field).setText(total.toString());
			}
			
			this.resetDinheiro();
		
		} else if (event.getSource() == this.btnRemoverCar) {
			this.removerCartao();
		}
	}

	@Override
	public void keyPressed(KeyEvent key) {
		if (key.getKeyCode() == 10) {
			try {
				double cartao = this.decFormat.parse(this.txCartao.getText()).doubleValue();
				
				this.totalCartao += cartao;
				this.totalGeral += cartao;
				this.periodoSelecionado.setTotCartao(this.periodoSelecionado.getTotCartao() + cartao);
				this.periodoSelecionado.setTotGeral(this.periodoSelecionado.getTotGeral() + cartao);
				
				((DefaultListModel<String>) this.cartaoList.getModel()).addElement(this.decFormat.format(cartao));
				
				this.txCartao.setText("");
				this.txTotalCartao.setText(this.decFormat.format(this.totalCartao));
				this.txTotal.setText(this.decFormat.format(this.totalGeral));
				this.txTotalCartaoPeriodo.setText(this.decFormat.format(this.periodoSelecionado.getTotCartao()));
				this.txTotalPeriodo.setText(this.decFormat.format(this.periodoSelecionado.getTotGeral()));
				
				int index = 0;
				Tipo tipo = null;
				
				for (int i = 0; i < this.maquinaSelecionada.getTipos().size(); i++) {
					if (this.cbTipoCartao.getSelectedItem().equals(this.maquinaSelecionada.getTipos().get(i).getNome())) {
						tipo = this.maquinaSelecionada.getTipos().get(i);
						
						for (; index < this.tpCartaoList.getModel().getSize(); index++) {
							if (tipo.getNome().equals(this.tpCartaoList.getModel().getElementAt(index))) {
								break;
							}
						}
						
						break;
					}
				}
				
				if (tipo != null) {
					double taxaValor = cartao * (tipo.getTaxa()/100);
					
					if (this.periodoSelecionado.getValoresTipo().get(tipo.getNome()) == null) {
						this.periodoSelecionado.getValoresTipo().put(tipo.getNome(), 0d);
					}
					
					if (this.periodoSelecionado.getTaxasTipo().get(tipo.getNome()) == null) {
						this.periodoSelecionado.getTaxasTipo().put(tipo.getNome(), 0d);
					}
					
					if (this.periodoSelecionado.getQtdTipo().get(tipo.getNome()) == null) {
						this.periodoSelecionado.getQtdTipo().put(tipo.getNome(), 0);
					}
					
					this.periodoSelecionado.getValoresTipo().put(tipo.getNome(), this.periodoSelecionado.getValoresTipo().get(tipo.getNome()) + cartao);
					this.periodoSelecionado.getTaxasTipo().put(tipo.getNome(), this.periodoSelecionado.getTaxasTipo().get(tipo.getNome()) + taxaValor);
					this.periodoSelecionado.getQtdTipo().put(tipo.getNome(), this.periodoSelecionado.getQtdTipo().get(tipo.getNome()) + 1);
					
					double temp = this.decFormat.parse(this.vlTpCartaoList.getModel().getElementAt(index)).doubleValue() + cartao;
					double temp2 = this.decFormat.parse(this.vlTpCartaoListPeriodo.getModel().getElementAt(index)).doubleValue() + cartao;
					
					((DefaultListModel<String>)this.vlTpCartaoList.getModel()).setElementAt(this.decFormat.format(temp), index);
					((DefaultListModel<String>)this.vlTpCartaoListPeriodo.getModel()).setElementAt(this.decFormat.format(temp2), index);
					
					temp = this.decFormat.parse(this.taxasList.getModel().getElementAt(index)).doubleValue() + taxaValor;
					temp2 = this.decFormat.parse(this.taxasListPeriodo.getModel().getElementAt(index)).doubleValue() + taxaValor;
					
					((DefaultListModel<String>)this.taxasList.getModel()).setElementAt(this.decFormat.format(temp), index);
					((DefaultListModel<String>)this.taxasListPeriodo.getModel()).setElementAt(this.decFormat.format(temp2), index);
					
					((DefaultListModel<Integer>)this.qtdCartaoList.getModel()).setElementAt(this.qtdCartaoList.getModel().getElementAt(index) + 1, index);
					((DefaultListModel<Integer>)this.qtdCartaoListPeriodo.getModel()).setElementAt(this.qtdCartaoListPeriodo.getModel().getElementAt(index) + 1, index);
				}
				
				
				
				/*Double taxa = 0d;
				Map<String, Double> valoresTipo = new HashMap<>();
				Map<String, Double> taxasTipo = new HashMap<>();
				Map<String, Integer> qtdTipo = new HashMap<>();
				
				for (Maquina maquina : this.maquinas.getMaquina()) {
					for (Tipo tipo : maquina.getTipos()) {
						if (!valoresTipo.containsKey(tipo.getNome())) {
							valoresTipo.put(tipo.getNome(), 0d);
							taxasTipo.put(tipo.getNome(), 0d);
							qtdTipo.put(tipo.getNome(), 0);
						}
						
						for (int i = 0; i < tipo.getListaValores().size(); i++) {
							double valor =  this.decFormat.parse(tipo.getListaValores().get(i)).doubleValue();
							double taxaValor = valor * (tipo.getTaxa()/100);
							
							valoresTipo.put(tipo.getNome(), valoresTipo.get(tipo.getNome()) + valor);
							taxasTipo.put(tipo.getNome(), taxasTipo.get(tipo.getNome()) + taxaValor);
							qtdTipo.put(tipo.getNome(), qtdTipo.get(tipo.getNome()) + 1);
							
							taxa += taxaValor;
						}
					}
				}
				
				((DefaultListModel<String>)this.vlTpCartaoList.getModel()).removeAllElements();
				((DefaultListModel<String>)this.taxasList.getModel()).removeAllElements();
				((DefaultListModel<Integer>)this.qtdCartaoList.getModel()).removeAllElements();
				
				for (String tipo : this.tiposCartoes) {
					((DefaultListModel<String>)this.vlTpCartaoList.getModel()).addElement(this.decFormat.format(valoresTipo.get(tipo)));
					((DefaultListModel<String>)this.taxasList.getModel()).addElement(this.decFormat.format(taxasTipo.get(tipo)));
					((DefaultListModel<Integer>)this.qtdCartaoList.getModel()).addElement(qtdTipo.get(tipo));
				}*/
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
	}
	
	//ItemListener
	@Override
	public void itemStateChanged(ItemEvent event) {
		if (event.getSource() == this.cbMaquina) {
			this.selecaoMaquina();
		
		} else if (event.getSource() == this.cbTipoCartao) {
			Tipo tipo = null;
			
			for (Tipo temp : this.maquinaSelecionada.getTipos()) {
				if (temp.getNome().equals(this.cbTipoCartao.getSelectedItem())) {
					tipo = temp;
				}
			}
			
			if (tipo != null) {
				this.cartaoList.setModel(tipo.getListaValores());
			} else {
				this.cartaoList.setModel(new DefaultListModel<String>());
			}
		} else if (event.getSource() == this.cbPeriodo) {
			this.setPeriodo();
			selecaoMaquina();
		}
	}

	//KeyListener
	@Override
	public void keyReleased(KeyEvent key) {}
	//KeyListener
	@Override
	public void keyTyped(KeyEvent key) {}
	
	private void resetDinheiro() {
		this.txCincoCent.setText("0");
		this.txDezCent.setText("0");
		this.txVinteCincoCent.setText("0");
		this.txCinqCent.setText("0");
		this.txUm.setText("0");
		this.txDois.setText("0");
		this.txCinco.setText("0");
		this.txDez.setText("0");
		this.txVinte.setText("0");
		this.txCinquenta.setText("0");
		this.txCem.setText("0");
		this.txSub.setText("0,00");
		
		for (JTextField field : this.paraMil.keySet()) {
			Double temp = 1000 / this.campoValor.get(field);
			
			this.paraMil.get(field).setText(Integer.toString(temp.intValue()));
		}
	}
	
	private void preparaCartoes() {
		try {
			File file = Paths.get(".").toAbsolutePath().normalize().resolve("cartoes.xml").toFile();
			JAXBContext jaxbContext = JAXBContext.newInstance(Maquinas.class);
			
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			
			for (int i = 0; i < this.periodos.size(); i++) {
				this.periodos.get(i).setMaquinas((Maquinas) jaxbUnmarshaller.unmarshal(file));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void selecaoMaquina() {
		for (Maquina temp : this.periodoSelecionado.getMaquinas().getMaquina()) {
			if (temp.getNome().equals(this.cbMaquina.getSelectedItem())) {
				this.maquinaSelecionada = temp;
				break;
			}
		}
		
		/*for (Maquina temp : this.maquinas.getMaquina()) {
			if (temp.getNome().equals(this.cbMaquina.getSelectedItem())) {
				this.maquinaSelecionada = temp;
				break;
			}
		}*/
		
		if (this.maquinaSelecionada != null) {
			this.cbTipoCartao.removeAllItems();
			this.cbTipoCartao.addItem("Selecione...");

			for (Tipo tipo : this.maquinaSelecionada.getTipos()) {
				this.cbTipoCartao.addItem(tipo.getNome());
			}
		}
	}
	
	private void removerCartao() {
		try {
			double cartao = this.decFormat.parse(this.cartaoList.getSelectedValue()).doubleValue();
			
			((DefaultListModel<String>) this.cartaoList.getModel()).removeElementAt(this.cartaoList.getSelectedIndex());
			
			this.totalCartao -= cartao;
			this.totalGeral -= cartao;
			this.periodoSelecionado.setTotCartao(this.periodoSelecionado.getTotCartao() - cartao);
			this.periodoSelecionado.setTotGeral(this.periodoSelecionado.getTotGeral() - cartao);
			
			this.txTotalCartao.setText(this.decFormat.format(this.totalCartao));
			this.txTotal.setText(this.decFormat.format(this.totalGeral));
			this.txTotalCartaoPeriodo.setText(this.decFormat.format(this.periodoSelecionado.getTotCartao()));
			this.txTotalPeriodo.setText(this.decFormat.format(this.periodoSelecionado.getTotGeral()));
			
			int index = 0;
			Tipo tipo = null;
			
			for (int i = 0; i < this.maquinaSelecionada.getTipos().size(); i++) {
				if (this.cbTipoCartao.getSelectedItem().equals(this.maquinaSelecionada.getTipos().get(i).getNome())) {
					tipo = this.maquinaSelecionada.getTipos().get(i);
					
					for (; index < this.tpCartaoList.getModel().getSize(); index++) {
						if (tipo.getNome().equals(this.tpCartaoList.getModel().getElementAt(index))) {
							break;
						}
					}
					
					break;
				}
			}
			
			if (tipo != null) {
				double taxaValor = cartao * (tipo.getTaxa()/100);
				
				double temp = this.decFormat.parse(this.vlTpCartaoList.getModel().getElementAt(index)).doubleValue() - cartao;
				double temp2 = this.decFormat.parse(this.vlTpCartaoListPeriodo.getModel().getElementAt(index)).doubleValue() - cartao;
				
				((DefaultListModel<String>)this.vlTpCartaoList.getModel()).setElementAt(this.decFormat.format(temp), index);
				((DefaultListModel<String>)this.vlTpCartaoListPeriodo.getModel()).setElementAt(this.decFormat.format(temp2), index);
				
				temp = this.decFormat.parse(this.taxasList.getModel().getElementAt(index)).doubleValue() - taxaValor;
				temp2 = this.decFormat.parse(this.taxasListPeriodo.getModel().getElementAt(index)).doubleValue() - taxaValor;
				
				((DefaultListModel<String>)this.taxasList.getModel()).setElementAt(this.decFormat.format(temp), index);
				((DefaultListModel<String>)this.taxasListPeriodo.getModel()).setElementAt(this.decFormat.format(temp2), index);
				
				((DefaultListModel<Integer>)this.qtdCartaoList.getModel()).setElementAt(this.qtdCartaoList.getModel().getElementAt(index) - 1, index);
				((DefaultListModel<Integer>)this.qtdCartaoListPeriodo.getModel()).setElementAt(this.qtdCartaoListPeriodo.getModel().getElementAt(index) - 1, index);
				
				this.periodoSelecionado.getValoresTipo().put(tipo.getNome(), this.periodoSelecionado.getValoresTipo().get(tipo.getNome()) - cartao);
				this.periodoSelecionado.getTaxasTipo().put(tipo.getNome(), this.periodoSelecionado.getTaxasTipo().get(tipo.getNome()) - taxaValor);
				this.periodoSelecionado.getQtdTipo().put(tipo.getNome(), this.periodoSelecionado.getQtdTipo().get(tipo.getNome()) - 1);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	private void setPeriodo() {
		this.periodoSelecionado = this.periodos.get(this.cbPeriodo.getSelectedIndex());
		
		this.txTotalDinheiroPeriodo.setText(this.decFormat.format(this.periodoSelecionado.getTotDinheiro()));
		this.txTotalPeriodo.setText(this.decFormat.format(this.periodoSelecionado.getTotGeral()));
		this.txTotalCartaoPeriodo.setText(this.decFormat.format(this.periodoSelecionado.getTotCartao()));
		
		((DefaultListModel<String>)this.vlTpCartaoListPeriodo.getModel()).removeAllElements();
		((DefaultListModel<String>)this.taxasListPeriodo.getModel()).removeAllElements();
		((DefaultListModel<Integer>)this.qtdCartaoListPeriodo.getModel()).removeAllElements();
		//((DefaultListModel<String>)this.cartaoList.getModel()).removeAllElements();
		
		for (String tipo : this.tiposCartoes) {
			
			if (this.periodoSelecionado.getValoresTipo().get(tipo) != null) {
				((DefaultListModel<String>)this.vlTpCartaoListPeriodo.getModel()).addElement(this.decFormat.format(this.periodoSelecionado.getValoresTipo().get(tipo)));
			
			} else {
				((DefaultListModel<String>)this.vlTpCartaoListPeriodo.getModel()).addElement("0,00");
			}
			
			if (this.periodoSelecionado.getTaxasTipo().get(tipo) != null) {
				((DefaultListModel<String>)this.taxasListPeriodo.getModel()).addElement(this.decFormat.format(this.periodoSelecionado.getTaxasTipo().get(tipo)));
			
			} else {
				((DefaultListModel<String>)this.taxasListPeriodo.getModel()).addElement("0,00");
			}
	
			if (this.periodoSelecionado.getQtdTipo().get(tipo) != null) {
				((DefaultListModel<Integer>)this.qtdCartaoListPeriodo.getModel()).addElement(this.periodoSelecionado.getQtdTipo().get(tipo));
			
			} else {
				((DefaultListModel<Integer>)this.qtdCartaoListPeriodo.getModel()).addElement(0);
			}
		}
	}
	
	private void inicializaListaCartoes() {
		for (int i = 0; i < this.tpCartaoList.getModel().getSize(); i++) {
			((DefaultListModel<String>)this.vlTpCartaoList.getModel()).addElement("0,00");
			((DefaultListModel<String>)this.taxasList.getModel()).addElement("0,00");
			((DefaultListModel<Integer>)this.qtdCartaoList.getModel()).addElement(0);
		}
	}
}
