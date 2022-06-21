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
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.stream.Collectors;
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

       try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(rootPath))) {
           for (Path path : stream) {
               if (!Files.isDirectory(path)) {
                   listFilesRecursively.add(path.toFile());
               }
           }
       } catch (IOException e) {
           logger.error("no such directory", e);
       }
        return listFilesRecursively;


    }

    @Override
    public List<String> readLines(File inputFile) {
        List<String> lines = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(rootPath))) {
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
                if (containsPattern(regex)) {
                    matchedLines.add(s);
                    writeToFile(matchedLines);
                }
            }
        }

    }

    public static void main(String[] args) {

        if (args.length != 3) {
            throw new IllegalArgumentException("USAGE: JavaGrep regex roothPath outFile");
        }


        BasicConfigurator.configure();

        JavaGrepImp javaGrepImp = new JavaGrepImp();
        javaGrepImp.setRegex(args[0]);
        javaGrepImp.setRootPath(args[1]);
        javaGrepImp.setOutFile(args[2]);

        try {
            javaGrepImp.process();
        } catch (Exception ex) {
            javaGrepImp.logger.error("Error: Unable to process", ex);
        }

    }

}
