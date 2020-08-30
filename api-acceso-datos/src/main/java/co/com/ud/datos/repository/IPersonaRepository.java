package co.com.ud.datos.repository;

import co.com.ud.datos.entity.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPersonaRepository extends JpaRepository<PersonaEntity, Long>, CrudRepository<PersonaEntity, Long> {
}
