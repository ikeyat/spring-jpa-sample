package com.example.domain.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by ikeya on 15/09/06.
 */
@Entity
@Data
public class Room implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private Integer capacity;
}
