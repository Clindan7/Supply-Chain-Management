package com.supplychain.supplychain.Helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import com.supplychain.supplychain.entity.Item;


public class CsvHelper {
    public static String TYPE = "text/csv";
  static String[] HEADERs = { "itemName", "description", "type", "userId" };

  public static boolean hasCSVFormat(MultipartFile file) {

    if (!TYPE.equals(file.getContentType())) {
      return false;
    }

    return true;
  }

  public static List<Item> csvToItems(InputStream is) {
    try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        CSVParser csvParser = new CSVParser(fileReader,
            CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

      List<Item> items = new ArrayList<Item>();

      Iterable<CSVRecord> csvRecords = csvParser.getRecords();

      for (CSVRecord csvRecord : csvRecords) {
        Item item = new Item(
              csvRecord.get("itemName"),
              csvRecord.get("description"),
              csvRecord.get("type"),
              Integer.parseInt(csvRecord.get("userId"))

            );

        items.add(item);
      }

      return items;
    } catch (IOException e) {
      throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
    }
  }
}
