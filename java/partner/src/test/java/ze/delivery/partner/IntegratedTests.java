package ze.delivery.partner;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import ze.delivery.partner.controller.response.PartnerResponse;

import java.nio.file.Files;
import java.nio.file.Paths;

public class IntegratedTests extends AbstractIntegrationTest {

	private static final String PATH_JSON_FILES = "src/test/resources/json/%s.json";

	private static final String PARTNER_REQUEST_CREATED = "partnerRequest";

	private static final String PARTNER_RESOURCE = "/partner";

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void givenAPartner_whenPostPartner_thenReturnStatus201() throws Exception {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<>(readFileAsString(PARTNER_REQUEST_CREATED), headers);
		ResponseEntity<String> response = restTemplate.postForEntity(PARTNER_RESOURCE , entity, String.class);
		Assertions.assertEquals(response.getStatusCode(), HttpStatus.CREATED);

		ResponseEntity<String> response2
				= restTemplate.getForEntity(PARTNER_RESOURCE + "?id=1", String.class, headers);
		Assertions.assertEquals(response2.getStatusCode(), HttpStatus.OK);

		ObjectMapper mapper = new ObjectMapper();
		PartnerResponse partnerResponse = mapper.readValue(response2.getBody(), PartnerResponse.class);
		Assertions.assertNotNull(partnerResponse.getId());
	}



	/*@Test
	public void givenPartnerId_whenGetPartner_thenReturnsPartnerJson() throws Exception {

		/*mvc.perform(post("/partner")
						.content(readFileAsString(PARTNER_REQUEST_CREATED))
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());

		mvc.perform(get("/partner?id=1")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
				//.andExpect(jsonPath("$", hasSize(1)))
				//.andExpect(jsonPath("$[0].name", is(alex.getName())));
	}*/

	private static String readFileAsString(String fileName)throws Exception {
		String file = String.format(PATH_JSON_FILES, fileName);
		return new String(Files.readAllBytes(Paths.get(file)));
	}

}
