package parser;

/**
 * Class represent essence vacancy from web-site sql.ru
 * @author asemenov
 * @since 25.09.2019
 * @version 1
 */
public class VacancyDataSet {
    private String name;
    private String text;
    private String link;
    private long time;

    public VacancyDataSet() {

    }

    public VacancyDataSet(final String name, final String text, final String link, long time) {
        this.name = name;
        this.text = text;
        this.link = link;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public String getText() {
        return text;
    }

    public String getLink() {
        return link;
    }

    public long getTime() {
        return time;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setTime(long time) {
        this.time = time;
    }

}
