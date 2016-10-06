package com.beimin.eveapi.handler.corporation;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import com.beimin.eveapi.handler.AbstractContentHandler;
import com.beimin.eveapi.model.corporation.CorpLogo;
import com.beimin.eveapi.model.corporation.Division;
import com.beimin.eveapi.response.corporation.CorpSheetResponse;

public class CorpSheetHandler extends AbstractContentHandler {
    private CorpSheetResponse response;
    private CorpLogo logo;
    private boolean divisions;
    private boolean walletDivisions;

    @Override
    public void startDocument() throws SAXException {
        response = new CorpSheetResponse();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attrs) throws SAXException {
        if ("logo".equals(qName)) {
            logo = new CorpLogo();
        } else if (ELEMENT_ROWSET.equals(qName)) {
            String name = getString(attrs, "name");
            divisions = name.equals("divisions");
            walletDivisions = name.equals("walletDivisions");
        } else if (ELEMENT_ROW.equals(qName)) {
            Division division = new Division();
            division.setAccountKey(getInt(attrs, "accountKey"));
            division.setDescription(getString(attrs, "description"));
            if (divisions)
                response.addDivision(division);
            else if (walletDivisions)
                response.addWalletDivision(division);
        }
        super.startElement(uri, localName, qName, attrs);
        accumulator.setLength(0);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if ("corporationID".equals(qName)) {
            response.setCorporationID(getLong());
        } else if ("corporationName".equals(qName)) {
            response.setCorporationName(getString());
        } else if ("ticker".equals(qName)) {
            response.setTicker(getString());
        } else if ("ceoID".equals(qName)) {
            response.setCeoID(getLong());
        } else if ("ceoName".equals(qName)) {
            response.setCeoName(getString());
        } else if ("stationID".equals(qName)) {
            response.setStationID(getLong());
        } else if ("stationName".equals(qName)) {
            response.setStationName(getString());
        } else if ("description".equals(qName)) {
            response.setDescription(getString());
        } else if ("url".equals(qName)) {
            response.setUrl(getString());
        } else if ("allianceID".equals(qName)) {
            response.setAllianceID(getLong());
        } else if ("allianceName".equals(qName)) {
            response.setAllianceName(getString());
        } else if ("factionID".equals(qName)) {
            response.setFactionID(getLong());
        } else if ("taxRate".equals(qName)) {
            response.setTaxRate(getDouble());
        } else if ("memberCount".equals(qName)) {
            response.setMemberCount(getInt());
        } else if ("memberLimit".equals(qName)) {
            response.setMemberLimit(getInt());
        } else if ("shares".equals(qName)) {
            response.setShares(getLong());
        } else if ("graphicsID".equals(qName)) {
            logo.setGraphicID(getInt());
        } else if ("shape1".equals(qName)) {
            logo.setShape1(getInt());
        } else if ("shape2".equals(qName)) {
            logo.setShape2(getInt());
        } else if ("shape3".equals(qName)) {
            logo.setShape3(getInt());
        } else if ("color1".equals(qName)) {
            logo.setColor1(getInt());
        } else if ("color2".equals(qName)) {
            logo.setColor2(getInt());
        } else if ("color3".equals(qName)) {
            logo.setColor3(getInt());
        } else if ("logo".equals(qName)) {
            response.setLogo(logo);
        } else if (ELEMENT_ROWSET.equals(qName)) {
            if (divisions || walletDivisions) {
                divisions = false;
                walletDivisions = false;
            }
        }

        super.endElement(uri, localName, qName);
    }

    @Override
    public CorpSheetResponse getResponse() {
        return response;
    }
}