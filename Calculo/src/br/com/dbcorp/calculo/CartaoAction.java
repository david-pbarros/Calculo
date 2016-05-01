package br.com.dbcorp.calculo;

import java.awt.Event;
import java.awt.event.ActionEvent;

import javax.swing.Icon;


public class CartaoAction extends Action {
	private static final long serialVersionUID = -6779320005618272922L;

	public static final String NAME_VALUE = "Cartões";

	//Tooltip
	public static final String LONG_DESCRIPTION_VALUE = "Detalhamento sobre taxas de Cartões";

	//Atalho do menu
	public static final int KEY_STROKE_MODIFIERS = Event.KEY_ACTION;

	//Ícone do Menu
	public static Icon SMALL_ICON_VALUE = null;

	//Descrição do menu
	public static final String SHORT_DESCRIPTION_VALUE = LONG_DESCRIPTION_VALUE;
	
	public CartaoAction(MainFrame mainFrame) {
		super(NAME_VALUE, mainFrame);

		putValue(LONG_DESCRIPTION, LONG_DESCRIPTION_VALUE);
		putValue(SMALL_ICON, SMALL_ICON_VALUE);
		putValue(SHORT_DESCRIPTION, SHORT_DESCRIPTION_VALUE);
	}
	
	public void actionPerformed(ActionEvent arg0) {
		CartaoUI cartaoUI = new CartaoUI();
		cartaoUI.setVisible(true);
	}
}
