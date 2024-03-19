package com.example.locationdemo.service;

import com.example.locationdemo.dto.DeviceDTO;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Service
//sınıfın tüm alanları için otomatik constructer oluşturur.
@AllArgsConstructor
@Log
public class BatchService {
    private final DeviceService service;
//1dkda bir kontrol eder, belirlenen periyotlarda güncelleme gelmiş mi diye, gelmediyse bildirim atar
    @Scheduled(fixedDelay = 10000)
    public void checkDevices(){
        log.info("checkDevices" + LocalDateTime.now());
        List<DeviceDTO> dtoList = service.list();

        Long now  = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC) * 1000;
        dtoList.forEach(dto -> {
            long timeDiff = now - dto.getLastNotify();
            if ( timeDiff > Long.valueOf(dto.getPeriod()) ) service.sendNotification(dto);
        });
    }
}
