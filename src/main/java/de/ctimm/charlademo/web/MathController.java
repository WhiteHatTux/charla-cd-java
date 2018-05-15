package de.ctimm.charlademo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import de.ctimm.charlademo.service.MathService;

/**
 * @author Christopher Timm <christopher.timm@beskgroup.com> on 15.05.18
 */
@RestController
@RequestMapping("/math")
public class MathController {

  private final MathService mathService;

  @Autowired
  public MathController(MathService mathService) {this.mathService = mathService;}

  @RequestMapping(value = "/add", method = RequestMethod.GET)
  public Integer add(@RequestParam Integer first, @RequestParam Integer second) {
    return mathService.add(first, second);
  }

  @RequestMapping(value = "/multiply", method = RequestMethod.GET)
  public Integer multiply(@RequestParam Integer first, @RequestParam Integer second) {
    return mathService.multiply(first, second);
  }
}
