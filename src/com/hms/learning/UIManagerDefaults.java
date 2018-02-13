package com.hms.learning;
import java.util.Set;
import java.util.Map.Entry;
import javax.swing.UIManager;

/**
 * A Java UIManager example, showing how to get all the 
 * UIManager default values.
 */
public class UIManagerDefaults
{
  public static void main(String[] args)
  throws Exception
  {
    Set<Entry<Object, Object>> entries =  UIManager.getLookAndFeelDefaults().entrySet();
    for (Entry entry : entries)
    {
      System.out.print(entry.getKey() + " = ");
      System.out.print(entry.getValue() + "\n");
    }
  }
}