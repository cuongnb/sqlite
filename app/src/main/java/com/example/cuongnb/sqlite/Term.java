package com.example.cuongnb.sqlite;

/**
 * Created by cuongnb on 8/9/16.
 */
public class Term {
    int id;
    String term;
    String def;

    public Term() {

    }

    public Term(String term, String def) {
        this.term = term;
        this.def = def;
    }
}
