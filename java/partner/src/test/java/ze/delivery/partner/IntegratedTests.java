package ze.delivery.partner;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.*;
import ze.delivery.partner.controller.response.PartnerResponse;
import ze.delivery.partner.repository.PartnerRepository;
import ze.delivery.partner.repository.entity.PartnerEntity;
import ze.delivery.partner.util.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IntegratedTests extends AbstractIntegrationTest {

	private static final String PATH_JSON_FILES = "src/test/resources/json/%s.json";

	private static final String PARTNER_REQUEST_CREATED = "partnerRequest";
	private static final String PDVS_JSON = "pdvs";
	private static final String PARTNER_RESOURCE = "/partner";
	private static final ObjectMapper objectMapper = new ObjectMapper();

	private List<PartnerEntity> partners = new ArrayList<>();

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private PartnerRepository repository;

	@Autowired
	private MongoTemplate template;

	@BeforeEach
	void init() throws Exception {
		partners = Arrays.asList(objectMapper.readValue(StringUtil.readFileAsString(PATH_JSON_FILES, PDVS_JSON), PartnerEntity[].class));
		repository.dropCollection("partners");
		repository.saveAll(partners);
	}

	@Test
	public void givenAPartner_whenPostPartner_thenReturnStatus201() throws Exception {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<>(StringUtil.readFileAsString(PATH_JSON_FILES, PARTNER_REQUEST_CREATED), headers);
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

	@Test
	public void givenPartnerLatitudeAndLongitude_whenGetPartner_thenReturnsPartnerJson() throws Exception {
		ResponseEntity<String> response = restTemplate.getForEntity(PARTNER_RESOURCE + "?latitude=-38.60625211788909&longitude=-3.773533510613699", String.class);
		Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);

		PartnerResponse partnerResponse = objectMapper.readValue(response.getBody(), PartnerResponse.class);
		Assertions.assertNotNull(partnerResponse.getId());
	}

}