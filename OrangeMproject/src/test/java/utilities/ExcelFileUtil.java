package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelFileUtil 
{
	  Workbook wb;
	    
	  public ExcelFileUtil() throws Throwable
	  {
    	    FileInputStream fis=new FileInputStream(".\\TestInputs\\InputSheet.xlsx");
	    	wb=WorkbookFactory.create(fis);
	    }
	  public int rowCount(String sheetName)
		{
			return wb.getSheet(sheetName).getLastRowNum();
		}
	  public int colCount(String sheetName,int row)
		{
			return wb.getSheet(sheetName).getRow(row).getLastCellNum();
		}
	  public String getData(String sheetName,int row,int column)
	    {
	    	String data = " ";
	    	if(wb.getSheet(sheetName).getRow(row).getCell(column).getCellType()==CellType.NUMERIC)
	    			{
	    		int celldata=(int)(wb.getSheet(sheetName).getRow(row).getCell(column).getNumericCellValue());
	    	}
	    	else
	    	{
	    		data=wb.getSheet(sheetName).getRow(row).getCell(column).getStringCellValue();
	    	}
			return data;
	    }
	  public void setData(String sheetName,int row,int column,String Status) throws Throwable{
	    	Sheet sh=wb.getSheet(sheetName);
	    	Row rownum=sh.getRow(row);
	    	Cell cell=rownum.createCell(column);
	    	cell.setCellValue(Status);
	    	if(Status.equalsIgnoreCase("Pass")){
	    		//create cell style
	    		CellStyle style=wb.createCellStyle();
	    		//create font
	    		Font font=wb.createFont();
	    		//Apply color to the text
	    		font.setColor(IndexedColors.GREEN.getIndex());
	    		//APPLY bold to the text
	    		font.setBold(true);
	    		//set font
	    		style.setFont(font);
	    		//set cell style
	    		rownum.getCell(column).setCellStyle(style);
	    	}
	    	else 
	    		if(Status.equalsIgnoreCase("Fail")){
	    			//create cell style
	    			CellStyle style=wb.createCellStyle();
	    			//create font
	    			Font font=wb.createFont();
	    			//Apply color to the text
	    			font.setColor(IndexedColors.RED.getIndex());
	    			//apply bold to the text
	    			font.setBold(true);
	    			//set font
	    			style.setFont(font);
	    			//set cell style
	    			rownum.getCell(column).setCellStyle(style);
	    		}
	    		else 
	    			if(Status.equalsIgnoreCase("Not Executed")){
	    				//create cell style
	    				CellStyle style=wb.createCellStyle();
	    				//cretae font
	    				Font font=wb.createFont();
	    				//apply color to the font
	    				font.setColor(IndexedColors.BLUE.getIndex());
	    				//apply bold to the text
	    				font.setBold(true);
	    				//set font
	    				style.setFont(font);
	    				//set cell style
	    				rownum.getCell(column).setCellStyle(style);
	    	}
	    
	    FileOutputStream fos=new FileOutputStream(".\\TestOutput\\OutputSheet.xlsx");
	    wb.write(fos);
	    fos.close();
	}
}
	 


