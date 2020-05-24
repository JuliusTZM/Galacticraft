//package micdoodle8.mods.galacticraft.core.blocks;
//
//import micdoodle8.mods.galacticraft.core.GalacticraftCore;
//import micdoodle8.mods.galacticraft.core.items.IShiftDescription;
//import micdoodle8.mods.galacticraft.core.tile.IMachineSides;
//import micdoodle8.mods.galacticraft.core.util.EnumSortCategoryBlock;
//import micdoodle8.mods.galacticraft.core.util.GCCoreUtil;
//import net.minecraft.block.BlockState;
//import net.minecraft.entity.LivingEntity;
//import net.minecraft.entity.player.PlayerEntity;
//import net.minecraft.item.ItemGroup;
//import net.minecraft.item.ItemStack;
//import net.minecraft.tileentity.TileEntity;
//import net.minecraft.util.Direction;
//import net.minecraft.util.Hand;
//import net.minecraft.util.IStringSerializable;
//import net.minecraft.util.NonNullList;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.util.math.MathHelper;
//import net.minecraft.world.IBlockReader;
//import net.minecraft.world.World;
//import net.minecraftforge.fml.relauncher.Side;
//import net.minecraftforge.fml.relauncher.SideOnly;
//
//import javax.annotation.Nullable;
//
//public abstract class BlockMachineBase extends BlockTileGC implements IShiftDescription, ISortableBlock
//{
//    public static final int METADATA_MASK = 0x0c; //Used to select the machine type from metadata
//    public static final DirectionProperty FACING = DirectionProperty.create("facing", Direction.Plane.HORIZONTAL);
//    protected EnumMachineBase[] types;
//    protected EnumMachineBase typeBase;
//
//    public BlockMachineBase(Properties builder)
//    {
//        super(builder);
//        this.initialiseTypes();
//    }
//
//    protected abstract void initialiseTypes();
//
//    @Override
//    @SideOnly(Side.CLIENT)
//    public ItemGroup getCreativeTabToDisplayOn()
//    {
//        return GalacticraftCore.galacticraftBlocksTab;
//    }
//
//    @Override
//    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack)
//    {
//        int metadata = getMetaFromState(state);
//
//        final int angle = MathHelper.floor(placer.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
//        int change = Direction.getHorizontal(angle).getOpposite().getHorizontalIndex();
//
//        worldIn.setBlockState(pos, this.getStateFromMeta((metadata & BlockMachineBase.METADATA_MASK) + change), 3);
//    }
//
//    /**
//     * Called when the block is right clicked by the player
//     */
//    @Override
//    public boolean onMachineActivated(World world, BlockPos pos, BlockState state, PlayerEntity entityPlayer, Hand hand, ItemStack heldItem, BlockRayTraceResult hit)
//    {
//        if (!world.isRemote)
//        {
//            entityPlayer.openGui(GalacticraftCore.instance, -1, world, pos.getX(), pos.getY(), pos.getZ());
//        }
//
//        return true;
//    }
//
//    @Override
//    public boolean onSneakUseWrench(World world, BlockPos pos, PlayerEntity entityPlayer, Hand hand, ItemStack heldItem, BlockRayTraceResult hit)
//    {
//        TileEntity tile = world.getTileEntity(pos);
//        if (tile instanceof IMachineSides)
//        {
//            ((IMachineSides)tile).nextSideConfiguration(tile);
//            return true;
//        }
//        return false;
//    }
//
//    @Nullable
//    @Override
//    public TileEntity createTileEntity(BlockState state, IBlockReader world)
//    {
//        int meta = getMetaFromState(state);
//        EnumMachineBase type = typeBase.fromMetadata(meta);
//        return type.tileConstructor();
//    }
//
//    @Override
//    public int damageDropped(BlockState state)
//    {
//        return getMetaFromState(state) & BlockMachineBase.METADATA_MASK;
//    }
//
//    public String getUnlocalizedName(int meta)
//    {
//        EnumMachineBase type = typeBase.fromMetadata(meta);
//        return type.getUnlocalizedName();
//    }
//
//    @Override
//    public String getShiftDescription(int meta)
//    {
//        EnumMachineBase type = typeBase.fromMetadata(meta);
//        return GCCoreUtil.translate(type.getShiftDescriptionKey());
//    }
//
//    @Override
//    public boolean showDescription(int meta)
//    {
//        return true;
//    }
//
//    public static Direction getFront(BlockState state)
//    {
//        if (state.getBlock() instanceof BlockMachineBase)
//        {
//            return (state.get(BlockMachineBase.FACING));
//        }
//        return Direction.NORTH;
//    }
//
//    @Override
//    public EnumSortCategoryBlock getCategory(int meta)
//    {
//        return EnumSortCategoryBlock.MACHINE;
//    }
//
//    @Override
//    public void getSubBlocks(ItemGroup tab, NonNullList<ItemStack> list)
//    {
//        for (EnumMachineBase type : types)
//            list.add(new ItemStack(this, 1, type.getMetadata()));
//    }
//
//    public interface EnumMachineBase<T extends Enum<T> & IStringSerializable>
//    {
//        int getMetadata();
//
//        EnumMachineBase fromMetadata(int meta);
//
//        String getShiftDescriptionKey();
//
//        String getUnlocalizedName();
//
//        TileEntity tileConstructor();
//    }
//}
