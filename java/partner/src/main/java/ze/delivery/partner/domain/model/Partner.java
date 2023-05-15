package ze.delivery.partner.domain.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Partner {
    public String id;
    public String tradingName;
    public String ownerName;
    public String document;
    public CoverageArea coverageArea;
    public Address address;
}