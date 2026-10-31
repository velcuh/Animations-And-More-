function animation_and_more:cube/clear_tags
tag @s add cs_prop
$tag @s add cs_t_$(id)
execute unless score @s cs_id matches 1.. run function animation_and_more:cube/assign_id
scoreboard players set @s cs_cool 0
scoreboard players set @s cs_squash -2
scoreboard players set @s cs_ground 0
effect give @s minecraft:invisibility infinite 0 true
function animation_and_more:cube/dispatch_skin
playsound minecraft:entity.sulfur_cube.absorb neutral @a ~ ~ ~ 1 1.2
