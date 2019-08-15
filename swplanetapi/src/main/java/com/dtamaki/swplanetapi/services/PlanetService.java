package com.dtamaki.swplanetapi.services;

import com.dtamaki.swplanetapi.documents.Planet;
import java.util.List;

public interface PlanetService
{
    List<Planet> listAll();
    
    Planet listById(String id);
    
    Planet listByName(String name);
    
    Planet add(Planet planet);
    
    Planet removePlanet(String id);
}
