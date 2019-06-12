package TrialExam.controller;

import TrialExam.model.Duck;
import TrialExam.model.Feeling;
import TrialExam.service.DuckMoodService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/ducks")
public class DuckController {

    private DuckMoodService service;

    public DuckController(DuckMoodService service) {
        this.service = service;
    }

//    @RequestMapping(value="", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    public List<Duck> getAllDucks(@RequestParam(value = "feeling", required = false) Feeling feeling){
//        return service.getDucks(feeling);
//    }

    @RequestMapping(value="", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Duck> getAllDucks(@RequestParam(value = "search") Feeling feeling){
        return service.getDucks(feeling);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Duck getDuckById(@RequestParam Long id) {
        return service.getDuck(id);
    }

    @RequestMapping(value="", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addDuck(@RequestBody Duck duck) {
        service.addDuck(duck);
    }

    @RequestMapping(value="/feeling", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateDuckFeeling(@RequestParam Long id, Feeling feeling){
        service.updateFeeling(id, feeling);
    }

    @RequestMapping(value="/fed", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateDuckLastFed(@RequestParam Long id){
        service.updateLastFed(id);
    }

    @RequestMapping(value="/favorite", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateDuckFavorite(@RequestParam Long id, Long favorite){
        service.updateFavorite(id, favorite);
    }

    @RequestMapping(value="", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateDuckLastFed(@RequestParam Long id, Long favorites){
        service.deleteFavorite(id, favorites);
    }
}
