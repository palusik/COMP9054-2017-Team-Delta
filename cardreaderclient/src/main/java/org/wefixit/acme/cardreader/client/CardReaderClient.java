package org.wefixit.acme.cardreader.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.wefixit.acme.cardreader.model.Location;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class CardReaderClient
{
    private static final String BASE_URL = "http://uuidlocator.cfapps.io/api";
    private static final String FORWARD_SLASH = "/";
    private static final String PANELS = "panels";
    private static final String MEDIA_TYPE = "application/json";
    private final Client mClient;

    public CardReaderClient()
    {
        mClient = Client.create();
    }

    public final ClientResponse handleGetCardReadersRequest()
    {
        final WebResource resource = mClient.resource(BASE_URL + FORWARD_SLASH + PANELS);
        return doGetOn(resource, MEDIA_TYPE);
    }

    public final ClientResponse handleGetCardReaderLocationRequest(String cardReaderId)
    {
        final WebResource resource = mClient.resource(BASE_URL + FORWARD_SLASH + PANELS + FORWARD_SLASH + cardReaderId);
        return doGetOn(resource, MEDIA_TYPE);
    }
    
    public final List<String> getListOfCardReadersIds() 
    {
        final ClientResponse cardReadersResponse = handleGetCardReadersRequest();       
        final String cardReadersResponseOutput = getStringOutput(cardReadersResponse);
        List<String> listOfCardReaders = new ArrayList<String>();
        final ObjectMapper mapper = new ObjectMapper();
        
        // Would be nice to refactor this into a common parse object method
        try
        {
            listOfCardReaders = mapper.readValue(cardReadersResponseOutput, List.class);
        }
        
        catch (JsonParseException ex)
        {
            ex.printStackTrace();
        }
        
        catch (JsonMappingException ex)
        {
            ex.printStackTrace();
        }
        
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
        
        return listOfCardReaders;
    }
    
    public final Location getCardReaderLocation(String cardReaderId) 
    {
        final ClientResponse cardReaderLocation = handleGetCardReaderLocationRequest(cardReaderId);
        Location location = null;
        final ObjectMapper mapper = new ObjectMapper();
        
        // Would be nice to refactor this into a common parse object method
        try
        {
            location = mapper.readValue(getStringOutput(cardReaderLocation), Location.class);
        }
        
        catch (JsonParseException ex)
        {
            ex.printStackTrace();
        }
        
        catch (JsonMappingException ex)
        {
            ex.printStackTrace();
        }
        
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
        
        return location;
        
    }
    
    public final List<Location> getAllCardReadersLocations() 
    {
        final List<String> cardReadersIds = getListOfCardReadersIds();
        final List<Location> listOfCardReadersLocations = new ArrayList<Location>();
        
        for (String cardReaderId : cardReadersIds) 
        {
            listOfCardReadersLocations.add(getCardReaderLocation(cardReaderId));
        }
        
        return listOfCardReadersLocations;
        
    }
    
    private ClientResponse doGetOn(WebResource resource, String mediaType)
    {
        return resource.accept(mediaType).get(ClientResponse.class);
    }

    private final String getStringOutput(ClientResponse response)
    {
        String output = null;

        if (response.getStatus() == 200)
        {
            output = response.getEntity(String.class);
        }

        else
        {
            System.err.println(response.getStatus());
        }

        return output;
    }

}
