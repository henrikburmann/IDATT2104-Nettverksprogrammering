package nettverk.oblig5.Controller;

import nettverk.oblig5.model.Code;
import nettverk.oblig5.service.Compiler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Logger;

@RestController
public class CompilerController {

    @Autowired
    Compiler compiler;

    @PostMapping("/compile")
    @CrossOrigin
    public String compile(@RequestBody Map<String, String> code) throws IOException, InterruptedException {

        String compiled = compiler.compile(code);
        // System.out.println("compiled" + compiled);
        return compiled;
    }
}
