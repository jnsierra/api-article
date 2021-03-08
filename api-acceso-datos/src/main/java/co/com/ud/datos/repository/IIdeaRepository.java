package co.com.ud.datos.repository;

import co.com.ud.datos.entity.IdeaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Repository
public interface IIdeaRepository extends JpaRepository<IdeaEntity, Long>, CrudRepository<IdeaEntity, Long> {

    List<IdeaEntity> findByUsuarioId(@Param("idUsua") Long id);

    List<IdeaEntity> findByProfesorIdAndEstado(@Param("idProfesor") Long idProfesor,@Param("estadoIdea") String estadoIdea );

    @Modifying
    @Transactional
    Integer modificarIdProfAutorizaAndEstadoAndFechaAutoriza(@Param("idIdea") Long idIdea, @Param("idProf") Long idProf, @Param("estado") String estado, @Param("fechaApro")Date fechaApr);

}
