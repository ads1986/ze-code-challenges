package ze.delivery.partner.repository.impl;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Indexes;
import com.mongodb.client.model.geojson.Point;
import com.mongodb.client.model.geojson.Position;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import ze.delivery.partner.repository.PartnerRepository;
import ze.delivery.partner.repository.entity.PartnerEntity;

import java.math.BigDecimal;
import java.util.List;

@Repository
public class PartnerRepositoryImpl implements PartnerRepository {

    @Autowired
    private MongoTemplate template;

    @Override
    public PartnerEntity findById(String id) {
        return template.findById(id, PartnerEntity.class);
    }

    @Override
    public PartnerEntity findByLatitudeAndLongitude(BigDecimal latitude, BigDecimal longitude) {
        CodecRegistry pojoCodecRegistry = org.bson.codecs.configuration.CodecRegistries
                .fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), org.bson.codecs.configuration.CodecRegistries
                        .fromProviders(PojoCodecProvider.builder().automatic(true).build()));

        MongoCollection<Document> collection = template.getCollection("partners").withCodecRegistry(pojoCodecRegistry);

        collection.createIndex(Indexes.geo2dsphere("coverageArea"));

        Point currentLoc = new Point(new Position(latitude.doubleValue(), longitude.doubleValue()));

        FindIterable<PartnerEntity> result = collection.find(
                Filters.geoIntersects("coverageArea", currentLoc), PartnerEntity.class);

        PartnerEntity partnerEntity = result.first();

        return partnerEntity;
    }

    @Override
    public void save(PartnerEntity entity) {
        template.save(entity);
    }

    @Override
    public void dropCollection(String collectionName) {
        template.dropCollection(collectionName);
    }

    @Override
    public void saveAll(List<PartnerEntity> entities) {
        template.insertAll(entities);
    }

}