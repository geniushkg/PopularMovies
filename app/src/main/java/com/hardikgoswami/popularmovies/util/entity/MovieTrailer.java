package com.hardikgoswami.popularmovies.util.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by geniushkg on 6/15/2016.
 */
public class MovieTrailer {

    private String id;
    private String iso_639_1;
    private String iso_3166_1;
    private String key;
    private String name;
    private String site;
    private Integer size;
    private String type;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * @return The id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return The iso_639_1
     */
    public String getIso_639_1() {
        return iso_639_1;
    }

    /**
     * @param iso_639_1 The iso_639_1
     */
    public void setIso_639_1(String iso_639_1) {
        this.iso_639_1 = iso_639_1;
    }

    /**
     * @return The iso_3166_1
     */
    public String getIso_3166_1() {
        return iso_3166_1;
    }

    /**
     * @param iso_3166_1 The iso_3166_1
     */
    public void setIso_3166_1(String iso_3166_1) {
        this.iso_3166_1 = iso_3166_1;
    }

    /**
     * @return The key
     */
    public String getKey() {
        return key;
    }

    /**
     * @param key The key
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The site
     */
    public String getSite() {
        return site;
    }

    /**
     * @param site The site
     */
    public void setSite(String site) {
        this.site = site;
    }

    /**
     * @return The size
     */
    public Integer getSize() {
        return size;
    }

    /**
     * @param size The size
     */
    public void setSize(Integer size) {
        this.size = size;
    }

    /**
     * @return The type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type The type
     */
    public void setType(String type) {
        this.type = type;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}