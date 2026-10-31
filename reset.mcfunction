function animation_and_more:cube/clear_tags
tag @s remove cs_prop
execute if score @s cs_id matches 1.. run scoreboard players operation #cur cs_id = @s cs_id
execute if score @s cs_id matches 1.. as @e[type=minecraft:item_display,tag=cs_view,distance=..8] if score @s cs_id = #cur cs_id run kill @s
effect clear @s minecraft:invisibility
scoreboard players reset @s cs_id
scoreboard players reset @s cs_squash
data remove entity @s CustomName
