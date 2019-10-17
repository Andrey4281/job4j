import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.api.Assertions.assertThat;

public class TemplateTest {
    private final Template template = new SimpleGenerator();

    @Test
    public void whenTakeTextWithKeysShouldReplaceKeysToValues() {
        String text = "I am ${name}, Who are ${subject}?";
        Map<String, String> map = new HashMap<>();
        map.put("name", "Petr");
        map.put("subject", "you");

        String result = template.generate(text, map);

        assertThat(result).isEqualTo("I am Petr, Who are you?");
    }

    @Test
    public void whenTakeTextWithInexistentKeysShouldThrowErrorOfGenerator() {
        String text = "What is your ${state}?";
        Map<String, String> map = new HashMap<>();
        map.put("name", "Petr");
        String expectedMessage = "Invalid argument.It does't exist in map of key \"state\"";
        testToCheckValidateOfTemplate(text, map, expectedMessage);
    }

    @Test
    public void whenTakeMapWithExtraKeysShouldThrowErrorOfGenerator() {
        String text = "What is your ${state}?";
        Map<String, String> map = new HashMap<>();
        map.put("state", "mood");
        map.put("name", "Petr");
        String expectedMessage = "Invalid argument.Map has extra keys";
        testToCheckValidateOfTemplate(text, map, expectedMessage);
    }

    private void testToCheckValidateOfTemplate(String text, Map<String, String> map, String expectedMessage) {
        Throwable thrown = catchThrowable(()->template.generate(text, map));
        assertThat(thrown).isInstanceOf(ErrorOfGenerator.class);
        assertThat(thrown.getMessage()).isEqualTo(expectedMessage);
    }
}