package com.neuedu.cloudfactoryvue.repository;

import com.neuedu.cloudfactoryvue.entity.module_order_tab;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<module_order_tab,String> {
    List<module_order_tab> findByUserid(String userid);
}
