package com.nfs.democours.beans;

import java.io.Serializable;
import java.util.Objects;

public class Quote implements Serializable {

    private int id;
    private String quote;
    private int notation;

    public Quote() {
    }

    public Quote(int id, String quote, int notation) {
        this.id = id;
        this.quote = quote;
        this.notation = notation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public int getNotation() {
        return notation;
    }

    public void setNotation(int notation) {
        this.notation = notation;
    }

    @Override
    public String toString() {
        return quote;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quote quote1 = (Quote) o;
        return id == quote1.id && notation == quote1.notation && Objects.equals(quote, quote1.quote);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, quote, notation);
    }
}
