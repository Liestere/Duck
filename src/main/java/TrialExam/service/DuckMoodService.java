package TrialExam.service;

import TrialExam.model.Duck;
import TrialExam.model.Feeling;
import TrialExam.model.Food;
import TrialExam.repository.DuckRepository;
import TrialExam.repository.FoodRepository;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class DuckMoodService {

//    List<Duck> ducks = new ArrayList<>(
//            Arrays.asList(
//                    new Duck(0L, "Waddles", Feeling.happy, new Date(), new ArrayList<String>(Arrays.asList("Peas", "Mais"))),
//                    new Duck(1L, "Quacker", Feeling.hungry, new Date(),new ArrayList<String>(Arrays.asList("Peas", "Mais", "Carrot"))),
//                    new Duck(2L, "Ducky", Feeling.sad, new Date(),new ArrayList<String>(Arrays.asList("Peas")))
//            )
//    );
    private DuckRepository duckRepository;
    private FoodRepository foodRepository;

    public DuckMoodService(DuckRepository duckRepository, FoodRepository foodRepository) {
        this.duckRepository = duckRepository;
        this.foodRepository = foodRepository;
    }

    public List<Duck> getDucks(Feeling feeling) {
//        List<Duck> goodFeelingDucks = ducks.stream()
//                .filter(d -> d.getFeeling().equals(feeling))
//                .collect(Collectors.toList());
//        return goodFeelingDucks;
        return duckRepository.findAll();
    }

    public Duck getDuck(Long id) {
//        for (Duck duck : ducks) {
//            if (duck.getId().equals(id)) {
//                return duck;
//            }
//        }
//        return null;
        return duckRepository.getOne(id);
    }

    public void addDuck(Duck duck) {
//        ducks.add(duck);
        duckRepository.save(duck);
    }

    public void updateFeeling(Long id, Feeling feeling) {
//        for (Duck duck : ducks) {
//            if (duck.getId().equals(id)) {
//                duck.setFeeling(feeling);
//            }
//        }
        Duck oldDuck = duckRepository.getOne(id);
        oldDuck.setFeeling(feeling);
        duckRepository.save(oldDuck);
    }

    public void updateLastFed(Long id) {
//        for (Duck duck : ducks) {
//            if (duck.getId().equals(id)) {
//                duck.setLastFed(new Date());
//            }
//        }
        Duck duck = duckRepository.getOne(id);
        duck.setLastFed(new Date());
        duckRepository.save(duck);
    }

    public void updateFavorite(Long duckId, Long foodId) {
//        for (Duck duck : ducks) {
//            if (duck.getId().equals(id)) {
//                List<String> favorites = duck.getFavorites();
//                favorites.add(newFavorite);
//                duck.setFavorites(favorites);
//            }
//        }
        Duck duck = duckRepository.getOne(duckId);
        List<Food> favorites = duck.getFavorites();
        favorites.add(foodRepository.getOne(foodId));
        //duck.setFavorites(favorites);
        duckRepository.save(duck);
    }

    public void deleteFavorite(Long duckId, Long foodId) {
//        for (Duck duck : ducks) {
//            if (duck.getId().equals(id)) {
//                List<String> favorites = duck.getFavorites();
//                favorites.remove(oldFavorite);
//                duck.setFavorites(favorites);
//            }
//        }
        Duck duck = duckRepository.getOne(duckId);
        List<Food> favorites = duck.getFavorites();
        Food food = favorites.stream().filter(f -> f.getId().equals(duckId)).findFirst().get();
        favorites.remove(food);
        duckRepository.save(duck);
    }
}
