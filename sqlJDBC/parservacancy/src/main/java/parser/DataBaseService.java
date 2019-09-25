package parser;

import java.util.List;

/**
 * Base interface for interaction to different databases
 * @author asemenov
 * @since 25.09.2019
 * @version 1
 */
public interface DataBaseService extends AutoCloseable {
    /**
     * Method for adding list of vacancies to database
     * If some vacancy already exists in database, it will be updated by more actual vacancy
     * More actual vacancy was published to site late
     * @param vacancies list of vacancies for adding
     */
    void addVacancies(List<VacancyDataSet> vacancies);

    /**
     * Method for returning timeLimit. timeLimit will be used for parsing of site
     * @return Time of the newest vacancy in database
     */
    long getTimeLimitForParse();

    /**
     * Method for inicialization of database
     */
    void init();
}
