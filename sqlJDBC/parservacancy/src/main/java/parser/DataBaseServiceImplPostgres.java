package parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 *This is implementation of interface DataBaseService for database PostgreSQL
 * @author asemenov
 * @since 25.09.2019
 * @version 1
 */
public class DataBaseServiceImplPostgres implements DataBaseService {
    public static final Logger LOG = LogManager.getLogger(DataBaseServiceImplPostgres.class);
    private final Connection connection;

    public DataBaseServiceImplPostgres(final Connection connection) {
        this.connection = connection;
    }

    @Override
    public void init() {
        createTableIfNotExists();
    }

    @Override
    public void addVacancies(final List<VacancyDataSet> vacancies) {
        vacancies.stream().forEach(vacancy->insertOrUpdateVacancy(vacancy));
    }

    @Override
    public long getTimeLimitForParse() {
        long res = returnDefaultDataForParseLimit();
        try (Statement st = connection.createStatement();
             ResultSet set = st.executeQuery("SELECT MAX(time) AS time FROM vacancies;")) {
            if (set.next()) {
                res = set.getLong("time");
            }
        } catch (SQLException e) {
            LOG.error("Error of select timeLimitForParse", e);
        }
        return res;
    }

    @Override
    public void close() throws Exception {
        this.connection.close();
    }

    /**
     * This method use for testing of DataBaseServiceImplPostgres
     * @param name
     * @return
     */
    public VacancyDataSet selectVacancyByName(final String name) {
        VacancyDataSet vacancy = null;
        try (PreparedStatement st = connection.prepareStatement("SELECT * FROM vacancies WHERE name=?;")) {
            st.setString(1, name);
            try (ResultSet set = st.executeQuery()) {
                if (set.next()) {
                    vacancy = new VacancyDataSet(set.getString("name"),
                            set.getString("description"),
                            set.getString("link"),
                            set.getLong("time"));
                }
            }
        } catch (SQLException e) {
            LOG.error("Error of selecting", e);
        }
        return vacancy;
    }

    private void createTableIfNotExists() {
        try (Statement st = connection.createStatement()) {
            st.execute("CREATE TABLE IF NOT EXISTS vacancies(id SERIAL PRIMARY KEY,name VARCHAR(1000),description TEXT,link VARCHAR(1000),time BIGINT);");
        } catch (SQLException e) {
            LOG.error("Error of creating database", e);
        }
    }

    private void insertVacancyToTable(final VacancyDataSet vacancy) {
        try (PreparedStatement st = connection.prepareStatement("INSERT INTO vacancies(name,description,link,time) VALUES(?,?,?,?);")) {
            st.setString(1, vacancy.getName());
            st.setString(2, vacancy.getText());
            st.setString(3, vacancy.getLink());
            st.setLong(4, vacancy.getTime());
            st.executeUpdate();
        } catch (SQLException e) {
            LOG.error("Error of inserting of data", e);
        }
    }

    private int updateVacancyToTable(final String name, final VacancyDataSet vacancy) {
        int res = 0;
        try (PreparedStatement st = connection.prepareStatement("UPDATE vacancies SET description=?,link=?,time=? WHERE name=?;")) {
            st.setString(1, vacancy.getText());
            st.setString(2, vacancy.getLink());
            st.setLong(3, vacancy.getTime());
            st.setString(4, name);
            res = st.executeUpdate();
        } catch (SQLException e) {
            LOG.error("Error of updating of data", e);
        }
        return res;
    }

    private void insertOrUpdateVacancy(final VacancyDataSet vacancy) {
        int res = updateVacancyToTable(vacancy.getName(), vacancy);
        if (res == 0) {
            insertVacancyToTable(vacancy);
        }
    }

    private long returnDefaultDataForParseLimit() {
        Calendar defaultDate = new GregorianCalendar();
        defaultDate.set(Calendar.MONTH, Calendar.JANUARY);
        defaultDate.set(Calendar.DAY_OF_MONTH, 1);
        defaultDate.set(Calendar.HOUR, 0);
        defaultDate.set(Calendar.MINUTE, 0);
        defaultDate.set(Calendar.SECOND, 0);
        defaultDate.set(Calendar.MILLISECOND, 0);

        return defaultDate.getTime().getTime();
    }
}