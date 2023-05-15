package ze.delivery.partner.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ze.delivery.partner.repository.entity.PartnerEntity;

public interface PartnerRepository extends MongoRepository<PartnerEntity, String> {}