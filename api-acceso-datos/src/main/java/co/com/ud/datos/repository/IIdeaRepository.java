package co.com.ud.datos.repository;

import co.com.ud.datos.entity.IdeaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IIdeaRepository extends JpaRepository<IdeaEntity, Long>, CrudRepository<IdeaEntity, Long> {
}
