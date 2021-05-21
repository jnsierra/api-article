package co.com.ud.datos.repository;

import co.com.ud.datos.entity.ComentarioArticuloEntity;
import co.com.ud.utiles.enumeracion.TYPE_COMMENTS_ARTICLE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IComentarioArticuloRepository extends JpaRepository<ComentarioArticuloEntity, Long>, CrudRepository<ComentarioArticuloEntity, Long> {

    List<ComentarioArticuloEntity> findByTypeComentarioArtAndArticulo(TYPE_COMMENTS_ARTICLE typeCommentsArticle, Long idArt);

    List<ComentarioArticuloEntity> findByArticulo(Long idArt);
}
