package com.kucheruk.drone.drone_devirery_system.data.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryRequest {
    @NotEmpty
    private String serialNumber;

    @NotEmpty
    private String source;

    @NotEmpty
    private String destination;

    @NotEmpty(message = "Requires list of medication codes to be delivered")
    private List<String> items;

}
