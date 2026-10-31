scoreboard players operation #diff cs_tmp = #now cs_tmp
scoreboard players operation #diff cs_tmp -= @s cs_seen
execute if score #diff cs_tmp matches 40.. run kill @s
