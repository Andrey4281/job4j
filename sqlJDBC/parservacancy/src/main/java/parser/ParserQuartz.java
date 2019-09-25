package parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Class represent a task for Quartz
 * @author asemenov
 * @since 25.09.2019
 * @version 1
 */
public class ParserQuartz implements Job {
    public static final Logger LOG = LogManager.getLogger(ParserQuartz.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDataMap dataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        Connection connection = null;
        try {
            Class.forName(dataMap.getString("jdbc.driver"));
            connection = DriverManager.getConnection(dataMap.getString("jdbc.url"),
                    dataMap.getString("jdbc.username"),
                    dataMap.getString("jdbc.password")
            );
        } catch (ClassNotFoundException e) {
            LOG.error("Driver for database it not loaded", e);
        } catch (SQLException e) {
            LOG.error("Error of connection to datebase", e);
        }

        try (DataBaseService dataBaseService = new DataBaseServiceImplPostgres(connection)) {
            dataBaseService.init();
            Document document = Jsoup.connect(dataMap.getString("site.url")).get();
            ParserOfSite parserOfSite = new ParserOfSite(document);
            dataBaseService.addVacancies(parserOfSite.parse(dataBaseService.getTimeLimitForParse()));
        } catch (IOException e) {
            LOG.error("Error of connection to site", e);
        } catch (Exception e) {
            LOG.error("Error working database", e);
        }
    }
}
