package co.com.ud.datos.repository;

import co.com.ud.datos.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsuarioRepository extends JpaRepository<UsuarioEntity, Long>, CrudRepository<UsuarioEntity, Long> {
}
