
package net.cubicmods.recursivecustomstart.block;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.IWorld;
import net.minecraft.world.IBlockReader;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.Rotation;
import net.minecraft.util.Mirror;
import net.minecraft.util.Direction;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.StateContainer;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.BooleanProperty;
import net.minecraft.loot.LootContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.BlockItem;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.FluidState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.block.material.Material;
import net.minecraft.block.SoundType;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Block;

import net.cubicmods.recursivecustomstart.RecursivecustomstartModElements;

import java.util.List;
import java.util.Collections;

@RecursivecustomstartModElements.ModElement.Tag
public class EngineersDecorHBeamBlock extends RecursivecustomstartModElements.ModElement {
	@ObjectHolder("recursivecustomstart:engineers_decor_h_beam")
	public static final Block block = null;
	public EngineersDecorHBeamBlock(RecursivecustomstartModElements instance) {
		super(instance, 7);
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new CustomBlock());
		elements.items
				.add(() -> new BlockItem(block, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)).setRegistryName(block.getRegistryName()));
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void clientLoad(FMLClientSetupEvent event) {
		RenderTypeLookup.setRenderLayer(block, RenderType.getCutout());
	}
	public static class CustomBlock extends Block implements IWaterLoggable {
		public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
		public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
		public CustomBlock() {
			super(Block.Properties.create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(1f, 10f).setLightLevel(s -> 0).notSolid()
					.tickRandomly().setOpaque((bs, br, bp) -> false));
			this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH).with(WATERLOGGED, false));
			setRegistryName("engineers_decor_h_beam");
		}

		@Override
		@OnlyIn(Dist.CLIENT)
		public void addInformation(ItemStack itemstack, IBlockReader world, List<ITextComponent> list, ITooltipFlag flag) {
			super.addInformation(itemstack, world, list, flag);
			list.add(new StringTextComponent(
					"The Engineers decor H-Beam block did not like to keep its orientation when used in a structure so i borrowed it. this mod requires engineers deco anyway so hope no one is unhapy about this.."));
		}

		@Override
		public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
			return state.getFluidState().isEmpty();
		}

		@Override
		public int getOpacity(BlockState state, IBlockReader worldIn, BlockPos pos) {
			return 0;
		}

		@Override
		public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
			Vector3d offset = state.getOffset(world, pos);
			switch ((Direction) state.get(FACING)) {
				case SOUTH :
				default :
					return VoxelShapes.combineAndSimplify(VoxelShapes.or(makeCuboidShape(11, 11, 16, 5, 16, 0)),
							VoxelShapes.or(makeCuboidShape(11, 12, 16, 10, 15, 0), makeCuboidShape(6, 12, 16, 5, 15, 0)), IBooleanFunction.ONLY_FIRST)
							.withOffset(offset.x, offset.y, offset.z);
				case NORTH :
					return VoxelShapes.combineAndSimplify(VoxelShapes.or(makeCuboidShape(5, 11, 0, 11, 16, 16)),
							VoxelShapes.or(makeCuboidShape(5, 12, 0, 6, 15, 16), makeCuboidShape(10, 12, 0, 11, 15, 16)), IBooleanFunction.ONLY_FIRST)
							.withOffset(offset.x, offset.y, offset.z);
				case EAST :
					return VoxelShapes.combineAndSimplify(VoxelShapes.or(makeCuboidShape(16, 11, 5, 0, 16, 11)),
							VoxelShapes.or(makeCuboidShape(16, 12, 5, 0, 15, 6), makeCuboidShape(16, 12, 10, 0, 15, 11)), IBooleanFunction.ONLY_FIRST)
							.withOffset(offset.x, offset.y, offset.z);
				case WEST :
					return VoxelShapes.combineAndSimplify(VoxelShapes.or(makeCuboidShape(0, 11, 11, 16, 16, 5)),
							VoxelShapes.or(makeCuboidShape(0, 12, 11, 16, 15, 10), makeCuboidShape(0, 12, 6, 16, 15, 5)), IBooleanFunction.ONLY_FIRST)
							.withOffset(offset.x, offset.y, offset.z);
			}
		}

		@Override
		protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
			builder.add(FACING, WATERLOGGED);
		}

		public BlockState rotate(BlockState state, Rotation rot) {
			return state.with(FACING, rot.rotate(state.get(FACING)));
		}

		public BlockState mirror(BlockState state, Mirror mirrorIn) {
			return state.rotate(mirrorIn.toRotation(state.get(FACING)));
		}

		@Override
		public BlockState getStateForPlacement(BlockItemUseContext context) {
			boolean flag = context.getWorld().getFluidState(context.getPos()).getFluid() == Fluids.WATER;;
			if (context.getFace() == Direction.UP || context.getFace() == Direction.DOWN)
				return this.getDefaultState().with(FACING, Direction.NORTH).with(WATERLOGGED, flag);
			return this.getDefaultState().with(FACING, context.getFace()).with(WATERLOGGED, flag);
		}

		@Override
		public FluidState getFluidState(BlockState state) {
			return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
		}

		@Override
		public BlockState updatePostPlacement(BlockState state, Direction facing, BlockState facingState, IWorld world, BlockPos currentPos,
				BlockPos facingPos) {
			if (state.get(WATERLOGGED)) {
				world.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(world));
			}
			return super.updatePostPlacement(state, facing, facingState, world, currentPos, facingPos);
		}

		@Override
		public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
			List<ItemStack> dropsOriginal = super.getDrops(state, builder);
			if (!dropsOriginal.isEmpty())
				return dropsOriginal;
			return Collections.singletonList(new ItemStack(this, 1));
		}
	}
}
