#!/bin/bash
set -e

find . -type f -name '*.class' -delete
javac -cp *:lib/*:. -sourcepath . test/Project2Evaluation.java -Xlint:unchecked
java -cp *:lib/*:. test.Project2Evaluation
