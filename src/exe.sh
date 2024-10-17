#!/bin/bash

echo "Compilando o arquivo Java..."
javac JsfTemplateDownloader.java

echo "Criando o JAR executável..."
jar cfm jsfapp.jar MANIFEST.MF *.class

echo "Executando o JAR..."
java -jar jsfapp.jar
