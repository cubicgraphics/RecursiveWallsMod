package net.cubicmods.recursivecustomstart.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.world.WorldEvent;

import net.minecraft.world.storage.ISpawnWorldInfo;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;

import net.cubicmods.recursivecustomstart.RecursivecustomstartModVariables;
import net.cubicmods.recursivecustomstart.RecursivecustomstartMod;

import java.util.Map;
import java.util.HashMap;

public class WorldStartProcedure {
	@Mod.EventBusSubscriber
	private static class GlobalTrigger {
		@SubscribeEvent
		public static void onWorldLoad(WorldEvent.Load event) {
			IWorld world = event.getWorld();
			Map<String, Object> dependencies = new HashMap<>();
			dependencies.put("world", world);
			dependencies.put("event", event);
			executeProcedure(dependencies);
		}
	}
	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				RecursivecustomstartMod.LOGGER.warn("Failed to load dependency world for procedure WorldStart!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		if ((RecursivecustomstartModVariables.MapVariables.get(world).SetNewSpawn == (false))) {
			RecursivecustomstartModVariables.MapVariables.get(world).SetNewSpawn = (boolean) (true);
			RecursivecustomstartModVariables.MapVariables.get(world).syncData(world);
			if (world.getWorldInfo() instanceof ISpawnWorldInfo)
				((ISpawnWorldInfo) world.getWorldInfo()).setSpawn(
						new BlockPos((int) 500, (int) (world.getHeight(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, (int) 500, (int) 500)), (int) 500),
						0);
		}
	}
}
