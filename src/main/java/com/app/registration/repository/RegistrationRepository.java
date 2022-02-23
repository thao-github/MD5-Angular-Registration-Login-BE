package com.app.registration.repository;

import com.app.registration.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationRepository extends JpaRepository<User, Long> {
    public User findByEmailId(String emailId);

    public User findByEmailIdAndPassword(String emailId, String password);
}
