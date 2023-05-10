package ze.delivery.partner.controller.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PartnerRequest {
    @NotBlank(message = "Id is mandatory")
    public String id;
    public String tradingName;
    public String ownerName;
    public String document;
    public CoverageAreaRequest coverageArea;
    public AddressRequest address;
}