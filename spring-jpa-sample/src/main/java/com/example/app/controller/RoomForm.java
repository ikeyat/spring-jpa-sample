package com.example.app.controller;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by ikeya on 15/09/06.
 */
@Data
public class RoomForm  implements Serializable {
    @NotBlank
    private String name;
    @NotNull
    @Min(1)
    @NumberFormat
    private Integer capacity;
}
