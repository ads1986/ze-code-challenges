package ze.delivery.partner.controller;

import jakarta.validation.Valid;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ze.delivery.partner.controller.mapper.ControllerMapper;
import ze.delivery.partner.controller.request.LocationRequest;
import ze.delivery.partner.controller.request.PartnerRequest;
import ze.delivery.partner.controller.response.PartnerResponse;
import ze.delivery.partner.domain.SavePartner;
import ze.delivery.partner.domain.SearchPartner;
import ze.delivery.partner.domain.model.Location;
import ze.delivery.partner.domain.model.Partner;

@RestController
public class PartnerController {
    private final ControllerMapper mapper = Mappers.getMapper(ControllerMapper.class);

    @Autowired
    private SearchPartner searchPartner;

    @Autowired
    private SavePartner savePartner;

    @PostMapping("/partner")
    @ResponseStatus(HttpStatus.CREATED)
    private void save( @Valid @RequestBody PartnerRequest request){
        Partner partner = mapper.toPartner(request);
        savePartner.save(partner);
    }

    @GetMapping("/partner")
    @ResponseBody
    private PartnerResponse get(LocationRequest request){
        Location location = mapper.toLocation(request);
        Partner partner = searchPartner.search(location);
        return mapper.toPartnerResponse(partner);
    }

}
