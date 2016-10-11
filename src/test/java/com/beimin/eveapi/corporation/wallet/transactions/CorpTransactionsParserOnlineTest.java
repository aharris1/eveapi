package com.beimin.eveapi.corporation.wallet.transactions;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.beimin.eveapi.AbstractOnlineTest;
import com.beimin.eveapi.parser.corporation.CorpWalletTransactionsParser;
import com.beimin.eveapi.response.shared.WalletTransactionsResponse;

public class CorpTransactionsParserOnlineTest extends AbstractOnlineTest {
    private CorpWalletTransactionsParser classToTest = new CorpWalletTransactionsParser();

    @Before
    public void prepare() {
        before();
        prepareParser(classToTest);
    }

    @Test @Ignore("No data returned by the API")
    public void getResponse() throws Exception {
        final WalletTransactionsResponse response = classToTest.getResponse(getCorp());
        testResponse(response);
    }

    @Test @Ignore("No data returned by the API")
    public void getResponseKey() throws Exception {
        final WalletTransactionsResponse response = classToTest.getResponse(getCorp(), 1000);
        testResponse(response);
    }

    @Test @Ignore("No data returned by the API")
    public void getResponseWalking() throws Exception {
        testFail("Transactions walking not tested");
        final WalletTransactionsResponse response = classToTest.getResponse(getCorp(), 0, 0, 0);
        testResponse(response);
    }

}
