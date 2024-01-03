package com.tenprj.multimoduleadminboard.repository;

import com.tenprj.multimoduleadminboard.domain.AdminAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminAccountRepository extends JpaRepository<AdminAccount, String> {
}
