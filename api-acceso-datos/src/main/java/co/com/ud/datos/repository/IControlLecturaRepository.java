package co.com.ud.datos.repository;

import co.com.ud.datos.entity.ControlLecturaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IControlLecturaRepository extends JpaRepository<ControlLecturaEntity, Long>, CrudRepository<ControlLecturaEntity, Long> {

    List<ControlLecturaEntity> getByIdArticulo(Long idArt);
}