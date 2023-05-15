package ze.delivery.partner.controller.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PartnerResponse {
    public String id;
    public String tradingName;
    public String ownerName;
    public String document;
    public CoverageAreaResponse coverageArea;
    public AddressResponse address;
}
