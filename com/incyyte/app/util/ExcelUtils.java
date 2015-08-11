package com.incyyte.app.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * @author RemiOseni
 *
 */
public class ExcelUtils {

	@SuppressWarnings("unused")
	private ExcelUtils instance;
	//private String docPath;
	//private POIFSFileSystem document;
	private HSSFWorkbook workbook;

	public static ExcelUtils getInstance(HSSFWorkbook workbook)
	throws IOException{
		return new ExcelUtils(workbook);
	}

	private ExcelUtils(){}

	private ExcelUtils(HSSFWorkbook workbook) throws IOException {
		this.instance = new ExcelUtils();
		//this.docPath  = sDocPath;
		//this.document = new POIFSFileSystem(inputStream);
		this.workbook = workbook;
	}

	public ArrayList<String> getColNames(String sheetName){
		return getColNames(this.workbook.getSheetIndex(sheetName));
	}

	public List<String> getColHeaders(int sheetIndex) {
		List<String>  colHeaders = new ArrayList<String>();
		HSSFSheet sheet = this.workbook.getSheetAt(sheetIndex);
		HSSFRow   row0   = sheet.getRow(0);		
		HSSFCell  cell0  = null;
		
		int cols0  = 0;

		if (row0 != null){
			cols0 = row0.getPhysicalNumberOfCells();
			
			for (int i = 0; i < cols0; i++){
				cell0 = row0.getCell((short) i);
				
				String colname=null;
				
				if (cell0 != null && cell0.getCellType() == Cell.CELL_TYPE_STRING){
					colname = cell0.getStringCellValue().trim();
				}								
				if (colname != null)
					colHeaders.add(colname);
			}
		}
		return colHeaders;
	}


	public ArrayList<String> getColNames(int sheetIndex) {
		ArrayList<String> colNames = new ArrayList<String>();
		HSSFSheet sheet = this.workbook.getSheetAt(sheetIndex);
		HSSFRow   row   = sheet.getRow(0);
		HSSFCell  cell  = null;
		int cols  = 0;

		if (row != null){
			cols = row.getPhysicalNumberOfCells();

			for (int i = 0; i < cols; i++){
				cell = row.getCell((short) i);

				if (cell != null && cell.getCellType() == Cell.CELL_TYPE_STRING){
					colNames.add(cell.getStringCellValue().trim());
				}
			}
		}
		return colNames;
	}

