package step2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import step2.domain.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class PlayerTest {
    @DisplayName("플레이어 생성 테스트")
    @Test
    void createPlayer() {
        Player cat = new Player("cat");
        assertThat(cat).isEqualTo(new Player("cat"));
        assertThat(cat.getName()).isEqualTo("cat");
    }

    @DisplayName("플레이어 생성 예외 발생 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"123", "catsbi", ""})
    void createPlayerWithException(String input) {
        assertThatIllegalArgumentException()
                .isThrownBy(()-> new Player(input)).withMessage(Player.ERROR_INVALID_NAME);
    }
}
