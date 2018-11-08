package com.gustavoforero.moviestest.data.remote.model;

import com.gustavoforero.moviestest.data.model.entity.Movie;

import java.util.ArrayList;

public class MoviesResponse {
    private ArrayList<Movie> results;
    private int page;
    private int total_pages;


    public ArrayList<Movie> getResults() {
        return results;
    }

    public void setResults(ArrayList<Movie> results) {
        this.results = results;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }
}
