@echo off
REM ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
REM Universidad de los Andes (Bogotá - Colombia)
REM Departamento de Ingeniería de Sistemas y Computación 
REM Licenciado bajo el esquema Academic Free License version 2.1 
REM
REM Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
REM Ejercicio: n9_aerolinea
REM Autor: Mario Sánchez - 07-dic-2005
REM ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

REM ---------------------------------------------------------
REM Ejecucion de las pruebas
REM ---------------------------------------------------------

cd..
java -classpath ./lib/aerolinea.jar;./test/lib/aerolineaTest.jar;./test/lib/junit.jar junit.swingui.TestRunner uniandes.cupi2.aerolinea.test.AerolineaTest
java -classpath ./lib/aerolinea.jar;./test/lib/aerolineaTest.jar;./test/lib/junit.jar junit.swingui.TestRunner uniandes.cupi2.aerolinea.test.CiudadTest
java -classpath ./lib/aerolinea.jar;./test/lib/aerolineaTest.jar;./test/lib/junit.jar junit.swingui.TestRunner uniandes.cupi2.aerolinea.test.VueloTest
cd bin