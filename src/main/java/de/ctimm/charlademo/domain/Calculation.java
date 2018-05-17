package de.ctimm.charlademo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

/**
 * @author Christopher Timm <christopher.timm@beskgroup.com> on 16.05.18
 */
@Entity(name = "calculation")
@Data
public class Calculation {

  private Integer first_param;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @JsonIgnore
  private Long id;

  private String operation;

  private Integer second_param;
}
