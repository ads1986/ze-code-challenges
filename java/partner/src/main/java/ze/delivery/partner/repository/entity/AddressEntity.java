package ze.delivery.partner.repository.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class AddressEntity {
    private String type;
    private List<BigDecimal> coordinates;
}
