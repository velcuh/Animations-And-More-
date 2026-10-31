execute if entity @s[name="Reset"] run function animation_and_more:cube/reset
execute if entity @s[name="!Reset"] run function animation_and_more:cube/reset
execute if entity @s[name="Football",tag=!cs_t_football] run function animation_and_more:cube/set {id:"football"}
execute if entity @s[name="!Football"] run function animation_and_more:cube/set_hidden {id:"football"}
execute if entity @s[name="Basketball",tag=!cs_t_basketball] run function animation_and_more:cube/set {id:"basketball"}
execute if entity @s[name="!Basketball"] run function animation_and_more:cube/set_hidden {id:"basketball"}
execute if entity @s[name="Tennis Ball",tag=!cs_t_tennis_ball] run function animation_and_more:cube/set {id:"tennis_ball"}
execute if entity @s[name="!Tennis Ball"] run function animation_and_more:cube/set_hidden {id:"tennis_ball"}
execute if entity @s[name="Baseball",tag=!cs_t_baseball] run function animation_and_more:cube/set {id:"baseball"}
execute if entity @s[name="!Baseball"] run function animation_and_more:cube/set_hidden {id:"baseball"}
execute if entity @s[name="Hockey Puck",tag=!cs_t_hockey_puck] run function animation_and_more:cube/set {id:"hockey_puck"}
execute if entity @s[name="!Hockey Puck"] run function animation_and_more:cube/set_hidden {id:"hockey_puck"}
execute if entity @s[name="Volleyball",tag=!cs_t_volleyball] run function animation_and_more:cube/set {id:"volleyball"}
execute if entity @s[name="!Volleyball"] run function animation_and_more:cube/set_hidden {id:"volleyball"}
execute if entity @s[name="Curling Stone",tag=!cs_t_curling_stone] run function animation_and_more:cube/set {id:"curling_stone"}
execute if entity @s[name="!Curling Stone"] run function animation_and_more:cube/set_hidden {id:"curling_stone"}
execute if entity @s[name="Bomb",tag=!cs_t_bomb] run function animation_and_more:cube/set {id:"bomb"}
execute if entity @s[name="!Bomb"] run function animation_and_more:cube/set_hidden {id:"bomb"}
execute if entity @s[name="Dice",tag=!cs_t_dice] run function animation_and_more:cube/set {id:"dice"}
execute if entity @s[name="!Dice"] run function animation_and_more:cube/set_hidden {id:"dice"}
execute if entity @s[name="Cheese",tag=!cs_t_cheese] run function animation_and_more:cube/set {id:"cheese"}
execute if entity @s[name="!Cheese"] run function animation_and_more:cube/set_hidden {id:"cheese"}
execute if entity @s[name="Squid Floaty",tag=!cs_t_squid_floaty] run function animation_and_more:cube/set {id:"squid_floaty"}
execute if entity @s[name="!Squid Floaty"] run function animation_and_more:cube/set_hidden {id:"squid_floaty"}
execute if entity @s[tag=cs_prop] run function animation_and_more:cube/follow
