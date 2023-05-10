package ze.delivery.partner.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import ze.delivery.partner.domain.exception.BadRequestException;
import ze.delivery.partner.domain.model.Location;
import ze.delivery.partner.domain.model.Partner;
import ze.delivery.partner.repository.PartnerRepository;
import ze.delivery.partner.repository.entity.PartnerEntity;

import java.util.Optional;

@SpringBootTest
public class SearchPartnerTest {

    @Mock
    private PartnerRepository partnerRepository;

    @InjectMocks
    private SearchPartner searchPartner;

    @Test
    void testWithOnlyLatitude(){
        PartnerEntity entity = new PartnerEntity();

        Mockito.when(partnerRepository.findByLatitudeAndLongitude(Mockito.any(), Mockito.any())).thenReturn(Optional.of(entity));

        Location location = new Location();
        location.setLatitude("1");

        BadRequestException badRequestException = Assertions.assertThrows(BadRequestException.class, () -> {
            searchPartner.search(location);
        });

        Assertions.assertEquals("Field longitude is mandatory", badRequestException.getError().getMessage());
    }

    @Test
    void testWithOnlyLongitude(){
        PartnerEntity entity = new PartnerEntity();

        Mockito.when(partnerRepository.findByLatitudeAndLongitude(Mockito.any(), Mockito.any())).thenReturn(Optional.of(entity));

        Location location = new Location();
        location.setLongitude("1");

        BadRequestException badRequestException = Assertions.assertThrows(BadRequestException.class, () -> {
            searchPartner.search(location);
        });

        Assertions.assertEquals("Field latitude is mandatory", badRequestException.getError().getMessage());
    }

    @Test
    void testWithNoParams(){
        PartnerEntity entity = new PartnerEntity();

        Mockito.when(partnerRepository.findById(Mockito.any())).thenReturn(Optional.of(entity));

        Location location = new Location();

        BadRequestException badRequestException = Assertions.assertThrows(BadRequestException.class, () -> {
            searchPartner.search(location);
        });

        Assertions.assertEquals("Field id is mandatory", badRequestException.getError().getMessage());
    }

    @Test
    void testWithOnlyId(){
        PartnerEntity entity = new PartnerEntity();

        Mockito.when(partnerRepository.findById(Mockito.any())).thenReturn(Optional.of(entity));

        Location location = new Location();
        location.setId("1");

        Partner partner = searchPartner.search(location);

        Assertions.assertTrue(partner != null);
    }

    @Test
    void testWithLatitudeAndLongitude(){
        PartnerEntity entity = new PartnerEntity();

        Mockito.when(partnerRepository.findByLatitudeAndLongitude(Mockito.any(), Mockito.any())).thenReturn(Optional.of(entity));

        Location location = new Location();
        location.setLatitude("1");
        location.setLongitude("2");

        Partner partner = searchPartner.search(location);

        Assertions.assertTrue(partner != null);
    }

}