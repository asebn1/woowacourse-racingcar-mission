package racingcar.domain;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CarsTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;
    List<Car> carValues = new ArrayList<>();

    @BeforeAll
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
        Car carOne = new Car("one", 1);
        Car carTwo = new Car("two", 2);
        Car carThree = new Car("three", 3);
        carValues.add(carOne);
        carValues.add(carTwo);
        carValues.add(carThree);
    }

    @AfterAll
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    void 자동차_최고위치검증_성공() {
        Cars cars = new Cars(carValues);
        Assertions.assertThat(cars.getMaxPosition()).isEqualTo(3);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2})
    void 자동차_최고위치검증_실패(int num) {
        Cars cars = new Cars(carValues);
        Assertions.assertThat(cars.getMaxPosition()).isNotEqualTo(num);
    }

    @Test
    void 우승자_검증_성공() {
        Cars cars = new Cars(carValues);
        Assertions.assertThat(cars.getWinners().toString()).isEqualTo("[three]");
    }

    @Test
    void 우승자_검증_실패() {
        Cars cars = new Cars(carValues);
        Assertions.assertThat(cars.getWinners()).doesNotContain("one");
        Assertions.assertThat(cars.getWinners()).doesNotContain("two");
    }
}
