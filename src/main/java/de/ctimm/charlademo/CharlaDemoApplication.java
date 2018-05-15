package de.ctimm.charlademo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class CharlaDemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(CharlaDemoApplication.class, args);
  }
}
