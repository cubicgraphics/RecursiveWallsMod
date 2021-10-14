package net.cubicmods.recursivecustomstart.procedures;

import net.minecraftforge.fml.server.ServerLifecycleHooks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.TickEvent;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.ChatType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.Util;
import net.minecraft.util.Rotation;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Mirror;
import net.minecraft.server.MinecraftServer;
import net.minecraft.entity.Entity;

import net.cubicmods.recursivecustomstart.block.StructurebedrockBlock;
import net.cubicmods.recursivecustomstart.RecursivecustomstartModVariables;
import net.cubicmods.recursivecustomstart.RecursivecustomstartMod;

import java.util.Map;
import java.util.HashMap;

public class GenerateStructuresDynamiclyOnPlayerTickProcedure {
	@Mod.EventBusSubscriber
	private static class GlobalTrigger {
		@SubscribeEvent
		public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
			if (event.phase == TickEvent.Phase.END) {
				Entity entity = event.player;
				World world = entity.world;
				double i = entity.getPosX();
				double j = entity.getPosY();
				double k = entity.getPosZ();
				Map<String, Object> dependencies = new HashMap<>();
				dependencies.put("x", i);
				dependencies.put("y", j);
				dependencies.put("z", k);
				dependencies.put("world", world);
				dependencies.put("entity", entity);
				dependencies.put("event", event);
				executeProcedure(dependencies);
			}
		}
	}
	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				RecursivecustomstartMod.LOGGER.warn("Failed to load dependency x for procedure GenerateStructuresDynamiclyOnPlayerTick!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				RecursivecustomstartMod.LOGGER.warn("Failed to load dependency z for procedure GenerateStructuresDynamiclyOnPlayerTick!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				RecursivecustomstartMod.LOGGER.warn("Failed to load dependency world for procedure GenerateStructuresDynamiclyOnPlayerTick!");
			return;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		double Xstart = 0;
		double Zstart = 0;
		double Z1 = 0;
		double Y = 0;
		double X1 = 0;
		if ((((world.getWorldInfo().getDayTime()) % 5) == 0)) {
			Xstart = (double) ((((x - (x % 16)) / 16) + 1) - RecursivecustomstartModVariables.MapVariables.get(world).GenerateStructureRenderDist);
			for (int index0 = 0; index0 < (int) (((RecursivecustomstartModVariables.MapVariables.get(world).GenerateStructureRenderDist * 2)
					- 1)); index0++) {
				if (((Xstart % 62) == 0)) {
					if (!world.isRemote()) {
						MinecraftServer mcserv = ServerLifecycleHooks.getCurrentServer();
						if (mcserv != null)
							mcserv.getPlayerList().func_232641_a_(new StringTextComponent("EW wall"), ChatType.SYSTEM, Util.DUMMY_UUID);
					}
					Z1 = (double) ((((z - (z % 16)) / 16) + 1)
							- RecursivecustomstartModVariables.MapVariables.get(world).GenerateStructureRenderDist);
					for (int index1 = 0; index1 < (int) (((RecursivecustomstartModVariables.MapVariables.get(world).GenerateStructureRenderDist * 2)
							- 1)); index1++) {
						if ((!((world.getBlockState(new BlockPos((int) (Xstart * 16), (int) 0, (int) (Z1 * 16))))
								.getBlock() == StructurebedrockBlock.block))) {
							if (((Z1 % 62) == 0)) {
								if (world instanceof ServerWorld) {
									Template template = ((ServerWorld) world).getStructureTemplateManager()
											.getTemplateDefaulted(new ResourceLocation("recursivecustomstart", "cornerwallbedrock"));
									if (template != null) {
										template.func_237144_a_((ServerWorld) world, new BlockPos((int) (Xstart * 16), (int) 0, (int) (Z1 * 16)),
												new PlacementSettings().setRotation(Rotation.NONE).setMirror(Mirror.NONE).setChunk(null)
														.setIgnoreEntities(false),
												((World) world).rand);
									}
								}
								Y = (double) 16;
								for (int index2 = 0; index2 < (int) (14); index2++) {
									if (world instanceof ServerWorld) {
										Template template = ((ServerWorld) world).getStructureTemplateManager()
												.getTemplateDefaulted(new ResourceLocation("recursivecustomstart", "cornerwall1"));
										if (template != null) {
											template.func_237144_a_((ServerWorld) world, new BlockPos((int) (Xstart * 16), (int) Y, (int) (Z1 * 16)),
													new PlacementSettings().setRotation(Rotation.NONE).setMirror(Mirror.NONE).setChunk(null)
															.setIgnoreEntities(false),
													((World) world).rand);
										}
									}
									Y = (double) (Y + 16);
								}
								if (world instanceof ServerWorld) {
									Template template = ((ServerWorld) world).getStructureTemplateManager()
											.getTemplateDefaulted(new ResourceLocation("recursivecustomstart", "cornerwalltop"));
									if (template != null) {
										template.func_237144_a_((ServerWorld) world, new BlockPos((int) (Xstart * 16), (int) Y, (int) (Z1 * 16)),
												new PlacementSettings().setRotation(Rotation.NONE).setMirror(Mirror.NONE).setChunk(null)
														.setIgnoreEntities(false),
												((World) world).rand);
									}
								}
							} else if ((((Z1 >= 0) && ((((Z1 % 62) - 1) % 6) == 0)) || ((Z1 < 0) && ((((Z1 % 62) + 1) % 6) == 0)))) {
								if (world instanceof ServerWorld) {
									Template template = ((ServerWorld) world).getStructureTemplateManager()
											.getTemplateDefaulted(new ResourceLocation("recursivecustomstart", "nssupportstructurebedrock"));
									if (template != null) {
										template.func_237144_a_((ServerWorld) world, new BlockPos((int) (Xstart * 16), (int) 0, (int) (Z1 * 16)),
												new PlacementSettings().setRotation(Rotation.NONE).setMirror(Mirror.NONE).setChunk(null)
														.setIgnoreEntities(false),
												((World) world).rand);
									}
								}
								Y = (double) 16;
								for (int index3 = 0; index3 < (int) (14); index3++) {
									if (world instanceof ServerWorld) {
										Template template = ((ServerWorld) world).getStructureTemplateManager()
												.getTemplateDefaulted(new ResourceLocation("recursivecustomstart", "nssupportstructure1"));
										if (template != null) {
											template.func_237144_a_((ServerWorld) world, new BlockPos((int) (Xstart * 16), (int) Y, (int) (Z1 * 16)),
													new PlacementSettings().setRotation(Rotation.NONE).setMirror(Mirror.NONE).setChunk(null)
															.setIgnoreEntities(false),
													((World) world).rand);
										}
									}
									Y = (double) (Y + 16);
								}
								if (world instanceof ServerWorld) {
									Template template = ((ServerWorld) world).getStructureTemplateManager()
											.getTemplateDefaulted(new ResourceLocation("recursivecustomstart", "nssupportstructuretop"));
									if (template != null) {
										template.func_237144_a_((ServerWorld) world, new BlockPos((int) (Xstart * 16), (int) Y, (int) (Z1 * 16)),
												new PlacementSettings().setRotation(Rotation.NONE).setMirror(Mirror.NONE).setChunk(null)
														.setIgnoreEntities(false),
												((World) world).rand);
									}
								}
							} else {
								if (world instanceof ServerWorld) {
									Template template = ((ServerWorld) world).getStructureTemplateManager()
											.getTemplateDefaulted(new ResourceLocation("recursivecustomstart", "nsflatwallbedrock"));
									if (template != null) {
										template.func_237144_a_((ServerWorld) world, new BlockPos((int) (Xstart * 16), (int) 0, (int) (Z1 * 16)),
												new PlacementSettings().setRotation(Rotation.NONE).setMirror(Mirror.NONE).setChunk(null)
														.setIgnoreEntities(false),
												((World) world).rand);
									}
								}
								Y = (double) 16;
								for (int index4 = 0; index4 < (int) (14); index4++) {
									if (world instanceof ServerWorld) {
										Template template = ((ServerWorld) world).getStructureTemplateManager()
												.getTemplateDefaulted(new ResourceLocation("recursivecustomstart", "nsflatwall1"));
										if (template != null) {
											template.func_237144_a_((ServerWorld) world, new BlockPos((int) (Xstart * 16), (int) Y, (int) (Z1 * 16)),
													new PlacementSettings().setRotation(Rotation.NONE).setMirror(Mirror.NONE).setChunk(null)
															.setIgnoreEntities(false),
													((World) world).rand);
										}
									}
									Y = (double) (Y + 16);
								}
								if (world instanceof ServerWorld) {
									Template template = ((ServerWorld) world).getStructureTemplateManager()
											.getTemplateDefaulted(new ResourceLocation("recursivecustomstart", "nsflatwalltop"));
									if (template != null) {
										template.func_237144_a_((ServerWorld) world, new BlockPos((int) (Xstart * 16), (int) Y, (int) (Z1 * 16)),
												new PlacementSettings().setRotation(Rotation.NONE).setMirror(Mirror.NONE).setChunk(null)
														.setIgnoreEntities(false),
												((World) world).rand);
									}
								}
							}
							break;
						}
						Z1 = (double) (Z1 + 1);
					}
				}
				Xstart = (double) (Xstart + 1);
			}
			Zstart = (double) ((((z - (z % 16)) / 16) + 1) - RecursivecustomstartModVariables.MapVariables.get(world).GenerateStructureRenderDist);
			for (int index5 = 0; index5 < (int) (((RecursivecustomstartModVariables.MapVariables.get(world).GenerateStructureRenderDist * 2)
					- 1)); index5++) {
				if (((Zstart % 62) == 0)) {
					if (!world.isRemote()) {
						MinecraftServer mcserv = ServerLifecycleHooks.getCurrentServer();
						if (mcserv != null)
							mcserv.getPlayerList().func_232641_a_(new StringTextComponent("NS wall"), ChatType.SYSTEM, Util.DUMMY_UUID);
					}
					X1 = (double) ((((x - (x % 16)) / 16) + 1)
							- RecursivecustomstartModVariables.MapVariables.get(world).GenerateStructureRenderDist);
					for (int index6 = 0; index6 < (int) (((RecursivecustomstartModVariables.MapVariables.get(world).GenerateStructureRenderDist * 2)
							- 1)); index6++) {
						if ((!((world.getBlockState(new BlockPos((int) (X1 * 16), (int) 0, (int) (Zstart * 16))))
								.getBlock() == StructurebedrockBlock.block))) {
							if (((X1 % 62) == 0)) {
								Y = (double) 16;
							} else if ((((X1 >= 0) && ((((X1 % 62) - 1) % 6) == 0)) || ((X1 < 0) && ((((X1 % 62) + 1) % 6) == 0)))) {
								if (world instanceof ServerWorld) {
									Template template = ((ServerWorld) world).getStructureTemplateManager()
											.getTemplateDefaulted(new ResourceLocation("recursivecustomstart", "ewsupportstructurebedrock"));
									if (template != null) {
										template.func_237144_a_((ServerWorld) world, new BlockPos((int) (X1 * 16), (int) 0, (int) (Zstart * 16)),
												new PlacementSettings().setRotation(Rotation.NONE).setMirror(Mirror.NONE).setChunk(null)
														.setIgnoreEntities(false),
												((World) world).rand);
									}
								}
								Y = (double) 16;
								for (int index7 = 0; index7 < (int) (14); index7++) {
									if (world instanceof ServerWorld) {
										Template template = ((ServerWorld) world).getStructureTemplateManager()
												.getTemplateDefaulted(new ResourceLocation("recursivecustomstart", "ewsupportstructure1"));
										if (template != null) {
											template.func_237144_a_((ServerWorld) world, new BlockPos((int) (X1 * 16), (int) Y, (int) (Zstart * 16)),
													new PlacementSettings().setRotation(Rotation.NONE).setMirror(Mirror.NONE).setChunk(null)
															.setIgnoreEntities(false),
													((World) world).rand);
										}
									}
									Y = (double) (Y + 16);
								}
								if (world instanceof ServerWorld) {
									Template template = ((ServerWorld) world).getStructureTemplateManager()
											.getTemplateDefaulted(new ResourceLocation("recursivecustomstart", "ewsupportstructuretop"));
									if (template != null) {
										template.func_237144_a_((ServerWorld) world, new BlockPos((int) (X1 * 16), (int) Y, (int) (Zstart * 16)),
												new PlacementSettings().setRotation(Rotation.NONE).setMirror(Mirror.NONE).setChunk(null)
														.setIgnoreEntities(false),
												((World) world).rand);
									}
								}
							} else {
								if (world instanceof ServerWorld) {
									Template template = ((ServerWorld) world).getStructureTemplateManager()
											.getTemplateDefaulted(new ResourceLocation("recursivecustomstart", "ewflatwallbedrock"));
									if (template != null) {
										template.func_237144_a_((ServerWorld) world, new BlockPos((int) (X1 * 16), (int) 0, (int) (Zstart * 16)),
												new PlacementSettings().setRotation(Rotation.NONE).setMirror(Mirror.NONE).setChunk(null)
														.setIgnoreEntities(false),
												((World) world).rand);
									}
								}
								Y = (double) 16;
								for (int index8 = 0; index8 < (int) (14); index8++) {
									if (world instanceof ServerWorld) {
										Template template = ((ServerWorld) world).getStructureTemplateManager()
												.getTemplateDefaulted(new ResourceLocation("recursivecustomstart", "ewflatwall1"));
										if (template != null) {
											template.func_237144_a_((ServerWorld) world, new BlockPos((int) (X1 * 16), (int) Y, (int) (Zstart * 16)),
													new PlacementSettings().setRotation(Rotation.NONE).setMirror(Mirror.NONE).setChunk(null)
															.setIgnoreEntities(false),
													((World) world).rand);
										}
									}
									Y = (double) (Y + 16);
								}
								if (world instanceof ServerWorld) {
									Template template = ((ServerWorld) world).getStructureTemplateManager()
											.getTemplateDefaulted(new ResourceLocation("recursivecustomstart", "ewflatwalltop"));
									if (template != null) {
										template.func_237144_a_((ServerWorld) world, new BlockPos((int) (X1 * 16), (int) Y, (int) (Zstart * 16)),
												new PlacementSettings().setRotation(Rotation.NONE).setMirror(Mirror.NONE).setChunk(null)
														.setIgnoreEntities(false),
												((World) world).rand);
									}
								}
							}
							break;
						}
						X1 = (double) (X1 + 1);
					}
				}
				Zstart = (double) (Zstart + 1);
			}
		}
	}
}
