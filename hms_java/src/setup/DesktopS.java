package setup;

import net.jimmc.jshortcut.JShellLink;

public class DesktopS {
JShellLink link;
String filePath;
public DesktopS() {
try {
link = new JShellLink();
filePath = JShellLink.getDirectory("") + "C:\\HotelManagement\\boot\\hms.exe";
} catch (Exception e) {
}
}
public void createDesktopShortcut() {
try {
link.setFolder(JShellLink.getDirectory("desktop"));
link.setName("hms");
link.setPath(filePath);
link.save();
} catch (Exception ex) {
ex.printStackTrace();
}
}
}