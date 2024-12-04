/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.redhat.fceresol.files;

import com.redhat.fceresol.threescale.api.services.Service;
import io.quarkus.logging.Log;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

/**
 *
 * @author fceresol
 */
public class CSVHandler {
    
    String[] HEADERS = {"ID", "Service Name"};
    private File csvFile;

    public CSVHandler(File outputCSV) {
        this.csvFile = outputCSV;
    }

    public void writeOutput(HashMap<Integer, Service> services) throws IOException {
        Log.info("start Writing CSV to " + this.csvFile.getPath());
        FileWriter writer = new FileWriter(this.csvFile);
        CSVFormat csvFormat = CSVFormat.DEFAULT.withHeader(HEADERS);

        CSVPrinter printer = new CSVPrinter(writer, csvFormat);

        for (Integer key : services.keySet()) {
            printer.printRecord(key.toString(),services.get(key).getName());

        }
        printer.close();
        Log.info("Successfully written " + services.size() + " entries to " + this.csvFile.getPath());

    }

    public Map<Integer, String> readCSV() throws IOException {
        
        
        HashMap<Integer, String> ret = new HashMap<>();
        CSVFormat csvFormat = CSVFormat.DEFAULT.withHeader(HEADERS)
                .withSkipHeaderRecord(true);

        FileReader reader = new FileReader(csvFile);
        Log.info("start Reading CSV from " + this.csvFile.getPath());
        Iterable<CSVRecord> records = csvFormat.parse(reader);

        for (CSVRecord record : records) {
            Integer serviceID = Integer.valueOf(record.get("ID"));
            String serviceName = record.get("Service Name");
            ret.put(serviceID, serviceName);
        }
        Log.info("Successfully read " + ret.size() + " entries from " + this.csvFile.getPath());
        return ret;
    }

}
