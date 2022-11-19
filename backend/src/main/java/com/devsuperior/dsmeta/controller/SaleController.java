package com.devsuperior.dsmeta.controller;

import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.service.SaleService;
import com.devsuperior.dsmeta.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("sale")
public class SaleController {
    @Autowired
    private SaleService saleService;

    @Autowired
    private SmsService smsService;

    @GetMapping("all")
    public Page<Sale> sales(
            @RequestParam(value = "from", defaultValue = "") String minDate,
            @RequestParam(value = "to", defaultValue = "") String maxDate,
            Pageable pageable) {
        return saleService.sales(minDate, maxDate, pageable);
    }

    @GetMapping("{id}/notification")
    public void notifySms(@PathVariable Long id){
        smsService.sendSms(id);
    }
}
