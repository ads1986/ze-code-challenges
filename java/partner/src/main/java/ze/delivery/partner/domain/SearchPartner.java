package ze.delivery.partner.domain;

import org.apache.commons.lang3.StringUtils;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ze.delivery.partner.domain.exception.BadRequestException;
import ze.delivery.partner.domain.exception.NotFoundException;
import ze.delivery.partner.domain.mapper.DomainMapper;
import ze.delivery.partner.domain.model.Location;
import ze.delivery.partner.domain.model.Partner;
import ze.delivery.partner.repository.PartnerRepository;
import ze.delivery.partner.repository.entity.PartnerEntity;

import static ze.delivery.partner.domain.exception.BadRequestException.MANDATORY_FIELD;
import static ze.delivery.partner.domain.exception.NotFoundException.NOT_FOUND_ENTITY;

@Component
public class SearchPartner {
    private final DomainMapper mapper = Mappers.getMapper(DomainMapper.class);

    @Autowired
    private PartnerRepository repository;

    public Partner search(Location location) {

        Partner partner = findById(location);

        if (partner == null)
            return findByLatitudeAndLongitude(location);

        return partner;
    }

    private Partner findById(Location location){
        if (StringUtils.isNoneBlank(location.getId())) {
            PartnerEntity entity = repository.findById(location.getId());

            if (entity != null) {
                return mapper.toPartner(entity);
            }

            throw new NotFoundException(NOT_FOUND_ENTITY, "Partner");
        }

        return null;
    }

    private Partner findByLatitudeAndLongitude(Location location) {
        if (location.getLatitude() == null && location.getLongitude() != null) {
            throw new BadRequestException(MANDATORY_FIELD, "latitude");
        }

        if (location.getLongitude() == null && location.getLatitude() != null) {
            throw new BadRequestException(MANDATORY_FIELD, "longitude");
        }

        if (location.getLongitude() == null && location.getLatitude() == null) {
            throw new BadRequestException(MANDATORY_FIELD, "id");
        }

        PartnerEntity entity = repository.findByLatitudeAndLongitude(location.getLatitude(), location.getLongitude());

        if (entity != null) {
            return mapper.toPartner(entity);
        }

        throw new NotFoundException(NOT_FOUND_ENTITY, "Partner");
    }

}