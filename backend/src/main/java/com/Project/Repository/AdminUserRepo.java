package com.Project.Repository;

import com.Project.Entity.AdminUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminUserRepo extends JpaRepository<AdminUser, Long> {
}
