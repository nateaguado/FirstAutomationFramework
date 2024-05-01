package utilities;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelTest {
    public static void main(String[] args) throws IOException {
        String path =System.getProperty("user.dir")+"\\src\\test\\resources\\testdata\\TestData (1).xlsx";

//        try {
//            FileInputStream file = new FileInputStream(path);
//            Workbook workbook= new XSSFWorkbook(file);
//            Sheet sheet1 = workbook.getSheet("Sheet1");
//            System.out.println(sheet1.getRow(1).getCell(0).toString());
//
//            sheet1.createRow(4).createCell(0).setCellValue("Naveen");
//            sheet1.getRow(4).createCell(1).setCellValue("Automation");
//            sheet1.getRow(4).createCell(2).setCellValue("naveen@gmail.com");
//            sheet1.getRow(4).createCell(3).setCellValue("123 youtube St");
//
//            FileOutputStream output = new FileOutputStream(path);
//            workbook.write(output);
//        } catch (FileNotFoundException e) {
//            System.out.println("Path for excel file is invaild or the file is missing");
//
//        } catch (IOException e) {
//            System.out.println("Failed to load the file");
//        }

        ExcelUtils.openExcelFile("TestData(1)","Sheet1");
        System.out.println(ExcelUtils.getCellValue(4, 0));
        System.out.println(ExcelUtils.getCellValue(4, 1));
        ExcelUtils.setValue(4, 1, "Patel");
    }
}
