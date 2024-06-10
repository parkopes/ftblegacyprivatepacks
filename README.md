# Private pack fetcher for FTB Legacy
A simple tool allowing you to download old FTB modpacks even though the original program is discontinued.

Usage: 
```
Displaying information:

java -jar bin.jar -code EXAMPLE_MOD

Name: Big boi Minecraft pack
Author: SM311YB01H4X0R
Description: Minecraft mod pack for 1337 gamers
Client download: https://dist.creeper.host/FTB2/privatepacks/LeetMod/version/LeetMod.zip
Server download: https://dist.creeper.host/FTB2/privatepacks/LeetMod/version/LeetModServer.zip
Minecraft version: 1.7.10
Available versions: 6,5,4,3,2,1
```
```
Requesting client URL:

java -jar bin.jar -code EXAMPLE_MOD -downloadCl latest
https://dist.creeper.host/FTB2/privatepacks/LeetMod/6/LeetMod.zip

java -jar bin.jar -code EXAMPLE_MOD -downloadCl 5
https://dist.creeper.host/FTB2/privatepacks/LeetMod/5/LeetMod.zip
```
```
Requesting server URL:

java -jar bin.jar -code EXAMPLE_MOD -downloadSr latest
https://dist.creeper.host/FTB2/privatepacks/LeetMod/6/LeetMod.zip

java -jar bin.jar -code EXAMPLE_MOD -downloadSr 5
https://dist.creeper.host/FTB2/privatepacks/LeetMod/5/LeetModServer.zip
```
