package com.demo.web;

import com.demo.dao.CommandeSurPlaceDao;
import com.demo.entities.CommandeSurPlace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4000")
public class CommandeSurPlaceService {
    @Autowired
    CommandeSurPlaceDao commandeSurPlaceDao;

    @GetMapping("/AllOrders")
    public List<CommandeSurPlace> AllOrders(){
        return commandeSurPlaceDao.findAll();
    }

    @PostMapping("/SaveOrder")
    public void saveOrder(@RequestParam("productName") String productName,@RequestParam("Quantity") int Quantity,@RequestParam("drive") @DateTimeFormat(pattern = "yyyy-MM-dd") Date drive,@RequestParam("time") @DateTimeFormat(pattern = "HH:mm") Date heurRamassage,@RequestParam("TotalPrice") float TotalPrice){
        commandeSurPlaceDao.save(new CommandeSurPlace(productName,Quantity,TotalPrice,drive,heurRamassage));
    }

    @GetMapping("/deleteMarketOrder/{id}")
    public void deleteOrder(@PathVariable Long id){
        commandeSurPlaceDao.deleteById(id);
    }


}
