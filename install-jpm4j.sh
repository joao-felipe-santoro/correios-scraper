#!/bin/sh
echo 'downloading jpm4j...'
curl -sL curl -sL https://github.com/jpm4j/jpm4j.installers/raw/master/dist/biz.aQute.jpm.run.jar >jpm4j.jar
echo 'DONE!'
echo 'installing it'
sudo java -jar jpm4j.jar -g init
echo 'DONE!'
sudo jpm install com.codacy:codacy-coverage-reporter:assembly
