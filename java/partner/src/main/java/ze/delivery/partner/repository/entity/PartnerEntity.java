package ze.delivery.partner.repository.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class PartnerEntity {

    @Id
    private String id;
    private String tradingName;
    private String ownerName;
    private String document;
    private CoverageAreaEntity coverageArea;
    private AddressEntity address;
}
