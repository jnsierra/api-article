package co.com.ud.datos.repository;

import co.com.ud.datos.entity.ComentarioEntity;
import co.com.ud.utiles.enumeracion.TYPE_COMMENTS;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface IComentarioRepository extends JpaRepository<ComentarioEntity, Long>, CrudRepository<ComentarioEntity, Long> {

    List<ComentarioEntity> findByLlaveAndTipoComentario(@Param("llave")Long llave, @Param("tipoComentario")TYPE_COMMENTS type_comments);

    @Modifying
    @Transactional
    Integer updateComentarioUbicacion(@Param("idCom")Long idCom, @Param("ubicacion")String ubicacion);
}
