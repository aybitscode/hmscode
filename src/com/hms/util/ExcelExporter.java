package com.hms.util;

import java.io.File;

import javax.swing.JTable;
import javax.swing.table.TableModel;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class ExcelExporter {
		public ExcelExporter()
		{
		
		}
    public static void fillData(JTable table, String path, String sheet) {
        try {
        	File file = new File(path);
        	if(!file.exists())
        	{
               
            WritableWorkbook workbook1 = Workbook.createWorkbook(file);
            WritableSheet sheet1 = workbook1.createSheet(sheet, 0); 
            TableModel tmodel = table.getModel();
            for (int i = 0; i < tmodel.getColumnCount(); i++) {
                Label column = new Label(i, 0, tmodel.getColumnName(i));
                sheet1.addCell(column);
            }
            int j = 0;
            for (int i = 0; i < tmodel.getRowCount(); i++) {
                for (j = 0; j < tmodel.getColumnCount(); j++) {
                	if(tmodel.getValueAt(i, j)!=null)
                	{
	                    Label row = new Label(j, i + 1, tmodel.getValueAt(i, j).toString());
	                    sheet1.addCell(row);
                	}
                	else
                	{
	                    Label row = new Label(j, i + 1, "NULL");
	                    sheet1.addCell(row);                		
                	}
                }
            }
            workbook1.write();
            workbook1.close();
        	}
        	else
        	{
        		 Workbook workbook = Workbook.getWorkbook(file);
        		 WritableWorkbook copy = Workbook.createWorkbook(file, workbook);
                 WritableSheet sheet1 = copy.createSheet(sheet,0); 
                 TableModel tmodel = table.getModel();
                 for (int i = 0; i < tmodel.getColumnCount(); i++) {
                     Label column = new Label(i, 0, tmodel.getColumnName(i));
                     sheet1.addCell(column);
                 }
                 int j = 0;
                 for (int i = 0; i < tmodel.getRowCount(); i++) {
                     for (j = 0; j < tmodel.getColumnCount(); j++) {
                         Label row = new Label(j, i + 1, 
                                 tmodel.getValueAt(i, j).toString());
                         sheet1.addCell(row);
                     }
                 }
                 copy.write();
                 copy.close();
        	}
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
