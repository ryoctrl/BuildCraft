/** 
 * BuildCraft is open-source. It is distributed under the terms of the 
 * BuildCraft Open Source License. It grants rights to read, modify, compile
 * or run the code. It does *NOT* grant the right to redistribute this software
 * or its modifications in any form, binary or source, except if expressively
 * granted by the copyright holder.
 */

package net.minecraft.src.buildcraft.transport.pipes;

import net.minecraft.src.buildcraft.transport.Pipe;
import net.minecraft.src.buildcraft.transport.PipeLogicCobblestone;
import net.minecraft.src.buildcraft.transport.PipeTransportLiquids;

public class PipeLiquidsCobblestone extends Pipe {

	public PipeLiquidsCobblestone(int itemID) {
		super(new PipeTransportLiquids(), new PipeLogicCobblestone(), itemID);		
	}
	
	@Override
	public int getBlockTexture() {
		return 7 * 16 + 1;
	}
}
