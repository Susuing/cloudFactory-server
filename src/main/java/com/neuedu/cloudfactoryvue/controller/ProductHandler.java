package com.neuedu.cloudfactoryvue.controller;

import com.neuedu.cloudfactoryvue.entity.module_product_tab;
import com.neuedu.cloudfactoryvue.repository.ProTypeRepository;
import com.neuedu.cloudfactoryvue.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/product")
public class ProductHandler {
    private static final ProductHandler productHandler = new ProductHandler();
    @Autowired
    private ProTypeRepository proTypeRepository;
    @Autowired
    private ProductRepository productRepository;

    private ProductHandler(){}
    public ProductHandler getProductHandler(){
        return productHandler;
    }

    @GetMapping("/findAll")
    public List<module_product_tab> findAll(){
        return productRepository.findAll();
    }

    @GetMapping("/findAll/{page}/{size}")
    public Page<module_product_tab> findAll(@PathVariable("page") Integer page, @PathVariable("size") Integer size){
        Pageable pageable = PageRequest.of(page-1,size);
        return productRepository.findAll(pageable);
    }

    @PostMapping("/save")
    public String save(@RequestBody module_product_tab product){
        module_product_tab result = productRepository.save(product);
        if(result != null){
            return "success";
        }else{
            return "failure";
        }
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable("id") String id){
        productRepository.deleteById(id);
    }

    @PutMapping("/update")
    public String update(@RequestBody module_product_tab product){
        module_product_tab result = productRepository.save(product);
        if(result != null){
            return "success";
        }else{
            return "failure";
        }
    }
    @GetMapping("/findById/{id}")
    public module_product_tab findById(@PathVariable("id") String id){
//        Module_Product_Tab result = productRepository.findById(id).get();
//        Module_Product_Type_Tab type = proTypeRepository.findById(result.getTypeid()).get();
//        result.setTypeid(type.getTypename());
//        return result;
        return productRepository.findById(id).get();
    }
}
