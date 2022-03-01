package com.neuedu.cloudfactoryvue.controller;

import com.neuedu.cloudfactoryvue.entity.Register;
import com.neuedu.cloudfactoryvue.entity.frame_user_tab;
import com.neuedu.cloudfactoryvue.entity.module_factory_tab;
import com.neuedu.cloudfactoryvue.repository.FactoryRepository;
import com.neuedu.cloudfactoryvue.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserHandler {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FactoryRepository factoryRepository;

    @GetMapping("/findAllUser")
    public List<frame_user_tab> findAllUser(){
        List<frame_user_tab> list = userRepository.findAll();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getRoleid().equals("1")){
                list.get(i).setRoleid("超级管理员");
            }else if(list.get(i).getRoleid().equals("2")){
                list.get(i).setRoleid("云工厂");
            }else {
                list.get(i).setRoleid("经销商");
            }
        }
        return list;
    }

    @GetMapping("/findAllUser/{page}/{size}")
    public Page<frame_user_tab> findAllUser(@PathVariable("page") int page, @PathVariable("size") int size){
        Pageable pageable = PageRequest.of(page-1, size);
        return userRepository.findAll(pageable);
    }

    @GetMapping("/findById/{id}")
    public frame_user_tab findById(@PathVariable("id") String id){
        return userRepository.findById(id).get();
    }

    @GetMapping("/login/{login_name}/{password}")
    public String login(@PathVariable("login_name")String login_name,@PathVariable("password")String password){
        List<frame_user_tab> list = userRepository.findAll();
        System.out.println(login_name);
        System.out.println(password);
        for (int i = 0;i < list.size(); i++){
            if (list.get(i).getLogin_name().equals(login_name) && list.get(i).getPassword().equals(password)){
                return list.get(i).getRoleid();
            }
        }
        return "error";
    }

//    @PostMapping("/save")
//    public String save(@RequestBody frame_user_tab user){
//        frame_user_tab result = userRepository.save(user);
//        if (result != null){
//            return "success";
//        }else {
//            return "error";
//        }
//    }

    @PutMapping("/update")
    public String update(@RequestBody frame_user_tab user){
        frame_user_tab result = userRepository.save(user);
        if (result != null){
            return "success";
        }else {
            return "error";
        }
    }

    @DeleteMapping("/deleteById/{id}")
    public void delete(@PathVariable("id") String id){
        userRepository.deleteById(id);
    }

    @PostMapping("/save")
    public String save(@RequestBody Register register){
        System.out.println(register.getFacid());
        frame_user_tab user = new frame_user_tab(register.getId(),register.getLogin_name(),register.getPassword(),register.getUser_name(),register.getMobile(),register.getRoleid());
        frame_user_tab result = userRepository.save(user);
        System.out.println();
        if ("2".equals(register.getRoleid())) {
            register.setStatus("2");
            System.out.println(register.getFacid()+'\n'+register.getFactoryname()+'\n'+register.getIntroduction()+'\n'+register.getId()+'\n'+register.getStatus());
            module_factory_tab factory = new module_factory_tab(register.getFacid(),register.getFactoryname(),register.getIntroduction(),register.getId(),register.getStatus());
            factoryRepository.save(factory);
        }
        if(result != null){
            return "success";
        }else{
            return "failure";
        }
    }

    @GetMapping("/findBylogin_name/{login_name}")
    public frame_user_tab findBylogin_name(@PathVariable("login_name")String login_name){
        return userRepository.findByLogin_name(login_name);
    }
}
