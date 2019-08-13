package archive;

import search.Search;

import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void pack(File root, File target, String ext) {
        List<File> sources = seekBy(root.getAbsolutePath(), ext);
        try (ZipOutputStream zip = new ZipOutputStream(
                new BufferedOutputStream(new FileOutputStream(target)))) {
            for (File current: sources) {
                try (BufferedInputStream out =
                             new BufferedInputStream(new FileInputStream(current))) {
                    zip.putNextEntry(new ZipEntry(
                            current.getPath().
                                    substring(current.getPath().indexOf(root.getName()))
                    ));
                    zip.write(out.readAllBytes());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<File> seekBy(String root, String ext) {
        return new Search().findFilesByPredicate(root, file -> !(file.getName().matches(ext)));
    }

    public static void main(String[] args) {
        Zip zip = new Zip();
        Args argsForZip = new Args(args);
        Map<String, String> map = argsForZip.parse();
        zip.pack(new File(map.get("-d")),
                new File(map.get("-o")), map.get("-e"));
    }
}
