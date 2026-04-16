package dto;

import lombok.*;

import java.util.List;
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class ContactsDto {
    private List<Contact> contacts;
}