package com.hotelmanagement;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeSelectionModel;
//SplitPaneDemo itself is not a visible component.
public class Help extends JPanel implements TreeSelectionListener{
    private JSplitPane splitPane;
    Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
    private JEditorPane htmlPane;
    private JTree tree;
    private URL helpURL;
    private static boolean DEBUG = false;

    //Optionally play with line styles.  Possible values are
    //"Angled" (the default), "Horizontal", and "None".
    private static boolean playWithLineStyle = false;
    private static String lineStyle = "Horizontal";
    
    //Optionally set the look and feel.
    private static boolean useSystemLookAndFeel = false;

    JFrame f;
    public Help() {

        //Create the list of images and put it in a scroll pane.
        f=new JFrame();
        f.setSize(d.width-200,d.height-100);
        f.setLocation(100,10);
        //Create the nodes.
        DefaultMutableTreeNode top =
            new DefaultMutableTreeNode("Fee Management System User Guide");
        createNodes(top);

        //Create a tree that allows one selection at a time.
        tree = new JTree(top);
        tree.getSelectionModel().setSelectionMode
                (TreeSelectionModel.SINGLE_TREE_SELECTION);
        
        tree.setRootVisible(true);
        //Listen for when the selection changes.
        ImageIcon leafIcon = createImageIcon("/images/welcome_editor.gif");
        ImageIcon closedIcon = createImageIcon("/images/toc_closed.gif");
        ImageIcon openIcon = createImageIcon("/images/toc_open.gif");
        ImageIcon plusIcon = createImageIcon("/images/plus.gif");
       if (leafIcon != null) {
           DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();
           renderer.setLeafIcon(leafIcon);
           renderer.setClosedIcon(closedIcon);
           renderer.setOpenIcon(openIcon);
           tree.setCellRenderer(renderer);
       } else {
           System.err.println("Leaf icon missing; using default.");
       }
       tree.addTreeSelectionListener(this);
        JScrollPane treeView = new JScrollPane(tree);
        htmlPane = new JEditorPane();
        htmlPane.setContentType("text/html");
        htmlPane.setEditable(false);
       
        initHelp();
        JScrollPane htmlView = new JScrollPane(htmlPane);

        //Create a split pane with the two scroll panes in it.
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setTopComponent(treeView);
        splitPane.setBottomComponent(htmlView);
        splitPane.setOneTouchExpandable(true);
        splitPane.setDividerLocation(250);

        //Provide minimum sizes for the two components in the split pane.

        Dimension minimumSize = new Dimension(100, 50);
        htmlView.setMinimumSize(minimumSize);
        treeView.setMinimumSize(minimumSize); 
        splitPane.setDividerSize(1);
        splitPane.setPreferredSize(new Dimension(500, 300));
        f.add(splitPane);
        f.setVisible(true);
    }
    
 
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = Help.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

	public JSplitPane getSplitPane() {
        return splitPane;
    }

	@Override
	public void valueChanged(TreeSelectionEvent arg0) {
		// TODO Auto-generated method stub
		   DefaultMutableTreeNode node = (DefaultMutableTreeNode)
                   tree.getLastSelectedPathComponent();

if (node == null) return;

Object nodeInfo = node.getUserObject();
if (node.isLeaf()) {
    ModInfo modNode = (ModInfo)nodeInfo;
    displayURL(modNode.modNodeURL);
    if (DEBUG) {
        System.out.print(modNode.modNodeURL + ":  \n    ");
    }
} else {
    displayURL(helpURL); 
}
if (DEBUG) {
    System.out.println(nodeInfo.toString());
}
	}   
private class ModInfo
{
	        public String modName;
	        public URL modNodeURL;

	        public ModInfo(String modNode, String filename) {
	            modName = modNode;
	            modNodeURL = this.getClass().getResource(filename);
	            if (modNodeURL == null) {
	                System.err.println("Couldn't find file: "
	                                   + filename);
	            }
	        }

	        public String toString() {
	            return modName;
	        }
}
private void initHelp() {
    String s = "/html/welcome.html";
    helpURL = this.getClass().getResource(s);
    System.out.println(helpURL);
    if (helpURL == null) {
        System.err.println("Couldn't open help file: " + s);
    } else if (DEBUG) {
        System.out.println("Help URL is " + helpURL);
    }

    displayURL(helpURL);
}

private void displayURL(URL url) {
    try {
        if (url != null) {
            htmlPane.setPage(url);
        } else { //null url
	htmlPane.setText("File Not Found");
            if (DEBUG) {
                System.out.println("Attempted to display a null URL.");
            }
        }
    } catch (IOException e) {
        System.err.println("Attempted to read a bad URL: " + url);
    }
}

private void createNodes(DefaultMutableTreeNode top) {
    DefaultMutableTreeNode category = null;
    DefaultMutableTreeNode modNode = null;

    category = new DefaultMutableTreeNode("Login and ChangePassword");
    top.add(category);

    //original Tutorial
    modNode = new DefaultMutableTreeNode(new ModInfo
        ("Login Module",
        "/html/login.html"));
    category.add(modNode);

    //Tutorial Continued
    modNode = new DefaultMutableTreeNode(new ModInfo
        ("ChangePassword Module",
        "/html/changepass.html"));
    category.add(modNode);

    //JFC Swing Tutorial
    category = new DefaultMutableTreeNode(
        ("Using the payments module"));
    top.add(category);

    //Bloch
    modNode = new DefaultMutableTreeNode(new ModInfo
        ("How to use payment module",
     "/html/payment.html"));
    category.add(modNode);

    //Arnold/Gosling
    modNode = new DefaultMutableTreeNode(new ModInfo
        ("How to generate fee receipt", "/html/receipt.html"));
    category.add(modNode);

    //Chan
    category = new DefaultMutableTreeNode("About Transactions");
    top.add(category);
    
    modNode = new DefaultMutableTreeNode(new ModInfo
            ("Transaction details",
             "/html/transac.html"));
        category.add(modNode);

    category = new DefaultMutableTreeNode("Search,edit and generate reports");
    top.add(category);

    //VM
    modNode = new DefaultMutableTreeNode(new ModInfo
        ("Search student details by id and edit",
         "/html/srchedit.html"));
    category.add(modNode);

    //Language Spec

    modNode = new DefaultMutableTreeNode(new ModInfo
        ("Search student details by course and year of join",
         "/html/srchbycrs.html"));
    category.add(modNode);
    
    modNode = new DefaultMutableTreeNode(new ModInfo
         ("Search student details by course, year of join & balance",
          "/html/srchbylmt.html"));
        category.add(modNode);
        
    category = new DefaultMutableTreeNode("Inserting data into the database");
    top.add(category);
    
   modNode = new DefaultMutableTreeNode(new ModInfo
            ("Inserting the student details",
             "/html/insertstd.html"));
           category.add(modNode);
                  
   modNode = new DefaultMutableTreeNode(new ModInfo
                   ("Insert, retrieve, update and deleting the courses",
                    "/html/insertcrs.html"));
                  category.add(modNode);
   category = new DefaultMutableTreeNode("Deleteing the transaction & student records");
   top.add(category);
   
   modNode = new DefaultMutableTreeNode(new ModInfo
           ("Deleting the transaction records",
            "/html/transac.html"));
          category.add(modNode);
          
   modNode = new DefaultMutableTreeNode(new ModInfo
           ("Deleting the student records",
             "/html/transac.html"));
           category.add(modNode); 
}
	public static void main(String ar[])
	{
		
		new Help();
	}
    
}
