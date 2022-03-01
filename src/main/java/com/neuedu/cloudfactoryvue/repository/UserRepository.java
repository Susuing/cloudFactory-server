package com.neuedu.cloudfactoryvue.repository;

import com.neuedu.cloudfactoryvue.entity.frame_user_tab;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<frame_user_tab,String> {
    frame_user_tab findByLogin_name(String login_name);
}
