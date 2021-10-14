package net.cubicmods.recursivecustomstart.procedures;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.Rotation;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Mirror;

import net.cubicmods.recursivecustomstart.RecursivecustomstartMod;

import java.util.Map;

public class GenerateNSWallProcedure {
	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				RecursivecustomstartMod.LOGGER.warn("Failed to load dependency x for procedure GenerateNSWall!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				RecursivecustomstartMod.LOGGER.warn("Failed to load dependency z for procedure GenerateNSWall!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				RecursivecustomstartMod.LOGGER.warn("Failed to load dependency world for procedure GenerateNSWall!");
			return;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		double Y = 0;
		double Z = 0;
		Z = (double) (Z + 16);
		for (int index0 = 0; index0 < (int) (61); index0++) {
			Y = (double) 0;
			if (((((Z / 16) - 1) % 6) == 0)) {
				for (int index1 = 0; index1 < (int) (16); index1++) {
					if (world instanceof ServerWorld) {
						Template template = ((ServerWorld) world).getStructureTemplateManager()
								.getTemplateDefaulted(new ResourceLocation("recursivecustomstart", "nssupportstructure1"));
						if (template != null) {
							template.func_237144_a_((ServerWorld) world, new BlockPos((int) x, (int) Y, (int) (Z + z)),
									new PlacementSettings().setRotation(Rotation.NONE).setMirror(Mirror.NONE).setChunk(null).setIgnoreEntities(false),
									((World) world).rand);
						}
					}
					Y = (double) (Y + 16);
				}
			} else {
				for (int index2 = 0; index2 < (int) (16); index2++) {
					if (world instanceof ServerWorld) {
						Template template = ((ServerWorld) world).getStructureTemplateManager()
								.getTemplateDefaulted(new ResourceLocation("recursivecustomstart", "nsflatwall1"));
						if (template != null) {
							template.func_237144_a_((ServerWorld) world, new BlockPos((int) x, (int) Y, (int) (Z + z)),
									new PlacementSettings().setRotation(Rotation.NONE).setMirror(Mirror.NONE).setChunk(null).setIgnoreEntities(false),
									((World) world).rand);
						}
					}
					Y = (double) (Y + 16);
				}
			}
			Z = (double) (Z + 16);
		}
	}
}
