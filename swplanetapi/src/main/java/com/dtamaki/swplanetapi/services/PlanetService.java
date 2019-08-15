package com.dtamaki.swplanetapi.services;

import java.util.Optional;
import com.dtamaki.swplanetapi.documents.Planet;
import java.util.List;

public interface PlanetService
{
    List<Planet> listAll();
    
    Optional<Planet> listById(String id);
    
    Planet listByName(String name);
    
    Planet add(Planet planet);
    
    Optional<Planet> removePlanet(String id);
}
