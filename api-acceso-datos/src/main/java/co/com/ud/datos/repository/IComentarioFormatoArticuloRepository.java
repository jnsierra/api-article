package co.com.ud.datos.repository;

import co.com.ud.datos.entity.ComentarioFormatoArticuloEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IComentarioFormatoArticuloRepository extends JpaRepository<ComentarioFormatoArticuloEntity, Long>, CrudRepository<ComentarioFormatoArticuloEntity, Long> {
}
