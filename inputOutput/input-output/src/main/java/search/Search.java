package search;

import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) {
        Search search = new Search();
        List<String> exts = new LinkedList<>();
        exts.add(".class");
        exts.add(".csv");
        exts.add(".java");
        List<File> list = search.files("/home/andrey/Документы/JavaCourseJob4j/job4j", exts);
        for (File el:
                list) {
            System.out.println(el.getName());
        }
        System.out.println(System.getProperty("java.io.tmpdir"));
    }


   public List<File> files(String parent, List<String> exts) {
        String patternForSearch = buildPatternForSearch(exts);
        return findFilesByPredicate(parent, file->file.getName().matches(patternForSearch));
    }

    public List<File> findFilesByPredicate(String parent, Predicate<File> predicate) {
        List<File> resultList = new LinkedList<>();
        Queue<File> queue = new LinkedList<>();
        queue.offer(new File(parent));
        while (!queue.isEmpty()) {
            File current = queue.poll();
            addChilds(current, queue);
            addFilesToList(current, resultList, predicate);
        }
        return resultList;
    }

    private void addChilds(File current, Queue<File> queue) {
        Arrays.stream(current.listFiles())
                .filter(file->file.isDirectory()).forEach(queue::offer);
    }

    private void addFilesToList(File current, List<File> list,
                                Predicate<File> predicate) {
        Arrays.stream(current.listFiles()).
                filter(file->
                        !file.isDirectory() && predicate.test(file))
                .forEach(list::add);
    }

    private String buildPatternForSearch(List<String> exts) {
        StringBuilder patternForSearch = new StringBuilder("[^\\.]+(");
        for (String el:
                exts) {
            patternForSearch.append("\\").append(el).append('|');
        }
        patternForSearch.setCharAt(patternForSearch.length()-1,')');
        return patternForSearch.toString();
    }
}
