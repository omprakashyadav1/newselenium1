package login;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;

import jxl.Workbook;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class Excel_data {
	XSSFSheet sheet;
	XSSFCell cell;
	XSSFRow row;
	WritableWorkbook wb ;
	WritableSheet ws;
	//XSSFWorkbook book;
	FileOutputStream fos;
    XSSFWorkbook workbook1 ;
    XSSFSheet spreadsheet;
	public void openSheet(String filepath, String sheetName) throws IOException{
		try{
		
			File file = new File(filepath);
			
			FileInputStream fis = new FileInputStream(file);
		 // load the workboo
		XSSFWorkbook wb=new XSSFWorkbook(fis);
		// get the sheet which you want to modify or create
		 sheet=wb.getSheet(sheetName);
				// getRow specify which row we want to read and getCell which column
		
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
	}
	public String getValueFromCella(int iColNumber,int iRowNumber) {
		try{
		cell=sheet.getRow(iRowNumber).getCell(iColNumber);
		DataFormatter formatter = new DataFormatter();

		String ol = formatter.formatCellValue(cell);
		
		//System.out.println("The value of CellData " + ol);
		//System.out.println(ol);
		return ol;
		}
		catch(Exception e){
			
			System.out.println(e.getMessage());
			return"";
		}
	}

	

	public int getRowCount() {
		int rowcount=sheet.getLastRowNum()-sheet.getFirstRowNum();
	 
		return rowcount;
		
	}

	public int getColumnCount() {
		int numberOfCells = 0;
		Iterator rowIterator = sheet.rowIterator();
		if (rowIterator.hasNext())
        {
			
			
			XSSFRow headerRow = (XSSFRow) rowIterator.next();
            //get the number of cells in the header row
             numberOfCells = headerRow.getPhysicalNumberOfCells();
           // System.out.println("No of Column" +numberOfCells);
        }
		
		return numberOfCells;
	}
public void writeSheet(String outputpath,int i, String testcaseid, String objective,String testdata,String result,Integer excelRow) throws IOException, RowsExceededException, WriteException{
		
		if(i==1){
	     wb = Workbook.createWorkbook(new File(outputpath));
	     ws = wb.createSheet("customsheet",1);
		}
		 WritableFont cellFont = new WritableFont(WritableFont.ARIAL, 10);
		    cellFont.setBoldStyle(WritableFont.BOLD);
		    cellFont.setColour(Colour.GREEN);
		    WritableCellFormat cellFormat = new WritableCellFormat(cellFont);
		    
		Label label0 = new Label(0,0,"Test case ID",cellFormat);
		Label label01 = new Label(1,0,"Objective",cellFormat);
		Label label02 = new Label(2,0,"Test Data",cellFormat);
		Label label03 = new Label(3,0,"Result",cellFormat);
		Label label04 = new Label(4,0,"Remarks",cellFormat);
		Label label1 = new Label(0,i,testcaseid);	
		Label label2 = new Label(1,i,objective);
		Label label3 = new Label(2,i,testdata);
		
		Label label4 = new Label(3,i,result);
		ws.addCell(label0);
		ws.addCell(label01);
		ws.addCell(label02);
		ws.addCell(label03);
		ws.addCell(label04);
		ws.addCell(label1);
		ws.addCell(label2);
		ws.addCell(label3);
		ws.addCell(label4);	
		if(i==excelRow-1){
		wb.write();
		wb.close();
	}
	}

	
}
