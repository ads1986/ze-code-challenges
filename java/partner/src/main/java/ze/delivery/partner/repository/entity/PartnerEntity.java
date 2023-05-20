package ze.delivery.partner.repository.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("partners")
public class PartnerEntity {
    @Id
    private String id;
    private String tradingName;
    private String ownerName;
    private String document;
    private CoverageAreaEntity coverageArea;
    private AddressEntity address;
}
