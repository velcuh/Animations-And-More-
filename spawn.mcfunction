summon minecraft:item_display ~ ~0.5 ~ {Tags:["cs_view","cs_new"],item:{id:"minecraft:stick",count:1},teleport_duration:2,transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0f,0f],scale:[1f,1f,1f]}}
scoreboard players operation @e[type=minecraft:item_display,tag=cs_new,distance=..1,limit=1] cs_id = #cur cs_id
scoreboard players operation @e[type=minecraft:item_display,tag=cs_new,distance=..1,limit=1] cs_seen = #now cs_tmp
tag @e[type=minecraft:item_display,tag=cs_new,distance=..1] remove cs_new
