package ze.delivery.partner.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;
import ze.delivery.partner.PartnerApplication;
import ze.delivery.partner.controller.request.PartnerRequest;
import ze.delivery.partner.domain.SavePartner;
import ze.delivery.partner.domain.SearchPartner;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PartnerController.class)
@Testcontainers
class PartnerControllerTests {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private SavePartner savePartner;

	@MockBean
	private SearchPartner searchPartner;

	// will be shared between test methods
	@Container
	private static final MongoDBContainer MONGO_DB_CONTAINER = new MongoDBContainer(DockerImageName.parse("mongo:4.0.10"));

	@BeforeEach
	void init () {
		List<String> list = new ArrayList<>();
		list.add("MONGO_INITDB_ROOT_USERNAME=admin");
		list.add("MONGO_INITDB_ROOT_PASSWORD=zedb");
		list.add("MONGO_INITDB_DATABASE=partner-db");
		MONGO_DB_CONTAINER.withExposedPorts(27017);
		MONGO_DB_CONTAINER.addEnv("MONGO_INITDB_ROOT_USERNAME", "admin");
		MONGO_DB_CONTAINER.addEnv("MONGO_INITDB_ROOT_PASSWORD", "zedb");
		MONGO_DB_CONTAINER.addEnv("MONGO_INITDB_DATABASE", "partner-db");
		MONGO_DB_CONTAINER.start();
	}
	@Test
	public void givenAPartner_whenPostPartner_thenReturnStatus201() throws Exception {


		String json = """
				{
				  "id": "1",\s
				  "tradingName": "Adega da Cerveja - Pinheiros",
				  "ownerName": "Zé da Silva",
				  "document": "1432132123891/0001",
				  "coverageArea": {\s
				    "type": "MultiPolygon",\s
				    "coordinates": [
				      [[[30, 20], [45, 40], [10, 40], [30, 20]]],\s
				      [[[15, 5], [40, 10], [10, 20], [5, 10], [15, 5]]]
				    ]
				  },
				  "address": {\s
				    "type": "Point",
				    "coordinates": [-46.57421, -21.785741]
				  }
				}
				""";

		// ObjectMapper instantiation
		ObjectMapper objectMapper = new ObjectMapper();

		// Deserialization into the `Employee` class
		PartnerRequest partnerRequest = objectMapper.readValue(json, PartnerRequest.class);
		mvc.perform(post("/partner").content(json)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());
	}

}
