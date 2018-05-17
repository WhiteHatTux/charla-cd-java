package de.ctimm.charlademo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import de.ctimm.charlademo.dao.CalculationRepository;
import de.ctimm.charlademo.domain.Calculation;
import de.ctimm.charlademo.service.MathService;

/**
 * @author Christopher Timm <christopher.timm@beskgroup.com> on 15.05.18
 */
@Service
public class MathServiceImpl
  implements MathService {

  private final CalculationRepository calculationRepository;

  @Autowired
  public MathServiceImpl(CalculationRepository calculationRepository) {this.calculationRepository = calculationRepository;}

  @Override
  public Integer add(Integer first, Integer second) {
    logUsage("ADD", first, second);
    return Math.addExact(first, second);
  }

  @Override
  public Long countCalculations() {
    return calculationRepository.count();
  }

  private void logUsage(String method, Integer first, Integer second) {
    Calculation calculation = new Calculation();
    calculation.setOperation(method);
    calculation.setFirst_param(first);
    calculation.setSecond_param(second);
    calculationRepository.save(calculation);
  }

  @Override
  public Integer multiply(Integer first, Integer second) {
    logUsage("MULTIPLY", first, second);
    return Math.multiplyExact(first, second);
  }
}
