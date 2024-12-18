package cl.desafio.java.pstaubr.repository;


import cl.desafio.java.pstaubr.entity.PhonesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Pablo Staub R
 */
public interface PhonesRepository extends JpaRepository<PhonesEntity, String> {

}
