/*
 * Copyright Sushil Kumar <0120sushil@gmail.com>.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at

 *     http://www.apache.org/licenses/LICENSE-2.0

 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * under the License.
 */
package me.uni.sushilkumar.geodine.util;

import java.util.ArrayList;

/**
 *
 * @author Sushil Kumar <0120sushil@gmail.com>
 */
public class CuisineObject {

    public CuisineObject(int id, String country, String cuisineId, String ytid, ArrayList<String> directions, String name,ArrayList<String> ingredients,String imageUrl ) {
        this.id = id;
        this.country = country;
        this.cuisineId = cuisineId;
        this.ytid = ytid;
        this.directions = directions;
        this.name = name;
        this.ingredients=ingredients;
        this.imageUrl=imageUrl;
    }

    

    private int id;

    /**
     * Get the value of id
     *
     * @return the value of id
     */
    public int getId() {
        return id;
    }

    /**
     * Set the value of id
     *
     * @param id new value of id
     */
    public void setId(int id) {
        this.id = id;
    }
    private String country;

    /**
     * Get the value of country
     *
     * @return the value of country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Set the value of country
     *
     * @param country new value of country
     */
    public void setCountry(String country) {
        this.country = country;
    }
    private String cuisineId;

    /**
     * Get the value of cuisineId
     *
     * @return the value of cuisineId
     */
    public String getCuisineId() {
        return cuisineId;
    }

    /**
     * Set the value of cuisineId
     *
     * @param cuisineId new value of cuisineId
     */
    public void setCuisineId(String cuisineId) {
        this.cuisineId = cuisineId;
    }
    private String ytid;

    /**
     * Get the value of ytid
     *
     * @return the value of ytid
     */
    public String getYtid() {
        return ytid;
    }

    /**
     * Set the value of ytid
     *
     * @param ytid new value of ytid
     */
    public void setYtid(String ytid) {
        this.ytid = ytid;
    }
    private ArrayList<String> directions;

    /**
     * Get the value of directions
     *
     * @return the value of directions
     */
    public ArrayList<String> getDirections() {
        return directions;
    }

    /**
     * Set the value of directions
     *
     * @param directions new value of directions
     */
    public void setDirections(ArrayList<String> directions) {
        this.directions = directions;
    }
    private String name;

    /**
     * Get the value of name
     *
     * @return the value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the value of name
     *
     * @param name new value of name
     */
    public void setName(String name) {
        this.name = name;
    }
    private ArrayList<String> ingredients;

    /**
     * Get the value of ingredients
     *
     * @return the value of ingredients
     */
    public ArrayList<String> getIngredients() {
        return ingredients;
    }

    /**
     * Set the value of ingredients
     *
     * @param ingredients new value of ingredients
     */
    public void setIngredients(ArrayList<String> ingredients) {
        this.ingredients = ingredients;
    }
    private String imageUrl;

    /**
     * Get the value of imageUrl
     *
     * @return the value of imageUrl
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * Set the value of imageUrl
     *
     * @param imageUrl new value of imageUrl
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}
