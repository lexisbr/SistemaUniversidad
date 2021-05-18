#! /bin/bash 
echo "STARTING COMPILING"
java -jar jflex-full-1.8.2.jar Lexer.jflex
java -jar java-cup-11b.jar -parser Parser -symbols sym Parser.cup
