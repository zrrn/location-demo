package com.example.locationdemo.controller;

import com.example.locationdemo.dto.DeviceDTO;
import com.example.locationdemo.dto.NotifyDeviceDTO;
import com.example.locationdemo.service.DeviceService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/device")
@AllArgsConstructor
public class DeviceController {
    private final DeviceService service;

    @PostMapping("/create")
    public DeviceDTO create(DeviceDTO dto) {
        return service.create(dto);
    }

    @PutMapping("/update")
    public DeviceDTO update(DeviceDTO dto) {
        return service.create(dto);
    }

    @DeleteMapping("/delete")
    public void update(Long id) {
        service.delete(id);
    }

    @GetMapping("/get")
    public DeviceDTO get(Long id) {
        return service.getById(id);
    }

    @GetMapping("/list")
    public List<DeviceDTO> list() {
        return service.list();
    }

    @PostMapping("/notify-location")
    public DeviceDTO notifyDeviceLocation(NotifyDeviceDTO dto) throws IOException {
        return service.notifyDeviceLocation(dto);
    }
}
