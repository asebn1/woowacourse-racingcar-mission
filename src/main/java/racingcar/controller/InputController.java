package racingcar.controller;

import racingcar.domain.Car;
import racingcar.util.Validation;
import racingcar.view.Input;
import java.util.ArrayList;
import java.util.List;

public class InputController {
    private static final String SPLIT_DELIM = ",";

    public static List<Car> inputCars() {
        return saveCars();
    }

    public static int inputTryNum() {
        return saveTryNum();
    }

    private static List<Car> saveCars() {
        while (true) {
            String names = Input.inputCarNames();

            try {
                new Car("hd");
                Validation.carNameValidation(names);
                List<Car> cars = convertStringList(names);
                return cars;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static List<Car> convertStringList(String names) {
        List<Car> cars = new ArrayList<>();
        for (String carName : names.split(SPLIT_DELIM)) {
            cars.add(new Car(carName));
        }
        return cars;
    }

    private static int saveTryNum() {
        while (true) {
            String tryValue = Input.inputTry();

            try {
                Validation.tryNumValidation(tryValue);
                return Integer.parseInt(tryValue);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
