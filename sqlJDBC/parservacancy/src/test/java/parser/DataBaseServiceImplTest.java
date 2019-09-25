package parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import tracker.sql.ConnectionRollback;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class DataBaseServiceImplTest {
    public static final Logger LOG = LogManager.getLogger(DataBaseServiceImplTest.class);
    private Connection connection;

    @Before
    public void setConnection() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/testVacancies",
                "postgres", "12345");
        LOG.info("Connected to database testVacancies");
    }

    @Test
    public void whenAddVacanciesShouldGetThem() throws Exception {
        try (DataBaseServiceImplPostgres dataBaseService = new DataBaseServiceImplPostgres(ConnectionRollback.create(connection))){
            dataBaseService.init();
            List<VacancyDataSet> list = new LinkedList<>();
            list.add(new VacancyDataSet("java1", "for clever", "link1", 1000));
            dataBaseService.addVacancies(list);

            VacancyDataSet vacancyActual = dataBaseService.selectVacancyByName("java1");

            assertThat(vacancyActual.getName(), is("java1"));
            assertThat(vacancyActual.getText(), is("for clever"));
            assertThat(vacancyActual.getLink(), is("link1"));
            assertThat(vacancyActual.getTime(), is(1000L));
        }
    }

    @Test
    public void whenAddAlreadyExistedVacanciesShouldUpdateIt() throws Exception {
        try (DataBaseServiceImplPostgres dataBaseService = new DataBaseServiceImplPostgres(ConnectionRollback.create(connection))) {
            dataBaseService.init();
            List<VacancyDataSet> list = new LinkedList<>();
            list.add(new VacancyDataSet("java1", "for clever", "link1", 1000));
            list.add(new VacancyDataSet("java1", "for cleverest", "link2", 10000));
            dataBaseService.addVacancies(list);

            VacancyDataSet vacancyActual = dataBaseService.selectVacancyByName("java1");

            assertThat(vacancyActual.getName(), is("java1"));
            assertThat(vacancyActual.getText(), is("for cleverest"));
            assertThat(vacancyActual.getLink(), is("link2"));
            assertThat(vacancyActual.getTime(), is(10000L));
        }
    }

    @Test
    public void whenGetTimeLimitForParseShouldGetMaxValue() throws Exception {
        try (DataBaseServiceImplPostgres dataBaseService = new DataBaseServiceImplPostgres(ConnectionRollback.create(connection))) {
            dataBaseService.init();
            List<VacancyDataSet> list = new LinkedList<>();
            list.add(new VacancyDataSet("java1", "for clever", "link1", 1000));
            list.add(new VacancyDataSet("java1", "for cleverest", "link2", 10000));
            dataBaseService.addVacancies(list);

            long timeLimitForParse = dataBaseService.getTimeLimitForParse();

            assertThat(timeLimitForParse, is(10000L));
        }
    }
}