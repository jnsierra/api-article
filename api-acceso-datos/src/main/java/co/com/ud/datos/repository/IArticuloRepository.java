package co.com.ud.datos.repository;

import co.com.ud.datos.entity.ArticuloEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IArticuloRepository extends JpaRepository<ArticuloEntity, Long>, CrudRepository<ArticuloEntity, Long> {

    Optional<ArticuloEntity> findByIdIdea(Long idIdea);

    List<ArticuloEntity> getArticulosByUser(Long idUser);
}
