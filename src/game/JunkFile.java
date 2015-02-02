package game;

/**
 * Created by Will on 2/2/2015.
 */
public class JunkFile {

    public int pIndex;
    public int tIndex;
    public int nIndex;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JunkFile junkFile = (JunkFile) o;

        if (nIndex != junkFile.nIndex) return false;
        if (pIndex != junkFile.pIndex) return false;
        if (tIndex != junkFile.tIndex) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = pIndex;
        result = 31 * result + tIndex;
        result = 31 * result + nIndex;
        return result;
    }
}
