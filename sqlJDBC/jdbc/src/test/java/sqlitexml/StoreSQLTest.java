package sqlitexml;

import org.junit.Test;

import java.io.File;
import java.util.List;

public class StoreSQLTest {
    @Test
    public void checkApplication() {
        Config config = new Config("sqlite.properties");
        config.init();
        try (StoreSQL sql = new StoreSQL(config)) {
            sql.init();
            sql.generate(1000000);
            List<Entry> load = sql.load();
            StoreXML storeXML = new StoreXML(new File("Entries.xml"));
            storeXML.save(load);
            ConvertXSQT convertXSQT = new ConvertXSQT();
            convertXSQT.convert(new File("Entries.xml"), new File("newEntries.xml"), new File("Entries.xsl"));
            SAXParsStarter saxParsStarter = new SAXParsStarter();
            saxParsStarter.start(new File("newEntries.xml"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}