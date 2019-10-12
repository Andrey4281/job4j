import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleGenerator implements Template {
    private final Pattern keys = Pattern.compile("\\$\\{([a-zA-Z]+)\\}");

    @Override
    public String generate(String template, Map<String, String> map) {
        Matcher matcher = keys.matcher(template);
        Set<String> usedKeys = new HashSet<>();
        String result = new String(template);
        while (matcher.find()) {
            if (map.get(matcher.group(1)) == null) {
                throw new ErrorOfGenerator(String.format("Invalid argument.It does't exist in map of key \"%s\"", matcher.group(1)));
            }
            result = result.replace(matcher.group(), map.get(matcher.group(1)));
            usedKeys.add(matcher.group(1));
        }
        if (usedKeys.size() < map.keySet().size()) {
            throw new ErrorOfGenerator("Invalid argument.Map has extra keys");
        }
        return result;
    }
}
