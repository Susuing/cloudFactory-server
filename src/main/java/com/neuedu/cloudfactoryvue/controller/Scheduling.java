package com.neuedu.cloudfactoryvue.controller;

import com.neuedu.cloudfactoryvue.entity.module_scheduling_tab;
import com.neuedu.cloudfactoryvue.repository.SchedulingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/scheduling")
public class Scheduling {
    @Autowired
    private SchedulingRepository schedulingRepository;

    @GetMapping("/findAll")
    public List<module_scheduling_tab> findAll(){
        return schedulingRepository.findAll();
    }
    @PostMapping("/save")
    public String save(@RequestBody module_scheduling_tab scheduling){
        String id = (findAll().size()+1)+"";
        scheduling.setId(id);
        module_scheduling_tab result = schedulingRepository.save(scheduling);
        if (result != null){
            return  "success";
        }else {
            return "error";
        }
    }
}
