package com.example.touristguideapi.service;

import com.example.touristguideapi.model.TouristAttraction;
import com.example.touristguideapi.repository.TouristRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TouristService {
private final TouristRepository touristRepository;

    public TouristService(TouristRepository touristRepository) {
        this.touristRepository = touristRepository;
    }

    public List<TouristAttraction> getAllAttractions(){
        return touristRepository.getAllAttractions();
    }
    public TouristAttraction findAttractionByName(String name){
    TouristAttraction attraction = touristRepository.findAttractionByName(name);
    if(attraction != null && attraction.getName().equalsIgnoreCase(name)){
        return new TouristAttraction(attraction.getName(), attraction.getDescription());
    }
    return null;
    }

    public TouristAttraction addAttraction(TouristAttraction attraction){
        return touristRepository.addAttraction(attraction);
    }

    public TouristAttraction updateAttraction(String name, String newName, String description) {
        TouristAttraction attraction = touristRepository.updateAttraction(name, newName, description);
        return attraction;
    }

    public TouristAttraction removeAttraction(String name) {
        return touristRepository.removeAttraction(name);
    }
}
