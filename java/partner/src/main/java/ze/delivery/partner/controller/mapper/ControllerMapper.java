package ze.delivery.partner.controller.mapper;

import org.mapstruct.Mapper;
import ze.delivery.partner.controller.config.ResponseError;
import ze.delivery.partner.controller.request.LocationRequest;
import ze.delivery.partner.controller.request.PartnerRequest;
import ze.delivery.partner.controller.response.PartnerResponse;
import ze.delivery.partner.domain.exception.Error;
import ze.delivery.partner.domain.model.Location;
import ze.delivery.partner.domain.model.Partner;

@Mapper(componentModel = "spring")
public interface ControllerMapper {
    Location toLocation(LocationRequest request);
    PartnerResponse toPartnerResponse(Partner partner);
    ResponseError toResponseError(Error error);
    Partner toPartner(PartnerRequest request);
}
