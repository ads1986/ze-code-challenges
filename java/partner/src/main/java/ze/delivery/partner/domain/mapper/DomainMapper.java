package ze.delivery.partner.domain.mapper;

import org.mapstruct.Mapper;
import ze.delivery.partner.domain.model.Partner;
import ze.delivery.partner.repository.entity.PartnerEntity;

@Mapper(componentModel = "spring")
public interface DomainMapper {
    Partner toPartner(PartnerEntity entity);

    PartnerEntity toPartner(Partner partner);
}
