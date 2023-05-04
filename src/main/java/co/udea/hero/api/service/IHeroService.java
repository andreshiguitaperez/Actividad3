package co.udea.hero.api.service;

import co.udea.hero.api.model.Hero;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;
import java.util.Optional;

public interface IHeroService {
    List<Hero> getHeroes();

    Hero getHero(Integer id) throws ChangeSetPersister.NotFoundException;

    Optional<Hero> findByName(String name);

    Hero searchHeroes(String name) throws ChangeSetPersister.NotFoundException;

    Hero addHero(Hero hero);

    void deleteHero(Integer id);
}
