package telephony;

import java.util.ArrayList;
import java.util.List;

public class Smartphone implements Browsable, Callable {
    private List<String> numbers;
    private List<String> urls;

    public Smartphone(List<String> numbers, List<String> urls) {
        setNumbers(numbers);
        setUrls(urls);
    }

    private void setNumbers(List<String> numbers) {
        this.numbers = new ArrayList<>(numbers);
    }

    private void setUrls(List<String> urls) {
        this.urls = new ArrayList<>(urls);
    }

    @Override
    public String browse() {
        StringBuilder result = new StringBuilder();
        String regex = "(http:\\/\\/)([a-z]+.)+";

        for (String url : urls) {
            if (url.matches(regex)){
                result.append(String.format("Browsing: %s!", url));
            } else {
                result.append("Invalid URL!");
            }
            result.append(System.lineSeparator());
        }

        return result.toString();
    }

    @Override
    public String call() {
        StringBuilder result = new StringBuilder();
        String regex = "([0-9]+)";
        for (String number : numbers) {
            if (number.matches(regex)) {
                result.append(String.format("Calling... %s", number));
            } else {
                result.append("Invalid number!");
            }
            result.append(System.lineSeparator());
        }

        return result.toString();
    }
}
