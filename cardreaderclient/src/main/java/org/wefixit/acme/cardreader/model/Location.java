package org.wefixit.acme.cardreader.model;

/**
 * A POJO for Location. 
 */
public final class Location
{
    private String mAltitude;
    private String mRelativeLocation;
    private Coordinates mCoordinates;

    public String getAltitude()
    {
        return mAltitude;
    }

    public void setAltitude(String altitude)
    {
        mAltitude = altitude;
    }

    public String getRelativeLocation()
    {
        return mRelativeLocation;
    }

    public void setRelativeLocation(String relativeLocation)
    {
        mRelativeLocation = relativeLocation;
    }

    public Coordinates getCoordinates()
    {
        return mCoordinates;
    }

    public void setCoordinates(Coordinates coordinates)
    {
        mCoordinates = coordinates;
    }

    @Override
    public String toString()
    {
        return "[altitude=" + mAltitude + ", relativeLocation=" + mRelativeLocation + ", coordinates=" + mCoordinates.toString() + "]";
    }
}
