package c7082.davisp.photoapp7082.data;

public class LocationBounds {

    /**
     * Minimal latitude
     */
    private double minLatitude;

    /**
     * max latitude
     */
    private double maxLatitude;

    /**
     * Minimal longitude
     */
    private double minLongitude;

    /**
     * Max longitude
     */
    private double maxLongitude;

    /**
     * Constructor for a location bounds
     * @param minLatitude
     * @param maxLatitude
     * @param minLongitude
     * @param maxLongitude
     */
    public LocationBounds(double minLatitude, double maxLatitude, double minLongitude, double maxLongitude) {
        this.minLatitude = minLatitude;
        this.maxLatitude = maxLatitude;
        this.minLongitude = minLongitude;
        this.maxLongitude = maxLongitude;
    }

    /**
     * Checks if the given lat/long is within the bounds
     * @param latitude
     * @param longitude
     * @return
     */
    public boolean contains(double latitude, double longitude) {
        if (latitude < minLatitude || latitude > maxLatitude) {
            return false;
        }

        if (longitude < minLongitude || longitude > maxLongitude) {
            return false;
        }

        return true;
    }
}