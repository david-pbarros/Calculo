package br.com.dbcorp.calculo;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DecimalFormat;
import java.text.ParseException;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

public class InicialUI extends InternalUI implements FocusListener, ActionListener, KeyListener {
	private static final long serialVersionUID = 7068067565699504965L;
	
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
	
	private JButton btnRemoverCar;
	private JButton btnAddSub;
	
	private double totalDinheiro;
	private double totalCartao;
	private double totalGeral;
	private JButton btnRemover;
	
	private DecimalFormat decFormat;
	
	public InicialUI() {
		
		this.setPreferredSize(new Dimension(824, 768));
		
		getContentPane();
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		this.decFormat = new DecimalFormat("##,##0.00");
		
		JPanel totaisPanel = new JPanel();
		totaisPanel.setBorder(new TitledBorder(null, "Totais", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(totaisPanel, BorderLayout.NORTH);
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
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		this.txTotal = new JTextField("0,00");
		this.txTotalDinheiro = new JTextField("0,00");
		this.txTotalCartao = new JTextField("0,00");
		this.txTotal.setEditable(false);
		this.txTotalDinheiro.setEditable(false);
		this.txTotalCartao.setEditable(false);
		
		totaisPanel.add(new JLabel("Total Geral:"), "2, 2, right, default");
		totaisPanel.add(this.txTotal, "4, 2, fill, default");
		totaisPanel.add(new JLabel("Total Dinheiro"), "6, 2, right, default");
		totaisPanel.add(this.txTotalDinheiro, "8, 2, fill, default");
		totaisPanel.add(new JLabel("Total Cart\u00E3o:"), "10, 2, right, default");
		totaisPanel.add(this.txTotalCartao, "12, 2, fill, default");
		
		JPanel cartaoPanel = new JPanel();
		cartaoPanel.setBorder(new TitledBorder(null, "Cart\u00E3o", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(cartaoPanel, BorderLayout.WEST);
		cartaoPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(100dlu;default):grow"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		this.txCartao = new JTextField("0,00");
		this.cartaoList = new JList<String>(new DefaultListModel<String>());
		this.btnRemoverCar = new JButton("Remover");
		
		this.txCartao.addKeyListener(this);
		this.btnRemoverCar.addActionListener(this);

		cartaoPanel.add(new JLabel("Valor:"), "2, 2, right, default");
		cartaoPanel.add(this.txCartao, "4, 2, fill, default");
		cartaoPanel.add(this.cartaoList, "2, 4, 3, 1, fill, fill");
		cartaoPanel.add(this.btnRemoverCar, "2, 6, 3, 1");
		
		JPanel dinheiroPanel = new JPanel();
		dinheiroPanel.setBorder(new TitledBorder(null, "Dinheiro", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(dinheiroPanel, BorderLayout.CENTER);
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
		double cincCent = Long.parseLong(this.txCincoCent.getText()) * 0.05;
		double dezCent = Long.parseLong(this.txDezCent.getText()) * 0.10;
		double vinteCincoCent = Long.parseLong(this.txVinteCincoCent.getText()) * 0.25;
		double cinqCent = Long.parseLong(this.txCinqCent.getText()) * 0.50;
		double um = Long.parseLong(this.txUm.getText());
		double dois = Long.parseLong(this.txDois.getText()) * 2;
		double cinco = Long.parseLong(this.txCinco.getText()) * 5;
		double dez = Long.parseLong(this.txDez.getText()) * 10;
		double vinte = Long.parseLong(this.txVinte.getText()) * 20;
		double cinq = Long.parseLong(this.txCinquenta.getText()) * 50;
		double cem = Long.parseLong(this.txCem.getText()) * 100;
		
		double subTotal = cincCent + dezCent + vinteCincoCent + cinqCent + um + dois + cinco + dez + vinte + cinq + cem;
		
		this.txSub.setText(this.decFormat.format(subTotal));
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
			
			this.txTotalDinheiro.setText(this.decFormat.format(this.totalDinheiro));
			this.txTotal.setText(this.decFormat.format(this.totalGeral));
			
			this.resetDinheiro();
		
		} else if (event.getSource() == this.btnRemover) {
			this.totalDinheiro -= subTotal;
			this.totalGeral -= subTotal;
			
			this.txTotalDinheiro.setText(this.decFormat.format(this.totalDinheiro));
			this.txTotal.setText(this.decFormat.format(this.totalGeral));
			
			this.resetDinheiro();
		
		} else if (event.getSource() == this.btnRemoverCar) {
			try {
				double cartao = this.decFormat.parse(this.cartaoList.getSelectedValue()).doubleValue();
				
				((DefaultListModel<String>) this.cartaoList.getModel()).removeElementAt(this.cartaoList.getSelectedIndex());
				
				this.totalCartao -= cartao;
				this.totalGeral -= cartao;
				
				this.txTotalCartao.setText(this.decFormat.format(this.totalCartao));
				this.txTotal.setText(this.decFormat.format(this.totalGeral));
				
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent key) {
		if (key.getKeyCode() == 10) {
			try {
				double cartao = this.decFormat.parse(this.txCartao.getText()).doubleValue();
				
				this.totalCartao += cartao;
				this.totalGeral += cartao;
				
				((DefaultListModel<String>) this.cartaoList.getModel()).addElement(this.decFormat.format(cartao));
				
				this.txCartao.setText("");
				this.txTotalCartao.setText(this.decFormat.format(this.totalCartao));
				this.txTotal.setText(this.decFormat.format(this.totalGeral));
				
			} catch (ParseException e) {
				e.printStackTrace();
			}
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
	}
}