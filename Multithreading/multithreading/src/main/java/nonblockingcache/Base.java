package nonblockingcache;

import java.util.Objects;

public class Base {
    private final int id;
    private int version = 1;

    public Base(int id) {
        this.id = id;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getVersion() {
        return this.version;
    }

    public int getId() {
        return this.id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Base base = (Base) o;
        return id == base.id &&
                version == base.version;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, version);
    }
}
