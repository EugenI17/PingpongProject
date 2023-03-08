package ro.hibyte.pingpong.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder

public class PlayerDto {
    private Long id;
    private String name;
    private int wins;
}
