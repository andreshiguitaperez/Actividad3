package co.udea.hero.api.controller;

import co.udea.hero.api.model.Hero;
import co.udea.hero.api.service.HeroService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/heroes")
public class HeroController {

    private final Logger log = LoggerFactory.getLogger(HeroController.class);

    @Autowired
    private HeroService heroService;

    public HeroController(HeroService heroService){
        this.heroService = heroService;
    }

    @GetMapping
    public List<Hero> getHeroes(){
        return heroService.getHeroes();
    }
    @GetMapping("{id}")
    public Hero getHero(@PathVariable Integer id){
        log.info("Rest request buscar heroe por id: "+ id);
        return heroService.getHero(id);
    }

    @GetMapping("buscar/{name}")
    public Hero searchHeroes(@PathVariable String name){
        return heroService.searchHeroes(name);
    }

    @PutMapping("actualizar")
    public Hero updateHero(@RequestBody Hero hero){
        Hero    heroActualService = heroService.getHero(hero.getId());

        heroActualService.setId(hero.getId());
        heroActualService.setName(hero.getName());

        return heroService.addHero(heroActualService);
    }

    @PostMapping("crear")
    public Hero addHero(@RequestBody Hero hero){
        return heroService.addHero(hero);
    }

    @DeleteMapping("borrar/{id}")
    public void deleteHero(@PathVariable Integer id){
        heroService.deleteHero(id);
    }

}
