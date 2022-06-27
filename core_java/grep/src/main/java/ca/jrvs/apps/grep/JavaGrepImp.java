package grep.src.main.java.ca.jrvs.apps.grep;

//import java.util.logging.Logger;
import org.slf4j.Logger;
import  org.slf4j.LoggerFactory;

import java.awt.*;
import java.io.*;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;
import java.util.function.Consumer;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.log4j.BasicConfigurator;


public class JavaGrepImp implements JavaGrep{

    final Logger logger = LoggerFactory.getLogger(JavaGrep.class);

    private String regex;
    private String rootPath;
    private String outFile;

    @Override
    public String getRegex() {
        return regex;
    }

    @Override
    public void setRegex(String regex) {
        this.regex = regex;
    }

    @Override
    public String getRootPath() {
        return rootPath;
    }

    @Override
    public void setRootPath(String rootPath) {
        this.rootPath = rootPath;
    }

    @Override
    public String getOutFile() {
        return outFile;
    }

    @Override
    public void setOutFile(String outFile) {
        this.outFile = outFile;
    }



    @Override
    public List<File> listFiles(String rootDir) {
        List<File> listFilesRecursively = new ArrayList<>();
        Path path = Paths.get(rootDir);
        try {
            List<Path> paths = walkFiles(path);
            paths.forEach(x -> listFilesRecursively.add(x.toFile()));
        } catch (IOException e) {
            logger.error("no such directory", e);
            System.out.println("no such directory");
       }
        System.out.println(listFilesRecursively);
        return listFilesRecursively;
    }

    private static List<Path> walkFiles(Path path) throws IOException {
        List<Path> result;
        try (Stream<Path> walk = Files.walk(path)) {
            result = walk.filter(Files :: isRegularFile).collect(Collectors.toList());
        }
        return result;
    }

    @Override
    public List<String> readLines(File inputFile) {
        List<String> lines = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            for (String line; (line = br.readLine()) != null; ) {
                lines.add(line);
            }
        } catch (IllegalArgumentException | IOException e) {
            logger.error("inputFile is not a file", e);
        }

        return lines;
    }

    @Override
    public boolean containsPattern(String line) {

        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(line);
        boolean matchFound = m.find();
        if (matchFound)
            return true;
        else
            return false;
    }

    @Override
    public void writeToFile(List<String> lines) throws IOException {
        for (int i = 0; i < lines.size(); i++) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(outFile, true))) {
                String s;
                s = lines.get(i);
                bw.write(s);
                bw.newLine();
                bw.flush();
            } catch (IOException e) {
                logger.error("write failed", e);
            }
        }

    }

    @Override
    public void process() throws IOException {
      List<String> matchedLines = new ArrayList<>();
        for (File file: listFiles(rootPath)) {
            for (String s: readLines(file)) {
                if (containsPattern(s)) {
                    matchedLines.add(s);
                    writeToFile(matchedLines);
                }
            }
        }

    }

    public static void main(String[] args) {



        if (args.length != 3) {
            throw new IllegalArgumentException("USAGE: JavaGrep regex rootPath outFile");
        }

        BasicConfigurator.configure();

        JavaGrepImp javaGrepImp = new JavaGrepImp();
        javaGrepImp.setRegex(args[0]);
        javaGrepImp.setRootPath(args[1]);
        javaGrepImp.setOutFile(args[2]);
        /*javaGrepImp.setRegex("(.*Romeo.*Juliet.*)");
        javaGrepImp.setRootPath("C:\\Users\\aarma\\OneDrive\\Desktop\\test");
        javaGrepImp.setOutFile("C:\\Users\\aarma\\OneDrive\\Documents\\GitHub\\jarvis_data_eng_AlisheaArmand\\core_java\\grep\\out\\grep.txt");*/

        try {
            javaGrepImp.process();
        } catch (Exception ex) {
            javaGrepImp.logger.error("Error: Unable to process", ex);
        }

    }

}
