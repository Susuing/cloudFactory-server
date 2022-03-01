package com.neuedu.cloudfactoryvue.controller;

import com.neuedu.cloudfactoryvue.entity.module_bid_tab;
import com.neuedu.cloudfactoryvue.repository.BidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bid")
public class BidHandler {
    @Autowired
    private BidRepository bidRepository;

    @PostMapping("/save")
    public String save(@RequestBody module_bid_tab bid){
        bid.setBidstatus("0");
        module_bid_tab result = bidRepository.save(bid);
        if (result != null){
            return "success";
        }else {
            return "error";
        }
    }

    @PutMapping("/update")
    public String update(@RequestBody module_bid_tab bid){
        bid.setBidstatus("1");
        module_bid_tab result = bidRepository.save(bid);
        if(result != null){
            return "success";
        }else{
            return "failure";
        }
    }

    @GetMapping("/findByOrderid/{orderid}")
    public List<module_bid_tab> findByOrderid(@PathVariable("orderid") String orderid){
        return bidRepository.findByOrderid(orderid);
    }

}
