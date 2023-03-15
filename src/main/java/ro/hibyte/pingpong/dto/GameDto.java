package ro.hibyte.pingpong.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class GameDto {
    private Long id;
    private Long player1Id;
    private Long player2Id;
    private int player1Score;
    private int player2Score;
    private String winner;

}
