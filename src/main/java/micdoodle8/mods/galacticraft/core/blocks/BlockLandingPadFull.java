//package micdoodle8.mods.galacticraft.core.blocks;
//
//import micdoodle8.mods.galacticraft.api.block.IPartialSealableBlock;
//import micdoodle8.mods.galacticraft.core.GCBlocks;
//import micdoodle8.mods.galacticraft.core.tile.IMultiBlock;
//import micdoodle8.mods.galacticraft.core.tile.TileEntityBuggyFueler;
//import micdoodle8.mods.galacticraft.core.tile.TileEntityLandingPad;
//import net.minecraft.block.Block;
//import net.minecraft.block.BlockState;
//import net.minecraft.block.state.BlockFaceShape;
//import net.minecraft.block.state.BlockStateContainer;
//import net.minecraft.entity.player.PlayerEntity;
//import net.minecraft.item.Item;
//import net.minecraft.item.ItemStack;
//import net.minecraft.tileentity.TileEntity;
//import net.minecraft.util.Direction;
//import net.minecraft.util.IStringSerializable;
//import net.minecraft.util.math.AxisAlignedBB;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.util.math.RayTraceResult;
//import net.minecraft.world.IBlockAccess;
//import net.minecraft.world.IBlockReader;
//import net.minecraft.world.World;
//import net.minecraftforge.fml.relauncher.Side;
//import net.minecraftforge.fml.relauncher.SideOnly;
//
//import javax.annotation.Nullable;
//import java.util.Random;
//
//public class BlockLandingPadFull extends BlockAdvancedTile implements IPartialSealableBlock
//{
//    public static final EnumProperty<EnumLandingPadFullType> PAD_TYPE = EnumProperty.create("type", EnumLandingPadFullType.class);
//    private final VoxelShape AABB = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 1.0D, 0.1875D, 1.0D);
//
//    public enum EnumLandingPadFullType implements IStringSerializable
//    {
//        ROCKET_PAD(0, "rocket"),
//        BUGGY_PAD(1, "buggy");
//
//        private final int meta;
//        private final String name;
//
//        EnumLandingPadFullType(int meta, String name)
//        {
//            this.meta = meta;
//            this.name = name;
//        }
//
//        public int getMeta()
//        {
//            return this.meta;
//        }
//
//        private final static EnumLandingPadFullType[] values = values();
//        public static EnumLandingPadFullType byMetadata(int meta)
//        {
//            return values[meta % values.length];
//        }
//
//        @Override
//        public String getName()
//        {
//            return this.name;
//        }
//    }
//
//    public BlockLandingPadFull(Properties builder)
//    {
//        super(builder);
////        this.maxY = 0.25F;
//    }
//
//    @Override
//    public int damageDropped(BlockState state)
//    {
//        return getMetaFromState(state);
//    }
//
//    @Override
//    public int quantityDropped(Random par1Random)
//    {
//        return 9;
//    }
//
//    @Override
//    public void breakBlock(World worldIn, BlockPos pos, BlockState state)
//    {
//        final TileEntity var9 = worldIn.getTileEntity(pos);
//
//        if (var9 instanceof IMultiBlock)
//        {
//            ((IMultiBlock) var9).onDestroy(var9);
//        }
//
//        super.breakBlock(worldIn, pos, state);
//    }
//
//    @Override
//    public Item getItemDropped(BlockState state, Random rand, int fortune)
//    {
//        return Item.getItemFromBlock(GCBlocks.landingPad);
//    }
//
//    @Override
//    public AxisAlignedBB getBoundingBox(BlockState blockState, IBlockAccess worldIn, BlockPos pos)
//    {
////        switch (getMetaFromState(blockState))
////        {
////        case 0:
////            return Block.makeCuboidShape(pos.getX() + this.minX, pos.getY() + this.minY, pos.getZ() + this.minZ,
////                    pos.getX() + this.maxX, pos.getY() + this.maxY, pos.getZ() + this.maxZ); TODO
////        case 2:
////            return Block.makeCuboidShape(pos.getX() + this.minX, pos.getY() + this.minY, pos.getZ() + this.minZ,
////                    pos.getX() + this.maxX, pos.getY() + this.maxY, pos.getZ() + this.maxZ);
////        default:
////        }
//        return this.AABB;
//    }
//
//    @Override
//    public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
//    {
//        for (int x2 = -1; x2 < 2; ++x2)
//        {
//            for (int z2 = -1; z2 < 2; ++z2)
//            {
//                if (!super.canPlaceBlockAt(worldIn, new BlockPos(pos.getX() + x2, pos.getY(), pos.getZ() + z2)))
//                {
//                    return false;
//                }
//            }
//
//        }
//
//        return true;
//    }
//
//    @Override
//    public boolean hasTileEntity(BlockState state)
//    {
//        return true;
//    }
//
//    @Nullable
//    @Override
//    public TileEntity createTileEntity(BlockState state, IBlockReader world)
//    {
//        switch (getMetaFromState(state))
//        {
//        case 0:
//            return new TileEntityLandingPad();
//        case 1:
//            return new TileEntityBuggyFueler();
//        // case 2:
//        // return new GCCoreTileEntityCargoPad();
//        default:
//            return null;
//        }
//    }
//
//    @Override
//    public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving)
//    {
//        worldIn.notifyBlockUpdate(pos, state, state, 3);
//    }
//
//    @Override
//    public boolean isOpaqueCube(BlockState state)
//    {
//        return false;
//    }
//
//    @Override
//    public boolean isFullCube(BlockState state)
//    {
//        return false;
//    }
//
//    @Override
//    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, BlockState state, BlockPos pos, Direction face)
//    {
//        return BlockFaceShape.UNDEFINED;
//    }
//
//    @SideOnly(Side.CLIENT)
//    @Override
//    public boolean shouldSideBeRendered(BlockState blockState, IBlockAccess blockAccess, BlockPos pos, Direction side)
//    {
//        return true;
//    }
//
//    @Override
//    public boolean isSealed(World worldIn, BlockPos pos, Direction direction)
//    {
//        return direction == Direction.UP;
//    }
//
//    @Override
//    public ItemStack getPickBlock(BlockState state, RayTraceResult target, World world, BlockPos pos, PlayerEntity player)
//    {
//        int metadata = getMetaFromState(world.getBlockState(pos));
//        return new ItemStack(Item.getItemFromBlock(GCBlocks.landingPad), 1, metadata);
//    }
//
//    @Override
//    public BlockState getStateFromMeta(int meta)
//    {
//        return this.getDefaultState().with(PAD_TYPE, EnumLandingPadFullType.byMetadata(meta));
//    }
//
//    @Override
//    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
//    {
//        builder.add(PAD_TYPE);
//    }
//}
