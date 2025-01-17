package hr_system_assignment_mtec22_001;


import java.awt.Color;
import java.io.FileWriter;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.util.*;
import java.io.*;
import java.nio.file.*;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

public class DataManager {
    
    public int WriteFileData(String filepath, String[] arrData) throws Exception{
        File myfile = new File(filepath);
        if (myfile.createNewFile()){ /*create file*/ }else{ /*exists*/ }
        
        try (FileWriter writer = new FileWriter(filepath, true)) {
            
            //create line data
            String linedata ="";
            for(String value : arrData){ linedata += value +"#"; }
            linedata = linedata.substring(0, linedata.length()-1); 

            //write tofile
            writer.write(linedata);
            writer.write("\r\n");   // write new line, to prepare for next line
            
            return 1; //success
        } catch (Exception ex) {
            throw ex;
        }
    }
    
    //read tabledata
    public String[][] ReadFileData(String filepath) throws Exception {
        try {
            List<String> listdata = Files.readAllLines( Paths.get(filepath) );
            int colcount = listdata.get(0).split("#").length;
            String[][] FileData = new String[listdata.size()][colcount];
            for(int k=0; k<listdata.size(); k++){
                String line = listdata.get(k);
                String[] linedata = line.split("#");
                FileData[k] = linedata;
            }
            return FileData;
        }catch (Exception ex) {
            throw ex;
        }
    }
    
    //search file data
    public String[][] SearchFileData(String filepath, int colIndex, String colValue) throws Exception {
        try {
            List<String> listdata = Files.readAllLines( Paths.get(filepath) );
            ArrayList<String[]> arrData = new ArrayList<>();
            for(int k=0; k<listdata.size(); k++){
                String line = listdata.get(k);
                String[] linedata = line.split("#");
                //search
                if(linedata[colIndex].equalsIgnoreCase(colValue)){
                    arrData.add(linedata);
                }
            }
            //convert arraylist to 2D array
            String[][] FileData = arrData.toArray(String[][]::new);

            return FileData;
        }catch (Exception ex) {
            throw ex;
        }
    }
    
    //load table data
    public void LoadTable(JTable tbl, String[][] arrData, String[] columns) throws Exception { 
        int recordCount = arrData.length; //get rowcount
        //columnnames
        int colCount = arrData[0].length; //get column count
        //create model and add columns
        DefaultTableModel dtm=new DefaultTableModel();
        for(int i = 0; i <= colCount - 1; i++) {dtm.addColumn(columns[i]);}
        //get table data array
        dtm.setDataVector(arrData, columns);
        tbl.setModel(dtm);
        tbl.setRowHeight(20); 
        tbl.setGridColor(Color.lightGray); //gridline color
        tbl.setForeground(Color.blue); //fore color
        tbl.setBackground(Color.white); //background color 
        tbl.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tbl.setShowGrid(true);
    }
    
    //file data column names
    public String[] GetColumns(String filename) throws Exception{
        String[] columns = {};
        if(filename.equalsIgnoreCase("Employees")){
            columns = new String[]{"EMPLOYEEID", "FIRSTNAME", "LASTNAME", "GENDER", "DOB", "ADDRESS", "CITY", "POSITION", "DEPARTMENT", "HOURSWORKED", "PAYRATE", "LEAVEDAYS", "CARALLOWANCE", "MONTHLYGRATUITY", "TAXRATE", "TOTALALLOWANCES", "TOTALDEDUCTIONS", "GROSSPAY", "NETPAY", "DATEHIRED"}; 
        }else if(filename.equalsIgnoreCase("EmployeeDetails")){
            columns = new String[]{"EMPLOYEEID", "FIRSTNAME", "LASTNAME", "GENDER", "DOB", "ADDRESS", "CITY", "POSITION", "DEPARTMENT", "HOURSWORKED", "PAYRATE", "LEAVEDAYS", "CARALLOWANCE", "MONTHLYGRATUITY", "TAXRATE", "TOTALALLOWANCES", "TOTALDEDUCTIONS", "GROSSPAY", "NETPAY", "DATEHIRED"}; 
        }else if(filename.equalsIgnoreCase("EmployeeSalary")){
            columns = new String[]{"EMPLOYEEID", "FIRSTNAME", "LASTNAME", "GENDER", "DOB", "ADDRESS", "CITY", "POSITION", "DEPARTMENT", "HOURSWORKED", "PAYRATE", "LEAVEDAYS", "CARALLOWANCE", "MONTHLYGRATUITY", "TAXRATE", "TOTALALLOWANCES", "TOTALDEDUCTIONS", "GROSSPAY", "NETPAY", "DATEHIRED"}; 
        }
        return columns;
    }
    
    //file data column names
    public String GetSystemTitle() {
        return "HR System_Assignment_MTEC22_001";
    }
    
    public void InitialiseForm(JFrame frm, String strTitle) throws Exception{
        frm.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);  //disable close button
        frm.setResizable(false);                                            //disable resize button
        frm.setLocationRelativeTo(null);  
        frm.setTitle(strTitle);
        //center form relative to main menu
        //set look and feel
        String laf = "";
        String str=frm.getClass().getSimpleName();
        if(frm.getClass().getSimpleName().equalsIgnoreCase("frmMain")){
            laf = "javax.swing.plaf.nimbus.NimbusLookAndFeel";
            laf = "javax.swing.plaf.metal.MetalLookAndFeel";
            frm.setExtendedState(frm.getExtendedState() | JFrame.MAXIMIZED_BOTH);
            frm.setResizable(false);
        }else{
            frm.setAlwaysOnTop(true);
            laf = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
            laf = "javax.swing.plaf.nimbus.NimbusLookAndFeel";
            laf = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
            laf = ("com.birosoft.liquid.LiquidLookAndFeel");
            laf = ("org.jvnet.substance.SubstanceLookAndFeel");
            laf = "javax.swing.plaf.metal.MetalLookAndFeel"; //overide all/
        }
        UIManager.setLookAndFeel(laf);
        //UIManager.setLookAndFeel(new NimbusLookAndFeel());
        //JOptionPane.showMessageDialog(null, UIManager.getLookAndFeel()); //debugging//
    }
 
    public void SetFormLookAndFeel(JFrame frm) throws Exception{
        String laf = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
//        laf = "javax.swing.plaf.nimbus.NimbusLookAndFeel";
        laf = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
//        laf = ("com.birosoft.liquid.LiquidLookAndFeel");
//        laf = ("org.jvnet.substance.SubstanceLookAndFeel");
//        laf = "javax.swing.plaf.metal.MetalLookAndFeel"; //overide all/
        UIManager.setLookAndFeel(laf);
    }
    
}





