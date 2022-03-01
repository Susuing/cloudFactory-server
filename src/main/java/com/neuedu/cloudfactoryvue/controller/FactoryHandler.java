package com.neuedu.cloudfactoryvue.controller;

import com.neuedu.cloudfactoryvue.entity.frame_user_tab;
import com.neuedu.cloudfactoryvue.entity.module_factory_tab;
import com.neuedu.cloudfactoryvue.repository.FactoryRepository;
import com.neuedu.cloudfactoryvue.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/factory")
public class FactoryHandler {
    @Autowired
    private FactoryRepository factoryRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/findAllFactory")
    public List<module_factory_tab> findAllFactory(){
        return factoryRepository.findAll();
    }

    @GetMapping("/findAll/{page}/{size}")
    public Page<module_factory_tab> findAll(@PathVariable("page") Integer page, @PathVariable("size") Integer size){
        Pageable pageable = PageRequest.of(page-1,size);
        return factoryRepository.findAll(pageable);
    }

    @GetMapping("/findById/{id}")
    public module_factory_tab findById(@PathVariable("id") String id){
        return factoryRepository.findById(id).get();
    }

    @PutMapping("/update")
    public String update(@RequestBody module_factory_tab factory){//工厂状态1代表正常，2代表关停
        if(factory.getStatus().equals("1")){
            factory.setStatus("2");
        }else if(factory.getStatus().equals("2")){
            factory.setStatus("1");
        }
        module_factory_tab result = factoryRepository.save(factory);
        if(result != null){
            return "success";
        }else{
            return "failure";
        }
    }
    @PutMapping("/updateInfo")
    public String updateInfo(@RequestBody module_factory_tab factory){//工厂状态1代表正常，2代表关停
        module_factory_tab result = factoryRepository.save(factory);
        if(result != null){
            return "success";
        }else{
            return "failure";
        }
    }
    @PostMapping("/save")
    public String save(@RequestBody module_factory_tab factory){
        factory.setUserid(factory.getId());
//        UUID uuid = UUID.randomUUID();
//        String str = uuid.toString();
//        String uuidStr = str.replace("-", "");
//        factory.setId(uuidStr);
        factory.setStatus("2");
        module_factory_tab result = factoryRepository.save(factory);
        if(result != null){
            return "success";
        }else{
            return "failure";
        }
    }
    @GetMapping("/findMyFactory/{login_name}")
    public List<module_factory_tab> findMyFactory(@PathVariable("login_name")String login_name){
        List<module_factory_tab> list = new ArrayList<>();
        List<frame_user_tab> users = userRepository.findAll();
        List<module_factory_tab> factorys = factoryRepository.findAll();
        String userid = "";
        for (int i =0 ;i < users.size();i++){
            if (login_name.equals(users.get(i).getLogin_name())){
                userid = users.get(i).getId();
            }
        }
        for (int j =0 ;j < factorys.size();j++){
            if (userid.equals(factorys.get(j).getUserid())){
                list.add(factorys.get(j));
            }
        }
        return list;
    }

    @DeleteMapping("/deleteById/{id}")
    public void delete(@PathVariable("id") String id){
        factoryRepository.deleteById(id);
    }

}
