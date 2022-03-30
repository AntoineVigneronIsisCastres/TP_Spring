package monprojet.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.List;

import monprojet.entity.*;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.jdbc.Sql;

@Log4j2 // Génère le 'logger' pour afficher les messages de trace
@DataJpaTest
public class CountryRepositoryTest {

    @Autowired
    private CountryRepository countryDAO;
    @Autowired
    private CityRepository cityDAO;

    @Test
    void lesNomsDePaysSontTousDifferents() {
        log.info("On vérifie que les noms de pays sont tous différents ('unique') dans la table 'Country'");
        
        Country paysQuiExisteDeja = new Country("XX", "France");
        try {
            countryDAO.save(paysQuiExisteDeja); // On essaye d'enregistrer un pays dont le nom existe   

            fail("On doit avoir une violation de contrainte d'intégrité (unicité)");
        } catch (DataIntegrityViolationException e) {
            // Si on arrive ici c'est normal, l'exception attendue s'est produite
        }
    }

    @Test
    @Sql("test-data.sql") // On peut charger des donnnées spécifiques pour un test
    void onSaitCompterLesEnregistrements() {
        log.info("On compte les enregistrements de la table 'Country'");
        int combienDePaysDansLeJeuDeTest = 3 + 1; // 3 dans data.sql, 1 dans test-data.sql
        long nombre = countryDAO.count();
        assertEquals(combienDePaysDansLeJeuDeTest, nombre, "On doit trouver 4 pays" );
    }

    @Test
    void onTrouveLesVillesDesPays() {
        log.info("On vérifie que les villes d'un pays sont accessibles");
        City paris = cityDAO.findByName("Paris");
        Country france = countryDAO.findById(1).orElseThrow();
        assertTrue( france.getCities().contains(paris), "France contient Paris");
    }

    @Test
    void onCompteLaPopulationDuPays() {
        log.info("On compte la population d'un pays");
        List<Integer> population = countryDAO.populationDuPays("1");
        assertEquals(population.get(0),12);
    }

    @Test
    void onAfficheLaPopulationDeChaquePays(){
        log.info("On affiche la population de chaqaue pays");
        List<Integer> populations = countryDAO.populationDeChaquePays();
        System.out.println(populations);
        assertEquals(populations.get(0),12);
        assertEquals(populations.get(1),18);
        assertEquals(populations.get(2),27);
    }

}
