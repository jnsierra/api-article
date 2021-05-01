package co.com.ud.datos.repository;

import co.com.ud.datos.entity.FormatoIdeaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IFormatoIdeaRepository extends JpaRepository<FormatoIdeaEntity, Long>, CrudRepository<FormatoIdeaEntity, Long> {


    List<FormatoIdeaEntity> getFormatosByIdea(Long idIdea);

    List<FormatoIdeaEntity> getFormatosByIdeaAndTipoFormato(Long idIdea, String tipoFormato);
}