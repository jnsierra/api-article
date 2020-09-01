package co.com.ud.datos.repository;

import co.com.ud.datos.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUsuarioRepository extends JpaRepository<UsuarioEntity, Long>, CrudRepository<UsuarioEntity, Long> {
    /**
     * Metodo con el cual consulto usuario y contrasenia de un usuario
     * @param correo
     * @param contrasena
     * @return
     */
    Optional<UsuarioEntity> findByCorreoAndContrasenaAllIgnoreCase(String correo, String contrasena);
}
