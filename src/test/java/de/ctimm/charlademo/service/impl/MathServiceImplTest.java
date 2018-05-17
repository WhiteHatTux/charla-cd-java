package de.ctimm.charlademo.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import de.ctimm.charlademo.dao.CalculationRepository;
import de.ctimm.charlademo.domain.Calculation;
import de.ctimm.charlademo.service.MathService;

/**
 * @author Christopher Timm <christopher.timm@beskgroup.com> on 15.05.18
 */
@RunWith(MockitoJUnitRunner.class)
public class MathServiceImplTest {

  @Mock
  private CalculationRepository calculationRepository;

  private MathService mathService;

  @Test
  public void add() {
    Integer result = mathService.add(50, 5);

    assertEquals((Integer) 55, result);
  }

  @Test
  public void ensureLogAdd() {
    ArgumentCaptor<Calculation> calculationArgumentCaptor = ArgumentCaptor.forClass(Calculation.class);

    mathService.add(50, 7);
    // Ensure log is written when calling "add"
    verify(calculationRepository).save(calculationArgumentCaptor.capture());
    assertEquals("ADD", calculationArgumentCaptor.getValue().getOperation());
    assertEquals((Integer) 50, calculationArgumentCaptor.getValue().getFirst_param());
    assertEquals((Integer) 7, calculationArgumentCaptor.getValue().getSecond_param());
  }

  @Test
  public void ensureLogMultiply() {
    ArgumentCaptor<Calculation> calculationArgumentCaptor = ArgumentCaptor.forClass(Calculation.class);

    mathService.multiply(50, 7);
    // Ensure log is written when calling "add"
    verify(calculationRepository).save(calculationArgumentCaptor.capture());
    assertEquals("MULTIPLY", calculationArgumentCaptor.getValue().getOperation());
    assertEquals((Integer) 50, calculationArgumentCaptor.getValue().getFirst_param());
    assertEquals((Integer) 7, calculationArgumentCaptor.getValue().getSecond_param());
  }

  @Test
  public void multiply() {
    Integer result = mathService.multiply(5, 6);

    assertEquals((Integer) 30, result);
  }

  @Before
  public void setUp() {
    initMocks(this);
    mathService = new MathServiceImpl(calculationRepository);
  }
}