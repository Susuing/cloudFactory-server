package com.neuedu.cloudfactoryvue.controller;

import com.neuedu.cloudfactoryvue.entity.module_product_type_tab;
import com.neuedu.cloudfactoryvue.repository.ProTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/proType")
public class ProTypeHandler {
    private static final ProTypeHandler proTypeHandler = new ProTypeHandler();
    @Autowired
    private ProTypeRepository proTypeRepository;

    private ProTypeHandler(){};
    public ProTypeHandler getProTypeHandler(){
        return proTypeHandler;
    }

    @GetMapping("/findAll/{page}/{size}")
    public Page<module_product_type_tab> findAll(@PathVariable("page") Integer page, @PathVariable("size") Integer size){
        Pageable pageable = PageRequest.of(page-1,size);
        return proTypeRepository.findAll(pageable);
    }

    @GetMapping("/findAll")
    public List<module_product_type_tab> findAll(){
        return proTypeRepository.findAll();
    }

    @PostMapping("/save")
    public String save(@RequestBody module_product_type_tab type){
//        UUID uuid = UUID.randomUUID();
//        String str = uuid.toString();
//        String uuidStr = str.replace("-", "");
//        type.setId(uuidStr);
        module_product_type_tab result = proTypeRepository.save(type);
        if(result != null){
            return "success";
        }else{
            return "failure";
        }
    }
    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable("id") String id){
        proTypeRepository.deleteById(id);
    }

    @PutMapping("/update")
    public String update(@RequestBody module_product_type_tab type){
        module_product_type_tab result = proTypeRepository.save(type);
        if(result != null){
            return "success";
        }else{
            return "failure";
        }
    }
    @GetMapping("/findById/{id}")
    public module_product_type_tab findById(@PathVariable("id") String id){
        return proTypeRepository.findById(id).get();
    }
}
