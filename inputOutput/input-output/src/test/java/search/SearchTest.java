package search;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class SearchTest {
    private File root;
    private List<File> xmlAndJavaFilesExpected;
    private List<File> classAndTxtFilesExpected;

    @Before
    public void setFileSystem() {
        xmlAndJavaFilesExpected = new LinkedList<>();
        classAndTxtFilesExpected = new LinkedList<>();
        try {
           root = new File(System.getProperty("java.io.tmpdir"), "root");
           root.mkdir();
           root.deleteOnExit();
           File dir1 = new File(root, "dir1");
           dir1.mkdir();
           dir1.deleteOnExit();
           File dir2 = new File(root, "dir2");
           dir2.mkdir();
           dir2.deleteOnExit();
           File dir3 = new File(dir2, "dir3");
           dir3.mkdir();
           dir3.deleteOnExit();

           String suffixXml = ".xml";
           String suffixTxt = ".txt";
           String suffixJava = ".java";
           String suffixClass = ".class";

           File fileXml = File.createTempFile("root", suffixXml, root);
           fileXml.deleteOnExit();
           File fileJava = File.createTempFile("dir2", suffixJava, dir2);
           fileJava.deleteOnExit();
           File fileTxt = File.createTempFile("dir3", suffixTxt, dir3);
           fileTxt.deleteOnExit();
           File fileClass = File.createTempFile("dir1", suffixClass, dir1);
           fileClass.deleteOnExit();

           xmlAndJavaFilesExpected.add(fileXml);
           xmlAndJavaFilesExpected.add(fileJava);
           classAndTxtFilesExpected.add(fileClass);
           classAndTxtFilesExpected.add(fileTxt);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenInvocateFilesMethodShouldGetListOfFiles() {
        Search search = new Search();
        List<String> exXmlJava = new LinkedList<>();
        exXmlJava.add(".xml");
        exXmlJava.add(".java");
        List<String> exClassTxt = new LinkedList<>();
        exClassTxt.add(".class");
        exClassTxt.add(".txt");
        List<File> xmlAndJavaFilesActual = search.files(root.getAbsolutePath(), exXmlJava);
        List<File> classAndTxtFilesActual = search.files(root.getAbsolutePath(), exClassTxt);

        assertThat(xmlAndJavaFilesActual.size(), is(xmlAndJavaFilesExpected.size()));
        assertThat(xmlAndJavaFilesActual.containsAll(xmlAndJavaFilesExpected), is(true));
        assertThat(classAndTxtFilesActual.size(), is(classAndTxtFilesExpected.size()));
        assertThat(classAndTxtFilesActual.containsAll(classAndTxtFilesExpected), is(true));

    }
}