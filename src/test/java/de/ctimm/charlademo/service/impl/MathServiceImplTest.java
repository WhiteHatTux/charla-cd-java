package de.ctimm.charlademo.service.impl;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import de.ctimm.charlademo.service.MathService;

/**
 * @author Christopher Timm <christopher.timm@beskgroup.com> on 15.05.18
 */
public class MathServiceImplTest {

  private MathService mathService = new MathServiceImpl();

  @Test
  public void add() {
    Integer result = mathService.add(50, 5);

    assertEquals((Integer) 55, result);
  }

  @Test
  public void multiply() {
    Integer result = mathService.multiply(5, 6);

    assertEquals((Integer) 30, result);
  }
}