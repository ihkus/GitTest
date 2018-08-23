import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Workbook {

	
	private XSSFWorkbook workbook;
	private XSSFSheet currentSheet;
	
	private String filePath;
	
	
	public Workbook(String filePath)
	{
		this.filePath=filePath;
		workbook = new XSSFWorkbook();
		
	}
	
	public void save()
	{
		try {
			workbook.write(new FileOutputStream(filePath));
		} catch (IOException e) {
			System.out.println("Unable to save the excel file.");
		}
		
	}
	
	public void openSheet(String sheetName) {
		
		XSSFSheet sheet=workbook.getSheet(sheetName);
		if(sheet==null)
		sheet=workbook.createSheet(sheetName);
		
		currentSheet=sheet;
	
	}
	public void makeEntry(String data, int rowNumber,int columnsToMerge)
	{
		XSSFRow currentRow=getRow(rowNumber);
		if(columnsToMerge>1)
		mergeArea(rowNumber,rowNumber,currentRow.getLastCellNum()+1,currentRow.getLastCellNum()+columnsToMerge+1);
		currentRow.createCell(currentRow.getLastCellNum()+1).setCellValue(data);
		
		
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
	
	
}
