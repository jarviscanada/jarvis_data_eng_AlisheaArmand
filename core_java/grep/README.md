# Introduction

[comment]: <> (&#40;50-100 words&#41;)

[comment]: <> (Discuss the design of each app. What does the app do? What technologies have you used? &#40;e.g. core java, libraries, lambda, IDE, docker, etc..&#41;)
This is a Java app that mimics the function of the Linux grep app. It takes three arguments: regex (pattern to be searched for), rootPath (root directory path), and outFile (output file name). It uses the provided regex expression to recursively search through the given directory for any matches within any file in the directory or its subdirectories. It then writes all lines that contain the pattern to the outFile. This app takes advantage of some core Java features such as object oriented programming,including interfaces and encapsulation, regex API, and array lists. Maven was also used to manage the project.

# Quick Start
[comment]: <> (How to use your apps?)
```aidl
docker run --rm \
-v `pwd`/data:/data -v `pwd`/log:/log \
${docker_user}/grep .*Romeo.*Juliet.* /data /log/grep.out

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

[comment]: <> (&#40;30-60 words&#41;)

[comment]: <> (Discuss the memory issue and how would you fix it)

# Test
[comment]: <> (How did you test your application manually? &#40;e.g. prepare sample data, run some test cases manually, compare result&#41;)
In order to test the grep app, specifically prepared sample data was used as test cases as well as existing sample data. Multiple input possibilities were used to ensure the functionality of the app.

# Deployment
In order to make the app more user-friendly, the Java grep app was Dockerized. A dockerfile was created, then Maven was used to package the app, a docker image was created using the packaged app, and  finally image was pushed to Docker Hub.

# Improvement
List three things you can improve in this project.