# Introduction

This is a Java app that mimics the function of the Linux grep app. It takes three arguments: regex (pattern to be searched for), rootPath (root directory path), and outFile (output file name). It uses the provided regex expression to recursively search through the given directory for any matches within any file in the directory or its subdirectories. It then writes all lines that contain the pattern to the outFile. This app takes advantage of some core Java features such as object oriented programming,including interfaces and encapsulation, regex API, and array lists. Maven was also used to manage the project.

# Quick Start
```aidl
docker run --rm \
-v `pwd`/data:/data -v `pwd`/log:/log \
${docker_user}/grep [regex] [rootDir] [outFile]

```

#Implemenation
## Pseudocode
```aidl
matchedLines = []
for file in listFilesRecursively(rootDir)
    for line in readLines(file)
        if containsPattern(line)
            matchedLines.add(line)
 writeToFile(matchedLines)
```

## Performance Issue
It is possible for this app to run into an `OutOfMemoryError` exception should the directory being searched be larger than the heap. In order to avoid that, BufferedReader or Stream APIs can be used, data can be processed in smaller pieces that fit into the heap, or another solution would be to use off heap memory.

# Test
In order to test the grep app, specifically prepared sample data was used as test cases as well as existing sample data. Multiple input possibilities were used to ensure the functionality of the app.

# Deployment
In order to make the app more user-friendly, the Java grep app was Dockerized. A dockerfile was created, then Maven was used to package the app, a docker image was created using the packaged app, and  finally image was pushed to Docker Hub.

# Improvement
- use Buffers instead of Lists to reduce memory usage 
- write unit tests to more thoroughly test the app
