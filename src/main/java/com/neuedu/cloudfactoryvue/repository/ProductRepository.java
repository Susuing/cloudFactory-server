package com.neuedu.cloudfactoryvue.repository;

import com.neuedu.cloudfactoryvue.entity.module_product_tab;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<module_product_tab,String> {
}
