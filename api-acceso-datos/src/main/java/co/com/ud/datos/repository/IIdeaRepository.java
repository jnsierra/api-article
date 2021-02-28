package co.com.ud.datos.repository;

import co.com.ud.datos.entity.IdeaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IIdeaRepository extends JpaRepository<IdeaEntity, Long>, CrudRepository<IdeaEntity, Long> {

    List<IdeaEntity> findByUsuarioId(@Param("idUsua") Long id);
}
