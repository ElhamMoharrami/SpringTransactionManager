package com.generator.transactiongenerator.generator;

import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Component
public abstract class Generator<T> {

    public void writeToFile(List<T> list, int recordsPerFile, String headers, String filePath) {
        int fileNumber = 1;

        for (int i = 0; i < list.size(); i += recordsPerFile) {
            List<T> sublist = list.subList(i, Math.min(i + recordsPerFile, list.size()));
            String fileName = filePath + fileNumber + ".csv";
            writeToCsv(sublist, fileName, headers);
            fileNumber++;
        }
        System.out.println("file wrote to " + filePath);
    }

    private void writeToCsv(List<T> sublist, String fileName, String headers) {
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(headers + "\n");
            for (T record : sublist) {
                writer.write(record.toString() + "\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    abstract List<T> generate();
}
