
package com.dtamaki.swplanetapi.services;

import java.util.Optional;
import com.dtamaki.swplanetapi.documents.Planet;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.dtamaki.swplanetapi.repositories.PlanetRepository;
import org.springframework.stereotype.Service;

@Service
public class PlanetServiceImpl implements PlanetService
{
    @Autowired
    private PlanetRepository planetRepository;
    
    public List<Planet> listAll() {
        return (List<Planet>)this.planetRepository.findAll();
    }
    
    public Optional<Planet> listById(String id) {
        return (Optional<Planet>)this.planetRepository.findById(id);
    }
    
    public Planet listByName(String name) {
        return this.planetRepository.findByNameIgnoreCase(name);
    }
    
    public Planet add(Planet planet) {
        return (Planet)this.planetRepository.save(planet);
    }
    
    public Optional<Planet> removePlanet(String id) {
        Optional<Planet> planet = (Optional<Planet>)this.planetRepository.findById(id);
        planet.ifPresent(b -> this.planetRepository.delete(b));
        return planet;
    }
}
