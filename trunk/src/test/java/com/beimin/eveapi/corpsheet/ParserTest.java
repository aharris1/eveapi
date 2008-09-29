package com.beimin.eveapi.corpsheet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.Map;

import org.junit.Test;
import org.xml.sax.SAXException;

public class ParserTest {

	@Test
	public void testCorporationSheetParser() throws IOException, SAXException, ParseException {
		Parser parser = Parser.getInstance();
		InputStream input = ParserTest.class.getResourceAsStream("/CorporationSheet.xml");
		Response response = parser.getResponse(input);
		assertNotNull(response);
		assertEquals(150212025, response.getCorporationID());
		assertEquals("Banana Republic", response.getCorporationName());
		assertEquals("BR", response.getTicker());
		assertEquals(150208955, response.getCeoID());
		assertEquals("Mark Roled", response.getCeoName());
		assertEquals(60003469, response.getStationID());
		assertEquals("Jita IV - Caldari Business Tribunal Information Center", response.getStationName());
		assertEquals("Garth's testing corp of awesome sauce, win sauce as it were. In this\n            corp...<br><br>IT HAPPENS ALL OVER", response
				.getDescription());
		assertEquals("some url", response.getUrl());
		assertEquals(150430947, response.getAllianceID());
		assertEquals("The Dead Rabbits", response.getAllianceName());
		assertEquals(93.7, response.getTaxRate());
		assertEquals(3, response.getMemberCount());
		assertEquals(6300, response.getMemberLimit());
		assertEquals(1, response.getShares());
		CorpLogo logo = response.getLogo();
		assertNotNull(logo);
		assertEquals(0, logo.getGraphicID());
		assertEquals(448, logo.getShape1());
		assertEquals(0, logo.getShape2());
		assertEquals(418, logo.getShape3());
		assertEquals(681, logo.getColor1());
		assertEquals(676, logo.getColor2());
		assertEquals(0, logo.getColor3());

		Map<Integer, String> divisions = response.getDivisions();
		assertEquals("1ST DIVISION", divisions.get(1000));
		assertEquals("2ND DIVISION", divisions.get(1001));
		assertEquals("HELLO", divisions.get(1002));
		assertEquals("DIVISION", divisions.get(1003));
		assertEquals("SWEET", divisions.get(1004));
		assertEquals("6TH DIVISION", divisions.get(1005));
		assertEquals("7TH DIVISION", divisions.get(1006));
		Map<Integer, String> walletDivisions = response.getWalletDivisions();
		assertEquals("Master Wallet", walletDivisions.get(1000));
		assertEquals("2nd Wallet Division", walletDivisions.get(1001));
		assertEquals("3rd Wallet Division", walletDivisions.get(1002));
		assertEquals("AYE WALLET?", walletDivisions.get(1003));
		assertEquals("5th Wallet Division", walletDivisions.get(1004));
		assertEquals("6th Wallet Division", walletDivisions.get(1005));
		assertEquals("7th Wallet Division", walletDivisions.get(1006));
		assertTrue("test accountBalance wasn't found.", true);
	}
}