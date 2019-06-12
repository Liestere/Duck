package TrialExam.model;

import lombok.Data;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Duck {
    @Id
    @SequenceGenerator(name = "duck_gen", initialValue = 1000001)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "duck_gen")
    private long id;
    String name;
    Feeling feeling;
    Date lastFed;
    //@ElementCollection for non String, int
    @OneToMany
    List<Food> favorites;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Feeling getFeeling() {
        return feeling;
    }

    public void setFeeling(Feeling feeling) {
        this.feeling = feeling;
    }

    public Date getLastFed() {
        return lastFed;
    }

    public void setLastFed(Date lastFed) {
        this.lastFed = lastFed;
    }

    public List<Food> getFavorites() {
        return favorites;
    }

    public void setFavorites(List<Food> favorites) {
        this.favorites = favorites;
    }

    public Duck(){}
    public Duck(String name, Feeling feeling, Date lastFed, List<Food> favorites) {
        this.name = name;
        this.feeling = feeling;
        this.lastFed = lastFed;
        this.favorites = favorites;
    }

    @Override
    public String toString() {
        return "Duck{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", feeling=" + feeling +
                ", lastFed= " + lastFed +
                ", favorites=" + favorites +
                '}';
    }
}

