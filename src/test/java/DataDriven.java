import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

public class DataDriven {

    //Identify Testcases colum by scanning  the entire 1st ror
    //once colum is identified then scan entire testcase colum to identify purchase testcase
    //after you grab purchase testcase row = pull all the data of that row and feed it into test

    public static void main(String[] args) throws IOException {

        //fileInputStream argument
        FileInputStream fis = new FileInputStream("/Users/vjaceslavsjermakovs/Desktop/Files/ExcelDrivenTable.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fis);

        int sheets = workbook.getNumberOfSheets();
        for (int i = 0; i < sheets; i++) {
            if (workbook.getSheetName(i).equalsIgnoreCase("test")) {
                XSSFSheet sheet = workbook.getSheetAt(i);

                Iterator <Row> rows = sheet.iterator(); //sheet is collection of rows
                Row firsRow = rows.next();
                Iterator <Cell > ce =  firsRow.cellIterator(); //row is collection of cells
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
            }
        }
    }
}
