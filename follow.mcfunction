scoreboard players operation #cur cs_id = @s cs_id
scoreboard players set #found cs_tmp 0
execute as @e[type=minecraft:item_display,tag=cs_view,distance=..8] if score @s cs_id = #cur cs_id run function animation_and_more:view/follow
execute if score #found cs_tmp matches 0 run function animation_and_more:cube/dispatch_skin
execute store result score #og cs_tmp run data get entity @s OnGround
execute if score #og cs_tmp matches 1 if score @s cs_ground matches 0 run function animation_and_more:cube/squash
scoreboard players operation @s cs_ground = #og cs_tmp
execute if score @s cs_squash matches 0.. run scoreboard players remove @s cs_squash 1
execute if score @s cs_squash matches -1 run function animation_and_more:cube/release
execute if score @s cs_cool matches 1.. run scoreboard players remove @s cs_cool 1
function animation_and_more:fx/check_goal
