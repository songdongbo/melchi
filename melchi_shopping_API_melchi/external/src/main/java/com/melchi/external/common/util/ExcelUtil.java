package com.melchi.external.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {
	
	private static SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");

	public static SimpleDateFormat getDateFormatter(){
		return formatter;
	}
	
	public static Workbook getWorkBook(InputStream is, String fileName) throws IOException{
		
		Workbook wb = null;
		if(fileName.endsWith("xls")){
			POIFSFileSystem fs = new POIFSFileSystem(is);
			wb = new HSSFWorkbook(fs);	
		}else{
			wb = new XSSFWorkbook(is);	
		}
		
		return wb;
	}
	
	public static Workbook createWorkBook(String fileName){
		Workbook wb = null;
		if(fileName.endsWith("xls")){
			wb = new HSSFWorkbook();	
		}else{
			wb = new XSSFWorkbook();	
		}
		
		return wb;
		
	}
	public static String getCellValue(Sheet sheet, int row, int cellNo){
		Cell cell = sheet.getRow(row).getCell(cellNo);
		
		String passing = "";
		int temp = 0;
		
		if(cell != null)
			 switch (cell.getCellType()) {
				case Cell.CELL_TYPE_BLANK:
					break;
               case Cell.CELL_TYPE_STRING:  
               		passing = cell.getRichStringCellValue().getString();
                   break;
               case Cell.CELL_TYPE_NUMERIC:
                   if (DateUtil.isCellDateFormatted(cell)) {                   	
	                   	passing = getDateFormatter().format(cell.getDateCellValue());
                   } else {
                	   DecimalFormat decimalFormat = new DecimalFormat("###");
                	   passing = decimalFormat.format(cell.getNumericCellValue());
                   }
                   break;
               case Cell.CELL_TYPE_BOOLEAN:       
                   passing = cell.getNumericCellValue()+"";
                   break;
               case Cell.CELL_TYPE_FORMULA:      
                   passing = cell.getCellFormula().toString();
                   break;
               default:
			}	
		
		return passing;
	}
	
	public static String getCellValueToDay(Sheet sheet, int row, int cellNo){
		Cell cell = sheet.getRow(row).getCell(cellNo);
		
		String passing = "";
		int temp = 0;
		
		if(cell != null)
			 switch (cell.getCellType()) {
				case Cell.CELL_TYPE_BLANK:
					break;
               case Cell.CELL_TYPE_STRING:  
               		passing = cell.getRichStringCellValue().getString();
                   break;
               case Cell.CELL_TYPE_NUMERIC:
                   if (DateUtil.isCellDateFormatted(cell)) {                   	
	                   	passing = getDateFormatter().format(cell.getDateCellValue());
                   } else {
                	   DecimalFormat decimalFormat = new DecimalFormat("###");
                	   passing = decimalFormat.format(cell.getNumericCellValue());
                   }
                   break;
               case Cell.CELL_TYPE_BOOLEAN:       
                   passing = cell.getNumericCellValue()+"";
                   break;
               case Cell.CELL_TYPE_FORMULA:      
                   passing = cell.getCellFormula().toString();
                   break;
               default:
			}	
		
		if(passing != null && passing != ""){
			passing= passing.replaceAll("-", "").replaceAll("/", "");
		}else{
			passing = "";
		}
		
		if(passing.length() > 8){
			passing = passing.substring(0, 8) ;
		}
		
		return passing;
	}
	
	
	/**
	 * 엑셀파일내 데이터 타입 리턴
	 * @param sheet
	 * @param row
	 * @param cellNo
	 * @return
	 */
	public static String getCellValueType(Sheet sheet, int row, int cellNo){
		Cell cell = sheet.getRow(row).getCell(cellNo);
		
		String cellType = "";
		
		if(cell != null){
			 switch (cell.getCellType()) {
			 	case Cell.CELL_TYPE_BLANK:
			 		cellType = "CELL_TYPE_BLANK";
					break;
			 	case Cell.CELL_TYPE_STRING:  
			 		cellType = "CELL_TYPE_STRING";
			 		break;
			 	case Cell.CELL_TYPE_NUMERIC:
			 		cellType = "CELL_TYPE_NUMERIC";
			 		break;
			 	case Cell.CELL_TYPE_BOOLEAN:       
			 		cellType = "CELL_TYPE_BOOLEAN";
			 		break;
			 	case Cell.CELL_TYPE_FORMULA:      
			 		cellType = "CELL_TYPE_FORMULA";
			 		break;
			 	default: break;
			}
		}
		return cellType;
	}
	
	
	public static int parseInt(String str, String element){
		if(str == null || "".equals(str))
			return 0;
		
		int result = 0;
		
		try{
			result =  Integer.parseInt(str);
		}catch(NumberFormatException e){
			//throw new ExcelTransformException("[" + element + "=" +str + "] 는 숫자 형식이어야 합니다.");
		}
		
		if(result < 0){
			//throw new ExcelTransformException("[" + element + "=" +str + "] 는 0보다 크거나 같아야 합니다.");
		}
		return result;
	}	
	
    public static Cell createCell(Row r, int i){
    	return r.createCell(i);
    }
    
    public static Cell createCell(Row r, int i, CellStyle cs){
    	Cell cell = r.createCell(i);
    	cell.setCellStyle(cs);
    	return cell;
    }


}
