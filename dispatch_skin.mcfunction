scoreboard players operation #cur cs_id = @s cs_id
scoreboard players set #found cs_tmp 0
execute as @e[type=minecraft:item_display,tag=cs_view,distance=..8] if score @s cs_id = #cur cs_id run scoreboard players set #found cs_tmp 1
execute if score #found cs_tmp matches 0 at @s run function animation_and_more:view/spawn
execute if entity @s[tag=cs_t_football] run function animation_and_more:view/skin {id:"football"}
execute if entity @s[tag=cs_t_basketball] run function animation_and_more:view/skin {id:"basketball"}
execute if entity @s[tag=cs_t_tennis_ball] run function animation_and_more:view/skin {id:"tennis_ball"}
execute if entity @s[tag=cs_t_baseball] run function animation_and_more:view/skin {id:"baseball"}
execute if entity @s[tag=cs_t_hockey_puck] run function animation_and_more:view/skin {id:"hockey_puck"}
execute if entity @s[tag=cs_t_volleyball] run function animation_and_more:view/skin {id:"volleyball"}
execute if entity @s[tag=cs_t_curling_stone] run function animation_and_more:view/skin {id:"curling_stone"}
execute if entity @s[tag=cs_t_bomb] run function animation_and_more:view/skin {id:"bomb"}
execute if entity @s[tag=cs_t_dice] run function animation_and_more:view/skin {id:"dice"}
execute if entity @s[tag=cs_t_cheese] run function animation_and_more:view/skin {id:"cheese"}
execute if entity @s[tag=cs_t_squid_floaty] run function animation_and_more:view/skin {id:"squid_floaty"}
