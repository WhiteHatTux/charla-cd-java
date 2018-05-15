package de.ctimm.charlademo.web;

import org.springframework.beans.factory.annotation.Autowired;
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

  @Autowired
  public GreetingsController(GreetingService greetingService) {
    this.greetingService = greetingService;
  }

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String helloWorld() {
    String hostname = greetingService.calcHostName();
    return "Hello World. Este servidor tiene el hostname: " + hostname;
  }
}
