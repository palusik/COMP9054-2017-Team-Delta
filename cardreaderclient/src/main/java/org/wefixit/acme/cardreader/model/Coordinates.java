package org.wefixit.acme.cardreader.model;

/**
 * A POJO for Coordinates. 
 */
public final class Coordinates
{
    private String mLongitude;
    private String mLatitude;

    public String getLongitude()
    {
        return mLongitude;
    }

    public void setLongitude(String longitude)
    {
        mLongitude = longitude;
    }
    
    public String getLatitude()
    {
        return mLatitude;
    }

    public void setLatitude(String latitude)
    {
        mLatitude = latitude;
    }

    @Override
    public String toString()
    {
        return "[longitude=" + mLongitude + ", latitude=" + mLatitude + "]";
    }
}
