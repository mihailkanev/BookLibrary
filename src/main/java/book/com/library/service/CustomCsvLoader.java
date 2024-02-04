package book.com.library.service;

import book.com.library.data.CustomCsvReader;
import jakarta.annotation.PostConstruct;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Set;
@Data
@Component
public class CustomCsvLoader {

    private final CustomCsvReader customCsvReader;

    @Value("${csv.file.path}")
    private String csvFilePath;

    private final Set<Long> existingBookIds;


    @PostConstruct
    public void loadCsvData() {
        customCsvReader.readCsv(csvFilePath, existingBookIds);
    }
}
