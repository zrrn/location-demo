package com.example.locationdemo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NotifyDeviceDTO {
    private Long id;
    private Double longitude;
    private Double altitude;
}
