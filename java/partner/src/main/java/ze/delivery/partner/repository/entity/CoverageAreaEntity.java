package ze.delivery.partner.repository.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class CoverageAreaEntity {
    private String type;
    private List<List<List<List<BigDecimal>>>> coordinates;
}
