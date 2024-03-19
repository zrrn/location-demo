package com.example.locationdemo.jpa;

import com.example.locationdemo.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//anotasyonu veritabanı işlemlerini gerçekleştiren sınıfları belirtmek için kullanılır.
//Repository sınıfı
@Repository
//anotasyonu veritabanı işlemlerini gerçekleştiren sınıfları belirtmek için kullanılır.
public interface DeviceRepository extends JpaRepository<Device, Long> {
}
