package com.devsuperior.dsmeta.controller;

import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("sale")
public class SaleController {
    @Autowired
    private SaleService saleService;

    @GetMapping("all")
    @ResponseBody
    public Page<Sale> sales(
            @RequestParam(value = "from", defaultValue = "") String minDate,
            @RequestParam(value = "to", defaultValue = "") String maxDate,
            Pageable pageable) {
        return saleService.sales(minDate, maxDate, pageable);
    }
}
