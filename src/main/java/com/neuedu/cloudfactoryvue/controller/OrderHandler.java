package com.neuedu.cloudfactoryvue.controller;

import com.neuedu.cloudfactoryvue.entity.frame_user_tab;
import com.neuedu.cloudfactoryvue.entity.module_device_type_tab;
import com.neuedu.cloudfactoryvue.entity.module_factory_tab;
import com.neuedu.cloudfactoryvue.entity.module_order_tab;
import com.neuedu.cloudfactoryvue.repository.FactoryRepository;
import com.neuedu.cloudfactoryvue.repository.OrderRepository;
import com.neuedu.cloudfactoryvue.repository.ProductRepository;
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
@RequestMapping("/order")
public class OrderHandler {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private FactoryRepository factoryRepository;
    @Autowired
    private FactoryHandler factoryHandler;


    @GetMapping("/findAllOrder")
    public List<module_order_tab> findAllOrder(){
        return orderRepository.findAll();
    }

    @GetMapping("/findAllOrder/{page}/{size}")
    public Page<module_order_tab> findAllOrder(@PathVariable("page") int page, @PathVariable("size") int size){
        Pageable pageable = PageRequest.of(page-1, size);
        return orderRepository.findAll(pageable);
    }

    @GetMapping("/findPutOrder")
    public List<module_order_tab> findPutOrder(){
        List<module_order_tab> list = new ArrayList<>();
        List<module_order_tab> orders = findAllOrder();
        for (int i =0 ;i < orders.size();i++){
            if ("2".equals(orders.get(i).getOrderstatus())){
                list.add(orders.get(i));
            }
        }
        return list;
    }
    @PutMapping("/updateOrderStatus/{id}")
    public module_order_tab updateOrderStatus(@PathVariable("id")String id){
        module_order_tab order = orderRepository.findById(id).get();
        order.setOrderstatus("3");
        return orderRepository.save(order);
    }
    @GetMapping("/findByUserid/{userid}")
    public List<module_order_tab> findByUserid(@PathVariable("userid") String login_name){
        frame_user_tab user = userRepository.findByLogin_name(login_name);
        List<module_order_tab> result = new ArrayList<module_order_tab>();
        if(user != null){
            result = orderRepository.findByUserid(user.getId());
            for(module_order_tab o:result){
//                o.setProductid(productRepository.findById(o.getProductid()).get().getProductname());
                if(o.getFactoryid() != null){
                    o.setFactoryid(factoryRepository.findById(o.getFactoryid()).get().getFactoryname());
                }
            }
        }
        return result;
    }

    @GetMapping("/findFinishOrder/{userid}")
    public List<module_order_tab> findFinishOrder(@PathVariable("userid") String login_name){
        List<module_factory_tab> factorys = factoryHandler.findMyFactory(login_name);
        List<module_order_tab> orders = findAllOrder();
        List<module_order_tab> list = new ArrayList<>();
        for (int i =0 ;i < orders.size(); i++){
            String status = orders.get(i).getOrderstatus();
            if ("1".equals(status) || "2".equals(status) || "3".equals(status)){
                continue;
            }
            for (int j = 0;j < factorys.size(); j++){
                if (factorys.get(j).getId().equals(orders.get(i).getFactoryid())){
                    list.add(orders.get(i));
                }
            }
        }
        return list;
    }

    @PostMapping("/update")
    public String updateOrder(@RequestBody module_order_tab order){
        order.setOrderstatus("1");
        module_order_tab result = orderRepository.save(order);
        if(result != null){
            return "success";
        }else{
            return "failure";
        }
    }
    @PostMapping("/save")
    public String save(@RequestBody module_order_tab order){
        order.setOrderstatus("1");
        module_order_tab result = orderRepository.save(order);
        if(result != null){
            return "success";
        }else{
            return "failure";
        }
    }

    @PostMapping("/updateStatus")
    public String update(@RequestBody module_order_tab order){
        module_order_tab result = orderRepository.findById(order.getId()).get();
        if(result != null){
            if("1".equals(result.getOrderstatus())){
                result.setOrderstatus("2");
            }else if("6".equals(result.getOrderstatus())){
                result.setOrderstatus("7");
            }else if("7".equals(result.getOrderstatus())){
                result.setOrderstatus("8");
            }else if("4".equals(result.getOrderstatus())){
                result.setOrderstatus("5");
            }else if("5".equals(result.getOrderstatus())){
                result.setOrderstatus("6");
            } else if("3".equals(result.getOrderstatus())){
                result.setOrderstatus("4");
            }
            orderRepository.save(result);
            return "success";
        }else{
            return "failure";
        }
    }
    @PostMapping("/scheduling/{id}")
    public String scheduling(@PathVariable("id")String id){
        module_order_tab result = orderRepository.findById(id).get();
        if(result != null){
            if("3".equals(result.getOrderstatus())){
                result.setOrderstatus("4");
            }
            orderRepository.save(result);
            return "success";
        }else{
            return "failure";
        }
    }
    @PostMapping("/update/{orderid}/{factoryid}")
    public void update(@PathVariable("orderid") String orderid,@PathVariable("factoryid") String factoryid){
        module_order_tab order = orderRepository.findById(orderid).get();
        order.setFactoryid(factoryid);
        order.setOrderstatus("3");
        module_order_tab result = orderRepository.save(order);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable("id") String id){
        orderRepository.deleteById(id);
    }

}
