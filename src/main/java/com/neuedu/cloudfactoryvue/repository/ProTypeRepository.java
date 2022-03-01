package com.neuedu.cloudfactoryvue.repository;

import com.neuedu.cloudfactoryvue.entity.module_product_type_tab;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProTypeRepository extends JpaRepository<module_product_type_tab,String> {
}
