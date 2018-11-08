package com.gustavoforero.moviestest.data.remote.model;

import com.gustavoforero.moviestest.data.model.entity.Trailer;

import java.util.ArrayList;

public class TrailersResponse {

    private ArrayList<Trailer> results;

    public ArrayList<Trailer> getResults() {
        return results;
    }

    public void setResults(ArrayList<Trailer> results) {
        this.results = results;
    }
}
