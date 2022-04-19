package com.unip.aps_msger;

import com.unip.aps_msger.model.MessageDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

// Tentativa de implementar a api junto com o websocket
@CrossOrigin @RestController
@RequestMapping("/api") @AllArgsConstructor(onConstructor = @__(@Autowired))
public class MsgController {
    MsgService service;

    @PostMapping
    public void save(@RequestBody String message) {
        System.out.println("Controller get message: " + message);
        MessageDto dto = new MessageDto(1L, message);
        service.save(dto);
    }
}
