package sqlitexml;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;

public class SAXParsStarter {
    public static final Logger LOG = LogManager.getLogger(SAXPars.class);

    private static class SAXPars extends DefaultHandler {
        private long sumOfEnties = 0;

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            if (qName.equals("entry")) {
                sumOfEnties += Integer.parseInt(attributes.getValue(0));
            }
        }

        public long getSumOfEnties() {
            return sumOfEnties;
        }
    }

    public void start(File input) {
        long res = 0;
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            SAXPars saxp = new SAXPars();
            parser.parse(input, saxp);
            res = saxp.getSumOfEnties();
        } catch (Exception e) {
            LOG.error("Error of parsing", e);
        }
        System.out.printf("Summ of field %d%n", res);
    }
}
