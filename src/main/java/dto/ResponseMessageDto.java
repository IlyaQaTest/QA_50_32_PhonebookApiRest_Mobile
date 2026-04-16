package dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@EqualsAndHashCode
public class ResponseMessageDto {

   private String message;

   public boolean isMessage(String expected) {
      return expected != null && expected.equals(message);
   }
}
