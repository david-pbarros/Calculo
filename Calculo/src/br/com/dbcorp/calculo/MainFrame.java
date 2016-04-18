package br.com.dbcorp.calculo;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = 5989744186177240809L;
	
	
	public MainFrame(int width, int height) {
		JDesktopPane desktop = new JDesktopPane();
		desktop.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
		
		getContentPane().add(desktop);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(width, height);
		setLocationRelativeTo(null);
		setTitle("Calculo");
		//setIconImage(Params.iconeAplicacao());
		setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
		
		InternalUI inicial = new InicialUI();
		inicial.show();
		inicial.grabFocus();
		((javax.swing.plaf.basic.BasicInternalFrameUI) inicial.getUI()).setNorthPane(null);
		
		desktop.add(inicial);
		
		try {
			inicial.setMaximum(true);
			inicial.setSelected(true);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
