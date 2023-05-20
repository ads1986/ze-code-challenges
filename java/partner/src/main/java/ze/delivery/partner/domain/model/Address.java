package ze.delivery.partner.domain.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class Address {
    private String type;
    private List<BigDecimal> coordinates;
}
