//	
//public class Example {
//    static String fileDictName = "";
//
//    public static void main(String[] args) throws FileNotFoundException, IOException {
//        XSSFWorkbook workbook;
//
//        JFileChooser fileChooser = new JFileChooser();
//        fileChooser.setDialogTitle("Open the file"); //name for chooser
//        FileFilter filter = new FileNameExtensionFilter("Files", ".xlsx"); //filter to show only that
//        fileChooser.setAcceptAllFileFilterUsed(false); //to show or not all other files
//        fileChooser.addChoosableFileFilter(filter);
//        fileChooser.setSelectedFile(new File(fileDictName)); //when you want to show the name of file into the chooser
//        fileChooser.setVisible(true);
//        int result = fileChooser.showOpenDialog(fileChooser);
//        if (result == JFileChooser.APPROVE_OPTION) {
//            fileDictName = fileChooser.getSelectedFile().getAbsolutePath();
//        } else {
//            return;
//        }
//
//        File file = new File(fileDictName);
//        if (file.exists() == false) {
//            workbook = new XSSFWorkbook();
//            XSSFSheet exampleSheet = workbook.createSheet("1");
//            XSSFRow firstRow = exampleSheet.createRow(1);
//            XSSFCell cell = firstRow.createCell(0);
//            cell.setCellValue("value");
//
//            try (
//                    //Write the workbook in file system
//                    FileOutputStream out = new FileOutputStream(file)) {
//                workbook.write(out);
//            }
//        } else {
//            // Sheet already exists
//            System.out.println("File already exist");
//        }
//    }
//
//}