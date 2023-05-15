package ze.delivery.partner;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;


@SpringBootTest(
        classes = PartnerApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
        properties = { "spring.data.mongodb.uri=mongodb://admin:zedb@localhost:27017/partner_db" }
)
@ActiveProfiles("test")
public abstract class AbstractIntegrationTest {

    static MongoDBContainer mongo = new MongoDBContainer("mongo:4.4.2");


    @DynamicPropertySource
    static void mongoProperties(DynamicPropertyRegistry registry) {
        mongo.start();
        registry.add("spring.data.mongodb.uri", mongo::getReplicaSetUrl);
    }
}
