package org.wefixit.acme.cardreader.client;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.wefixit.acme.cardreader.model.Location;

public class CardReaderClientTest
{
    private final CardReaderClient mCardReaderClient = new CardReaderClient();
    private static final String EXAMPLE_PANEL_ID = "ad9611b0-c759-4714-872e-2ed55e70ec80";
    private static final String EXAMPLE_PANEL_ID_ALTITUDE = "150.0";
    private static final String EXAMPLE_PANEL_ID_RELATIVE_LOCATION = "UCL, Institute of Archaeology, South Entrance, London, UK";
    private static final String EXAMPLE_PANEL_ID_LONGITUDE = "51.524774";
    private static final String EXAMPLE_PANEL_ID_LATITUDE = "-0.131913";

    @Test
    public final void getListOfCardReadersIdsTest() 
    {
        final List<String> listOfCardReadersIds = mCardReaderClient.getListOfCardReadersIds();
        
        for (String cardReaderId : listOfCardReadersIds) 
        {
            System.out.println("CardReaderId = " + cardReaderId);
        }
        
        Assert.assertTrue(listOfCardReadersIds.size() > 0);
    }
    
    @Test
    public final void getCardReaderLocationTest() 
    {
        final Location location = mCardReaderClient.getCardReaderLocation(EXAMPLE_PANEL_ID);
        System.out.println(location.toString());
        assertEquals(EXAMPLE_PANEL_ID_ALTITUDE, location.getAltitude());
        assertEquals(EXAMPLE_PANEL_ID_RELATIVE_LOCATION, location.getRelativeLocation());
        assertEquals(EXAMPLE_PANEL_ID_LONGITUDE, location.getCoordinates().getLongitude());
        assertEquals(EXAMPLE_PANEL_ID_LATITUDE, location.getCoordinates().getLatitude());
    }
    
    @Test
    public final void getAllCardReadersLocationsTest() 
    {
        final List<Location> allPanelLocations = mCardReaderClient.getAllCardReadersLocations();
        
        for (Location location : allPanelLocations) 
        {
            System.out.println("Location = " + location.toString());
        }
        
        Assert.assertTrue(allPanelLocations.size() > 0);
    }
    
}
