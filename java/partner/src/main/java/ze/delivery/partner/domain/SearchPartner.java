package ze.delivery.partner.domain;

import org.apache.commons.lang3.StringUtils;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ze.delivery.partner.domain.exception.BadRequestException;
import ze.delivery.partner.domain.mapper.DomainMapper;
import ze.delivery.partner.domain.model.Location;
import ze.delivery.partner.domain.model.Partner;
import ze.delivery.partner.repository.PartnerRepository;
import ze.delivery.partner.repository.entity.PartnerEntity;

import java.util.Optional;

import static ze.delivery.partner.domain.exception.DomainException.MANDATORY_FIELD;

@Component
public class SearchPartner {
    private final DomainMapper mapper = Mappers.getMapper(DomainMapper.class);

    @Autowired
    private PartnerRepository repository;

    public Partner search(Location location) {

        Optional<PartnerEntity> entity = null;

        try {

            if (StringUtils.isNoneBlank(location.getLatitude()) && StringUtils.isNoneBlank(location.getLongitude())) {

                //entity = repository.findByLatitudeAndLongitude(location.getLatitude(), location.getLongitude());

                if (entity.isPresent()) {
                    //return mapper.toPartner(entity.get());
                }
            } else {

                if (StringUtils.isBlank(location.getLatitude()) && StringUtils.isNotBlank(location.getLongitude())) {
                    throw new BadRequestException(MANDATORY_FIELD, "latitude");
                }

                if (StringUtils.isBlank(location.getLongitude()) && StringUtils.isNotBlank(location.getLatitude())) {
                    throw new BadRequestException(MANDATORY_FIELD, "longitude");
                }

                if (StringUtils.isNoneBlank(location.getId())) {

                    entity = repository.findById(location.getId());

                    if (entity.isPresent()) {
                        return mapper.toPartner(entity.get());
                    }
                } else {
                    throw new BadRequestException(MANDATORY_FIELD, "id");
                }
            }

        } catch (BadRequestException e) {
            throw e;
        }

        return null;
    }
}
