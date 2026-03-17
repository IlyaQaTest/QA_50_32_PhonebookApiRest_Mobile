package dto;

import lombok.*;

@Setter
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenDto {
    private String token;
    public String getToken() {
        return token;
    }
    public void setToken(String token) {}
}
