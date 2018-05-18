package de.ctimm.charlademo.web;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Christopher Timm <christopher.timm@beskgroup.com> on 16.05.18
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MathControllerITest {

  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  public void add() {
    String url = "/math/add?first=50&second=8";
    Integer result = restTemplate.getForObject(url, Integer.class);
    assertEquals((Integer) 58, result);
  }
}