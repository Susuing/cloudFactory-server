package com.neuedu.cloudfactoryvue.repository;

import com.neuedu.cloudfactoryvue.entity.module_device_tab;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceRepository extends JpaRepository<module_device_tab,String>{

//    module_device_tab findRentMyDev(String login_name);
}
