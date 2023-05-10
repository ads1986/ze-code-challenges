package ze.delivery.partner.domain;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ze.delivery.partner.domain.mapper.DomainMapper;
import ze.delivery.partner.domain.model.Partner;
import ze.delivery.partner.repository.PartnerRepository;
import ze.delivery.partner.repository.entity.PartnerEntity;

@Component
public class SavePartner {

    private final DomainMapper mapper = Mappers.getMapper(DomainMapper.class);

    @Autowired
    private PartnerRepository repository;

    public void save(Partner partner){




        PartnerEntity entity = mapper.toPartner(partner);
        repository.save(entity);
    }

}
