package com.finedge.finedge.Repository;

import com.finedge.finedge.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<User,Long> {


   User findByEmail(String email);
}
