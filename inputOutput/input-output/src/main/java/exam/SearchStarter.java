package exam;

import search.Search;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public class SearchStarter {
    private String[] args;
    private Map<String, String> map;


    public SearchStarter(final String[] args) {
        this.args = args;
    }


    public static void main(String[] args) {
        SearchStarter searchStarter = new SearchStarter(args);
        searchStarter.start();
    }

    public void start() {
        this.map = new CommandLineAnaluze(args).parse();
        List<File> res = new Search().findFilesByPredicate(map.get(Directory.getInstance().getValue()),
                file -> file.getName().matches(map.get(RegularExpressionForSearch.getInstance().getValue())));
        try (PrintWriter writer = new PrintWriter(
                new FileOutputStream(map.get(OutputFile.getInstance().getValue())))) {
            res.stream().forEach(file -> writer.println(file.getAbsolutePath()));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
