package com.example.locationdemo.service;

import com.example.locationdemo.controller.UserWebSocketController;
import com.example.locationdemo.dto.DeviceDTO;
import com.example.locationdemo.dto.NotifyDeviceDTO;
import com.example.locationdemo.entity.Device;
import com.example.locationdemo.jpa.DeviceRepository;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.stream.Collectors;

//sınıfın tüm alanları için otomatik constructer oluşturur.
@AllArgsConstructor
@Component
@Log
//devicela ilgili servisler yazıldı
public class DeviceService {
    private final DeviceRepository repository;
    private final ModelMapper mapper;

    private final UserWebSocketController socketController;
//device ı id ye göre bulup döndürür
    public DeviceDTO getById(Long id) {
        return mapper.map(repository.getReferenceById(id), DeviceDTO.class);
    }
//yeni device create
    public DeviceDTO create(DeviceDTO dto) {
        dto.setId(null);
        return mapper.map(repository.save(mapper.map(dto, Device.class)), DeviceDTO.class);
    }
//device update
    public DeviceDTO update(DeviceDTO dto) {
        return mapper.map(repository.save(mapper.map(dto, Device.class)), DeviceDTO.class);
    }
//device delete olcaksa eklenebilir
    public void delete(Long id) {
        repository.deleteById(id);
    }
//deviceları listeleme
    public List<DeviceDTO> list() {
        return repository.findAll().stream().map(t -> mapper.map(t, DeviceDTO.class)).collect(Collectors.toList());
    }
//cihazdan bildiirim alma
    public DeviceDTO notifyDeviceLocation(NotifyDeviceDTO dto) throws IOException {
        DeviceDTO deviceDTO = getById(dto.getId());
        deviceDTO.setAltitude(dto.getAltitude());
        deviceDTO.setLongitude(dto.getLongitude());
        deviceDTO.setLastNotify(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC) * 1000);
        sendNotification(deviceDTO);
        return update(deviceDTO);
    }
//cihaza bildirim gönderme
    public void sendNotification(DeviceDTO dto) {
        double longitude = Math.abs(dto.getLongitude() - UserWebSocketController.longitude);
        double altitude = Math.abs(dto.getAltitude() - UserWebSocketController.altitude);
        Double distance = Math.hypot(longitude, altitude);
        if (dto.getDistance() <= distance) {
            try {
                socketController.notifyUser(dto.getName() + ": erişim kayboldu");
            } catch (IOException e) {
                log.info(e.getLocalizedMessage());
            }
        }
    }
}
