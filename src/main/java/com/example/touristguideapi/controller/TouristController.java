package com.example.touristguideapi.controller;

import com.example.touristguideapi.model.TouristAttraction;
import com.example.touristguideapi.service.TouristService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
//Controller klasse
public class TouristController {
private final TouristService touristService;
//Controller constructor
    public TouristController(TouristService touristService) {
        this.touristService = touristService;
    }
    //En GetMapping til forside http://localhost:8080/
@GetMapping ("/")
    public String showFrontPage(Model model) {
        List<TouristAttraction> attractionslist = touristService.getAllAttractions();
        System.out.println("Attractions: " + attractionslist);
        model.addAttribute("attractionslist", attractionslist);
        return "index";
    }

    //en GetMapping til alle attraktioner
@GetMapping("/attractions")
    public ResponseEntity<List<TouristAttraction>> getAllAttractions(){
        List<TouristAttraction> attractions = touristService.getAllAttractions();
        return new ResponseEntity<>(attractions, HttpStatus.OK);
}


//en GetMapping til en specifik attraktion, f.eks. http://localhost:8080/attractions/tivoli
    @GetMapping("/attractions/{name}")
public ResponseEntity<TouristAttraction> getAttractionName(@PathVariable String name) {
    TouristAttraction attraction = touristService.findAttractionByName(name);
        return new ResponseEntity<>(attraction, HttpStatus.OK);
    }

    //laver en PostMapping der tilføjer en attraktion til vores attraktionsliste
    @PostMapping("/attractions/add")
    public ResponseEntity<TouristAttraction> addAttraction(@RequestBody TouristAttraction attraction) {
        TouristAttraction newAttraction = touristService.addAttraction(attraction);
        if (newAttraction != null) {
            return new ResponseEntity<>(newAttraction, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    //http://localhost:8080/attractions/update/Tivoli
    //en PostMapping der kan opdatere navn og beskrivelse på den skrevne attraktion i json
    //det vil sige at hvis du ændrer navnet, så ændrer du også på endpointet - f.eks. fra http://localhost:8080/attractions/Tivoli til http://localhost:8080/attractions/Havfrue
    @PostMapping("/attractions/update/{name}")
    public ResponseEntity<TouristAttraction> updateAttraction(@PathVariable String name, @RequestBody TouristAttraction attraction) {
        TouristAttraction updateAttraction = touristService.updateAttraction(name,attraction.getName(), attraction.getDescription());
        return new ResponseEntity<>(updateAttraction, HttpStatus.OK);
    }

    //http://localhost:8080/attractions/delete/Tivoli
    //DeleteMapping som fjerner en Attraktion
    @PostMapping("/attractions/delete/{name}")
    public ResponseEntity<TouristAttraction> removeAttraction(@PathVariable String name) {
        TouristAttraction deleteAttraction = touristService.removeAttraction(name);
        return new ResponseEntity<>(deleteAttraction, HttpStatus.OK);
    }

}
