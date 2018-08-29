import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.helpers.XSSFRowShifter;

public class Workbook {

	
	private XSSFWorkbook workbook;
	private XSSFSheet currentSheet;
	
	private String filePath;
	
	
	public Workbook(String filePath) throws FileNotFoundException, IOException
	{
		this.filePath=filePath;
		if(new File(filePath).exists())
			workbook=new XSSFWorkbook(new FileInputStream(filePath));
		else
		workbook = new XSSFWorkbook();
		
	}
	
	public void save()
	{
		try {
			autoSizeColumns(workbook);
			workbook.write(new FileOutputStream(filePath));
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Unable to save the excel file.");
		}
		
	}
	
	public void openSheet(String sheetName) {
		
		System.out.println(sheetName);
		XSSFSheet sheet=workbook.getSheet(sheetName);
		if(sheet==null)
		sheet=workbook.createSheet(sheetName);
		
		
		currentSheet=sheet;
		
	
	}
	public void makeEntry(CustomNode node)
	{
		
		String data=node.getName();
	int	rowNumber=node.getLevel();
		int startCol=node.getStartCol()+1;
		int endCol=node.getEndCol()+1;
		XSSFRow currentRow=getRow(rowNumber);
		 CellStyle lockedNumericStyle = workbook.createCellStyle(); 
		
		 lockedNumericStyle.setLocked(true); 
		
		
			
//		System.out.println(data+","+startCol+" "+rowNumber+" "+endCol);
		
		Cell cell=CellUtil.createCell(currentRow, startCol, data);
		
		
		
		if(endCol-startCol>1)
		{		
			mergeArea(rowNumber,rowNumber,startCol,endCol-1);
		}
		
		CellUtil.setAlignment(cell, workbook, CellStyle.ALIGN_CENTER);
		
	}
	public void mergeArea(int rowFrom,int rowTo,int colFrom,int colTo)
	{
		currentSheet.addMergedRegion(new CellRangeAddress(rowFrom, rowTo, colFrom, colTo));
		
	}
	private XSSFRow getRow(int rowNumber)
	{
		XSSFRow currentRow=currentSheet.getRow(rowNumber);
		if(currentRow==null)
			currentRow=currentSheet.createRow(rowNumber);
			
		return currentRow;
	}
	
	
	public void autoSizeColumns(XSSFWorkbook workbook) {
	    int numberOfSheets = workbook.getNumberOfSheets();
	    for (int i = 0; i < numberOfSheets; i++) {
	        Sheet sheet = workbook.getSheetAt(i);
	       
	           for(int j=0;j<100;j++)
	           {
	                sheet.autoSizeColumn(j);
	            }
	        
	    }
	}
	
}
