package com.imanzano.sc.common.parser;

/**
 * Basic Tuple
 */
@SuppressWarnings("JavaDoc")
public class Tuple<X, Y> {
    private final X x;
    private  final Y y;
    private Tuple(X x, Y y) {
        this.x = x;
        this.y = y;
    }

    public X first(){ return x;}
    public Y second(){ return y;}

    public static <X,Y> Tuple<X,Y> tuple(X x,Y y) { return new Tuple<>(x,y); }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (other == this) {
            return true;
        }
        if (!(other instanceof Tuple)){
            return false;
        }
        final Tuple<X,Y> other_ = (Tuple<X,Y>) other;
        return other_.x == x && other_.y == y;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((x == null) ? 0 : x.hashCode());
        result = prime * result + ((y == null) ? 0 : y.hashCode());
        return result;
    }
}
