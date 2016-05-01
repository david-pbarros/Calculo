package br.com.dbcorp.calculo;

import java.awt.Cursor;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

public abstract class Action extends AbstractAction {
	private static final long serialVersionUID = 7045015726193193251L;
	
	@SuppressWarnings("rawtypes")
	Class internalFrameClass;
	MainFrame mainFrame;
	InternalUI internalFrame;
	
	public Action(String name, MainFrame mainFrame) {
		super(name);
		this.mainFrame = mainFrame;
	}
	
	public void actionPerformed(ActionEvent arg0) {
		this.mainFrame.setCursor(new Cursor(Cursor.WAIT_CURSOR));
		
		try {
			
			this.internalFrame = (InternalUI) this.internalFrameClass.newInstance();
			this.internalFrame.show();
			this.internalFrame.grabFocus();
			this.internalFrame.setMaximum(true);
			((javax.swing.plaf.basic.BasicInternalFrameUI) this.internalFrame.getUI()).setNorthPane(null);
			
		}catch (Exception exception) {
			exception.printStackTrace();
		}
		
		this.mainFrame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}
}
