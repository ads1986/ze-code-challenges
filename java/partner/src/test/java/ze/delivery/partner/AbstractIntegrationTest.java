package ze.delivery.partner;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;

import java.nio.file.Files;
import java.nio.file.Paths;


@SpringBootTest(
        classes = PartnerApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
        properties = { "spring.data.mongodb.uri=mongodb://admin:zedb@localhost:27017/partner_db" }
)
@ActiveProfiles("test")
public abstract class AbstractIntegrationTest {

    private static final String PATH_JSON_FILES = "src/test/resources/json/%s.json";

    static MongoDBContainer mongo = new MongoDBContainer("mongo:4.4.2");

    @DynamicPropertySource
    static void mongoProperties(DynamicPropertyRegistry registry) {
        mongo.start();
        registry.add("spring.data.mongodb.uri", mongo::getReplicaSetUrl);
    }
	protected static String readFileAsString(String fileName)throws Exception {
        String file = String.format(PATH_JSON_FILES, fileName);
        return new String(Files.readAllBytes(Paths.get(file)));
    }

}