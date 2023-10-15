package com.example.crimes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/excel")
public class ExcelController {
    @Autowired
    private ExcelDataProcessingService excelService;

    @GetMapping("/process-excel")
    public ResponseEntity<List<Map<String, String>>> processExcelFromURL(@RequestParam String excelFileURL) {
        List<Map<String, String>> excelData = excelService.processExcelDataFromURL(excelFileURL);
        return new ResponseEntity<>(excelData, HttpStatus.OK);
    }
}
