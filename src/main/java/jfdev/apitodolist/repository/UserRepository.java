package jfdev.apitodolist.repository;

import jfdev.apitodolist.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
