/** 
 * BuildCraft is open-source. It is distributed under the terms of the 
 * BuildCraft Open Source License. It grants rights to read, modify, compile
 * or run the code. It does *NOT* grant the right to redistribute this software
 * or its modifications in any form, binary or source, except if expressively
 * granted by the copyright holder.
 */

package net.minecraft.src.buildcraft.energy;

import net.minecraft.src.ICrafting;
import net.minecraft.src.InventoryPlayer;

public class ContainerEngine extends ContainerEngineRoot {

    public ContainerEngine(InventoryPlayer inventoryplayer,
			TileEngine tileEngine) {
		super(inventoryplayer, tileEngine);
		// TODO Auto-generated constructor stub
	}

    @Override
	public void onCraftGuiOpened(ICrafting icrafting) {
        super.onCraftGuiOpened(icrafting);
//        icrafting.updateCraftingInventoryInfo(this, 0, engine.scaledBurnTime);
    }
	
	@Override
	public void updateCraftingResults() {
        super.updateCraftingResults();
//        for(int i = 0; i < crafters.size(); i++) {
//            ICrafting icrafting = (ICrafting)crafters.get(i);
//            if(scaledBurnTime != engine.scaledBurnTime) {
//				icrafting.updateCraftingInventoryInfo(this, 0,
//						engine.scaledBurnTime);
//            }
//        }

        
//        scaledBurnTime = engine.scaledBurnTime;
    }
	
}
