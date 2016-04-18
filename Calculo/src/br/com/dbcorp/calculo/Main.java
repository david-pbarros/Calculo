package br.com.dbcorp.calculo;

import java.awt.EventQueue;

public class Main {

	public static int width = 1024;
	public static int height = 768;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				MainFrame main = new MainFrame(width, height);
				main.setVisible(true);
			}
		});
	}
}
