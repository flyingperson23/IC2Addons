package flyingperson.ic2addons;

import flyingperson.ic2addons.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = IC2Addons.MODID, name = IC2Addons.NAME, version = IC2Addons.VERSION, dependencies = "required:ic2")
public class IC2Addons {
    public static final String MODID = "ic2addons";
    public static final String NAME = "IC2 Addons";
    public static final String VERSION = "1.0";

    public static Logger logger;

    @SidedProxy(clientSide = "flyingperson.ic2addons.proxy.ClientProxy", serverSide = "flyingperson.ic2addons.proxy.ServerProxy")
    public static CommonProxy proxy;

    @Mod.Instance
    public static IC2Addons instance;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        proxy.preInit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }
}
