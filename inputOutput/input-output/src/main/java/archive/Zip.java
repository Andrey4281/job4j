package archive;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
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
        List<File> res = new LinkedList<>();
        Queue<File> queue = new LinkedList<>();
        queue.offer(new File(root));
        while (!queue.isEmpty()) {
            File current = queue.poll();
            if (!current.isDirectory()) {
                res.add(current);
            } else {
                Arrays.stream(current.listFiles())
                        .filter(file -> !(file.getName().matches(ext)))
                        .forEach(queue::offer);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(Zip.class.getName());
        Zip zip = new Zip();
        Args argsForZip = new Args(args);
        zip.pack(argsForZip.directory(),
                argsForZip.output(), argsForZip.excule());
    }
}
