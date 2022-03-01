package com.neuedu.cloudfactoryvue.controller;

import com.neuedu.cloudfactoryvue.entity.*;
import com.neuedu.cloudfactoryvue.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/device")
public class DeviceHandler {
    @Autowired
    private DeviceRepository deviceRepository;
    @Autowired
    private Device_ProductHandler device_productHandler;
    @Autowired
    private DeviceTypeHandler deviceTypeHandler;
    @Autowired
    private FactoryHandler factoryHandler;
    @Autowired
    private UserHandler userHandler;
    @Autowired
    private DeviceHandler deviceHandler;

    @GetMapping("/findAllDevice")
    public List<module_device_tab> findAllDevice(){
        return deviceRepository.findAll();
    }

    @GetMapping("/findAllDeviceById")
    public List<module_device_tab> findAllDeviceById(){
        List<module_device_product_tab> list = device_productHandler.findAllDevPro();
        List<String> ids = new ArrayList<String>();
        for (int i = 0;i < list.size(); i++){
            ids.add(list.get(i).getDeviceid());
        }
        return deviceRepository.findAllById(ids);
    }

    @GetMapping("/findEndDevice")
    public List<module_device_tab> findEndDevice(){
//        List<module_device_tab> devices = findAllDeviceById();
        List<module_device_tab> devices = findAllDevice();
        List<module_device_type_tab> device_types = deviceTypeHandler.findAllDeviceType();
        List<module_factory_tab> factorys = factoryHandler.findAllFactory();
        for (int i = 0;i < devices.size(); i++) {
            for (int j = 0; j < device_types.size(); j++) {
                if (devices.get(i).getTypeid().equals(device_types.get(j).getId())) {
                    devices.get(i).setTypeid(device_types.get(j).getTypename());
                }
            }
            for (int k = 0;k < factorys.size();k++){
                if (devices.get(i).getFactoryid().equals(factorys.get(k).getId())){
                    devices.get(i).setFactoryid(factorys.get(k).getFactoryname());
                }
            }
            if ("0".equals(devices.get(i).getDevicestatus())){
                devices.get(i).setDevicestatus("关闭");
            }else if ("1".equals(devices.get(i).getDevicestatus())){
                devices.get(i).setDevicestatus("生产中");
            }else if ("2".equals(devices.get(i).getDevicestatus())){
                devices.get(i).setDevicestatus("闲置中");
            }else if ("3".equals(devices.get(i).getDevicestatus())){
                devices.get(i).setDevicestatus("故障");
            }
            if ("0".equals(devices.get(i).getRentstatus())){
                devices.get(i).setRentstatus("自用设备");
            }else if ("1".equals(devices.get(i).getRentstatus())){
                devices.get(i).setRentstatus("租用设备");
            }else if ("2".equals(devices.get(i).getRentstatus())){
                devices.get(i).setRentstatus("空闲");
            }
        }
        return devices;
    }
    @GetMapping("/findRentDevice")
    public List<module_device_tab> findRentDevice(){
        List<module_device_tab> devices = findAllDevice();
        List<module_device_tab> list = new ArrayList<>();
        for (int i =0 ;i < devices.size();i++){
            if ("1".equals(devices.get(i).getFactoryid())){
                if ("0".equals(devices.get(i).getDevicestatus())){
                    devices.get(i).setDevicestatus("关闭");
                }else if ("1".equals(devices.get(i).getDevicestatus())){
                    devices.get(i).setDevicestatus("生产中");
                }else if ("2".equals(devices.get(i).getDevicestatus())){
                    devices.get(i).setDevicestatus("闲置中");
                }else if ("3".equals(devices.get(i).getDevicestatus())){
                    devices.get(i).setDevicestatus("故障");
                }
                if ("0".equals(devices.get(i).getRentstatus())){
                    devices.get(i).setRentstatus("自用设备");
                }else if ("1".equals(devices.get(i).getRentstatus())){
                    devices.get(i).setRentstatus("租用设备");
                }else if ("2".equals(devices.get(i).getRentstatus())){
                    devices.get(i).setRentstatus("空闲");
                }
                devices.get(i).setFactoryid("产能中心");
                list.add(devices.get(i));
            }
        }
        return list;
    }

    @GetMapping("/findDeviceById/{id}")
    public module_device_tab findDeviceById(@PathVariable("id")  String id){
        return deviceRepository.findById(id).get();
    }

    @GetMapping("/findAllDeviceById/{page}/{size}")
    public Page<module_device_tab> findAllDeviceById(@PathVariable("page") int page, @PathVariable("size") int size){
        Pageable pageable = PageRequest.of(page-1, size);
        List<module_device_product_tab> list = device_productHandler.findAllDevPro();
        List<String> ids = new ArrayList<String>();
        for (int i = 0;i < list.size(); i++){
            ids.add(list.get(i).getDeviceid());
        }
        return deviceRepository.findAll(pageable);
    }

    @PostMapping("/save")
    public String save(@RequestBody module_device_tab device){
        module_device_tab result = deviceRepository.save(device);
        if (result != null){
            return "success";
        }else {
            return "error";
        }
    }

