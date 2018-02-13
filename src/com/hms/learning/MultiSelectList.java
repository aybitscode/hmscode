package com.hms.learning;
import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
class MultiSelectList {
    public static void main(String[] args) throws Exception {
        File f = new File("C:/MultiSelectList.java");
        InputStream is = new FileInputStream(f);
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        final ArrayList<String> lines = new ArrayList<String>();
        String line = br.readLine();
        while (line!=null) {
            lines.add(line);
            line = br.readLine();
        }
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JList list = new JList(lines.toArray());
                list.setSelectionMode(
                    ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
                int[] select = {19, 20, 22};
                list.setSelectedIndices(select);
                JOptionPane.showMessageDialog(null, new JScrollPane(list));
            }
        });
    }
}