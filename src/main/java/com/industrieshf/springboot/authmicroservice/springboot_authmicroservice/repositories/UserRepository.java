package com.industrieshf.springboot.authmicroservice.springboot_authmicroservice.repositories;

import com.industrieshf.springboot.authmicroservice.springboot_authmicroservice.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    // Método para verificar si un usuario con el correo electrónico ya existe
    boolean existsByEmail(String email);

    // Puedes agregar otros métodos personalizados según sea necesario
    Optional<User>  findByEmail(String email); // Método para encontrar un usuario por su correo electrónico
}
