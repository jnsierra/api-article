package co.com.ud.datos.repository;

import co.com.ud.datos.entity.ComentarioArticuloEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IComentarioArticuloRepository extends JpaRepository<ComentarioArticuloEntity, Long>, CrudRepository<ComentarioArticuloEntity, Long> {
}
