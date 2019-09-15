package sqlitexml;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.*;
import java.io.File;
import java.util.List;

public class StoreXML {
    private static final Logger LOG = LogManager.getLogger(StoreXML.class);

    @XmlRootElement
    private static final class Entries {
        private List<Entry> entries;

        public Entries(final List<Entry> entries) {
            this.entries = entries;
        }

        public Entries() {

        }


        public void setEntries(final  List<Entry> entries) {
            this.entries = entries;
        }
        @XmlElement(name = "entry")
        public  List<Entry> getEntries() {
            return this.entries;
        }
    }

    private File target;

    public StoreXML(File target) {
        this.target = target;
    }

    public void save(List<Entry> entries) {
        Entries entriesForSave = new Entries(entries);
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Entries.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.marshal(entriesForSave, target);
        } catch (JAXBException e) {
            LOG.error("Error saving XML file", e);
        }
    }


}
