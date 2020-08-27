package com.xgit.boot.entities;


import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/*
 * Created by tianxuanxuan
 * On 2020-08-26 17:11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
    private Long id;
    private String serial;
}
