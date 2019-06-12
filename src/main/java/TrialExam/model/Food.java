package TrialExam.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Entity
public class Food {

    @Id
    @SequenceGenerator(name = "food_gen", initialValue = 1000001)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "food_gen")
    private long id;
    private String name;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    int calories;

    public Food(String name, int calories) {
        this.name = name;
        this.calories = calories;
    }
}
