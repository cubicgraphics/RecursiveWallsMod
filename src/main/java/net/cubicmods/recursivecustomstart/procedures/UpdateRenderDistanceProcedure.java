package net.cubicmods.recursivecustomstart.procedures;

import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.TickEvent;

import net.minecraft.world.IWorld;

import net.cubicmods.recursivecustomstart.RecursivecustomstartModVariables;
import net.cubicmods.recursivecustomstart.RecursivecustomstartMod;

import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

import java.io.IOException;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.BufferedReader;

public class UpdateRenderDistanceProcedure {
	@Mod.EventBusSubscriber
	private static class GlobalTrigger {
		@SubscribeEvent
		public static void onWorldTick(TickEvent.WorldTickEvent event) {
			if (event.phase == TickEvent.Phase.END) {
				IWorld world = event.world;
				Map<String, Object> dependencies = new HashMap<>();
				dependencies.put("world", world);
				dependencies.put("event", event);
				executeProcedure(dependencies);
			}
		}
	}
	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				RecursivecustomstartMod.LOGGER.warn("Failed to load dependency world for procedure UpdateRenderDistance!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		ArrayList OptionsList = new ArrayList();
		File OptionsTxT = new File("");
		File PropertiesTxT = new File("");
		if ((!(world.isRemote()))) {
			if ((((world.getWorldInfo().getDayTime()) % 200) == 0)) {
				if ((!OptionsTxT.exists())) {
					OptionsTxT = new File(FMLPaths.GAMEDIR.get().toString(), File.separator + "options.txt");
					if (!OptionsTxT.exists()) {
						try {
							OptionsTxT.createNewFile();
						} catch (IOException exception) {
							exception.printStackTrace();
						}
					}
					try {
						BufferedReader OptionsTxTReader = new BufferedReader(new FileReader(OptionsTxT));
						String stringiterator = "";
						while ((stringiterator = OptionsTxTReader.readLine()) != null) {
							OptionsList.add(stringiterator);
						}
						OptionsTxTReader.close();
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if (OptionsList.isEmpty()) {
					PropertiesTxT = new File(FMLPaths.GAMEDIR.get().toString(), File.separator + "server.properties");
					if (!PropertiesTxT.exists()) {
						try {
							PropertiesTxT.createNewFile();
						} catch (IOException exception) {
							exception.printStackTrace();
						}
					}
					try {
						BufferedReader PropertiesTxTReader = new BufferedReader(new FileReader(PropertiesTxT));
						String stringiterator = "";
						while ((stringiterator = PropertiesTxTReader.readLine()) != null) {
							OptionsList.add(stringiterator);
						}
						PropertiesTxTReader.close();
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					RecursivecustomstartModVariables.MapVariables.get(world).GenerateStructureRenderDist = (double) new Object() {
						double convert(String s) {
							try {
								return Double.parseDouble(s.trim());
							} catch (Exception e) {
							}
							return 0;
						}
					}.convert((("" + (OptionsList.get(39))).substring((int) 15, (int) (("" + (OptionsList.get(39)))).length())));
					RecursivecustomstartModVariables.MapVariables.get(world).syncData(world);
				} else {
					RecursivecustomstartModVariables.MapVariables.get(world).GenerateStructureRenderDist = (double) new Object() {
						double convert(String s) {
							try {
								return Double.parseDouble(s.trim());
							} catch (Exception e) {
							}
							return 0;
						}
					}.convert((("" + (OptionsList.get(25))).substring((int) 15, (int) (("" + (OptionsList.get(25)))).length())));
					RecursivecustomstartModVariables.MapVariables.get(world).syncData(world);
				}
			}
		}
	}
}
