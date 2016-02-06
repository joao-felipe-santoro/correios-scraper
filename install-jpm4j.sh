#!/bin/sh
echo 'downloading jpm4j...'
curl -sL http://bit.ly/jpm4j >jpm4j.jar
echo 'DONE!'
echo 'installing it'
sudo java -jar jpm4j.jar -g init
echo 'DONE!'
sudo jpm install com.codacy:codacy-coverage-reporter:assembly
