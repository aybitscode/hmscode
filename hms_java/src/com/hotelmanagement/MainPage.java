package com.hotelmanagement;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;
import java.util.prefs.Preferences;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.hms.cache.HMSCache;
import com.hms.controller.HotelController;
import com.hms.util.DBConnection;
import com.hms.util.DatabaseConstants;
import com.hms.util.DateDifferenceCalculator;
import com.hms.util.GetFileCreationDateExample;
import com.hms.util.StringToUtilDate;
import com.hms.view.BookingCancel;
import com.hms.view.BookingCheckout;
import com.hms.view.BookingContainer;
import com.hms.view.BookingTransactions;
import com.hms.view.CheckInEntry;
import com.hms.view.CustomerEntry;
import com.hms.view.DWelcomeEntry;
import com.hms.view.ExpensesEntry;
import com.hms.view.LoginEntry;
import com.hms.view.NotificationEntry;
import com.hms.viewhandler.ViewHandler;
public class MainPage extends JFrame implements ChangeListener,MouseListener,KeyListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static JPanel containerPanel;
	public static JPanel footerPanel;
	public static JPanel panel_4;
	Dimension d;
	private static JTabbedPane tabbedPane;
	int stt=1,sum=0,pd=0,count,iday,imon,iyear,ihr,imin,isec,eday;
	static int status=0;
	File src,dst;
	ResultSet rs;
	Statement stmt;
	public static JLabel lblhotelName;
	public static JLabel labelUser;
	public static String userID;
	static Settings st;
	public static int scrwidth ,scrheight; 
	int scrlx,scrly;
	public static String user_role = null;
	public static String user_mode = null;
	static JPanel headerPanel;
	BookingContainer bgui;
	BookingTransactions tns;
	BookingCancel booking_cancel;
	Search srch;
	UpdatePanel upl;
	DataBas dbs;
	Trash tsh;
	static DWelcomeEntry welcome;
	RFPanel expiry;
	About abt;
	CustomerEntry customers;
	CheckInEntry checkInEntry;
	BookingCheckout booking_checkout;	
	private JPopupMenu popupMenu;
	private static JPanel panel_2;
	private JLabel label_1;
	JLabel label_2;
	private JLabel label_3;
	private static JLabel lblFriMay;
	public static JLabel lblLbltime;
	int year,month,dayOfMonth,dayOfWeek,hour,mn,sec;
	java.sql.Date sqldate;
	String smonth,sday,tdate;
	private static JLabel lblVersion;
	Preferences prefs;
	public JLabel label_4;
	public GridBagConstraints gbc_label_4,gbc_lblNewLabel;
	Connection con;
	//JFrame f = new JFrame("Frame");
	HotelController hotel_controller;
	List<Image> icons = new ArrayList<Image>();
	public static JLabel lblHome;
	public static JPanel bodyPanel;
	public static JPanel notificationPanel;
	
	
	LoginEntry objLogin;
	private JLabel lblEnterpriseName;
	public static GridBagConstraints gbc_dashboard;
	public static GridBagLayout gbl_bodyPanel;
	public MainPage() {
		//SetColor.mtColor=Color.blue;
		welcome  = new DWelcomeEntry(this);// there is some pblm during expiry remember and solve
		con = DBConnection.getDBConnection();
		if(con == null)
			JOptionPane.showMessageDialog(this, "Server not started please start thes server", "Failure", JOptionPane.ERROR_MESSAGE);
		d=Toolkit.getDefaultToolkit().getScreenSize();		
		prefs = Preferences.userRoot().node(Constants.PRE_RESOURCE_SET);
		String tempdate=prefs.get("installdate", "");

		getDate();
		eday=prefs.getInt("wbcColor", 32);
		if(eday==32)
		{
			prefs.putInt("wbcColor", 31);
		}
		
		if(!tempdate.equals(tdate))
		{
			prefs.put("installdate", tdate);
			eday=eday+1;
			prefs.putInt("wbcColor", eday);
		}
		defaultFontProperties(prefs);
		defaultColors(prefs);
		defaultLFeel(prefs);
		defaultLayoutProperties(prefs);
		hotel_controller = new HotelController();
		tabbedPane= new JTabbedPane(JTabbedPane.TOP);
		try {
			
		UIManager.setLookAndFeel(SLookFeel.lfType);
		} catch (ClassNotFoundException  | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e1) {
			JOptionPane.showMessageDialog(this, "This software cannot be used with out the permission of vendor.\n For fresh copy contact us","Message",JOptionPane.INFORMATION_MESSAGE);
			e1.printStackTrace();
		
			DBConnection.closeDBConnection();
			System.exit(0);
			
		}
	
		setLocation(scrlx,scrly);
		setSize(scrwidth,scrheight);
		setUndecorated(true);
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		src=new File("D:/database");
		dst=new File("C:/Users/database");
		containerPanel = new JPanel();
		containerPanel.setForeground(new Color(0, 0, 0));
		containerPanel.setBackground(Color.BLACK);
		
		//contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(containerPanel);
		//containerPanel.setLayout(new BorderLayout(0, 0));
		GridBagLayout gbc_container = new GridBagLayout();
		gbc_container.columnWidths = new int[]{0, 0};
		gbc_container.rowHeights = new int[]{0, 0, 0, 0};
		gbc_container.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbc_container.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		containerPanel.setLayout(gbc_container);
		
		headerPanel = new JPanel();
		GridBagConstraints gbc_header = new GridBagConstraints();
		gbc_header.insets = new Insets(0, 0, 0, 0);
		gbc_header.fill = GridBagConstraints.BOTH;
		gbc_header.gridx = 0;
		gbc_header.gridy = 0;
		containerPanel.add(headerPanel, gbc_header);
		headerPanel.setBackground(Color.BLACK);

		GridBagLayout gbl_headerPanel = new GridBagLayout();
		gbl_headerPanel.columnWidths = new int[]{105, 0, 160, 200, 0};
		gbl_headerPanel.rowHeights = new int[]{35, 40, 0};
		gbl_headerPanel.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_headerPanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		headerPanel.setLayout(gbl_headerPanel);
		

		
		
		bodyPanel = new JPanel();
		GridBagConstraints gbc_body = new GridBagConstraints();
		gbc_body.insets = new Insets(0, 0, 0, 0);
		gbc_body.fill = GridBagConstraints.BOTH;
		gbc_body.gridx = 0;
		gbc_body.gridy = 1;
		getContentPane().add(bodyPanel, gbc_body);
		gbl_bodyPanel = new GridBagLayout();
		gbl_bodyPanel.columnWidths = new int[]{0, 0, 0, 0};
		gbl_bodyPanel.rowHeights = new int[]{0, 0, 0, 0};
		gbl_bodyPanel.columnWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_bodyPanel.rowWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
		bodyPanel.setLayout(gbl_bodyPanel);
		bodyPanel.setBackground(new Color(54, 65, 80));
		


		
		lblEnterpriseName = new JLabel("M.V. BOUTIQUE");
		GridBagConstraints gbc_lblEnterpriseName = new GridBagConstraints();
		gbc_lblEnterpriseName.insets = new Insets(0, 0, 5, 5);
		gbc_lblEnterpriseName.gridx = 1;
		gbc_lblEnterpriseName.gridy = 0;
		bodyPanel.add(lblEnterpriseName, gbc_lblEnterpriseName);
		lblEnterpriseName.setForeground(Color.white);
		lblEnterpriseName.setFont(new Font("Open Sans", Font.PLAIN, 28));
		objLogin = new LoginEntry(this);
		
		gbc_dashboard = new GridBagConstraints();
		gbc_dashboard.insets = new Insets(0, 0, 5, 5);
		gbc_dashboard.fill = GridBagConstraints.BOTH;
		gbc_dashboard.gridx = 1;
		gbc_dashboard.gridy = 1;
		//bodyPanel.add(welcome, gbc_dashboard);
		bodyPanel.add(objLogin, gbc_dashboard);
		
		
		
		footerPanel = new JPanel();
		footerPanel.setBackground(Color.BLACK);
		GridBagConstraints gbc_footer = new GridBagConstraints();
		gbc_footer.fill = GridBagConstraints.BOTH;
		gbc_footer.gridx = 0;
		gbc_footer.gridy = 2;
		containerPanel.add(footerPanel, gbc_footer);
		
		GridBagLayout gbl_footerPanel = new GridBagLayout();
		gbl_footerPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 65, 0};
		gbl_footerPanel.rowHeights = new int[]{0, 0, 0, 0};
		gbl_footerPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_footerPanel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		footerPanel.setLayout(gbl_footerPanel);
		
		
		lblhotelName = new JLabel();
		lblhotelName.setForeground(Color.ORANGE);
		lblhotelName.setFont(new Font("Arial", Font.PLAIN, 24));
		MainFrame m = new MainFrame(this);
		
		lblHome = new JLabel("");
		lblHome.setIcon(new ImageIcon("C:\\HotelManagement\\boot\\images\\home.png"));
		GridBagConstraints gbc_lblHome = new GridBagConstraints();
		gbc_lblHome.fill = GridBagConstraints.BOTH;
		gbc_lblHome.gridheight = 2;
		gbc_lblHome.insets = new Insets(0, 0, 0, 5);
		gbc_lblHome.gridx = 0;
		gbc_lblHome.gridy = 0;
		headerPanel.add(lblHome, gbc_lblHome);
		lblHome.addMouseListener(this);
		lblHome.setOpaque(true);
		lblHome.setBackground(new Color(23,196, 187));
		lblHome.setForeground(Color.white);
		lblHome.setVisible(false);
		lblHome.setHorizontalAlignment(JLabel.CENTER);
		lblHome.setVerticalAlignment(JLabel.CENTER);
		GridBagConstraints gbc_lblhotelName = new GridBagConstraints();
		gbc_lblhotelName.insets = new Insets(0, 0, 5, 5);
		gbc_lblhotelName.gridx = 1;
		gbc_lblhotelName.gridy = 0;
		headerPanel.add(lblhotelName, gbc_lblhotelName);
		String hotelName = hotel_controller.retrieveHotelName(DatabaseConstants.HOTEL_NAME);
		if(hotelName==null)
			lblhotelName.setText("                            ");
		else
			lblhotelName.setText("                            "+hotelName);

		lblhotelName.setFont(new Font(SFont.mtFType,SFont.mtfProp,SFont.mtSize));
		
		panel_2 = new JPanel();
		panel_2.setBackground(Color.BLACK);
		panel_2.setLayout(null);
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.insets = new Insets(0, 0, 5, 0);
		gbc_panel_2.gridx = 3;
		gbc_panel_2.gridy = 0;
		headerPanel.add(panel_2, gbc_panel_2);
		
		label_4 = new JLabel("");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setIcon(new ImageIcon(MainPage.class.getResource("/images/calc.png")));
		label_4.setBounds(80, -1, 30, 26);
		panel_2.add(label_4);
		
		
		label_2 = new JLabel("");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setForeground(Color.BLACK);
		label_2.setIcon(new ImageIcon(MainPage.class.getResource("/images/settings.png")));
		label_2.setBounds(110, 0, 30, 26);
		panel_2.add(label_2);
		
		label_1 = new JLabel("");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setForeground(Color.BLACK);
		label_1.setIcon(new ImageIcon(MainPage.class.getResource("/images/minus.png")));
		label_1.setBounds(140, 0, 30, 26);
		panel_2.add(label_1);
		
		label_3 = new JLabel("");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setForeground(Color.BLACK);
		label_3.setIcon(new ImageIcon(MainPage.class.getResource("/images/close.png")));
		label_3.setBounds(170, 0,30, 26);
		panel_2.add(label_3);
		
		notificationPanel = new JPanel();
		GridBagConstraints gbc_notificationPanel = new GridBagConstraints();
		gbc_notificationPanel.insets = new Insets(0, 0, 0, 0);
		gbc_notificationPanel.fill = GridBagConstraints.BOTH;
		gbc_notificationPanel.gridx = 2;
		gbc_notificationPanel.gridy = 1;
		//headerPanel.add(notificationPanel, gbc_notificationPanel);
		notificationPanel.setBackground(Color.white);
		GridBagLayout gbl_notificationPanel = new GridBagLayout();
		gbl_notificationPanel.columnWidths = new int[]{0, 0};
		gbl_notificationPanel.rowHeights = new int[]{0, 0};
		gbl_notificationPanel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_notificationPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		//notificationPanel.setLayout(gbl_notificationPanel);
		notificationPanel.setLayout(null);
		
		NotificationEntry obj_notf = new NotificationEntry(this);
		GridBagConstraints gbc_notf = new GridBagConstraints();
		gbc_notf.anchor = GridBagConstraints.EAST;
		gbc_notf.fill = GridBagConstraints.VERTICAL;
		gbc_notf.gridx = 0;
		gbc_notf.gridy = 0;
		//notificationPanel.add(obj_notf, gbc_notf);
		obj_notf.setBounds(0, 0, 700, 100);
		notificationPanel.add(obj_notf);
		notificationPanel.setVisible(false);
		
		
		
		labelUser = new JLabel("          ");
		labelUser.setFont(new Font("Tahoma", Font.PLAIN, 15));
		labelUser.setForeground(Color.WHITE);
		GridBagConstraints gbc_label1 = new GridBagConstraints();
		gbc_label1.anchor = GridBagConstraints.EAST;
		gbc_label1.gridx = 3;
		gbc_label1.gridy = 1;
		headerPanel.add(labelUser, gbc_label1);
		
		
		

		
		gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.fill = GridBagConstraints.VERTICAL;
		gbc_lblNewLabel.insets = new Insets(0, 0, 0, 0);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 2;

		gbc_label_4 = new GridBagConstraints();
		gbc_label_4.fill = GridBagConstraints.VERTICAL;
		gbc_label_4.anchor = GridBagConstraints.WEST;
		gbc_label_4.insets = new Insets(0, 0, 0, 0);
		gbc_label_4.gridx = 2;
		gbc_label_4.gridy = 2;

		
		
		GridBagConstraints gbc_lblFri = new GridBagConstraints();
		gbc_lblFri.anchor = GridBagConstraints.EAST;
		gbc_lblFri.insets = new Insets(0, 0, 0, 5);
		gbc_lblFri.gridx = 5;
		gbc_lblFri.gridy = 2;
		lblFriMay = new JLabel("DD/MM/YYYY");
		lblFriMay.setBounds(10,0,175,30);
		footerPanel.add(lblFriMay, gbc_lblFri);

		panel_4 = new JPanel();
		panel_4.setLayout(new BorderLayout());
		GridBagConstraints gbc_panel_4 = new GridBagConstraints();
		gbc_panel_4.fill = GridBagConstraints.BOTH;
		gbc_panel_4.gridx = 6;
		gbc_panel_4.gridy = 2;
		footerPanel.add(panel_4, gbc_panel_4);

		lblLbltime = new JLabel("00.00.00");
		//lblLbltime.setBounds(10,-8,80,20);
		panel_4.add(lblLbltime);
		swingTimer();


		//contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		//containerPanel.add(welcome, BorderLayout.CENTER);
		//expiry =new Expiry();
	
		try{
			FileReader fr=new FileReader("C:/FeeManagement/oraexe/app/harness/docs/docs/swing-layout-1.0.4-src/swing-layout-1.0.4/Installation.txt");
			
			int ch;
					while ((ch=fr.read()) != -1)
					{
						sum=sum+ch;
					}
					if(sum>50)
					pd=1;
					fr.close();
			}catch(Exception e){}


