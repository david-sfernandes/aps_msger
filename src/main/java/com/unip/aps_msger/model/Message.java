package com.unip.aps_msger.model;

import lombok.*;

import javax.persistence.*;

@Getter @Setter
@Entity @Builder
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String msg;
}
