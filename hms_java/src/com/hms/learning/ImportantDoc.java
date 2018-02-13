package com.hms.learning;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


public class ImportantDoc extends JPanel {
	
	
	public ImportantDoc(){
		testing();
	}
	
	public void testing() {
	
	JPanel panel = new JPanel();
	panel.add(new JLabel("Hello"));
	JButton button = new JButton("Hello");
	JButton button2 = new JButton("Button2");

	final JDialog dialog = new JDialog();
	dialog.setVisible(false);
	dialog.setSize(800,800);
	dialog.setLocation(395, 10);
	final JEditorPane pane = new JEditorPane();
	pane.setEditable(false);
	
	//JScrollPane scroll = new JScrollPane(pane); //scroll pane code added
	//scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	//scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	//dialog.add(scroll);
	
	
	button.addActionListener(new ActionListener(){
		
		public void actionPerformed(ActionEvent e) {
			
						
			if(!dialog.isVisible()) {
				dialog.setVisible(true);
			}
			URL url = null;
			try {
				File file = new File("Safari for MAC.htm");
				url = file.toURI().toURL();
				//url = new URL("http://fay.iniminimo.com/paint7.html");
			} catch (MalformedURLException e1) {
				// TODO Auto-generated catch block
				
				e1.printStackTrace();
				
			}
			finally {
				System.out.println("Fucked UP");
			}
			try {
				
				pane.setPage(url);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}		
			//dialog.add(pane);
			
		}
	
	});
button2.addActionListener(new ActionListener(){
		
		public void actionPerformed(ActionEvent e) {
			if(!dialog.isVisible()) {
				dialog.setVisible(true);
			}
			
			URL url = null;
			try {
				File file = new File("RBC Helper.htm");
				url = file.toURI().toURL();
				//url = new URL("http://www.google.ca");
			
			} catch (MalformedURLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				
				pane.setPage(url);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}		
			//dialog.add(pane);
			
		}
	
	});
	
	dialog.add(pane); //TO BE ADDED LIKE THIS RIGHT?
	JScrollPane scroll = new JScrollPane(pane); //scroll pane code added
	scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	dialog.add(scroll);
		
	panel.add(button);
	panel.add(button2);
	this.add(panel);
	
	
	
	
	}


public static void main(String[] args){
	
	JFrame frame = new JFrame();
	
	
	frame.setSize(400,400);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	ImportantDoc test = new ImportantDoc();
	
	frame.add(test);
	frame.setVisible(true);
	//frame.validate();
	

}
}
