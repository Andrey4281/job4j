package sqlitexml;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;

public class ConvertXSQT {
    public static final Logger LOG = LogManager.getLogger(ConvertXSQT.class);

    public void convert(File source, File dest, File scheme) {

        try {
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer(new StreamSource(scheme));
            transformer.transform(new StreamSource(source), new StreamResult(dest));
        } catch (Exception e) {
            LOG.error("Error converting to another format of xml", e);
        }
    }
}
