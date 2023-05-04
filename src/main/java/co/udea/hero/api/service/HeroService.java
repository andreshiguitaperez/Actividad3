package co.udea.hero.api.service;

import co.udea.hero.api.model.Hero;
import co.udea.hero.api.repository.HeroRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HeroService implements IHeroService {

    private final Logger log = LoggerFactory.getLogger(HeroService.class);

    @Autowired
    private HeroRepository heroRepository;

    public  HeroService(HeroRepository heroRepository){
        this.heroRepository = heroRepository;
    }

    @Override
    public List<Hero> getHeroes(){
        return heroRepository.findAll();
    }

    @Override
    public Hero getHero(Integer id) {
        Optional<Hero> optionalHero = heroRepository.findById(id);
        Hero heroResult;
        try{
            if(optionalHero.isPresent()){

                 heroResult = optionalHero.get();

                return heroResult;

            }else{
                log.error("No fue encontrado");

                return null;
            }

        }catch (Exception e){

            return null;

        }
    }

    @Override
    public Optional<Hero> findByName(String name) {
        return heroRepository.findByName(name);
    }

    @Override
    public Hero searchHeroes(String name) {
        Optional<Hero> optionalHero = heroRepository.findByName(name);
        Hero heroResult;
        try{
            if(optionalHero.isPresent()){

                heroResult = optionalHero.get();

                return heroResult;

            }else{
                log.error("No fue encontrado");

                return null;
            }

        }catch (Exception e){

            return null;

        }
    }

    @Override
    public Hero addHero(Hero hero){
        return heroRepository.save(hero);
    }

    @Override
    public void deleteHero(Integer id){
        heroRepository.deleteById(id);
    }

}
