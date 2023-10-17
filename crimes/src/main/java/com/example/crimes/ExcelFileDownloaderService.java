package com.example.crimes;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.NotOLE2FileException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class ExcelFileDownloaderService {

    public List<String> extractExcelLinks(String url) throws IOException {
        List<String> excelLinks = new ArrayList<>();
        Document doc = Jsoup.connect(url).get();
        Elements links = doc.select("a[href$=.xls]"); // Select links ending with .xls

        for (Element link : links) {
            excelLinks.add(link.attr("abs:href")); // Get the absolute URL
        }

        return excelLinks;
    }

    public List<Map<String, String>> downloadAndProcessExcelFiles(List<String> excelLinks) {
        List<Map<String, String>> excelDataList = new ArrayList<>();

        for (String excelLink : excelLinks) {
            try {
                // Download the data from the Excel file
                byte[] excelData = Jsoup.connect(excelLink).ignoreContentType(true).execute().bodyAsBytes();

                try {
                    HSSFWorkbook workbook = new HSSFWorkbook(new ByteArrayInputStream(excelData));
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
                } catch (NotOLE2FileException e) {
                    // Handle invalid Excel format
                    System.err.println("Skipping file: " + excelLink + " - Not a valid Excel file.");
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return excelDataList;
    }
}
