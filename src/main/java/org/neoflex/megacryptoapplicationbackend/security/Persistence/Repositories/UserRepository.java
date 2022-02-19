package org.neoflex.megacryptoapplicationbackend.security.Persistence.Repositories;

import org.neoflex.megacryptoapplicationbackend.security.Persistence.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
}
