package co.com.ud.datos.repository;

import co.com.ud.datos.entity.FormatoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IFormatoRepository extends JpaRepository<FormatoEntity, Long>, CrudRepository<FormatoEntity, Long> {

    List<FormatoEntity> findByIdArt(Long idArt);
}
