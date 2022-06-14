#!/bin/bash
regex_pattern=".*Romeo.*Juliet.*"
src_dir="./data"
egrep -r ${regex_pattern} ${src_dir}
