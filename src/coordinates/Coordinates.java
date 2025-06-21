package coordinates;

public class Coordinates {
    private int longitude;
    private int latitude;
    private int height;

    public Coordinates(int p_longitude, int p_latitude, int p_height) {
        this.longitude = p_longitude;
        this.latitude = p_latitude;
        setHeight(p_height);
    }

    public int getLongitude() { return this.longitude; }
    public int getLatitude() { return this.latitude; }
    public int getHeight() { return this.height; }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }

    public void setHeight(int height) {
        if (height > 100) {
            this.height = 100;
        } else if (height < 0) {
            this.height = 0;
        } else {
            this.height = height;
        }
    }
}