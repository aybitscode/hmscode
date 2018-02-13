package setup;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.swing.JFrame;

public class Readme extends JFrame {

	URI fn;
	String absolutePath;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Readme frame = new Readme();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Readme() {
		
		 absolutePath = getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
 		  absolutePath = absolutePath.substring(0, absolutePath.lastIndexOf("/"));
 			URL turl = this.getClass().getResource("/images/Readme.txt");
 			try {
 				fn=turl.toURI();
 			} catch (URISyntaxException e1) {
 				// TODO Auto-generated catch block
 				e1.printStackTrace();
 			}

		//fn=absolutePath+"/Readme.txt";
		setSize(800,500);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		TextArea textArea = new TextArea();
		textArea.setFont(new Font("Tahoma", Font.PLAIN, 15));
		getContentPane().add(textArea, BorderLayout.CENTER);
		try
		{
		File f=new File(fn);
		FileInputStream fs=new FileInputStream(f);
		byte b[]=new byte[fs.available()];
		fs.read(b);
		textArea.setText(new String(b));
		fs.close();
		}catch(Exception ea){System.out.println(ea);}
	setTitle("Readme");
	addWindowListener(new WindowAdapter()
{
	
	public void windowClosing(WindowEvent we)
	
		{
		
			System.exit(0);
	
		}

		});


	
	setVisible(true);
		
	
	}

}
