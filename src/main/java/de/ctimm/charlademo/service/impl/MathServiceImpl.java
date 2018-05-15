package de.ctimm.charlademo.service.impl;

import org.springframework.stereotype.Service;
import de.ctimm.charlademo.service.MathService;

/**
 * @author Christopher Timm <christopher.timm@beskgroup.com> on 15.05.18
 */
@Service
public class MathServiceImpl
  implements MathService {

  @Override
  public Integer multiply(Integer first, Integer second) {
    return Math.multiplyExact(first, second);
  }

  @Override
  public Integer add(Integer first, Integer second){
    return Math.addExact(first, second);
  }
}
