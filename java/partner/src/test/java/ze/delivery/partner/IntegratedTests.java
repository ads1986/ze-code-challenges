package ze.delivery.partner;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import ze.delivery.partner.controller.response.PartnerResponse;
import ze.delivery.partner.repository.PartnerRepository;
import ze.delivery.partner.repository.entity.PartnerEntity;

public class IntegratedTests extends AbstractIntegrationTest {

	private static final String PARTNER_REQUEST_CREATED = "partnerRequest";
	private static final String PARTNER_RESOURCE = "/partner";
	private static final ObjectMapper objectMapper = new ObjectMapper();

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private PartnerRepository repository;

	@BeforeEach
	void init() throws Exception {
		PartnerEntity partnerEntity = objectMapper.readValue(readFileAsString(PARTNER_REQUEST_CREATED), PartnerEntity.class);
		repository.save(partnerEntity);
	}

	@Test
	public void givenAPartner_whenPostPartner_thenReturnStatus201() throws Exception {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<>(readFileAsString(PARTNER_REQUEST_CREATED), headers);
		ResponseEntity<String> response = restTemplate.postForEntity(PARTNER_RESOURCE, entity, String.class);
		Assertions.assertEquals(response.getStatusCode(), HttpStatus.CREATED);
	}

	@Test
	public void givenPartnerId_whenGetPartner_thenReturnsPartnerJson() throws Exception {
		ResponseEntity<String> response = restTemplate.getForEntity(PARTNER_RESOURCE + "?id=1", String.class);
		Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);

		PartnerResponse partnerResponse = objectMapper.readValue(response.getBody(), PartnerResponse.class);
		Assertions.assertNotNull(partnerResponse.getId());
	}

}
