package com.neuedu.cloudfactoryvue.controller;

import com.neuedu.cloudfactoryvue.entity.module_device_product_tab;
import com.neuedu.cloudfactoryvue.repository.Device_ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/device_product")
public class Device_ProductHandler {
    @Autowired
    private Device_ProductRepository d_pRepository;

    @GetMapping("/findAllDevPro")
    public List<module_device_product_tab> findAllDevPro(){
        return d_pRepository.findAll();
    }

}
