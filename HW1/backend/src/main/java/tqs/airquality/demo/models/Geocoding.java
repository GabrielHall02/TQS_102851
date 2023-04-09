package tqs.airquality.demo.models;


public class Geocoding {
    private String name;
    private double lat;
    private double lon;
    private String country;

    public Geocoding() {
    }

    public Geocoding(String name, double lat, double lon, String country) {
        this.name = name;
        this.lat = lat;
        this.lon = lon;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}

