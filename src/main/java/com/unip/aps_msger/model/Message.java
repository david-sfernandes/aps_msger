package com.unip.aps_msger.model;

import lombok.*;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class Message {
    private String text;
    private String date;
    private String img;
    private String sendFrom;
}
