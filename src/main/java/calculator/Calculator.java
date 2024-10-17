package calculator;

import camp.nextstep.edu.missionutils.Console;

public class Calculator {

    public int process() {
        System.out.println("덧셈할 문자열을 입력해 주세요.");
        String userInput = Console.readLine();

        if (userInput.trim().isBlank()) {
            return 0;
        }

        String delimiter = ",:";
        if (userInput.startsWith("//")) {
            int endIdx = userInput.indexOf("\\n");
            validateWrongCustom(endIdx);
            delimiter += userInput.substring(2, endIdx);
            userInput = userInput.substring(endIdx + 2);
        }

        String[] split = userInput.split("[" + delimiter + "]");
        validateWrongFormat(split);
        validatePositiveNumber(split);
        return sum(split);
    }

    private void validateWrongFormat(String[] split) {
        // 구분자 사이 숫자가 아닌 입력의 예외처리
        // 커스텀구분자, 기본구분자에 포함 안되는 구분자 사용
        for (String s : split) {
            try {
                Integer.parseInt(s);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("잘못된 포맷");
            }
        }
    }

    private static void validateWrongCustom(int endIdx) {
        if (endIdx == -1 || endIdx - 2 > 1) {
            throw new IllegalArgumentException();
        }
    }

    private static void validatePositiveNumber(String[] split) {
        for (String s : split) {
            if (Integer.parseInt(s) <= 0) {
                throw new IllegalArgumentException("음수 입력 x");
            }
        }
    }

    private static int sum(String[] split) {
        int sum = 0;
        for (String c : split) {
            sum += Integer.parseInt(c);
        }

        return sum;
    }
}
