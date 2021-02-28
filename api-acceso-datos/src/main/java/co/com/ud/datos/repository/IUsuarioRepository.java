package co.com.ud.datos.repository;

import co.com.ud.datos.entity.UsuarioEntity;
import co.com.ud.utiles.enumeracion.USER_STATE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
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

    /**
     * Metodo con el cual consulto por medio del email
     * @param correo
     * @return
     */
    Optional<UsuarioEntity> findByCorreoAllIgnoreCase(String correo);

    @Modifying
    @Transactional
    Integer updateIntentos(@Param("id") Long id,@Param("intentos") Integer intentos);

    @Modifying
    @Transactional
    Integer inactivarUsuario(@Param("id") Long id);

    @Modifying
    @Transactional
    Integer modificarEstadoUsuario(@Param("id") Long id,@Param("estado") USER_STATE estado);

    List<UsuarioEntity> findByTipoUsuario(@Param("tipoUsuario") String tipoUsuario);
}
