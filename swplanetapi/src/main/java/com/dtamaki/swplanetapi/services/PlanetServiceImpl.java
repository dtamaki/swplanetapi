
package com.dtamaki.swplanetapi.services;

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
    
    public Planet listByName(String name) {
        return this.planetRepository.findByNameIgnoreCase(name);
    }
    
    public Planet add(Planet planet) {
        return (Planet)this.planetRepository.save(planet);
    }

	@Override
	public Planet listById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Planet removePlanet(String id) {
		// TODO Auto-generated method stub
		return null;
	}
    
}
