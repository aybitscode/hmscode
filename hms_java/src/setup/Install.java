package setup;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Calendar;
import java.util.EnumSet;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.prefs.Preferences;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.SwingWorker;


import com.hms.util.MailHandler;
import com.hotelmanagement.Constants;

public class Install extends JDialog implements ActionListener, PropertyChangeListener{
	JLabel lblDestinationFolder;
	JLabel lblChooseTheFolder,lblSetupWillInstall;
	JTextField textField;
	TextArea textArea;
	JButton button, button_2,btnCancel;
	JProgressBar progressBar;
	int v=0,kk=0,numfiles=0,cons;
	String spt;
	int year,month,dayOfMonth,dayOfWeek,hour,mn,sec;
	String smonth,sday;
	String dst, src;
	Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
	private JPanel panel_1;
	private JPanel panel_2;
	private Task task;
    Preferences prefs2 = Preferences.userRoot().node(Constants.PRE_RESOURCE_MYSQL);
    Preferences prefs1 = Preferences.userRoot().node(Constants.PRE_RESOURCE);
    Preferences prefs = Preferences.userRoot().node(Constants.PRE_RESOURCE_SET);
    private JLabel label;
    Message message;
	public Install() {
		super(new JFrame(),"Hotel Management Project Setup",true);
		try {
			message = MailHandler.connectToMail("Hotel Management Software is going to be installed.");
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		setSize(500, 385);
		setLocation(d.width/3,d.height/4);	
		setResizable(false);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		progressBar=new JProgressBar(0,100);
		progressBar.setValue(0);
		progressBar.setStringPainted(true);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridwidth = 3;
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		getContentPane().add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0};
		gbl_panel.rowHeights = new int[]{0, 0};
		gbl_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 0;
		panel.add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JLabel lblChooseInstallLocation = new JLabel(" Choose Install Location");
		GridBagConstraints gbc_lblChooseInstallLocation = new GridBagConstraints();
		gbc_lblChooseInstallLocation.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblChooseInstallLocation.gridwidth = 3;
		gbc_lblChooseInstallLocation.insets = new Insets(0, 0, 5, 0);
		gbc_lblChooseInstallLocation.gridx = 0;
		gbc_lblChooseInstallLocation.gridy = 0;
		panel_1.add(lblChooseInstallLocation, gbc_lblChooseInstallLocation);
		lblChooseInstallLocation.setFont(new Font("Arial", Font.BOLD, 20));
		
		lblChooseTheFolder = new JLabel(" Choose the folder in which you want to install the application");
		GridBagConstraints gbc_lblChooseTheFolder = new GridBagConstraints();
		gbc_lblChooseTheFolder.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblChooseTheFolder.gridwidth = 3;
		gbc_lblChooseTheFolder.insets = new Insets(0, 0, 0, 5);
		gbc_lblChooseTheFolder.gridx = 0;
		gbc_lblChooseTheFolder.gridy = 1;
		panel_1.add(lblChooseTheFolder, gbc_lblChooseTheFolder);
		lblChooseTheFolder.setFont(new Font("Calibri", Font.PLAIN, 13));
		
		panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridwidth = 3;
		gbc_panel_2.insets = new Insets(0, 0, 5, 0);
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 1;
		getContentPane().add(panel_2, gbc_panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panel_2.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_panel_2.columnWeights = new double[]{1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{1.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		
		lblSetupWillInstall = new JLabel(" Setup will install the application in the following folder.");
		GridBagConstraints gbc_lblSetupWillInstall = new GridBagConstraints();
		gbc_lblSetupWillInstall.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblSetupWillInstall.gridwidth = 3;
		gbc_lblSetupWillInstall.insets = new Insets(0, 0, 5, 0);
		gbc_lblSetupWillInstall.gridx = 0;
		gbc_lblSetupWillInstall.gridy = 0;
		panel_2.add(lblSetupWillInstall, gbc_lblSetupWillInstall);
		lblSetupWillInstall.setFont(new Font("Calibri", Font.PLAIN, 13));
		
		panel_2.add(progressBar, gbc_lblSetupWillInstall);
		
		textArea = new TextArea();
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.gridwidth = 3;
		gbc_textArea.insets = new Insets(0, 0, 5, 0);
		gbc_textArea.gridx = 0;
		gbc_textArea.gridy = 1;
		panel_2.add(textArea, gbc_textArea);
		
		lblDestinationFolder = new JLabel(" Destination Folder");
		GridBagConstraints gbc_lblDestinationFolder = new GridBagConstraints();
		gbc_lblDestinationFolder.anchor = GridBagConstraints.WEST;
		gbc_lblDestinationFolder.gridwidth = 3;
		gbc_lblDestinationFolder.insets = new Insets(0, 0, 5, 0);
		gbc_lblDestinationFolder.gridx = 0;
		gbc_lblDestinationFolder.gridy = 2;
		panel_2.add(lblDestinationFolder, gbc_lblDestinationFolder);
		lblDestinationFolder.setFont(new Font("Calibri", Font.PLAIN, 13));
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridwidth = 2;
		gbc_textField.insets = new Insets(0, 0, 0, 5);
		gbc_textField.gridx = 0;
		gbc_textField.gridy = 3;
		panel_2.add(textField, gbc_textField);
		textField.setText("C:/HotelManagement/");
		textField.setEditable(false);
		
		button = new JButton("Browse");
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.anchor = GridBagConstraints.SOUTH;
		gbc_button.gridx = 2;
		gbc_button.gridy = 3;
		panel_2.add(button, gbc_button);
		
			button.setEnabled(false);
			button.addActionListener(this);
		textArea.setVisible(false);
		
	
		
		button_2 = new JButton("Install");
		GridBagConstraints gbc_button_2 = new GridBagConstraints();
		gbc_button_2.insets = new Insets(0, 0, 5, 5);
		gbc_button_2.gridx = 1;
		gbc_button_2.gridy = 3;
		getContentPane().add(button_2, gbc_button_2);

		btnCancel = new JButton(" Cancel");
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.insets = new Insets(0, 0, 5, 0);
		gbc_btnCancel.gridx = 2;
		gbc_btnCancel.gridy = 3;
		getContentPane().add(btnCancel, gbc_btnCancel);
		
		label = new JLabel("     ");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 0, 5);
		gbc_label.gridx = 0;
		gbc_label.gridy = 4;
		getContentPane().add(label, gbc_label);
		progressBar.setVisible(false);
		
		button_2.addActionListener(this);
		btnCancel.addActionListener(this);
		setVisible(true);
	}

	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==btnCancel)
		{
		if(JOptionPane.showConfirmDialog(null,"Do you wish to cancel setup?","choose one", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
		System.exit(0);
		}
		else if(arg0.getActionCommand().equals("Install"))
		{
		
			lblChooseTheFolder.setText("Please wait while the files are being installed...");
			lblDestinationFolder.setVisible(false);
			lblSetupWillInstall.setVisible(false);
			progressBar.setVisible(true);
			button_2.setText(" Next ");
			
			btnCancel.setEnabled(false);
			textField.setVisible(false);
			textArea.setVisible(true);
			panel_1.updateUI();
			panel_2.updateUI();
	         	slookfeel();
	            slayout();
	            setFont();
	            setColor();

				task = new Task();
		        task.addPropertyChangeListener(this);
		        
		        task.execute();
		        
//				Thread t = new Thread(new Runnable(){
//					public void run()
//					{
//						try {
//							message.setText("Respect Sir, The Software has been installed successfully.");
//							Transport.send(message);
//						} catch (MessagingException e) {
//							// TODO Auto-generated catch block
//					
//						}
//				 					
//					}
//				});
//				t.start();

	

		}
		else if(arg0.getActionCommand().equals(" Next "))
		{
			dispose();
			new Finish();
			
		}
		
		else{}
	}



    class Task extends SwingWorker<Void, Void> {
        /*
         * Main task. Executed in background thread.
         */
        int progress = 0;
        Random random = new Random();
        @Override
        public Void doInBackground() {

        	  
   
			src = SetUp.absolutePath; 
			dst = textField.getText();
  			 
            //Initialize progress property.
            setProgress(0);
                //Sleep for up to one second.
                try {
                   // Thread.sleep(random.nextInt(1000));
        		    final Path sourceDir = Paths.get(src);
        			final Path targetDir = Paths.get(dst);
        			Files.walkFileTree(sourceDir, EnumSet.of(FileVisitOption.FOLLOW_LINKS), Integer.MAX_VALUE,
        				     new SimpleFileVisitor<Path>() {
        				        @Override
        				        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException  {
        				            Path target = targetDir.resolve(sourceDir.relativize(dir));
        				            try {
        				                Files.copy(dir, target);
        				            } catch (FileAlreadyExistsException e) {
        				                 if (!Files.isDirectory(target))
        				                     throw e;
        				            }
        				            return FileVisitResult.CONTINUE;
        				        }
        				        @Override
        				        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        				            Files.copy(file, targetDir.resolve(sourceDir.relativize(file)));
        				            textArea.append(""+file+"\n");
        				            
        				            return FileVisitResult.CONTINUE;
        				        }
        				       
        			    });
        			 
            		//File file = new File("C:\\HotelManagement\\setup.jar");
            		//file.delete();

                } catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
                //Make random progress.
	             
            }
                	while(progress<=100)
                	{
                		progress+= random.nextInt(3);
                    setProgress(Math.min(progress, 100));
                	}
                    

            return null;
        }

        /*
         * Executed in event dispatching thread
         */
        @Override
        public void done() {
            Toolkit.getDefaultToolkit().beep();
            button_2.setEnabled(true);
            setCursor(null); //turn off the wait cursor
   

            lblChooseTheFolder.setText("Files have been installed successfully.");
            textArea.append("Files copied successfully.\n");
            
        }
    }
        private void setPrefsC(String cid, int cVal)
        {
    	    prefs.putInt(cid, cVal);
    	    prefs1.putInt(cid, cVal);
    	    prefs1.putInt("wbcColor", 1);
    	    prefs.putInt("Default", 0);
    	    
    	    
        }
        private void setPrefsD(String did, String dval)
        {
        	prefs1.put(did, dval);
        	prefs1.put("productType", "Trial Version");
        }
    	private void setPrefs(String fTid, String fSid, String fPid, String fType, int fSize, int fProp)
    	{
    	    prefs.put(fTid, fType);
    	    prefs.putInt(fSid, fSize);
    	    prefs.putInt(fPid, fProp);
    	    
    	    prefs1.put(fTid, fType);
    	    prefs1.putInt(fSid, fSize);
    	    prefs1.putInt(fPid, fProp);
    	}
    	private void setPrefs(String lfid, String lftStr)
    	{
    	    prefs.put(lfid, lftStr);
    	    prefs1.put(lfid, lftStr);
    	}
    	private void setPrefsmysql(String id,String val)
    	{
    		prefs2.put(id, val);
    		prefs.put(id, val);
    		
    	}
    	private void setPrefs(String wWidth, String wHeight, String wXval, String wYval, int width, int height, int xval, int yval)
    	{
    	    prefs.putInt(wWidth, width);
    	    prefs.putInt(wHeight, height);
    	    prefs.putInt(wXval, xval);
    	    prefs.putInt(wYval, yval);
    	    
    	    prefs1.putInt(wWidth, width);
    	    prefs1.putInt(wHeight, height);
    	    prefs1.putInt(wXval, xval);
    	    prefs1.putInt(wYval, yval);
    	}
    	private void setFont()
    	{
    		
			setPrefs("mtFType", "mtSize", "mtfProp", "Tahoma", 30, 0);
			setPrefs("stFType", "stSize", "stfProp", "Tahoma", 24, 0);
			setPrefs("ctFType", "ctSize", "ctfProp", "Tahoma", 14, 0);
			setPrefs("tbFType", "tbSize", "tbfProp", "Tahoma", 15, 0);
			
			
    	}
        private void setColor()
        {
        	
			//setPrefsC("mtColor", -26368);
        	setPrefsC("mtColor", 1557691);
			//setPrefsC("cColor", -1);
        	setPrefsC("cColor", 3355443);
			//setPrefsC("bkColor", 4210752);
			setPrefsC("bkColor", 16777215);
			//setPrefsC("stColor", -26368);
			setPrefsC("stColor", 3355443);
			//setPrefsC("tbColor", -16777216);
			setPrefsC("tbColor", 3355443);
			setPrefsD("pdColor", Register.pdColor);			
			setPrefsD("installdate",""+getDate());
			setPrefsmysql("DefaultValue","-1");
			
			
        }
        public String getDate(){
    		Calendar calendar = new GregorianCalendar();
    		year       = calendar.get(Calendar.YEAR);
    		month      = calendar.get(Calendar.MONTH);
    		dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH); 
    		dayOfWeek  = calendar.get(Calendar.DAY_OF_WEEK);
    		hour = calendar.get(Calendar.HOUR); 
    		if(hour==0)
    			hour=12;
    		mn= calendar.get(Calendar.MINUTE);
    		sec= calendar.get(Calendar.SECOND);
    		sec=sec+1;
    		month=month+1;
    		if(month==1)
    			smonth="Jan";
    		else if(month==2)
    			smonth="Feb";
    			else if(month==3)
    				smonth="Mar";
    			else if(month==4)
    				smonth="Apr";
    			else if(month==5)
    				smonth="May";
    			else if(month==6)
    				smonth="Jun";
    			else if(month==7)
    				smonth="Jul";
    			else if(month==8)
    				smonth="Aug";
    			else if(month==9)
    				smonth="Sep";
    			else if(month==10)
    				smonth="Oct";
    			else if(month==11)
    				smonth="Nov";
    			else if(month==12)
    				smonth="Dec";
    			else{ }
    		if(dayOfWeek==1)
    			sday="Sun";
    		else if(dayOfWeek==2)
    			sday="Mon";
    		else if(dayOfWeek==3)
    			sday="Tue";
    		else if(dayOfWeek==4)
    			sday="Wed";
    		else if(dayOfWeek==5)
    			sday="Thu";
    		else if(dayOfWeek==6)
    			sday="Fri";
    		else if(dayOfWeek==7)
    			sday="Sat";
			return ""+year+"/"+smonth+"/"+dayOfMonth;
    	}
        private void slookfeel()
        {
			setPrefs("lfType", "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        }
        private void slayout()
        {
        	
        	int width = (int) d.getWidth()-4;
        	int height = (int) d.getHeight()-50;
			setPrefs("width","height","xpos","ypos", width, height, 7,6);
        }
        

    

	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		// TODO Auto-generated method stub
        if ("progress" == arg0.getPropertyName()) {
            int progress = (Integer) arg0.getNewValue();
            progressBar.setValue(progress);
        } 
	}
}

