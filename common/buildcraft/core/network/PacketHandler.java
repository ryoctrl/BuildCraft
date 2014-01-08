package buildcraft.core.network;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.repackage.com.nothome.delta.DebugDiffWriter;

public class PacketHandler implements IPacketHandler {

	private void onTileUpdate(EntityPlayer player, PacketTileUpdate packet) throws IOException {
		World world = player.worldObj;

		if (!packet.targetExists(world))
			return;

		TileEntity entity = packet.getTarget(world);
		if (!(entity instanceof ISynchronizedTile))
			return;

		ISynchronizedTile tile = (ISynchronizedTile) entity;
		tile.handleUpdatePacket(packet);
		tile.postPacketHandling(packet);
	}

	@Override
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) {
		DataInputStream data = new DataInputStream(new ByteArrayInputStream(packet.data));

		try {
			int packetID = data.read();
			switch (packetID) {
				case PacketIds.TILE_UPDATE: {
					PacketTileUpdate pkt = new PacketTileUpdate();
					pkt.readData(data);
					onTileUpdate((EntityPlayer) player, pkt);
					break;
				}

				case PacketIds.STATE_UPDATE: {
					PacketTileState pkt = new PacketTileState();
					pkt.readData(data);
					World world = ((EntityPlayer) player).worldObj;
					TileEntity tile = world.getBlockTileEntity(pkt.posX, pkt.posY, pkt.posZ);
					if (tile instanceof ISyncedTile) {
						pkt.applyStates(data, (ISyncedTile) tile);
					}
					break;
				}

				case PacketIds.GUI_RETURN: {
					PacketGuiReturn pkt = new PacketGuiReturn((EntityPlayer) player);
					pkt.readData(data);
					break;
				}

				case PacketIds.GUI_WIDGET: {
					PacketGuiWidget pkt = new PacketGuiWidget();
					pkt.readData(data);
					break;
				}

				case PacketIds.RPC: {
					PacketRPC rpc = new PacketRPC();
					rpc.sender = (EntityPlayer) player;

					int dimId = data.readShort();
					World world = null;

					if (!rpc.sender.worldObj.isRemote) {
						// if this is a server, then get the world

						world = DimensionManager.getProvider(dimId).worldObj;
					} else if (rpc.sender.worldObj.provider.dimensionId == dimId) {
						// if the player is on this world, then synchronize things

						world = rpc.sender.worldObj;
					}

					if (world != null) {
						int x = data.readInt();
						int y = data.readInt();
						int z = data.readInt();

						TileEntity tile = world.getBlockTileEntity(x, y, z);

						rpc.setTile (tile);
						rpc.readData(data);
					}

					break;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}
}
