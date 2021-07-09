package com.udacity.pricing;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class PriceControllerIntegrationTest {

   @LocalServerPort
   private int port;

   @Autowired
   private TestRestTemplate restTemplate;

   /**
    * Add an additional test to check whether the application appropriately
    * generates a price for a given vehicle ID
    */
   @Test
   public void testGetprice(){
      Integer id = 1;
      String url = "http://localhost:" + port + "/prices/" + id;
      ResponseEntity<String> responseEntity =
              restTemplate.getForEntity(url, String.class);

      assertThat(responseEntity.getStatusCode(), equalTo(HttpStatus.OK));
      assertThat(responseEntity.getBody(), equalTo(HttpStatus.OK));

   }


}
