package ze.delivery.partner.repository.entity;

public class PartnerEntity {
    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTradingName() {
        return tradingName;
    }

    public void setTradingName(String tradingName) {
        this.tradingName = tradingName;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public CoverageAreaEntity getCoverageArea() {
        return coverageArea;
    }

    public void setCoverageArea(CoverageAreaEntity coverageArea) {
        this.coverageArea = coverageArea;
    }

    public AddressEntity getAddress() {
        return address;
    }

    public void setAddress(AddressEntity address) {
        this.address = address;
    }

    private String latitude;
    private String longitude;
    public String id;
    public String tradingName;
    public String ownerName;
    public String document;
    public CoverageAreaEntity coverageArea;
    public AddressEntity address;
}
