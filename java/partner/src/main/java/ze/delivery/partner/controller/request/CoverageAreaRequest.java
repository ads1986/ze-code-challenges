package ze.delivery.partner.controller.request;

import java.math.BigDecimal;
import java.util.List;

public class CoverageAreaRequest {
    public String type;
    public List<List<List<List<BigDecimal>>>> coordinates;
}