//		tabbedPane.addTab("   Dashboard   ", null, welcome, null);
//		tabbedPane.setFont(new Font("Tahoma", Font.PLAIN,14));
//		tabbedPane.addTab("  First Desk   ", null);
//		tabbedPane.addTab("  Room Booking   ", null);
//		tabbedPane.addTab("  Check In   ", null);
//		tabbedPane.addTab("  Check Out   ", null);
//		tabbedPane.addTab("Cancel Booking", null);
//		tabbedPane.addTab("Booking History ", null);
//		tabbedPane.addTab("Expenses ", null);

//		tabbedPane.setMnemonicAt(0, KeyEvent.VK_W);
//		tabbedPane.setMnemonicAt(1, KeyEvent.VK_Y);
//		tabbedPane.setMnemonicAt(2, KeyEvent.VK_T);
//		tabbedPane.setMnemonicAt(3, KeyEvent.VK_S);		
//		tabbedPane.setMnemonicAt(4, KeyEvent.VK_I);
//		tabbedPane.setMnemonicAt(5, KeyEvent.VK_D);


		JPanel panel_5 = new JPanel();
		panel_5.setBackground(new Color(SetColor.bkColor));
		
		tabbedPane.addChangeListener(this);

		lblVersion = new JLabel();
		GridBagConstraints gbc_lblVersion = new GridBagConstraints();
		gbc_lblVersion.fill = GridBagConstraints.VERTICAL;
		gbc_lblVersion.anchor = GridBagConstraints.WEST;
		gbc_lblVersion.insets = new Insets(0, 0, 0, 5);
		gbc_lblVersion.gridx = 0;
		gbc_lblVersion.gridy = 2;
		footerPanel.add(lblVersion, gbc_lblVersion);

