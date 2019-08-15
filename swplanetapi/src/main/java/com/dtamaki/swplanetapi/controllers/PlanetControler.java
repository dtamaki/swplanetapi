
package com.dtamaki.swplanetapi.controllers;

import com.dtamaki.swplanetapi.documents.SwapiPlanet;
import com.dtamaki.swplanetapi.documents.RestResult;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.ArrayList;
import com.dtamaki.swplanetapi.responses.Response;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import javax.validation.Valid;
import java.util.Optional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import com.dtamaki.swplanetapi.documents.Planet;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import com.dtamaki.swplanetapi.services.PlanetService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = { "/swplanetapi/planets" })
public class PlanetControler
{
    @Autowired
    private PlanetService planetService;
    
    @GetMapping
    public ResponseEntity<List<Planet>> listAll() {
        return (ResponseEntity<List<Planet>>)ResponseEntity.ok(this.planetService.listAll());
    }
    
    @GetMapping(path = { "/id/{id}" })
    public Optional<Planet> listById(@PathVariable(name = "id") String id) {
        return (Optional<Planet>)this.planetService.listById(id);
    }
    
    @GetMapping(path = { "/name/{name}" })
    public Planet listByName(@PathVariable(name = "name") String name) {
        return this.planetService.listByName(name);
    }

    
    //Verifica se campos de nome, clima e terreno foram enviados e retorna mensagem de erro correspondente caso ocorram.
    //Verifica se um planeta com mesmo nome já foi cadastrado e retorna mensagem alertando.
    //Chama método para calcular quantidade de filmes e define o campo correspondente.
    @PostMapping
    public ResponseEntity<Response<Planet>> addPlanet(@Valid @RequestBody Planet planet, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = new ArrayList<String>();
            bindingResult.getAllErrors().forEach(error -> errors.add(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(new Response<Planet>(errors));

        }
        if (this.planetService.listByName(planet.getName()) != null) {
        	List<String> errors = new ArrayList<String>();
			errors.add("A planet with this name already exists");
			return ResponseEntity.badRequest().body(new Response<Planet>(errors));
        }
        Integer movieAppearances = this.getTotalMovieAppearances(planet);
        planet.setMovieAppearances(movieAppearances);
        return ResponseEntity.ok(new Response<Planet>(this.planetService.add(planet)));
    }
    
    @DeleteMapping(path = { "/id/{id}" })
    public Optional<Planet> removePlanetById(@PathVariable(name = "id") String id) {
        return (Optional<Planet>)this.planetService.removePlanet(id);
    }
    
    //Realiza busca na api swapi para descobrir o total de filmes correspondente ao nome do planeta.
    //Caso nome não se encontre na base de dados, retorna como zero aparições.
    //Header para evitar bloqueio de acesso pelo Cloudfare
    public Integer getTotalMovieAppearances(Planet planet) {
        String name = planet.getName();
        Integer movieAppearancesResult = new Integer(0);
        HttpHeaders headers = new HttpHeaders();
        headers.add("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.12; rv:55.0) Gecko/20100101 Firefox/55.0");
        HttpEntity<String> entity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<RestResult> restResultJson = (ResponseEntity<RestResult>)restTemplate.exchange("https://swapi.co/api/planets/?search=" + name, HttpMethod.GET, entity, RestResult.class, new Object[0]);
        RestResult restResult = (RestResult)restResultJson.getBody();
        List<SwapiPlanet> swapiPlanets = restResult.getResults();
        if (restResult.getCount() == 1) {
            for (SwapiPlanet swPlanet : swapiPlanets) {
                movieAppearancesResult = swPlanet.getFilms().length;
            }
        }
        return movieAppearancesResult;
    }
}
