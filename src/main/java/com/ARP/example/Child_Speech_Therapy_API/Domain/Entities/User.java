package com.ARP.example.Child_Speech_Therapy_API.Domain.Entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;


import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "users")

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String userName;

    @NotBlank
    @Email
    @Column(nullable = false , unique = true)
    private String email;

    @Column(nullable = false)
    private int age;

    @Column(nullable = false)
    private List<String> interests;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime updatedAt;

    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = LocalDateTime.now();
    }


    @PrePersist
    protected void onCreate(){
        this.updatedAt = LocalDateTime.now();
        this.createdAt = LocalDateTime.now();
    }
}
