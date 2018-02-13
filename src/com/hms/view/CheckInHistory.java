package com.hms.view; 
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;

import net.miginfocom.swing.MigLayout;

import com.hms.controller.CheckInController;
import com.hms.util.Constants;
import com.hms.util.DatabaseConstants;
import com.hms.util.ExcelExporter;
import com.hms.util.SearchBoxModel;
import com.hms.viewhandler.ViewHandler;
import com.hotelmanagement.MainPage;
import com.hotelmanagement.SFont;
import com.hotelmanagement.SetColor;
import com.hotelmanagement.WelcomeEntry;

public class CheckInHistory extends JPanel {
	private JTable table;
	DefaultTableModel tableModel; 
	String filePath;
	private CheckInController objCheckInController;
	JComboBox comboSearch;
	public static SearchBoxModel sbm_customer_mobile;
	public static JLabel lblRows;
	JScrollPane scrollPane;

	/**
	 * Create the panel.
	 */
	Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
	private JLabel lblHistory;
	private JLabel lblBack;
	MainPage mpg;
	private JLabel transExcel;
	public CheckInHistory(final MainPage mpg) {
		this.mpg = mpg;
		//setSize(d.width-100,d.height-150);
		setLayout(new MigLayout("", "[][10][][grow][][][]", "[][grow]"));
		

		
		table = new JTable();
		tableModel = new DefaultTableModel(Constants.checkInEntryNames, 0);
		table = new JTable(tableModel)
		{
			public boolean isCellEditable(int row, int column){  
				if(table.getRowCount()>1)
				{
					return false;

				}
				else
				{
					   return true;  
				}
				}
		}; 
		
		lblBack = new JLabel("");
		lblBack.setIcon(new ImageIcon("C:\\HotelManagement\\boot\\images\\back.png"));
		add(lblBack, "cell 0 0");
		lblBack.addMouseListener(new MouseAdapter(){
			public void mouseEntered(MouseEvent e)
			{
				lblBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			public void mouseReleased(MouseEvent e)
			{
				
				ReportContainer obj = new ReportContainer(mpg);
				ViewHandler.updateDashBoard(obj, WelcomeEntry.dashBoardContainer, WelcomeEntry.gbc_bodyPanel, WelcomeEntry.sliderMenu, WelcomeEntry.gbc_sliderMenu);
			}
		});
		
		
		lblRows = new JLabel("Rows");
		add(lblRows, "cell 2 0,alignx trailing");
		
		lblHistory = new JLabel("Checkin Report");
		add(lblHistory, "cell 3 0,alignx center");
		lblHistory.setFont(new Font("Open Sans", Font.PLAIN, 22));
		lblHistory.setForeground(new Color(50, 197, 210));
		
		comboSearch = new JComboBox();
		add(comboSearch, "cell 4 0,alignx right");
		 
		objCheckInController = new CheckInController(tableModel, table);
		objCheckInController.retrieveAll(DatabaseConstants.TABLE_CHECK_IN_COLS);
		
		transExcel = new JLabel("");
		add(transExcel, "cell 5 0, alignx right,aligny center");
		transExcel.setIcon(new ImageIcon(MainPage.class.getResource("/images/excel.png")));
		transExcel.addMouseListener(new MouseAdapter(){
			public void mouseReleased(MouseEvent e)
			{
	
			
                try {
                	JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            		jfc.setDialogTitle("Choose a directory to save your file: ");
            		//jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            		jfc.setAcceptAllFileFilterUsed(false);
            		FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel File", "xls");
            		jfc.addChoosableFileFilter(filter);
            		
            		int returnValue = jfc.showSaveDialog(null);
            		if (returnValue == JFileChooser.APPROVE_OPTION) {
            			 File file = jfc.getSelectedFile();
            		      if (file == null) {
            		        return;
            		      }
            		      if (!file.getName().toLowerCase().endsWith(".xls")) {
            		        file = new File(file.getParentFile(), file.getName() + ".xls");
            		      }
            		      filePath=file.getAbsolutePath();
                          ExcelExporter.fillData(table, filePath,"Check-In Details");
                          JOptionPane.showMessageDialog(null, "Data saved at "+filePath+" successfully", "Message",JOptionPane.INFORMATION_MESSAGE);
                          Desktop.getDesktop().open(new File(filePath));
            		}
//            		else
//            		{
//            			System.out.println("from cancel bro pls solve");
//            		}
       			//FileDialog fd=new FileDialog(new JFrame(),"Save",FileDialog.SAVE);
     			//fd.setVisible(true);
     			
//                
//     			if(!filePath.equals("nullnull"))
//     			{
//  
//     			}
     			
     			
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
			}
			public void mouseEntered(MouseEvent e)
			{
				transExcel.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		}		
		);
		

		scrollPane = new JScrollPane(table);
		add(scrollPane, "cell 0 1 6 1,grow");
		
		table.setForeground(new Color(SetColor.cColor));
		setBackground(new Color(SetColor.bkColor));
		table.setBackground(new Color(SetColor.bkColor));
		table.setFont(new Font(SFont.ctFType,SFont.ctfProp, SFont.ctSize));
		scrollPane.getViewport().setBackground(new Color(SetColor.bkColor));
 
		
		comboSearch.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {

		    @Override
		    public void keyReleased(KeyEvent event) {
		        if (event.getKeyChar() == KeyEvent.VK_ENTER) {
		            if (((JTextComponent) ((JComboBox) ((Component) event
		                    .getSource()).getParent()).getEditor()
		                    .getEditorComponent()).getText().isEmpty())
		            {
		            	objCheckInController.retrieveAll(DatabaseConstants.TABLE_CHECK_IN_COLS);
		            }
		            else
		            {		            	
		            	objCheckInController.retrieve(DatabaseConstants.ALL_CHECKIN_ID, ""+comboSearch.getSelectedItem());
		            }
		            	
		        }
		    }
		});
		comboSearch.setMaximumRowCount(10);
		comboSearch.setEditable(true);
		sbm_customer_mobile = new SearchBoxModel(comboSearch, DatabaseConstants.SELECT_CHECKIN_BOOKING_ID);
		comboSearch.setModel(sbm_customer_mobile);
		comboSearch.addItemListener(sbm_customer_mobile);
		comboSearch.addPopupMenuListener(sbm_customer_mobile);
		
		 
		
		//setVisible(true);

	}

}
