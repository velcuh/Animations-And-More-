scoreboard players operation #cur cs_id = @s cs_id
scoreboard players set @s cs_squash 3
execute as @e[type=minecraft:item_display,tag=cs_view,distance=..8] if score @s cs_id = #cur cs_id run data merge entity @s {start_interpolation:0,interpolation_duration:2,transformation:{translation:[0f,-0.1f,0f],scale:[1.18f,0.8f,1.18f]}}
