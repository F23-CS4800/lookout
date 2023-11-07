package com.example.crimes;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
@Service
public class DataExportService {
    private final ExcelFileDownloaderService excelFileDownloaderService;
    public DataExportService(ExcelFileDownloaderService excelFileDownloaderService) {
        this.excelFileDownloaderService = excelFileDownloaderService;
    }
    public void exportDataToJson(String url, String outputFilePath) throws IOException {
        List<String> excelLinks = excelFileDownloaderService.extractExcelLinks(url);
        List<Map<String, String>> excelDataList = excelFileDownloaderService.downloadAndProcessExcelFiles(excelLinks);

        // Reformat the data into a list of maps (objects)
        List<Map<String, String>> reformattedDataList = reformatData(excelDataList);
        // Export the reformatted data to a JSON file
        try (FileWriter writer = new FileWriter(outputFilePath)) {
            mapListToJsonFile(reformattedDataList, writer);
        }
    }
    private List<Map<String, String>> reformatData(List<Map<String, String>> originalData) {
        List<Map<String, String>> reformattedDataList = new ArrayList<>();
        for (Map<String, String> row : originalData) {
            // Convert each row into a map (object)
            Map<String, String> reformattedData = new LinkedHashMap<>();
            for (Map.Entry<String, String> entry : row.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                reformattedData.put(key, value);
            }
            reformattedDataList.add(reformattedData);
        }
        return reformattedDataList;
    }
    private void mapListToJsonFile(List<Map<String, String>> dataList, FileWriter writer) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(writer, dataList);
    }
}
