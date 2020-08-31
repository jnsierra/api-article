package co.com.ud.datos.repository;

import co.com.ud.datos.entity.TipoUsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITipoUsuarioRepository extends JpaRepository<TipoUsuarioEntity, Long>, CrudRepository<TipoUsuarioEntity, Long> {
}