	private Object getCellValue(HSSFCell cell, String colName){
		Object obj = null;

		if (cell != null && cell.getCellType() == Cell.CELL_TYPE_STRING) {			
			obj = cell.getStringCellValue();
		} else if (cell != null && cell.getCellType() == Cell.CELL_TYPE_NUMERIC && colName.indexOf("Date") != -1) {			
			obj = cell.getDateCellValue();
		} else if (cell != null && cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {			
			obj = cell.getNumericCellValue();
		}

		return obj;
	}

	/**
	 * @param sheetIndex
	 * @return ArrayList<Map> where the key is the field names.
	 * Assumes first row contains field names
	 */
	public ArrayList<Map<String, Object>> getMappedValues(int sheetIndex) {
		ArrayList<String> colNames = null;
		ArrayList<Map<String, Object>> mapArray = null;
		HSSFRow row = null;
		HSSFSheet sheet = null;
		int sheetRows = 0;
		int rowCols  = 0;
		Map<String, Object> rowMap = null;

		sheet = this.workbook.getSheetAt(sheetIndex);
		sheetRows = sheet.getPhysicalNumberOfRows();
		// do not initialise size of array as there may be blank rows
		//mapArray  = new ArrayList<Map<String, Object>>(sheetRows - 1);
		mapArray  = new ArrayList<Map<String, Object>>();

		colNames  = getColNames(sheetIndex);

		colNames.trimToSize();

		rowCols = colNames.size();

		for (int i = 1; i < sheetRows; i++) {
			row    = sheet.getRow(i);
			// do not process blank row
			if (row != null) {
				rowMap = new HashMap<String, Object>(rowCols);
				for (int c = 0; c < rowCols; c++) {
					rowMap.put(colNames.get(c), getCellValue(row.getCell((short) c),colNames.get(c)));
				}
				mapArray.add(rowMap);
			}
		}
		return mapArray;
	}

	public ArrayList<Map<String, Object>> getMappedValues(String sheetName){
	   return getMappedValues(this.workbook.getSheetIndex(sheetName));
	}
	
	/*@SuppressWarnings("rawtypes")
	public static FileOutputStream createExcelFile(File testFile, List <String> headings,
			List<List> rows){

		//	create a new file
		FileOutputStream out = null;

		try {
			out = new FileOutputStream(testFile);

			//	create a new workbook
			HSSFWorkbook wb = new HSSFWorkbook();
			CreationHelper createHelper = wb.getCreationHelper();
			
			//	create a new sheet
			Sheet s = wb.createSheet();

			//  declare a row object reference
			Row r = null;

			//	 declare a cell object reference
			Cell c = null;

			//	create 3 cell styles
			CellStyle cs = wb.createCellStyle();
			CellStyle cs2 = wb.createCellStyle();
			CellStyle cs3 = wb.createCellStyle();
			DataFormat df = wb.createDataFormat();
			
			//	create 2 fonts objects
			Font f = wb.createFont();
			Font f2 = wb.createFont();

			//	set font 1 to 12 point type
			f.setFontHeightInPoints((short) 10);

			//	make it blue
			// IT 4.11 - commented out next line
			//f.setColor(IndexedColors.BLUE.getIndex());

			// make it bold. Arial is the default font
			// IT 4.11 - commented out next line
			//f.setBoldweight(Font.BOLDWEIGHT_BOLD);

			//	set font 2 to 10 point type
			f2.setFontHeightInPoints((short) 12);

			//	make it red
			f2.setColor(IndexedColors.RED.getIndex());

			//	make it bold
			f2.setBoldweight(Font.BOLDWEIGHT_BOLD);

			// set cell style
			cs.setFont(f);

			//set the cell format to text see HSSFDataFormat for a full list
			cs.setDataFormat(df.getFormat("general"));

			// set a thin border
			cs2.setBorderBottom(CellStyle.BORDER_THIN);

			// fill CellStyle colour
			cs2.setFillPattern((short) CellStyle.SOLID_FOREGROUND);

			// set the cell format to text see HSSFDataFormat for a full list
			cs2.setDataFormat(df.getFormat("text"));

			// set the font
			cs2.setFont(f2);

			//set the sheet name in Uni_code			
			wb.setSheetName(0, "Result List");

			int rownum = 0;

			// create a row
			r = s.createRow(rownum);

			// create 10 cells (0-9) (the += 2 becomes apparent later
			for (int cellnum =  0; cellnum < headings.size(); cellnum ++){
				
				// create a numeric cell
				c = r.createCell(cellnum);
				
				// set this cell to the first cell style we defined
				c.setCellStyle(cs2);
				
				c.setCellValue(createHelper.createRichTextString(headings.get(cellnum)));
			}

			// create a sheet with 30 rows (0-29)
			for (rownum = (short) 1; rownum < rows.size()+1; rownum++) {
				// create a row
				r = s.createRow(rownum);	
				int index = rownum - 1;
				List columns =  (ArrayList)rows.get(index);
				
				for (int x = 0; x < columns.size(); x++) {
					
					// create a numeric cell
					c = r.createCell(x);
					// set this cell to the first cell style we defined
					c.setCellStyle(cs);
					c.setCellValue(createHelper.createRichTextString((String)columns.get(x)));
					
					//make this column a bit wider
					//s.setColumnWidth((short) x, (short)colValue.length() );
					s.setColumnWidth(x, (short) ((40 * 8) / ((double) 1 / 20)));
				}	
			}
			
			if(rows.isEmpty()){
				
				// create a row
				r = s.createRow(rownum);					
				
				//create a numeric cell
				c = r.createCell(0);

				// set this cell to the first cell style we defined
				c.setCellStyle(cs);
				RichTextString colValue = createHelper.createRichTextString("No record found");
				c.setCellValue(colValue);
				
				//make this column a bit wider
				s.setColumnWidth(0, colValue.length() );
			}

			//draw a thick black border on the row at the bottom using BLANKS
			// advance 2 rows
			rownum++;
			rownum++;
			r = s.createRow(rownum);

			// define the third style to be the default
			// except with a thick black border at the bottom
			cs3.setBorderBottom(CellStyle.BORDER_THICK);

			// create 50 cells
			for (int cellnum =  0; cellnum < 50; cellnum++) {
				//create a blank type cell (no value)
				c = r.createCell(cellnum);
				// set it to the thick black border style
				c.setCellStyle(cs3);
			}

			// end draw thick black border

			// write the workbook to the output stream
			// close our file (don't blow out our file handles
			wb.write(out);
			out.close();
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return out;
	}*/

}
