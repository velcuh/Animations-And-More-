package dev.animationmore.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.player.PlayerModel;
import net.minecraft.client.renderer.entity.state.AvatarRenderState;

/** Applies the current animation to the player model. Poses are original; tune the numbers freely. */
public final class AnimPose {
    /** If flips spin the wrong way visually, change to -1f. */
    private static final float FLIP_SIGN = 1f;
    /** If balancing arms point down/inward instead of out, change to -1f. */
    private static final float ARM_SIGN = 1f;
    private static final float TWO_PI = (float) (Math.PI * 2);
    /** Model-space (1/16 block) height of the pivot we rotate around (waist). */
    private static final float PIVOT_Y = 12f;

    private AnimPose() {}

    private static float smooth(float t) { t = Math.max(0f, Math.min(1f, t)); return t * t * (3f - 2f * t); }

    private static void rotX(ModelPart root, float a) {
        root.xRot = a;
        root.y = PIVOT_Y * (1f - (float) Math.cos(a));
        root.z = -PIVOT_Y * (float) Math.sin(a);
    }

    private static void rotZ(ModelPart root, float a) {
        root.zRot = a;
        root.x = PIVOT_Y * (float) Math.sin(a);
        root.y = PIVOT_Y * (1f - (float) Math.cos(a));
    }

    public static void apply(PlayerModel m, AvatarRenderState state) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || state.id != mc.player.getId()) return; // local player only (v0.1)

        ModelPart root = m.root();
        float age = state.ageInTicks;
        AnimState.Kind k = AnimState.kind;

        if (k != AnimState.Kind.NONE) {
            float t = (age - AnimState.startTick) / (float) AnimState.duration;
            if (t < 0f || t >= 1f) return;
            float e = smooth(t);
            float tuck = (float) Math.sin(Math.PI * t);

            switch (k) {
                case FLIP_FWD, FLIP_BACK -> {
                    float dir = (k == AnimState.Kind.FLIP_FWD ? 1f : -1f) * FLIP_SIGN;
                    rotX(root, dir * e * TWO_PI);
                    tuckLimbs(m, tuck);
                }
                case FLIP_LEFT, FLIP_RIGHT -> {
                    float dir = (k == AnimState.Kind.FLIP_RIGHT ? 1f : -1f) * FLIP_SIGN;
                    rotZ(root, dir * e * TWO_PI);
                    tuckLimbs(m, tuck);
                }
                case ROLL -> {
                    rotX(root, FLIP_SIGN * e * TWO_PI);
                    root.y += 6f * tuck;
                    tuckLimbs(m, Math.min(1f, tuck * 1.4f));
                }
                case HERO -> {
                    float w = smooth(Math.min(1f, t * 5f)) * (1f - smooth((t - 0.7f) / 0.3f));
                    root.y += 9f * w;
                    m.body.xRot += 0.35f * w;
                    m.rightLeg.xRot = -1.45f * w;
                    m.leftLeg.xRot = 0.6f * w;
                    m.rightArm.xRot = -0.3f * w;
                    m.rightArm.zRot += 0.25f * w;
                    m.leftArm.xRot = 0.9f * w;
                    m.leftArm.zRot -= 0.4f * w;
                }
                default -> { }
            }
        } else if (AnimState.balancing) {
            float sway = 0.08f * (float) Math.sin(age * 0.3f);
            m.rightArm.xRot = 0f;
            m.leftArm.xRot = 0f;
            m.rightArm.zRot = ARM_SIGN * (1.35f + sway);
            m.leftArm.zRot = -ARM_SIGN * (1.35f - sway);
            m.rightLeg.zRot = 0.06f;
            m.leftLeg.zRot = -0.06f;
        }
    }

    private static void tuckLimbs(PlayerModel m, float amount) {
        m.rightLeg.xRot = -1.3f * amount;
        m.leftLeg.xRot = -1.3f * amount;
        m.rightArm.xRot = -1.0f * amount;
        m.leftArm.xRot = -1.0f * amount;
    }
}
