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

### Incorrect files
``` bash
java simulation.Simulator incorrect_scenarios/iterations.txt && cat -e simulation.txt
java simulation.Simulator incorrect_scenarios/type.txt && cat -e simulation.txt
java simulation.Simulator incorrect_scenarios/type2.txt && cat -e simulation.txt
java simulation.Simulator incorrect_scenarios/longitude.txt && cat -e simulation.txt
java simulation.Simulator incorrect_scenarios/longitude2.txt && cat -e simulation.txt
java simulation.Simulator incorrect_scenarios/latitude.txt && cat -e simulation.txt
java simulation.Simulator incorrect_scenarios/height.txt && cat -e simulation.txt
```