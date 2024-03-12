package edu.escuelaing.arem.ASE.app;

public class Key {

    private final String x;
    private final String y;

    public Key(String x, String y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Key)) return false;
        Key key = (Key) o;
        return x.equalsIgnoreCase(key.x) && y.equalsIgnoreCase(key.y);
    }

    @Override
    public int hashCode() {
        return (x + y).hashCode();
    }

}