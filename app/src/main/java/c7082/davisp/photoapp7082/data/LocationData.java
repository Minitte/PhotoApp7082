package c7082.davisp.photoapp7082.data;

import java.io.Serializable;

public class LocationData  implements Serializable {

    /**
     * longitude value
     */
    private String longitude;

    /**
     * latitude value
     */
    private String latitude;

    /**
     * Constructor taking a long and lat
     * @param longitude
     * @param latitude
     */
    public LocationData(String longitude, String latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    /**
     * Gets the value of longitude
     *
     * @return a java.lang.String
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     * Sets the longitude
     *
     * @param longitude set longitude to this value
     */
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    /**
     * Gets the value of latitude
     *
     * @return a java.lang.String
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     * Sets the latitude
     *
     * @param latitude set latitude to this value
     */
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return latitude + ", " + longitude;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof  LocationData)) {
            return false;
        }

        LocationData other = (LocationData) obj;

        return longitude == other.longitude && latitude == other.latitude;
    }
}

