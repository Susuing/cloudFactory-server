package com.neuedu.cloudfactoryvue.repository;

import com.neuedu.cloudfactoryvue.entity.module_factory_tab;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FactoryRepository extends JpaRepository<module_factory_tab,String> {
}
