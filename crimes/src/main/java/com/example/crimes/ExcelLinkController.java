package com.example.crimes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ExcelLinkController {
    @Autowired
    private ExcelFileDownloaderService excelFileDownloaderService;
    @Autowired
    private DataExportService jsonExporter;
    @Autowired
    private DataExportService dataExportService; // Inject the JSON export service

    @Autowired
    private DataPopulationService dataPopulationService; // Inject the data population service

    @GetMapping(value = "/process-excel-data", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Map<String, String>> downloadAndProcessExcelData() throws IOException {
        // url with links
        String url = "https://www.cpp.edu/police/daily-crime-and-fire-log.shtml";

        //get.xls links from the URL
        List<String> excelLinks = excelFileDownloaderService.extractExcelLinks(url);


        // Download and process .xls files
        List<Map<String, String>> jsonDataList = excelFileDownloaderService.downloadAndProcessExcelFiles(excelLinks);


        // Export JSON data to a file
        String jsonOutputPath = "exported_data.json";
        dataExportService.exportDataToJson(url, jsonOutputPath);


         dataPopulationService.clearDatabase();

        // Populate MongoDB with the exported JSON data
         dataPopulationService.populateData("exported_data.json");


        return jsonDataList;


    }
}

