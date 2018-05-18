package de.ctimm.charlademo.web;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Christopher Timm <christopher.timm@beskgroup.com> on 15.05.18
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GreetingsControllerTest {


  static {
    System.setProperty("JAR_VERSION", "test-jar-version");
  }

  @Autowired
  private TestRestTemplate restTemplate;


  @Test
  public void helloWorld() {
      ResponseEntity<String> result = restTemplate.getForEntity("/", String.class);

      assertTrue(result.getBody().startsWith("Hello World"));

  }
}