package com.beimin.eveapi.character;

import com.beimin.eveapi.AbstractOnlineTest;
import com.beimin.eveapi.parser.ApiAuth;
import com.beimin.eveapi.parser.ApiAuthorization;

public abstract class AbstractCharacterOnlineTest extends AbstractOnlineTest {
    /*
     * Api Key thanks to Marcon
     *
     * https://github.com/ZyorTaelon/eveapi/issues/50#issuecomment-107965503
     */
    private final int keyID = 4428355;
    private final String vCode = "Efnyja8S6pawB4EzefgZBFLDWGGTv0U9RZTfC6bD3vZ1pIc45FdgOUiCL6bpEssm";
    private final long characterID = 1528592227l;

    private final ApiAuth apiAuth;

    public AbstractCharacterOnlineTest() {
        apiAuth = new ApiAuthorization(keyID, characterID, vCode);
    }

    protected ApiAuth getApiAuth() {
        return apiAuth;
    }
}