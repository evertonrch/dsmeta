package com.devsuperior.dsmeta.controller;

import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.EntityManager;
import java.util.List;

@Controller
@RequestMapping("sale")
public class SaleController {
    @Autowired
    private SaleService saleService;

    @GetMapping("all")
    @ResponseBody
    public List<Sale> sales() {
        return saleService.sales();
    }
}
