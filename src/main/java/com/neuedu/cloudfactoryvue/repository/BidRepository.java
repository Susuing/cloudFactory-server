package com.neuedu.cloudfactoryvue.repository;

import com.neuedu.cloudfactoryvue.entity.module_bid_tab;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BidRepository extends JpaRepository<module_bid_tab,String> {
    List<module_bid_tab> findByOrderid(String orderid);
}
