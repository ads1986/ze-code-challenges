package ze.delivery.partner.repository;

import ze.delivery.partner.repository.entity.PartnerEntity;

import java.math.BigDecimal;
import java.util.List;

public interface PartnerRepository {
    PartnerEntity findById(String id);
    PartnerEntity findByLatitudeAndLongitude(BigDecimal latitude, BigDecimal longitude);

    void save(PartnerEntity entity);

    void saveAll(List<PartnerEntity> entities);

    void dropCollection(String collectionName);
}