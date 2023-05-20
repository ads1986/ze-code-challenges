package ze.delivery.partner.domain.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class CoverageArea {
    private String type;
    private List<List<List<List<BigDecimal>>>> coordinates;
}
