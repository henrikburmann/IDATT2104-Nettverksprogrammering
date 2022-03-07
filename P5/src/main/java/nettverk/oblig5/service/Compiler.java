package nettverk.oblig5.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

@Service
public class Compiler {

    public ResponseEntity<Object> compiler () throws Exception {

        return null;
    }

    public String compile(String code) throws IOException{
        File myFile = new File("P5/src/main/temp/code.cpp");
        if (myFile.createNewFile()) {
            System.out.println("New file is created");
        } else {
            System.err.println("Henrik why are you running?");
        }

        BufferedWriter br = new BufferedWriter(new FileWriter(myFile));

        br.write(code);
        br.flush();

        // Runtime.getRuntime().exec("docker rmi cpp-compiler");
        Process process = Runtime.getRuntime().exec("docker build ./P5/ -t cpp-compiler");
        String buildError = Arrays.toString(process.getErrorStream().readAllBytes());

        Process run = Runtime.getRuntime().exec("docker run --rm cpp-compiler");

        String runError = Arrays.toString(run.getErrorStream().readAllBytes());
        String runOutput = Arrays.toString(run.getInputStream().readAllBytes());

        if (!runError.isBlank()) {
            return buildError + "\n" + runError;
        }

        return runError + "\n" + runOutput;
    }
}