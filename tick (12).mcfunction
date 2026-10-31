execute store result score #now cs_tmp run time query gametime
execute as @e[type=minecraft:sulfur_cube] at @s run function animation_and_more:cube/tick
execute as @e[type=minecraft:item_display,tag=cs_view] run function animation_and_more:view/tick
