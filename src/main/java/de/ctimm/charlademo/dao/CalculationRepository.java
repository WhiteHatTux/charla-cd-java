package de.ctimm.charlademo.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import de.ctimm.charlademo.domain.Calculation;

/**
 * @author Christopher Timm <christopher.timm@beskgroup.com> on 16.05.18
 */
public interface CalculationRepository
  extends CrudRepository<Calculation, Long> {

}