    @PutMapping("/update")
    public String update(@RequestBody module_device_tab device){
        module_device_tab result = deviceRepository.save(device);
        if (result != null){
            return "success";
        }else {
            return "error";
        }
    }
    @PutMapping("/updateStatus")
    public String updateStatus(@RequestBody module_device_tab device){
        List<module_factory_tab> allFactory = factoryHandler.findAllFactory();
        if (!"关闭".equals(device.getDevicestatus())){
            device.setDevicestatus("0");
        }else {
            device.setDevicestatus("2");
        }
        if ("自用设备".equals(device.getRentstatus())){
            device.setRentstatus("0");
        }else if ("租用设备".equals(device.getRentstatus())){
            device.setRentstatus("1");
        }else if ("空闲".equals(device.getRentstatus())){
            device.setRentstatus("2");
        }
        for (int i = 0;i < allFactory.size();i++){
            if (device.getFactoryid().equals(allFactory.get(i).getFactoryname())){
                device.setFactoryid(allFactory.get(i).getId());
            }
        }
        module_device_tab result = deviceRepository.save(device);
        if (result != null){
            return "success";
        }else {
            return "error";
        }
    }

    @DeleteMapping("/deleteById/{id}")
    public void delete(@PathVariable("id") String id){
        deviceRepository.deleteById(id);
    }

    @GetMapping("/findMyDevice/{login_name}")
    public List<module_device_tab> findMyDevice(@PathVariable("login_name")String login_name){
        List<frame_user_tab> users = userHandler.findAllUser();
        List<module_factory_tab> factorys = factoryHandler.findAllFactory();
        List<module_device_tab> allDevice = deviceRepository.findAll();
        List<module_device_type_tab> device_types = deviceTypeHandler.findAllDeviceType();
        List<module_device_tab> list = new ArrayList<>();
        String user_id = "";
        List<String> factory_ids = new ArrayList<>();
        for (int i = 0;i < users.size(); i++){
            if (login_name.equals(users.get(i).getLogin_name())){
                user_id = users.get(i).getId();
            }
        }
        for (int j = 0;j < factorys.size(); j++){
            if (user_id.equals(factorys.get(j).getUserid())){
                factory_ids.add(factorys.get(j).getId());
            }
        }
        for (int k = 0;k < allDevice.size();k++){
            for (int p = 0;p < factory_ids.size(); p++){
                if (factory_ids.get(p).equals(allDevice.get(k).getFactoryid())){
                    for (int q = 0;q < factorys.size(); q++){
                        if (factory_ids.get(p).equals(factorys.get(q).getId())){
                            allDevice.get(k).setFactoryid(factorys.get(q).getFactoryname());
                        }
                    }
                    for (int t = 0;t < device_types.size(); t++){
                        if (allDevice.get(k).getTypeid().equals(device_types.get(t).getId())){
                            allDevice.get(k).setTypeid(device_types.get(t).getTypename());
                        }
                    }
                    if ("0".equals(allDevice.get(k).getDevicestatus())){
                        allDevice.get(k).setDevicestatus("关闭");
                    }else if ("1".equals(allDevice.get(k).getDevicestatus())){
                        allDevice.get(k).setDevicestatus("生产中");
                    }else if ("2".equals(allDevice.get(k).getDevicestatus())){
                        allDevice.get(k).setDevicestatus("闲置中");
                    }else if ("3".equals(allDevice.get(k).getDevicestatus())){
                        allDevice.get(k).setDevicestatus("故障");
                    }
                    if ("0".equals(allDevice.get(k).getRentstatus())){
                        allDevice.get(k).setRentstatus("自用设备");
                    }else if ("1".equals(allDevice.get(k).getRentstatus())){
                        allDevice.get(k).setRentstatus("租用设备");
                    }else if ("2".equals(allDevice.get(k).getRentstatus())){
                        allDevice.get(k).setRentstatus("空闲");
                    }
                    list.add(allDevice.get(k));
                }

            }
        }
        return list;
    }

    @GetMapping("/findRentMyDev/{id}/{login_name}")
    public String findRentMyDev(@PathVariable("id")String id,@PathVariable("login_name")String login_name){
        module_device_tab device = deviceRepository.findById(id).get();
        String fid = "";
        List<frame_user_tab> users = userHandler.findAllUser();
        List<module_factory_tab> factorys = factoryHandler.findAllFactory();
        if(!"0".equals(device.getDevicestatus())){
            for (int i = 0;i < users.size();i++){
                if (login_name.equals(users.get(i).getLogin_name())){
                    for (int j = 0;j < factorys.size();j++){
                        if (users.get(i).getId().equals(factorys.get(j).getUserid())){
                            fid = factorys.get(j).getId();
                            System.out.println(fid);
                        }
                    }
                }
            }
        }
        if (fid != ""){
            device.setFactoryid(fid);
            deviceHandler.update(device);
            return "success";
        }else {
            return "error";
        }

    }
}








