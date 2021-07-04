package co.com.ud.datos.repository;

import co.com.ud.datos.entity.IdeaEntity;
import co.com.ud.utiles.dto.CountStateDto;
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

    List<IdeaEntity> findByProfesorIdAndEstadoJurado(@Param("idProfesor") Long idProfesor,@Param("estadoIdea") String estadoIdea );

    List<IdeaEntity> findByEstado(@Param("estadoIdea") String estadoIdea );

    List<CountStateDto> conteoByEstado();

    @Modifying
    @Transactional
    Integer modificarIdProfAutorizaAndEstadoAndFechaAutoriza(@Param("idIdea") Long idIdea,
                                                             @Param("idProf") Long idProf,
                                                             @Param("estado") String estado,
                                                             @Param("fechaApro")Date fechaApr);

    @Modifying
    @Transactional
    Integer modificarIdea(@Param("idIdea") Long idIdea,
                          @Param("ideaTitulo") String titulo,
                          @Param("ideaContenido") String contenido,
                          @Param("estado") String estado,
                          @Param("idProf") Long idProf
                          );

    @Modifying
    @Transactional
    Integer modificarEstado(@Param("idIdea") Long idIdea,
                            @Param("estado") String estado
                            );

    @Modifying
    @Transactional
    Integer modificarJurado(@Param("idIdea") Long idIdea,
                            @Param("idProfesorJurado") Long idJurado);
}