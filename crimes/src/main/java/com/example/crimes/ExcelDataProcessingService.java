package com.example.crimes;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@Service
public class ExcelDataProcessingService {
    public List<Map<String, String>> processExcelDataFromURL(String excelFileURL) {
        List<Map<String, String>> excelDataList = new ArrayList<>();
        try {
            // download the file
            HttpClient httpClient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(excelFileURL);
            HttpResponse response = httpClient.execute(httpGet);
            InputStream inputStream = response.getEntity().getContent();

            //  local file to save the downloaded content
            String localFilePath = "downloaded.xls";
            FileOutputStream fos = new FileOutputStream(localFilePath);
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }
            fos.close();

            try (InputStream xlsInputStream = new FileInputStream(localFilePath);
                 HSSFWorkbook workbook = new HSSFWorkbook(xlsInputStream)) {
                Sheet sheet = workbook.getSheetAt(0);

                for (Row row : sheet) {
                    Map<String, String> rowMap = new HashMap<>();
                    for (Cell cell : row) {

                        CellType cellType = cell.getCellType();
                        String cellValue = "";
                        switch (cellType) {
                            case STRING:
                                cellValue = cell.getStringCellValue();
                                break;
                            case NUMERIC:
                                cellValue = Double.toString(cell.getNumericCellValue());
                                break;

                        }
                        rowMap.put(sheet.getRow(0).getCell(cell.getColumnIndex()).getStringCellValue(), cellValue);
                    }
                    excelDataList.add(rowMap);
                }
            }
        } catch (Exception e) {

            e.printStackTrace();
        }
        return excelDataList;
    }
}
