package dev.animationmore.client;

import java.util.Set;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

/** Client-side state machine for the LOCAL player's animations (original work). */
public final class AnimState {
    public enum Kind { NONE, FLIP_FWD, FLIP_BACK, FLIP_LEFT, FLIP_RIGHT, ROLL, HERO }

    // ---- tunables -------------------------------------------------------------------------
    public static final int FLIP_TICKS = 14;
    public static final int ROLL_TICKS = 14;
    public static final int HERO_TICKS = 24;
    public static final double MIN_FALL_FOR_LANDING = 4.0;

    // ---- shared with the renderer ----------------------------------------------------------
    public static volatile Kind kind = Kind.NONE;
    public static volatile int startTick = 0;
    public static volatile int duration = 1;
    public static volatile boolean balancing = false;

    private static boolean wasAir, wasShift;
    private static int airTicks;
    private static double maxFall;

    private static final Set<String> BALANCE_HINTS = Set.of("bars", "chain", "end_rod", "lightning_rod", "cauldron");

    private AnimState() {}

    public static void tick(Minecraft mc) {
        LocalPlayer p = mc.player;
        if (p == null || mc.level == null) {
            kind = Kind.NONE; balancing = false; wasAir = false; airTicks = 0; maxFall = 0;
            return;
        }

        boolean ground = p.onGround();
        boolean shift = p.isShiftKeyDown();
        boolean airborne = !ground && !p.isInWater() && !p.isInLava() && !p.onClimbable()
                && !p.isFallFlying() && !p.isPassenger() && !p.getAbilities().flying;

        if (airborne) {
            airTicks++;
            maxFall = Math.max(maxFall, (double) p.fallDistance);
            // crouch mid-air -> flip (direction from movement)
            if (shift && !wasShift && airTicks > 4 && kind == Kind.NONE) {
                startFlip(p);
            }
        } else {
            if (ground && wasAir) onLand(p);
            airTicks = 0;
            maxFall = 0;
        }

        if (kind != Kind.NONE && p.tickCount - startTick >= duration) kind = Kind.NONE;

        balancing = ground && !shift && kind == Kind.NONE && standingOnThinBlock(p);

        wasAir = airborne;
        wasShift = shift;
    }

    private static void start(Kind k, int ticks, LocalPlayer p) {
        kind = k; duration = ticks; startTick = p.tickCount;
    }

    private static void startFlip(LocalPlayer p) {
        Vec3 v = p.getDeltaMovement();
        double yaw = Math.toRadians(p.getYRot());
        double fwd = v.x * -Math.sin(yaw) + v.z * Math.cos(yaw);
        double right = v.x * -Math.cos(yaw) + v.z * -Math.sin(yaw);
        Kind k;
        if (Math.abs(fwd) < 0.03 && Math.abs(right) < 0.03) k = Kind.FLIP_BACK; // standing still: backflip
        else if (Math.abs(fwd) >= Math.abs(right)) k = fwd >= 0 ? Kind.FLIP_FWD : Kind.FLIP_BACK;
        else k = right >= 0 ? Kind.FLIP_RIGHT : Kind.FLIP_LEFT;
        start(k, FLIP_TICKS, p);
    }

    private static void onLand(LocalPlayer p) {
        if (kind == Kind.FLIP_FWD || kind == Kind.FLIP_BACK || kind == Kind.FLIP_LEFT || kind == Kind.FLIP_RIGHT) {
            kind = Kind.NONE;
        }
        if (maxFall >= MIN_FALL_FOR_LANDING) {
            if (p.isSprinting()) start(Kind.ROLL, ROLL_TICKS, p);
            else start(Kind.HERO, HERO_TICKS, p);
        }
    }

    private static boolean standingOnThinBlock(LocalPlayer p) {
        BlockState s = p.level().getBlockState(p.blockPosition().below());
        if (s.is(BlockTags.FENCES) || s.is(BlockTags.WALLS)) return true;
        String path = BuiltInRegistries.BLOCK.getKey(s.getBlock()).getPath();
        for (String h : BALANCE_HINTS) if (path.contains(h)) return true;
        return false;
    }
}
