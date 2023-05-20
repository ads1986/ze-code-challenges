package ze.delivery.partner.repository.entity;

import lombok.Data;
import org.bson.types.Decimal128;

import java.util.List;

@Data
public class AddressEntity {
    private String type;
    private List<Decimal128> coordinates;
}
