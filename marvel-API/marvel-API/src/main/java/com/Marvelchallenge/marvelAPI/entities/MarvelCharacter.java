package com.Marvelchallenge.marvelAPI.entities;

import kong.unirest.json.JSONObject;

public class MarvelCharacter {
    public long id;
    public String name;
    public String description;
    //public JSONObject thumbnail;

    public MarvelCharacter(long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

//    public JSONObject getThumbnail() {
//        return thumbnail;
//    }
//
//    public void setThumbnail(JSONObject thumbnail) {
//        this.thumbnail = thumbnail;
//    }
}
