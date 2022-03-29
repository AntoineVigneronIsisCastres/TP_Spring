package monprojet.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import monprojet.entity.City;
import monprojet.entity.Country;

// This will be AUTO IMPLEMENTED by Spring 
//

public interface CountryRepository extends JpaRepository<Country, Integer> {

        /**
     * Trouve une ville par son nom.
     * Cette méthode sera automatiquement implémentée par Spring Data JPA.
     * @param cityName
     * @return La ville correspondant au nom fourni, ou null si pas trouvé.
     */
    public City findByName(String cityName);

}
