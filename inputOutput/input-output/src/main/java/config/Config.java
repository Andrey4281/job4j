package config;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            String line;
            Pattern pattern = Pattern.compile("[^\\s]+\\s*=\\s*[^\\s]+");
            while ((line = read.readLine()) != null) {
                if (!line.matches("(\\s*#.*|^\\s*$)")) {
                    Matcher matcher = pattern.matcher(line);
                    if (matcher.find()) {
                        String[] temp = matcher.group().split("\\s*=\\s*");
                        values.put(temp[0], temp[1]);
                    }
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public int getCountOfValues() {
        return values.size();
    }

    public static void main(String[] args) {
        System.out.println(new Config("app.properties"));
    }
}
