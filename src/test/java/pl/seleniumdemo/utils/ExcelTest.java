package pl.seleniumdemo.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelTest {

    public static void testExcel() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("src/test/resources/testData1.xlsx");

        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        XSSFSheet sheet = workbook.getSheetAt(0);

        Row row = sheet.getRow(0);
        Cell cell = row.getCell(0);
        System.out.println(sheet.getRow(2).getCell(1));
    }

    public static void main(String[] args) throws IOException {
        testExcel();
    }
}
