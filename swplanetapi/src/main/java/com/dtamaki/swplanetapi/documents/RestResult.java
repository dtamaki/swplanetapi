
package com.dtamaki.swplanetapi.documents;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RestResult
{
    private Integer count;
    private Integer next;
    private Integer previous;
    private List<SwapiPlanet> results;
    
    public Integer getCount() {
        return this.count;
    }
    
    public void setCount(  Integer count) {
        this.count = count;
    }
    
    public Integer getNext() {
        return this.next;
    }
    
    public void setNext(  Integer next) {
        this.next = next;
    }
    
    public Integer getPrevious() {
        return this.previous;
    }
    
    public void setPrevious(  Integer previous) {
        this.previous = previous;
    }
    
    public List<SwapiPlanet> getResults() {
        return this.results;
    }
    
    public void setResults(  List<SwapiPlanet> results) {
        this.results = results;
    }
    
    @Override
    public String toString() {
        return String.format("Result[count=%s, next='%s', previous='%s', results='%s']", this.count, this.next, this.previous, this.results);
    }
}
