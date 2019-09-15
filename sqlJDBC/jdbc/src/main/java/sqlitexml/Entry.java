package sqlitexml;

import javax.xml.bind.annotation.*;

@XmlRootElement
public class Entry {
    private int field;

    public Entry(int field) {
        this.field = field;
    }

    public Entry() {

    }


    public void setField(int field) {
        this.field = field;
    }

    @XmlElement
    public int getField() {
        return field;
    }
}
