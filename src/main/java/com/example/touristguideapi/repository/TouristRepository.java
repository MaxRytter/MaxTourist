package com.example.touristguideapi.repository;


import com.example.touristguideapi.model.TouristAttraction;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TouristRepository {
    private final List<TouristAttraction> repositoryListe = new ArrayList<>();


    public TouristRepository() {
        allAttractions();

    }

    private void allAttractions() {
        repositoryListe.add(new TouristAttraction("Tivoli", "Danmarks fedeste forlystelsespark tæt ved hovedbanegården, hvor der både er forlystelser, koncerter, samt andre kulturrige oplevelser."));
        repositoryListe.add(new TouristAttraction("ZoologiskHave", "Er du træt af kun at se på hunde og katte? så kan du tage i danmarks største Zoologisk have og se på dyr fra hele verdenen! alt fra livsfarlige bjørne, til nuttede pingviner."));
        repositoryListe.add(new TouristAttraction("Nørrebroparken", "Et dejligt grønt område midt på Nørrebro, hvor der er fodboldbane, volleybane og andre aktiviter. Perfekt til en god sommerdag hvis man medbringer et tæppe man kan sidde på."));
        repositoryListe.add(new TouristAttraction("EKFredagsbar", "Her kan man få billige øl, spille beerpong og møde en masse studerende fra Erhversakademi København og med stor sandsynlighed andre som har sneget sig ind for de billige øl!"));
    }



    public List<TouristAttraction> getAllAttractions(){
        return repositoryListe;
    }
    public TouristAttraction findAttractionByName(String name) {
        for (TouristAttraction attraction : repositoryListe) {
            System.out.println("Checking attraction: " + attraction.getName());
            if (attraction.getName().equalsIgnoreCase(name)) {
                return attraction;
            }
        }
        System.out.println("No attraction found for: " + name);
        return null;
    }

    public TouristAttraction addAttraction(TouristAttraction attraction) {
        if (attraction.getName() != null && !attraction.getName().isEmpty()) {
            repositoryListe.add(attraction);
            return attraction;
        }
        return null;
    }
    public TouristAttraction updateAttraction(String name, String newName, String description) {
        for(TouristAttraction attraction : repositoryListe){
            if (attraction.getName().equalsIgnoreCase(name)) {
                attraction.setName(newName);
                attraction.setDescription(description);
                return attraction;
            }

        }
        return null;
    }
    public TouristAttraction removeAttraction(String name) {
        for (TouristAttraction attraction : repositoryListe) {
            if (attraction.getName().equalsIgnoreCase(name)) {
                repositoryListe.remove(attraction);
                return attraction;
            }
        }
        return null;
    }


}