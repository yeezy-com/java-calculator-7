package calculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Application {
    public static void main(String[] args) {
        Calculator calculator = new Calculator(new Splitter());

        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("덧셈할 문자열을 입력해 주세요.");
            String s = bufferedReader.readLine();
            System.out.println("input: " + s);

            long sum = calculator.sum(s);

            System.out.println("결과 : " + sum);
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
