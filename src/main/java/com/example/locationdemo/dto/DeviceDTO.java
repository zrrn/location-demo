package com.example.locationdemo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DeviceDTO {
    private Long id;
    private String name;
    private String description;
    private Double longitude;
    private Double altitude;
    private Integer period;
    private Double distance;
    private Long lastNotify;
}
