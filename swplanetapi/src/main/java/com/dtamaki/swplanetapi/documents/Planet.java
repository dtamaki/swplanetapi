package com.dtamaki.swplanetapi.documents;

import javax.validation.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Planet
{
    @Id
    private String id;
    private String name;
    private String climate;
    private String terrain;
    private Integer totalMovieAppearances;
    
    public String getId() {
        return this.id;
    }
    
    public void setId(  String id) {
        this.id = id;
    }
    
    @NotEmpty(message = "Name can't be empty")
    public String getName() {
        return this.name;
    }
    
    public void setName(  String name) {
        this.name = name;
    }
    
    @NotEmpty(message = "Climate can't be empty")
    public String getClimate() {
        return this.climate;
    }
    
    public void setClimate(String climate) {
        this.climate = climate;
    }
    
    @NotEmpty(message = "Terrain can't be empty")
    public String getTerrain() {
        return this.terrain;
    }
    
    public void setTerrain(String terrain) {
        this.terrain = terrain;
    }
    
    public Integer getMovieAppearances() {
        return this.totalMovieAppearances;
    }
    
	public void setMovieAppearances( Integer movieAppearances) {
        this.totalMovieAppearances = movieAppearances;
    }
    
    @Override
    public String toString() {
        return String.format("Planet[id=%s, name='%s', climate='%s', terrain='%s', movieAppearances='%s']", this.id, this.name, this.climate, this.terrain, this.totalMovieAppearances);
    }
}
