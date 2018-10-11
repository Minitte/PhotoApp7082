package c7082.davisp.photoapp7082.data;

import java.io.Serializable;

public class LocationData  implements Serializable {

    /**
     * longitude value
     */
    private double longitude;

    /**
     * latitude value
     */
    private double latitude;

    /**
     * Constructor taking a long and lat
     * @param longitude
     * @param latitude
     */
    public LocationData(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    /**
     * Gets the value of longitude
     *
     * @return a double
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Sets the longitude
     *
     * @param longitude set longitude to this value
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     * Gets the value of latitude
     *
     * @return a double
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Sets the latitude
     *
     * @param latitude set latitude to this value
     */
    public void setLatitude(double latitude) {
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

