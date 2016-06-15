package com.hardikgoswami.popularmovies.util.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MovieTrailerResult {

    private Integer id;
    private List<MovieTrailer> results = new ArrayList<MovieTrailer>();
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * @return The id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return The results
     */
    public List<MovieTrailer> getResults() {
        return results;
    }

    /**
     * @param results The results
     */
    public void setResults(List<MovieTrailer> results) {
        this.results = results;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}