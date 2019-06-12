package TrialExam;

import TrialExam.model.ApiKey;
import TrialExam.model.Duck;
import TrialExam.model.Feeling;
import TrialExam.model.Food;
import TrialExam.repository.ApiKeyRepository;
import TrialExam.repository.DuckRepository;
import TrialExam.repository.FoodRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Component
public class AppRunner implements ApplicationRunner {

    private DuckRepository duckRepository;
    private FoodRepository foodRepository;
    private ApiKeyRepository keyRepository;

    public AppRunner(DuckRepository duckRepository, FoodRepository foodRepository, ApiKeyRepository keyRepository) {
        this.duckRepository = duckRepository;
        this.foodRepository = foodRepository;
        this.keyRepository = keyRepository;
    }

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        List<Food> foods = new ArrayList<Food>(
                Arrays.asList(
                        new Food("Peas", 42),
                        new Food("Mais", 32),
                        new Food("Carrot", 12)
            )
        );

        List<Duck> ducks = new ArrayList<>(
                Arrays.asList(
                        new Duck("Waddles", Feeling.happy, new Date(), new ArrayList<Food>(Arrays.asList(foods.get(0)))),
                        new Duck("Quacker", Feeling.hungry, new Date(),new ArrayList<Food>(Arrays.asList(foods.get(1)))),
                        new Duck("Ducky", Feeling.sad, new Date(),new ArrayList<Food>(Arrays.asList(foods.get(2))))
                )
        );

        //foods.stream().forEach(f -> foodRepository.save(f));
        //ducks.stream().forEach(d -> duckRepository.save(d));

        foodRepository.saveAll(foods);
        duckRepository.saveAll(ducks);

        for (String s : Arrays.asList(new String[]{"5962A7199EBCA21A48ABAE8E8921A", "A21896CC68CF6822A8F4A9EC2D6A8", "57CB8558ADF9CE22FEE4DF2A34B86"})) {
            keyRepository.save(new ApiKey(s));
        }

        keyRepository.findAll()
                .forEach(System.out::println);


    }


//      Files.lines(Paths.get("guitars.csv"))
//              .forEach(
//            line -> guitarRepository.save(
//            new Guitar(line.split(",")[0],
//            line.split(",")[1],
//            "../images/" + line.split(",")[2] + ".jpg",
//            Integer.parseInt(line.split(",")[4]),
//            line.split(",")[3])
//            )
//            );

//    guitarRepository.findAll()
//            .forEach(System.out::println);
//
//    List<Guitar> guitars = (List<Guitar>) guitarRepository.findAll();
//        guitars.stream()
//                .forEach(a -> stockRepository.save(new Stock(a, new Random().nextInt(10))));
//
//        stockRepository.findAll()
//                .forEach(System.out::println);


}
