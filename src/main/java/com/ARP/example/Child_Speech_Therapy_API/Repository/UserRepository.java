package com.ARP.example.Child_Speech_Therapy_API.Repository;

import com.ARP.example.Child_Speech_Therapy_API.Domain.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User , UUID> {
    Optional<User> findById(UUID id);

    void deleteById(UUID id);

    User findByEmail(String email);
}
