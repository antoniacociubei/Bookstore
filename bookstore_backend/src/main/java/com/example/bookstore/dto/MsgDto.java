package com.example.bookstore.dto;

import com.example.bookstore.constraints.Status;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MsgDto {

    private String message;
    private String reciver;
    private String sender;
    private Status status;

}
