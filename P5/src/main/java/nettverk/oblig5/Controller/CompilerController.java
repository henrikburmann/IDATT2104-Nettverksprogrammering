package nettverk.oblig5.Controller;

import org.apache.tomcat.jni.Directory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Map;

@RestController
public class CompilerController {

        @PostMapping("/compile")
        @CrossOrigin
        public ResponseEntity<Object> compile(@RequestBody Map<String, String> data) throws IOException, InterruptedException {

            if (!data.containsKey("code")) {
                throw new IllegalArgumentException();
            }

            String code = data.get("code");

            // File temp = new File("P5/src/main/temp");
            Path dir = Files.createTempDirectory("P5/docker/temp");
            System.out.println("Dir: " + dir.toString());
            // boolean isWritten = temp.mkdir();

            // System.out.println("Laget directory: " + isWritten);

            FileWriter writer = new FileWriter("P5/docker/temp/eksempel.cpp");
            writer.write(code);
            writer.flush();

            Runtime.getRuntime().exec("docker rmi cpp-compiler").waitFor();

            Process process = Runtime.getRuntime().exec("docker build ./P5/ -t cpp-compiler");

            String buildError = Arrays.toString(process.getErrorStream().readAllBytes());

            // temp.deleteOnExit();

            Process run = Runtime.getRuntime().exec("docker run --rm cpp-compiler");

            String runError = Arrays.toString(run.getErrorStream().readAllBytes());

            return ResponseEntity.status(HttpStatus.OK).body(makeMap("output", buildError + "\nProgram output:\n" + run));
        }

        private Map<String, String> makeMap(String key, String content) {
            return Map.of(key, content);
        }
}
