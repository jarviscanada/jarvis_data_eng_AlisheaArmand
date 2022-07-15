#!/bin/bash
regext_pattern=".*Romeo.*Juliet.*"
src_dir="./data"
wget -O grep-demo.jar https://github.com/jarviscanada/jarvis_data_eng_demo/raw/feature/grep_demo_jar/core_java/grep/target/grep-1.0-SNAPSHOT.jar
outfile=grep_$(date +%F_%T).txt
java -jar grep-demo.jar ${regex_pattern} ${src_dir} ./out/${outfile}
cat out/$outfile

