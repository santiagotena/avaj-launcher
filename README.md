# ajav-launcher

### Run the following commands to run the program:

``` bash
find src -name "*.java" > sources.txt
javac -d . @sources.txt
java simulation.Simulator scenario.txt
cat -e simulation.txt
```

``` bash
rm -rf aircraft/ coordinates/ factory/ provider/ simulation/ tower/
rm -rf simulation.txt sources.txt
clear
```