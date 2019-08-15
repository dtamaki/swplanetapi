package com.dtamaki.swplanetapi.documents;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SwapiPlanet
{
    private String name;
    private String rotation_period;
    private String diameter;
    private String climate;
    private String gravity;
    private String terrain;
    private String surface_water;
    private String population;
    private String[] residents;
    private String[] films;
    private String created;
    private String edited;
    private String url;
    
    public String getName() {
        return this.name;
    }
    
    public void setName(  String name) {
        this.name = name;
    }
    
    public String getRotation_period() {
        return this.rotation_period;
    }
    
    public void setRotation_period(  String rotation_period) {
        this.rotation_period = rotation_period;
    }
    
    public String getDiameter() {
        return this.diameter;
    }
    
    public void setDiameter(  String diameter) {
        this.diameter = diameter;
    }
    
    public String getClimate() {
        return this.climate;
    }
    
    public void setClimate(  String climate) {
        this.climate = climate;
    }
    
    public String getGravity() {
        return this.gravity;
    }
    
    public void setGravity(  String gravity) {
        this.gravity = gravity;
    }
    
    public String getTerrain() {
        return this.terrain;
    }
    
    public void setTerrain(  String terrain) {
        this.terrain = terrain;
    }
    
    public String getSurface_water() {
        return this.surface_water;
    }
    
    public void setSurface_water(  String surface_water) {
        this.surface_water = surface_water;
    }
    
    public String getPopulation() {
        return this.population;
    }
    
    public void setPopulation(  String population) {
        this.population = population;
    }
    
    public String[] getResidents() {
        return this.residents;
    }
    
    public void setResidents(  String[] residents) {
        this.residents = residents;
    }
    
    public String[] getFilms() {
        return this.films;
    }
    
    public void setFilms(  String[] films) {
        this.films = films;
    }
    
    public String getCreated() {
        return this.created;
    }
    
    public void setCreated(  String created) {
        this.created = created;
    }
    
    public String getEdited() {
        return this.edited;
    }
    
    public void setEdited(  String edited) {
        this.edited = edited;
    }
    
    public String getUrl() {
        return this.url;
    }
    
    public void setUrl(  String url) {
        this.url = url;
    }
    
    @Override
    public String toString() {
        return String.format("SwapiPlanet[name=%s, rotation_period='%s', diameter='%s', climate='%s', gravity='%s', terrain='%s',surface_water='%s', population='%s', residents='%s', films='%s', created='%s', edited='%s', url='%s']", this.name, this.rotation_period, this.diameter, this.climate, this.gravity, this.terrain, this.surface_water, this.population, this.residents, this.films, this.created, this.edited, this.url);
    }
}
