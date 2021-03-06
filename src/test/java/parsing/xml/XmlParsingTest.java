package parsing.xml;

import static utils.DomHandler.countTagOccurrences;
import static utils.DomHandler.getTagTextByTagName;
import static utils.DomHandler.getTagTextByXpath;
import static utils.DomHandler.parseDocument;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class XmlParsingTest {
	private Document dom = null;
	final Logger logger = LoggerFactory.getLogger(XmlParsingTest.class);

	@BeforeMethod
	public void createDom() throws ParserConfigurationException, SAXException, IOException {
		dom = parseDocument("src\\test\\resources\\document.xml");
		Assert.assertNotNull(dom, "The document was not parsed successfully: ");
	}

	@Test
	public void checkCoutryTag() {
		String tagName = "country";
		String expectedText = "HONG_KONG";
		String tagText = getTagTextByTagName(dom, tagName);
		Assert.assertEquals(tagText, expectedText,
				"The text of '" + tagName + "' tag isn't equal to " + expectedText + ": ");
	}

	@Test
	public void checkParagrapthTitle() throws XPathExpressionException {
		String expectedText = "Section";
		String titleText = getTagTextByXpath(dom, "//paragraph[@id='593ASECT1']/title");
		Assert.assertTrue(titleText.contains(expectedText),
				"Paragraph title's text doesn't contain '" + expectedText + "': ");
	}

	@Test
	public void checkHeadingTagNumber() {
		String tagName = "heading";
		int numOfOccurr = countTagOccurrences(dom, tagName);
		Assert.assertTrue(numOfOccurr > 1, "Tag '" + tagName + "' is absent in the document.: ");
		logger.info("Number of tag 'heading' occurrences: " + numOfOccurr);
	}
}
