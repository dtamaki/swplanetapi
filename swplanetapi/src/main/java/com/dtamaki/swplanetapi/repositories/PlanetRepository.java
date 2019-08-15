
package com.dtamaki.swplanetapi.repositories;

import org.springframework.data.repository.query.Param;
import com.dtamaki.swplanetapi.documents.Planet;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PlanetRepository extends MongoRepository<Planet, String>
{
    Planet findByNameIgnoreCase(@Param("name") String name);
}
