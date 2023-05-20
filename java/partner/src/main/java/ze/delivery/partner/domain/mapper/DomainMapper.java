package ze.delivery.partner.domain.mapper;

import org.bson.types.Decimal128;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ze.delivery.partner.domain.model.Address;
import ze.delivery.partner.domain.model.CoverageArea;
import ze.delivery.partner.domain.model.Partner;
import ze.delivery.partner.repository.entity.AddressEntity;
import ze.delivery.partner.repository.entity.CoverageAreaEntity;
import ze.delivery.partner.repository.entity.PartnerEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface DomainMapper {

    Partner toPartner(PartnerEntity entity);

    @Mapping(target = "coordinates", expression = "java(toBigDecimalList(address.getCoordinates()))")
    Address toCoverage(AddressEntity address);

    @Mapping(target = "coordinates", expression = "java(toBigDecimalLists(coverageArea.getCoordinates()))")
    CoverageArea toCoverage(CoverageAreaEntity coverageArea);

    PartnerEntity toPartner(Partner partner);

    @Mapping(target = "coordinates", expression = "java(toDecimalList(address.getCoordinates()))")
    AddressEntity toCoverage(Address address);

    @Mapping(target = "coordinates", expression = "java(toDecimalLists(coverageArea.getCoordinates()))")
    CoverageAreaEntity toCoverage(CoverageArea coverageArea);

    default List<Decimal128> toDecimalList(List<BigDecimal> coordinates){
        return coordinates.stream().map(Decimal128::new).toList();
    }

    default List<List<List<List<Decimal128>>>> toDecimalLists(List<List<List<List<BigDecimal>>>> coordinates){
        List<Decimal128> decimalLists4 = coordinates.get(0).get(0).get(0)
                .stream()
                .map(Decimal128::new)
                .toList();

        List<List<Decimal128>> decimalLists3 = new ArrayList<>();
        decimalLists3.add(decimalLists4);

        List<List<List<Decimal128>>> decimalLists2 = new ArrayList<>();
        decimalLists2.add(decimalLists3);

        List<List<List<List<Decimal128>>>> decimalLists = new ArrayList<>();
        decimalLists.add(decimalLists2);

        return decimalLists;
    }

    default List<BigDecimal> toBigDecimalList(List<Decimal128> coordinates){
        return coordinates.stream().map(Decimal128::bigDecimalValue).toList();
    }

    default List<List<List<List<BigDecimal>>>> toBigDecimalLists(List<List<List<List<Decimal128>>>> coordinates){
        List<BigDecimal> decimalLists4 = coordinates.get(0).get(0).get(0)
                .stream()
                .map(Decimal128::bigDecimalValue)
                .toList();

        List<List<BigDecimal>> decimalLists3 = new ArrayList<>();
        decimalLists3.add(decimalLists4);

        List<List<List<BigDecimal>>> decimalLists2 = new ArrayList<>();
        decimalLists2.add(decimalLists3);

        List<List<List<List<BigDecimal>>>> decimalLists = new ArrayList<>();
        decimalLists.add(decimalLists2);

        return decimalLists;
    }
}
