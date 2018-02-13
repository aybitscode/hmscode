package com.hms.learning;
import java.util.prefs.Preferences;

public class PreferenceTest {
  private Preferences prefs;
  private Preferences prefs1;
  public void setPreference() {
    // This will define a node in which the preferences can be stored
    prefs = Preferences.userRoot().node(this.getClass().getName());
	prefs1 = Preferences.userRoot().node("com/javasoft/def");
    String ID1 = "Test1";
    String ID2 = "Test2";
    String ID3 = "Test3";

    // First we will get the values
    // Define a boolean value
    System.out.println("the date is"+prefs1.get("pdColor", ""));
    System.out.println(prefs.getBoolean(ID1, true));
    // Define a string with default "Hello World
    System.out.println(prefs.get(ID2, "Hello World"));
    // Define a integer with default 50
    System.out.println(prefs.getInt(ID3, 50));

    // now set the values
    prefs.putBoolean(ID1, false);
    prefs.put(ID2, "Hello Europa");
    prefs.putInt(ID3, 45);

    // Delete the preference settings for the first value
    prefs.remove(ID1);

  }

  public static void main(String[] args) {
//    PreferenceTest test = new PreferenceTest();
//    test.setPreference();
	  String s1 = "-1";
	  String s2 = "-1 ";
	  if(s1.equals(s2))
		  System.out.println("strings are equal");
	  else 
		  System.out.println("Strings are not equal");
  }
}