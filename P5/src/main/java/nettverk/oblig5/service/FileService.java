package nettverk.oblig5.service;

import java.io.*;

public class FileService {

    public void writeToFile(File file, String code) throws FileNotFoundException {
        String[] lineSep = code.split("\"\\r?\\n|\\r");

        for (String line: lineSep) {
            System.out.println(line);
        }

        try (OutputStream os = new FileOutputStream(file);
             PrintWriter pw = new PrintWriter(new OutputStreamWriter(os))) {

            for (int i = 0; i < lineSep.length; i++) {
                pw.write(lineSep[i] + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
