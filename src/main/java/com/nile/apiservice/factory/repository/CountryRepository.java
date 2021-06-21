package com.nile.apiservice.factory.repository;

import java.util.List;
import java.util.Set;

import com.nile.apiservice.factory.model.entity.Country;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer>{
    Country findById(int id);

    List<Country> findByCountrynameAndPopulationGreaterThanEqual(String countryname, long population);
    List<Country> findByCountrynameAndPopulationLessThanEqual(String countryname, long population);
    List<Country> findByCountrynameAndPopulationLessThan(String countryname, long population);
    List<Country> findByCountrynameAndPopulationGreaterThan(String countryname, long population);

    List<Country> findByCountrynameStartsWithOrderByPopulationAsc(String prefix);
    List<Country> findByCountrynameStartsWithOrderByPopulationDesc(String suffix);

    List<Country> findByCountrynameStartsWith(String prefix);
    List<Country> findByCountrynameContaining(String substring);
    List<Country> findByCountrynameEndsWith(String suffix);

    // 클래스명, 변수명으로 쿼리
    @Query("from Country where id = ?1")
    Country getById(int id);

    @Query("select c from Country c where c.countryname like ?1% ORDER BY c.population ASC")
    List<Country> getByCountryname(String prefix);

    @Query("select c.countryname, c.population from Country c where c.countryname like :countryname% and c.population >= :population")
    List<Object[]> getByCountrynameAndPopulation(@Param("countryname") String countryname, @Param("population") long population);
    
    // todo: 스키마명을 붙여야하는지 테스트 필요
    // ! google-search: jpa @query schema
    // ! https://docs.jboss.org/hibernate/orm/5.2/userguide/html_single/chapters/query/native/Native.html#sql-global-catalog-schema
    // ! https://blog.hyunsub.kim/Spring/Spring-Jpa/
    // ! https://www.reimaginer.me/entry/JPA-generated-query-comments
    // ! https://hongchan.tistory.com/21?category=844803

    // ! google-search: spring jpa repository default schema
    // ! https://www.baeldung.com/spring-boot-data-sql-and-schema-sql
    // ! https://stackoverflow.com/questions/38771731/spring-data-jpa-default-schema-for-native-query-in-repository
    // ! https://stackoverflow.com/questions/24278659/change-database-schema-used-by-spring-boot/24278772
    @Query(value = "select c.country_name, c.population, c.gdp, ct.city_name from t_country_info c left join t_city_info ct on c.country_id = ct.country_id where c.country_name like :countryname% and c.population >= :population", nativeQuery = true)
    List<Object[]> getByCountrynameAndPopulationNative(@Param("countryname") String countryname, @Param("population") long population);

    @Query("from Country where id in (?1)")
    List<Country> getByIds(Set<Integer> ids);

}
