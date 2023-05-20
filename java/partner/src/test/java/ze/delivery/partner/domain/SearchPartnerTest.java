package ze.delivery.partner.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ze.delivery.partner.domain.exception.BadRequestException;
import ze.delivery.partner.domain.model.Location;
import ze.delivery.partner.domain.model.Partner;
import ze.delivery.partner.repository.PartnerRepository;
import ze.delivery.partner.repository.entity.PartnerEntity;
import ze.delivery.partner.repository.impl.PartnerRepositoryImpl;
import ze.delivery.partner.util.StringUtil;

import java.math.BigDecimal;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class SearchPartnerTest {

    private static final String PATH_JSON_FILES = "src/test/resources/json/%s.json";

    private static final String PARTNER_REQUEST_CREATED = "partnerRequest";

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private PartnerEntity entity = null;

    @Mock
    private MongoTemplate mongoTemplate;

    @Mock
    private PartnerRepository partnerRepository = new PartnerRepositoryImpl();

    @InjectMocks
    private SearchPartner searchPartner;

    @BeforeEach
    void init () throws Exception {
        String json = StringUtil.readFileAsString(PATH_JSON_FILES, PARTNER_REQUEST_CREATED);
        this.entity = objectMapper.readValue(json, PartnerEntity.class);
    }

    @Test
    void testWithOnlyLatitude(){
        Mockito.when(partnerRepository.findByLatitudeAndLongitude(Mockito.any(), Mockito.any())).thenReturn(this.entity);

        Location location = new Location();
        location.setLatitude(BigDecimal.valueOf(1));

        BadRequestException badRequestException = Assertions.assertThrows(BadRequestException.class, () -> {
            searchPartner.search(location);
        });

        Assertions.assertEquals("Field longitude is mandatory", badRequestException.getError().getMessage());
    }

    @Test
    void testWithOnlyLongitude(){
        Mockito.when(partnerRepository.findByLatitudeAndLongitude(Mockito.any(), Mockito.any())).thenReturn(this.entity);

        Location location = new Location();
        location.setLongitude(BigDecimal.valueOf(1));

        BadRequestException badRequestException = Assertions.assertThrows(BadRequestException.class, () -> {
            searchPartner.search(location);
        });

        Assertions.assertEquals("Field latitude is mandatory", badRequestException.getError().getMessage());
    }

    @Test
    void testWithNoParams(){
        Mockito.when(partnerRepository.findById(Mockito.any())).thenReturn(this.entity);

        Location location = new Location();

        BadRequestException badRequestException = Assertions.assertThrows(BadRequestException.class, () -> {
            searchPartner.search(location);
        });

        Assertions.assertEquals("Field id is mandatory", badRequestException.getError().getMessage());
    }

    @Test
    void testWithOnlyId(){
        Mockito.when(partnerRepository.findById(Mockito.any())).thenReturn(this.entity);

        Location location = new Location();
        location.setId("1");

        Partner partner = searchPartner.search(location);

        Assertions.assertTrue(partner != null);
    }

    @Test
    void testWithLatitudeAndLongitude(){
        Mockito.when(partnerRepository.findByLatitudeAndLongitude(Mockito.any(), Mockito.any())).thenReturn(this.entity);

        Location location = new Location();
        location.setLatitude(BigDecimal.valueOf(1));
        location.setLongitude(BigDecimal.valueOf(2));

        Partner partner = searchPartner.search(location);

        Assertions.assertTrue(partner != null);
    }

}