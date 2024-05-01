package utilities;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelUtils {

   private static String path;
   private static Workbook workbook;
   private static Sheet sheet;

    /**
     * this method opens Excel files with the file name and sheet name
     * @param fileName
     * @param sheetName
     */
    public static void openExcelFile(String fileName, String sheetName){
         path =System.getProperty("user.dir")+"\\src\\test\\resources\\testdata\\TestData (1).xlsx";
        try {
            FileInputStream file = new FileInputStream(path);
            workbook = new XSSFWorkbook(file);
            sheet = workbook.getSheet((sheetName));
        } catch (FileNotFoundException e) {
            System.out.println("Path for excel file is invalid");
        } catch (IOException e) {
            System.out.println("Failed to load the file");
        }
    }


    /**
     * This method returns cell value by row and cell indexes
     * @param row
     * @param cell
     * @return
     */
    public static String getCellValue(int row, int cell){
    return sheet.getRow(row).getCell(cell).toString();
    }

    /**
     * this method setd cell value by row and cell inexes
     * @param row
     * @param cell
     * @param value
     * @throws IOException
     */
    public static void setValue(int row, int cell, String value) throws IOException {
        int numberOfRows = sheet.getPhysicalNumberOfRows();
        Row newRow;
        if(row>numberOfRows){
            newRow = sheet.createRow(row);
        }else {
            newRow = sheet.getRow(row);
        }
        int numberOfCells = newRow.getPhysicalNumberOfCells();

        Cell newCell;

        if(cell>numberOfCells){
            newCell = newRow.createCell(cell);
        }else{
            newCell = newRow.getCell(cell);
        }
        newCell.setCellValue(value);

        FileOutputStream output = null;
        try {
           output = new FileOutputStream(path);
           workbook.write(output);
        }catch (FileNotFoundException e) {
            System.out.println("Path for excel file");
        } catch (IOException e) {
            System.out.println("Failed to output excel file");
        }finally {
            assert output != null;
            output.close();
        }
    }
}
