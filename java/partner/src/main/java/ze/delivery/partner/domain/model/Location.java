package ze.delivery.partner.domain.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Location {
    private String id;
    private BigDecimal latitude;
    private BigDecimal longitude;
}
