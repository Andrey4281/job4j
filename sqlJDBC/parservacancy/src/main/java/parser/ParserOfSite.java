package parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Class for extracting information about vacancies from web-site
 * @author asemenov
 * @since 25.09.2019
 * @version 1
 */
public class ParserOfSite {
    private  final static Logger LOG = LogManager.getLogger(ParserOfSite.class);
    private Document document;
    private DateParser dateParser;

    public ParserOfSite(final Document document) {
        this.document = document;
        this.dateParser = new DateParser();
    }

    /**
     * This method execute parsing of web-site
     * Vacancies which have time
     * of publishig in web-site earlier than timeLimitForParse will not be added
     * to list
     * @param timeLimitForParse
     * @return list of extracted from web-site vacancies
     */
    public List<VacancyDataSet> parse(long timeLimitForParse)  {
        List<VacancyDataSet> res = new LinkedList<>();
        int countOfPage = getCountOfPage();
        for (int i = 1; i <= countOfPage; i++) {
            if (!parseCurrentPage(timeLimitForParse, res, new StringBuilder(document.baseUri()).append(i).toString())) {
                break;
            }
        }
        LOG.info("Parsing is finished success");
        return res;
    }

    private boolean parseCurrentPage(long timeLimitForParsem, List<VacancyDataSet> vacancies, String url) {
        boolean res = true;
        try {
            Document currentPage = Jsoup.connect(url).get();
            Elements rowsOfTable = currentPage.selectFirst("table.forumTable").select("tr");
            for (Element currentRow : rowsOfTable) {
                Elements cols = currentRow.select("td").not("th");
                if (!cols.isEmpty()) {
                    Element currentRef = cols.get(1).selectFirst("a[href]");
                    if (checkVacancy(currentRef.text())) {
                        long timeOfVacancy = dateParser.parseDate(cols.get(5).text());
                        if (timeOfVacancy >= timeLimitForParsem) {
                            VacancyDataSet currentVacancy = new VacancyDataSet();
                            currentVacancy.setName(currentRef.text());
                            currentVacancy.setText(parseDescriptionOfVacancy(currentRef.attr("href")));
                            currentVacancy.setLink(currentRef.attr("href"));
                            currentVacancy.setTime(timeOfVacancy);
                            vacancies.add(currentVacancy);
                        } else {
                            res = false;
                            break;
                        }
                    }
                }
            }
        } catch (IOException e) {
            LOG.error("Error of connection to page", e);
        }
        return res;
    }

    private String parseDescriptionOfVacancy(String url) {
        String res = new String();
        try {
            Elements rows = Jsoup.connect(url).get().
                    selectFirst("table.msgTable").select("td.msgBody");
            if (rows.size() >= 2) {
                res = rows.get(1).text();
            }
        } catch (IOException e) {
            LOG.error("Error of connection to description of vacancy", e);
        }
        return res;
    }

    private int getCountOfPage() {
        return Integer.parseInt(document.select("table[style=\"font-weight: bold\"].sort_options")
                .first().getElementsByTag("a").last().text());
    }

    private boolean checkVacancy(String vacancyName) {
        String vacancyNameL = vacancyName.toLowerCase();
        return vacancyNameL.contains("java") && !vacancyNameL.contains("java script")
                && !vacancyNameL.contains("javascript");
    }
}
