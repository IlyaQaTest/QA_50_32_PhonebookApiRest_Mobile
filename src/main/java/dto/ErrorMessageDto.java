package dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ErrorMessageDto {
    private String message;
}