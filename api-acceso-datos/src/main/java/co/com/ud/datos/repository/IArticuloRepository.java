package co.com.ud.datos.repository;

import co.com.ud.datos.entity.ArticuloEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface IArticuloRepository extends JpaRepository<ArticuloEntity, Long>, CrudRepository<ArticuloEntity, Long> {

    Optional<ArticuloEntity> findByIdIdea(Long idIdea);

    List<ArticuloEntity> getArticulosByUser(Long idUser);

    List<ArticuloEntity> getArticulosByTutorAndEstado(Long idTutor, String estado);

    @Modifying
    @Transactional
    Integer updateArticulo(@Param("idArt") Long id, @Param("ingles") String ingles
            , @Param("resumen") String resumen
            , @Param("titulo") String titulo
            , @Param("introduccion") String introduccion
            , @Param("conclusion") String conclusion);

    @Modifying
    @Transactional
    Integer updateUbicacionFormato(@Param("idArt") Long id, @Param("ubFormato") String ubFormato);
}
