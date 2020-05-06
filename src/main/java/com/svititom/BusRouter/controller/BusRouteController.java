package com.svititom.BusRouter.controller;

import com.svititom.BusRouter.model.BusRoute;
import com.svititom.BusRouter.service.BusRouteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class BusRouteController {

    public static final Logger log = LoggerFactory.getLogger(BusRouteController.class);

    @Autowired
    BusRouteService busRouteService;

    @GetMapping("/routes")
    public String getBusRoutes(Model model){
        log.info("I got resolved!");
        List<BusRoute> busRoutes = busRouteService.getAllBusRoutes();
        model.addAttribute("busRoutes", busRoutes);
        return "busroutes";
    }

}