//		try {
//			updateData();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		lblVersion.setText(prefs.get("productType", ""));	

		mtColor();
		cColor();
		tbColor();
		bkColor();
		mtFont(SFont.mtFType,SFont.mtfProp,SFont.mtSize);
		ctFont(SFont.ctFType,SFont.ctfProp,SFont.ctSize);
		tabbedPane.setFont(new Font(SFont.tbFType,SFont.tbfProp,SFont.tbSize));

		labelUser.addMouseListener(this);
		label_1.addMouseListener(this);
		label_2.addMouseListener(this);
		label_3.addMouseListener(this);
		label_4.addMouseListener(this);
		tabbedPane.addKeyListener(this);

		icons.add(Toolkit.getDefaultToolkit().getImage("C:/HotelManagement/boot/images/hms_small.gif"));
		icons.add(Toolkit.getDefaultToolkit().getImage("C:/HotelManagement/boot/images/hms_big.gif"));
		setIconImages(icons);
		
		setVisible(true);
		m.setVisible(); 
		
	}
 
	public class panel extends JPanel
	{
		public panel()
		{
			
		}
	}
	public void defaultLayoutProperties(Preferences prefs) {
		scrwidth=prefs.getInt("width", 0);
		scrheight=prefs.getInt("height", 0);
		scrlx=prefs.getInt("xpos", 0);
		scrly=prefs.getInt("ypos", 0);
		//System.out.println("From default Layout");
	}
	public static void defaultLFeel(Preferences prefs) {
		try
		{

		SLookFeel.lfType=prefs.get("lfType", "");
		//System.out.println(""+SLookFeel.lfType);
		}catch(Exception ee){System.out.println(ee);}
	}
	public static void defaultColors(Preferences prefs) {
		try
		{
		SetColor.cColor=prefs.getInt("cColor", 0);
		SetColor.bkColor=prefs.getInt("bkColor", 0);
		SetColor.mtColor=prefs.getInt("mtColor", 0);
		SetColor.tbColor=prefs.getInt("tbColor", 0);
		//System.out.println(""+SetColor.tbColor);
		}catch(Exception ee){System.out.println(ee);}
	}
	public static void defaultFontProperties(Preferences prefs) {
		SFont.mtFType=prefs.get("mtFType", "");
		SFont.mtfProp=prefs.getInt("mtfProp", 1);
		SFont.mtSize=prefs.getInt("mtSize", 1);
		
		SFont.stFType=prefs.get("stFType", "");
		SFont.stfProp=prefs.getInt("stfProp",0);
		SFont.stSize=prefs.getInt("stSize",0);
		
		SFont.ctFType=prefs.get("ctFType", "");
		SFont.ctfProp=prefs.getInt("ctfProp",0);
		SFont.ctSize=prefs.getInt("ctSize",0);
		
		SFont.tbFType=prefs.get("tbFType", "");
		SFont.tbfProp=prefs.getInt("tbfProp",0);
		SFont.tbSize=prefs.getInt("tbSize",0);
	}
	public static void copyDatabase(File src,File dst)
	{

		if (src.isDirectory())
		{

			if (!dst.exists())
			{
				dst.mkdir();
			}

			String files[] = src.list();
	
			for(int i = 0; i < files.length; i++)
			{
				copyDatabase(new File(src, files[i]), new File(dst, files[i]));
			
			}
			
		}
		else
		{
			if(!src.exists())
			{
				JOptionPane.showMessageDialog(null,"File of Directory doesn't exist","!!Error",JOptionPane.ERROR_MESSAGE);
				DBConnection.closeDBConnection();
				System.exit(0);
			}
			else
			{
		try{
				Thread.sleep(100);
				InputStream in = new FileInputStream(src);
		        OutputStream out = new FileOutputStream(dst);
				// Transfer bytes from in to out
		        byte[] buf = new byte[1024];
				int len;
		        while ((len = in.read(buf)) > 0) {
					out.write(buf, 0, len);
				}
				in.close();
		        out.close();
		  }catch(Exception eae){}
		}
	}

		}



	public static void actScreen()
	{
		
		tabbedPane.setComponentAt(0, welcome);
		tabbedPane.setEnabled(true);
		
	}
	public void setVisible()
	{
		java.util.Date currentDate = new java.util.Date();
		System.out.println("Current Date is"+currentDate);
		int days = (int) DateDifferenceCalculator.calculateDays(StringToUtilDate.getDate_MMDDYYYY(GetFileCreationDateExample.getFileCD()), currentDate);
		if(days > 0)
		{
		
			 Preferences prefs = Preferences.userRoot().node(Constants.PRE_RESOURCE);
			 System.out.println("Prefs"+prefs.get("installdate", ""));
			 System.out.println("Convert prefs"+StringToUtilDate.getDate_YYYYMMDD(prefs.get("installdate", "")));
			 System.out.println("File Date"+StringToUtilDate.getDate_MMDDYYYY(GetFileCreationDateExample.getFileCD()));
			if(!StringToUtilDate.getDate_YYYYMMDD(prefs.get("installdate", "")).equals(StringToUtilDate.getDate_MMDDYYYY(GetFileCreationDateExample.getFileCD())))
			{
				System.out.println("diff days are from more inside"+days);
				MainPage.tabbedPane.setEnabled(false);
				MainPage.tabbedPane.setComponentAt(0, new RFPanel("Trial Version Has Been Expired"));
				RFPanel.setExVisible(true);				
				MainPage.tabbedPane.updateUI();
				System.out.println("from inside welcome");
			}
			else
			{
//				Thread t=new Thread(){
//					public void run()
//					{
//						new Login(this);
//					}
//					};
//					t.start();
				new Login(this);
			}		
			
		}
		else
		{
//			Thread t=new Thread(){
//				public void run()
//				{
//					new Login(this);
//				}
//				};
//				t.start();	
			new Login(this);
		
		}

	}
	public void getDate(){
		Calendar calendar = new GregorianCalendar();
		sqldate= new java.sql.Date(calendar.getTimeInMillis());
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
		tdate=""+year+"/"+smonth+"/"+dayOfMonth;
	}
