package flyingperson.ic2addons;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

public class Utils {
    public static <T> boolean hasCapability(TileEntity te, Capability<T> cap) {
        if (te == null) return false;
        if (te.hasCapability(cap, EnumFacing.DOWN)) return true;
        if (te.hasCapability(cap, EnumFacing.UP)) return true;
        if (te.hasCapability(cap, EnumFacing.NORTH)) return true;
        if (te.hasCapability(cap, EnumFacing.SOUTH)) return true;
        if (te.hasCapability(cap, EnumFacing.EAST)) return true;
        if (te.hasCapability(cap, EnumFacing.WEST)) return true;
        return false;
    }
    public static <T> T getCapability(TileEntity te, Capability<T> capability) {
        if (te.getCapability(capability, EnumFacing.DOWN) != null) return te.getCapability(capability, EnumFacing.DOWN);
        if (te.getCapability(capability, EnumFacing.UP) != null) return te.getCapability(capability, EnumFacing.UP);
        if (te.getCapability(capability, EnumFacing.NORTH) != null) return te.getCapability(capability, EnumFacing.NORTH);
        if (te.getCapability(capability, EnumFacing.SOUTH) != null) return te.getCapability(capability, EnumFacing.SOUTH);
        if (te.getCapability(capability, EnumFacing.EAST) != null) return te.getCapability(capability, EnumFacing.EAST);
        if (te.getCapability(capability, EnumFacing.WEST) != null) return te.getCapability(capability, EnumFacing.WEST);
        return null;
    }

    public static double divideByRfToEu(double toDivide) {
        return toDivide / Config.RF_PER_EU;
    }
}
