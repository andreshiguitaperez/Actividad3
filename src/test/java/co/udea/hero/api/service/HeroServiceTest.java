package co.udea.hero.api.service;

import co.udea.hero.api.model.Hero;
import co.udea.hero.api.repository.HeroRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class HeroServiceTest {

    @Mock
    private HeroRepository heroRepository;

    @InjectMocks
    private HeroService heroService;

    private Hero hero;
    List<Hero> heroes = new ArrayList();

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);

        hero = new Hero();
        hero.setId(10);
        hero.setName("Carlos");

        List<Hero> heroes = new ArrayList();
        heroes.add(hero);
    }



    @Test
    void getHero() {
        when(heroRepository.findById(10)).thenReturn(Optional.ofNullable(hero));
        assertNotNull(heroService.getHeroes());
    }

    /*
    @Test
    void getHeroes() {
        when(heroRepository.findAll()).thenReturn(Arrays.asList(heroes));
        assertNotNull(heroService.getHeroes());
    }

    @Test
    void findByName() {
    }

    @Test
    void searchHeroes() {
    }

    @Test
    void addHero() {
    }

    @Test
    void deleteHero() {
    }*/
}