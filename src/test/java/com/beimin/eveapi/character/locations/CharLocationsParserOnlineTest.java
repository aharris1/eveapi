package com.beimin.eveapi.character.locations;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import com.beimin.eveapi.AbstractOnlineTest;
import com.beimin.eveapi.model.shared.Asset;
import com.beimin.eveapi.parser.pilot.CharLocationsParser;
import com.beimin.eveapi.parser.pilot.CharAssetListParser;
import com.beimin.eveapi.response.shared.AssetListResponse;
import com.beimin.eveapi.response.shared.LocationsResponse;

public class CharLocationsParserOnlineTest extends AbstractOnlineTest {

    private List<Long> getItemIDs() throws Exception {
        final CharAssetListParser parser = new CharAssetListParser();
        final AssetListResponse response = parser.getResponse(getPilot());
        testResponse(response);
        final List<Asset> assets = response.getAll();
        final Set<Long> itemIDs = new HashSet<Long>();
        deepAssets(assets, itemIDs);
        return new ArrayList<Long>(itemIDs);
    }

    private void deepAssets(final List<Asset> assets, final Set<Long> itemIDs) {
        for (final Asset asset : assets) {
            itemIDs.add(asset.getItemID());
            deepAssets(asset.getAssets(), itemIDs);
        }
    }

    @Test
    public void getResponse() throws Exception {
        final CharLocationsParser parser = new CharLocationsParser();
        final LocationsResponse response = parser.getResponse(getPilot(), getItemIDs());
        testResponse(response);
    }

}