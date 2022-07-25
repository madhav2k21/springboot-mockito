package com.techleads.app.integration;

import com.techleads.app.model.Item;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.List;
//@TestPropertySource(locations = {"classpath:/test-configuration.properties", "classpath:/data.sql", "classpath:/schema.sql"})
//@SqlGroup({
////        @Sql(scripts = "/test-schema.sql",
//                config = @SqlConfig(transactionMode = SqlConfig.TransactionMode.ISOLATED)),
//        @Sql("/test-data.sql")})
//@Sql("/test-data.sql")
//@TestPropertySource(locations = {"classpath: test-configuration.properties"})
//@DataJpaTest
//@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2, replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ItemControllerIT {
    @Autowired
    private TestRestTemplate testRestTemplate;
    @Test
    void contextLoads() throws JSONException {
        // Set headers
        HttpHeaders requestHeaders = new HttpHeaders();

        // request entity is created with request headers
        HttpEntity<?> requestEntity = new HttpEntity<>(requestHeaders);

        ResponseEntity<List<Item>> responseEntity = testRestTemplate.exchange("/items", HttpMethod.GET, requestEntity,
                new ParameterizedTypeReference<List<Item>>() {
                });

        String reponse = testRestTemplate.getForObject("/items", String.class);
        JSONAssert.assertEquals("[{id:101},{id:102},{id:103},{id:104},{id:105}]",reponse, false);
//        JSONAssert.assertEquals("[{id:101},{id:102},{id:103},{id:104},{id:105}]",responseEntity.getBody().toString(),false);

    }
}
