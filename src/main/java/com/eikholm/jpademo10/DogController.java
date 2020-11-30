package com.eikholm.jpademo10;

import com.eikholm.jpademo10.model.Dog;
import com.eikholm.jpademo10.model.Owner;
import com.eikholm.jpademo10.service.DogService;
import com.eikholm.jpademo10.service.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.Set;

@Controller
public class DogController {

    private DogService dogService; // Spring vil selv komme med objektet hertil
    private OwnerService ownerService;
    private final String LOGINSTATUS = "loginstatus";
    private final String USERNAME = "username";
    private final String PASSWORD = "password";

    public DogController(DogService dogService, OwnerService ownerService) {
        this.dogService = dogService;
        this.ownerService = ownerService;
    }

    @PostMapping("/dogs/login")
    public String login(HttpSession httpSession, @RequestParam("username") String usr, @RequestParam("password") String pw){
        if(usr.equals(USERNAME) && pw.equals(PASSWORD)){
            httpSession.setAttribute(LOGINSTATUS, "yes");
        } else{
            httpSession.setAttribute(LOGINSTATUS, "no");
        }
        return "redirect:";
    }

    @RequestMapping({"/dogs/","/dogs", "dogs/index"})
    public String getDogs(Model model){
        model.addAttribute("dog", new Dog());
        model.addAttribute("owner", new Owner());
        Set<Owner> owners = ownerService.findAll();
        model.addAttribute("owners", owners);
        model.addAttribute("ownerLessDogs", dogService.getOwnerlessDogs());
        model.addAttribute("ownedDogs", dogService.getOwnedDogs());
        System.out.println("GET OWNED DOGS " + dogService.getOwnedDogs());
        Set<Dog> dogs = dogService.findAll();
        model.addAttribute("dogs", dogs);
        System.out.println(dogs.size());
        return "dogs/doghome";
    }

    @PostMapping("/createDog")
    public String createDog(@ModelAttribute Dog dog){
        System.out.println(dog.getFirstName());
        dogService.save(dog);
        return "redirect:dogs";
    }

    @PostMapping("/createOwner")
    public String createDog(@ModelAttribute Owner owner){
        System.out.println(owner.getFirstName());
        ownerService.save(owner);
        return "redirect:dogs";
    }

    @PostMapping("/addOwnerToDog")
    public String addOwnerToDog(@RequestParam("dogId") Long dogId, @RequestParam("ownerId") Long ownerId) {
        System.out.println("dog id " + dogId + " owner id " + ownerId);
        if(ownerService.findById(ownerId).isPresent()){
            System.out.println("owner er present");
            Optional<Dog> dog = dogService.findById(dogId);
            Optional<Owner> owner = ownerService.findById(ownerId);
            dog.get().setOwner(owner.get());
            dogService.save(dog.get());
            owner.get().getDogs().add(dog.get());
            ownerService.save(owner.get());
        }
        return "redirect:dogs";
    }
}

