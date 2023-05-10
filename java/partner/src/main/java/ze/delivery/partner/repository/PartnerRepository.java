package ze.delivery.partner.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ze.delivery.partner.repository.entity.PartnerEntity;

import java.util.Optional;

public interface PartnerRepository extends MongoRepository<PartnerEntity, String> {
    Optional<PartnerEntity> findByLatitudeAndLongitude(String latitude, String longitude);
}