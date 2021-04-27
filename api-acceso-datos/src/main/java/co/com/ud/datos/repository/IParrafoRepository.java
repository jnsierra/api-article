package co.com.ud.datos.repository;

import co.com.ud.datos.entity.ParrafoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IParrafoRepository extends CrudRepository<ParrafoEntity, Long>, JpaRepository<ParrafoEntity, Long> {

    List<ParrafoEntity> getParrafoByArticulo(Long artId);
}
