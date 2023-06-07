import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class DataDriven {

    //Identify Testcases colum by scanning  the entire 1st ror
    //once colum is identified then scan entire testcase colum to identify purchase testcase
    //after you grab purchase testcase row = pull all the data of that row and feed it into test

    public ArrayList<String> getData(String testcaseName) throws IOException {
        //fileInputStream argument
        ArrayList<String> a = new ArrayList<String>();

        FileInputStream fis = new FileInputStream("/Users/vjaceslavsjermakovs/Desktop/Files/ExcelDrivenTable.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fis);

        int sheets = workbook.getNumberOfSheets();
        for (int i = 0; i < sheets; i++) {
            if (workbook.getSheetName(i).equalsIgnoreCase("test")) {
                XSSFSheet sheet = workbook.getSheetAt(i);

                Iterator<Row> rows = sheet.iterator(); //sheet is collection of rows
                Row firsRow = rows.next();
                Iterator<Cell> ce = firsRow.cellIterator(); //row is collection of cells
                int k = 0;
                int colum = 0;
                while (ce.hasNext()) {
                    Cell value = ce.next();
                    if (value.getStringCellValue().equalsIgnoreCase("TestCases")) {
                        colum = k;
                    }
                    k++;
                }
                System.out.println(colum);

                //once colum is identified then scan entire testcase colum to identify purchase testcase

                while (rows.hasNext()) {
                    Row r = rows.next();
                    if (r.getCell(colum).getStringCellValue().equalsIgnoreCase(testcaseName)) {

                        //after you grab purchase testcase row = pull all the data of that row and feed it into test
                        Iterator <Cell> cv = r.cellIterator();
                        while (cv.hasNext()) {
                            Cell c = cv.next();
                            if (c.getCellType() == CellType.STRING) {
                                a.add(c.getStringCellValue());
                            } else {
                                a.add(NumberToTextConverter.toText(c.getNumericCellValue()));
                            }
                        }
                    }
                }
            }
        }
        return a;
    }

    public static void main(String[] args) throws IOException {

    }
}
