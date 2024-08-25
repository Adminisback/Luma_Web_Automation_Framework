import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.NumberToTextConverter;
public class Demo1Excell {

	/*
	 * STEPS: Identify Testcases column by scanning entire 1st row; Once column is
	 * identify then identify entire testcase column to identify purchase testcase
	 * row After you grab all the purchase testcase row Pull all the data of the row
	 * and feel it into the test.
	 */
	public static void main(String[] args) throws IOException {
		Demo1Excell d = new Demo1Excell();
		List<String> a = new ArrayList<String>();
		a.addAll(d.getData("Purchase"));
		a.addAll(d.getData("Material"));
		a.addAll(d.getData("DataEngineering"));
		a.addAll(d.getData("Store"));
		
		System.out.println(a);
		/*
		 * System.out.println(getData("Material").get(1));
		 * System.out.println(getData("Material").get(2));
		 * System.out.println(getData("Material").get(3));
		 * System.out.println(getData("Material").get(4));
		 */
		
		}

	public static List<String> getData(String testCaseName) throws IOException {
		List<String> a = new ArrayList<String>();
		//It accepts FileInpute Strame argument
				FileInputStream excelFpath = new FileInputStream("C://Users//Shree//Desktop//DemoExcelTesting.xlsx");
				XSSFWorkbook xsswb = new XSSFWorkbook(excelFpath);
				int numberOfSheets = xsswb.getNumberOfSheets();
				
				for(int i = 0; i<numberOfSheets; i++) {
					if(xsswb.getSheetName(i).equalsIgnoreCase("TestData")) {
						 XSSFSheet sheet = xsswb.getSheetAt(i);
						 // STEPS: Identify Testcases column by scanning entire 1st row; Once column is
						 
							Iterator<Row> rows = sheet.iterator();  //Sheet is a collection of rows..
						 Row firstRow = rows.next();  //First row of sheet..
						 Iterator<Cell> cells = firstRow.iterator();     //Getting cells(Row is a collection of cells)
						int k=0;
						int column=0;
						 while(cells.hasNext()) {
							 Cell value = cells.next();
							 if(value.getStringCellValue().equals(testCaseName)) {
								 column=k;
							 }
							 k++;
						 }
						// System.out.println(column);
		//identify then identify entire testcase column to identify purchase testcase
						while(rows.hasNext()) {
							Row r = rows.next();
							
							if(r.getCell(column).getStringCellValue().equalsIgnoreCase(testCaseName)) {
								Iterator<Cell> cv = r.cellIterator();
								while(cv.hasNext()) {
									Cell c = cv.next();
									if(c.getCellType()== CellType.STRING) {
										
										a.add(c.getStringCellValue()) ;
									}else {
										a.add(NumberToTextConverter.toText(c.getNumericCellValue()));
									}
								}
							}
						}
						 
					}
				}
				
				return a;
				
	}
}
