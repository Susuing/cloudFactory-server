package com.neuedu.cloudfactoryvue.controller;

import com.neuedu.cloudfactoryvue.entity.frame_user_tab;
import com.neuedu.cloudfactoryvue.entity.module_device_tab;
import com.neuedu.cloudfactoryvue.entity.module_device_type_tab;
import com.neuedu.cloudfactoryvue.repository.DeviceTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/deviceType")
public class DeviceTypeHandler {
    @Autowired
    private DeviceTypeRepository deviceTypeRepository;

    @GetMapping("/findAllDeviceType")
    public List<module_device_type_tab> findAllDeviceType(){
        return deviceTypeRepository.findAll();
    }

    @GetMapping("/findAllDeviceType/{page}/{size}")
    public Page<module_device_type_tab> findAllDeviceType(@PathVariable("page") int page, @PathVariable("size") int size){
        Pageable pageable = PageRequest.of(page-1, size);
        return deviceTypeRepository.findAll(pageable);
    }

    @GetMapping("/findById/{id}")
    public module_device_type_tab findById(@PathVariable("id") String id){
        return deviceTypeRepository.findById(id).get();
    }

    @PostMapping("/save")
    public String save(@RequestBody module_device_type_tab deviceType){
        module_device_type_tab result = deviceTypeRepository.save(deviceType);
        if (result != null){
            return "success";
        }else {
            return "error";
        }
    }

    @PutMapping("/update")
    public String update(@RequestBody module_device_type_tab deviceType){
        module_device_type_tab result = deviceTypeRepository.save(deviceType);
        if (result != null){
            return "success";
        }else {
            return "error";
        }
    }

    @DeleteMapping("/deleteById/{id}")
    public void delete(@PathVariable("id") String id){
        deviceTypeRepository.deleteById(id);
    }

}
