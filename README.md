# Animation & More - full source (Fabric, Minecraft Java 26.2)

One project, one jar, two parts:
1. Sulfur Cube props (data + resources, no code): name a Sulfur Cube Football, Basketball, Tennis Ball,
   Baseball, Hockey Puck, Volleyball, Curling Stone, Bomb, Dice, Cheese, Squid Floaty. `!Name` hides the label,
   `Reset` undoes it. Goal confetti on trapdoors. Squash-and-stretch on landing.
   Files: src/main/resources/data/animation_and_more/function/ and assets/animation_and_more/
2. Original third-person player animations (Java client code): mid-air flip (crouch in air), roll landing,
   hero landing, balancing on fences/walls/bars. Files: src/main/java/dev/animationmore/client/

## Build (JDK 25)
1. Copy `gradle/wrapper/` + `gradlew*` from the official Fabric example mod for 26.2
   (or `gradle wrapper --gradle-version 9.7.1`).
2. `./gradlew build` -> jar in build/libs/. Install with Fabric Loader 0.19.3+ and Fabric API.
   Remove the old animation_and_more-0.1.0.jar first (same mod id).

## Not compiled/run by me
Likely fix points if the build fails: `state.id` (AvatarRenderState), the PlayerModel.setupAnim descriptor in
PlayerModelMixin, Loom version (`1.18-SNAPSHOT` in gradle.properties), Gradle wrapper version.
Paste any error and I'll patch it. Tuning knobs: FLIP_SIGN / ARM_SIGN in AnimPose.java.
Props: unverified in-game - invisibility hiding, prop size/offset (view/spawn, view/follow), sounds/particles.
