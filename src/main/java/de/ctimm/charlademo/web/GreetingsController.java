package de.ctimm.charlademo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import de.ctimm.charlademo.service.GreetingService;

/**
 * @author Christopher Timm
 */
@RestController
public class GreetingsController {

  private final GreetingService greetingService;

  private String version;

  @Autowired
  public GreetingsController(GreetingService greetingService, @Value("${JAR_VERSION:default-version}") String version) {
    this.version = version;
    this.greetingService = greetingService;
  }

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String helloWorld() {
    String hostname = greetingService.calcHostName();
    return "Hola PUCESA. Este servidor tiene el hostname: " + hostname + ". Corriendo version " + version;
  }
}