//	public void updateData() throws Exception
//	{
//		getDate();
//		try{
//		PreparedStatement pst= con.prepareStatement("insert into expiry(edate) "+ "values(?)");
//		pst.setDate(1,sqldate);
//		int s=pst.executeUpdate();
//		if(s>0)
//		{
//		}
//		else
//		{
//		JOptionPane.showMessageDialog(this,"Check For Solution","Failure",JOptionPane.ERROR_MESSAGE);
//		}
//	
//		}catch(Exception ee){System.out.println(ee);}
//		finally
//		{
//			//con.close();
//		}
//
//	}
//	public void updatePref()
//	{
//	    Preferences prefs = Preferences.userRoot().node(Constants.PRE_RESOURCE);
//	    prefs.put("ProductId", "1349");
//	}
	public int genrandom()
	{

		int no;
		Random r=new Random();
		no=r.nextInt(10000);
		return no;
	}
	public void swingTimer()
	{
		javax.swing.Timer t=new javax.swing.Timer(1000,new ActionListener(){
		public void actionPerformed(ActionEvent e)
		{
		
			getDate();
			lblFriMay.setText(sday+", "+dayOfMonth+" "+smonth+", "+year+"   ");
			if(mn<10&&sec<10)
			lblLbltime.setText(""+hour+":"+"0"+mn+":"+"0"+sec+"  ");
			else if(mn<10)
				lblLbltime.setText(""+hour+":"+"0"+mn+":"+sec+"  ");
			else if(sec<10)
			lblLbltime.setText(""+hour+":"+mn+":"+"0"+sec+"  ");
			else
				lblLbltime.setText(""+hour+":"+mn+":"+sec+"  ");	
			if(hour==8&&mn==30&&sec==10)
			{
			
				//copyDatabase(src,dst);
				try{
					byte[] data = BackUp.getData("localhost", "3307","ecstasy", "ecstasy", "college").getBytes();		
					File filedst = new File("D:/backup/college.sql");
					FileOutputStream dest = new FileOutputStream(filedst);
					dest.write(data);
						}catch(Exception ee){}
					
			}

		}
		});
		t.start();
		
	}
	@Override
	public void stateChanged(ChangeEvent arg0) {
		if(arg0.getSource()==tabbedPane)
		{
		if(tabbedPane.getSelectedIndex()==0)
		{
			label_2.setEnabled(true);
			label_2.addMouseListener(this);
			tabbedPane.setComponentAt(0, welcome=new DWelcomeEntry(this));
		}
		else if(tabbedPane.getSelectedIndex()==1)
		{
			label_2.setEnabled(false);
			label_2.removeMouseListener(this);
			tabbedPane.setComponentAt(1, customers = new CustomerEntry(this));
		}		
		else if(tabbedPane.getSelectedIndex()==2)
		{
			label_2.setEnabled(false);
			label_2.removeMouseListener(this);
			tabbedPane.setComponentAt(2, bgui = new BookingContainer(this));
		}
		else if(tabbedPane.getSelectedIndex()==3)
		{
			label_2.setEnabled(false);
			label_2.removeMouseListener(this);
			tabbedPane.setComponentAt(3, checkInEntry = new CheckInEntry(this));
		}
		else if(tabbedPane.getSelectedIndex()==4)
		{
			label_2.setEnabled(false);
			label_2.removeMouseListener(this);
			tabbedPane.setComponentAt(4, booking_checkout = new BookingCheckout(this));
		}
		else if(tabbedPane.getSelectedIndex()==5)
		{
			label_2.setEnabled(false);
			label_2.removeMouseListener(this);
			tabbedPane.setComponentAt(5, booking_cancel=new BookingCancel(this));
		}
		else if(tabbedPane.getSelectedIndex()==6)
		{
			label_2.setEnabled(false);
			label_2.removeMouseListener(this);
			tabbedPane.setComponentAt(6, tns = new BookingTransactions(this));
		}
		else if(tabbedPane.getSelectedIndex()==8)
		{
			if(MainPage.user_role.equalsIgnoreCase("ADMIN"))
			{
			label_2.setEnabled(false);
			label_2.removeMouseListener(this);
			tabbedPane.setComponentAt(8, upl=new UpdatePanel(this));
			}
			else
				JOptionPane.showMessageDialog(this, "No privileges, login as admin","Message",JOptionPane.INFORMATION_MESSAGE);
		}
		else if(tabbedPane.getSelectedIndex()==7)
		{
			label_2.setEnabled(false);
			label_2.removeMouseListener(this);
			tabbedPane.setComponentAt(7, new ExpensesEntry());
		}
 
		else{}
		}
		
	}
	
	public static void main(String[] args) {

		try {
			

	        SwingUtilities.invokeLater(new Runnable() {
	            @Override
	            public void run() {
	            	HMSCache.loadApplicationCache();
	    		new MainPage();
	    		//Added comment to test git
	            	if(status==1)
	            	{
	            		JOptionPane.showMessageDialog(null, "Server not started consult admin","Error",JOptionPane.ERROR_MESSAGE);
	            		DBConnection.closeDBConnection();
	            		System.exit(0);
	            	}
	            }
	        });

		} catch (Exception e) { }
//		MainPage mp = new MainPage();
//		mp.init();

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		
		if(arg0.getSource()==label_1)
			label_1.setCursor(new Cursor(Cursor.HAND_CURSOR));
		else if(arg0.getSource()==label_2)
			label_2.setCursor(new Cursor(Cursor.HAND_CURSOR));
		else if(arg0.getSource()==label_3)
			label_3.setCursor(new Cursor(Cursor.HAND_CURSOR));
		else if(arg0.getSource()==label_4)
			label_4.setCursor(new Cursor(Cursor.HAND_CURSOR));
		else if(arg0.getSource()==labelUser)
		{
			labelUser.setCursor(new Cursor(Cursor.HAND_CURSOR));
		}
		
		// TODO Auto-generated method stub
		if(arg0.getSource()==label_1)
		{
		label_1.setIcon(new ImageIcon(MainPage.class.getResource("/images/minus2.png")));
		label_1.setToolTipText("Minimize");
		}
		else if(arg0.getSource()==label_2)
		{
			label_2.setIcon(new ImageIcon(MainPage.class.getResource("/images/settings2.png")));
			label_2.setToolTipText("Settings");
		}
		else if(arg0.getSource()==label_3)
		{
			label_3.setIcon(new ImageIcon(MainPage.class.getResource("/images/close2.png")));
			label_3.setToolTipText("Close");
		}
		else if(arg0.getSource()==label_4)
		{
			label_4.setIcon(new ImageIcon(MainPage.class.getResource("/images/calc2.png")));
			label_4.setToolTipText("Calculator");
		}
		else if(arg0.getSource()==labelUser)
		{
			labelUser.setCursor(new Cursor(Cursor.HAND_CURSOR));
			labelUser.setText("SIGN OUT");
		}
		else if(arg0.getSource()==lblHome)
		{
			lblHome.setCursor(new Cursor(Cursor.HAND_CURSOR));
		}
		else{
			
		}
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==label_1)
		label_1.setIcon(new ImageIcon(MainPage.class.getResource("/images/minus.png")));
		else if(arg0.getSource()==label_2)
			label_2.setIcon(new ImageIcon(MainPage.class.getResource("/images/settings.png")));
		else if(arg0.getSource()==label_3)
			label_3.setIcon(new ImageIcon(MainPage.class.getResource("/images/close.png")));
		else if(arg0.getSource()==label_4)
			label_4.setIcon(new ImageIcon(MainPage.class.getResource("/images/calc.png")));
		else if(arg0.getSource()==labelUser)
		{
			labelUser.setText(Login.userName);
		}
	}
	public void triggerLogin()
	{
		new Login(this);
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==label_2)
		{
		st=new Settings(this);
		}
		if(arg0.getSource()==label_1)
		{
			this.setState(JFrame.ICONIFIED);
		}
		if(arg0.getSource()==label_3)
		{
			if(JOptionPane.showConfirmDialog(this,"Do you want to exit", "Confirm Exit", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
					System.exit(0);
			
	
		}
		if(arg0.getSource()==label_4)
		{
		try {
			Process p=Runtime.getRuntime().exec("calc");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		else if(arg0.getSource()==labelUser)
		{
        	if(JOptionPane.showConfirmDialog(this,"Do you want to exit", "Choose one", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
			{
				if(MainPage.user_role.equals(com.hms.util.Constants.ADMIN))
				{
				MainPage.tabbedPane.removeTabAt(7);
				}
				labelUser.setText("       ");
				tabbedPane.setSelectedIndex(0);
        		new Login(this);
        		//System.exit(0);
			}
		}
		else if(arg0.getSource()==lblHome)
		{
			ViewHandler.updateDashBoard(WelcomeEntry.bodyPanel, WelcomeEntry.dashBoardContainer, WelcomeEntry.gbc_bodyPanel, WelcomeEntry.sliderMenu, WelcomeEntry.gbc_sliderMenu);		
		}
	}
	
	public static void mtColor()
	{
		lblhotelName.setForeground(new Color(SetColor.mtColor));
	}
	public static void cColor()
	{
		lblVersion.setForeground(new Color(SetColor.cColor));
		labelUser.setForeground(new Color(SetColor.cColor));
		lblLbltime.setForeground(new Color(SetColor.cColor));
		lblFriMay.setForeground(new Color(SetColor.cColor));
	}
	public void tbColor()
	{
		tabbedPane.setForeground(new Color(SetColor.tbColor));
	}
	public static void bkColor()
	{
		containerPanel.setBackground(new Color(SetColor.bkColor));
		headerPanel.setBackground(new Color(SetColor.bkColor));
		footerPanel.setBackground(new Color(SetColor.bkColor));
		panel_2.setBackground(new Color(SetColor.bkColor));
		panel_4.setBackground(new Color(SetColor.bkColor));
	}
	public static void mtFont(String mtFType,int mtfProp,int mtSize)
	{
		lblhotelName.setFont(new Font(mtFType,mtfProp,mtSize));
	}
	public static void ctFont(String ctFType,int ctfProp,int ctSize)
	{
		lblVersion.setFont(new Font(ctFType,ctfProp,ctSize));
		labelUser.setFont(new Font(ctFType,ctfProp,ctSize));
		lblLbltime.setFont(new Font(ctFType,ctfProp,ctSize));
		lblFriMay.setFont(new Font(ctFType,ctfProp,ctSize));
	}
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stubex
		
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if(tabbedPane.getSelectedIndex()==0)
		{
        switch (arg0.getKeyCode()) {
        case 27: 
        	if(JOptionPane.showConfirmDialog(null,"Do you wish to quit", "Choose one", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
			{
        		DBConnection.closeDBConnection();
        		System.exit(0);
			}
        	
        }
		}
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
