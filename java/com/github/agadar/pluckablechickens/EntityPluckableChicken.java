package com.github.agadar.pluckablechickens;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;

public class EntityPluckableChicken extends EntityChicken implements IShearable
{
	/** The time until the feathers grow back. */
	private int timeUntilFeathers;
	
	public EntityPluckableChicken(World par1World) 
	{
		super(par1World);
		this.timeUntilFeathers = this.rand.nextInt(3000) + 3000;
	}
	
	/**
	 * Special constructor for when replacing a normal chicken.
	 *
	 * @param par1Chicken the normal chicken which this will replace
	 */
	public EntityPluckableChicken(EntityChicken chicken)
	{
		this(chicken.worldObj);
		this.setLocationAndAngles(chicken.posX, chicken.posY, chicken.posZ, chicken.rotationYaw, chicken.rotationPitch);
		this.setGrowingAge(chicken.getGrowingAge());
	}
	
	@Override
	public void onLivingUpdate()
	{		
		super.onLivingUpdate();
		
		if (!this.isChild() && !this.worldObj.isRemote && this.getSheared() && --this.timeUntilFeathers <= 0)
        {
			this.setSheared(false);
            this.timeUntilFeathers = this.rand.nextInt(3000) + 3000;
        }
	}
	
	@Override
	protected void dropFewItems(boolean wasRecentlyHit, int lootingModifier)
    {
        int i = this.rand.nextInt(3) + this.rand.nextInt(1 + lootingModifier);

        if (!this.getSheared())
        {
        	for (int j = 0; j < i; ++j)
            {
                this.dropItem(Items.feather, 1);
            }
        }
        
        if (this.isBurning())
        {
            this.dropItem(Items.cooked_chicken, 1);
        }
        else
        {
            this.dropItem(Items.chicken, 1);
        }
    }
	
	@Override
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeEntityToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setBoolean("Sheared", this.getSheared());
    }

	@Override
    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readEntityFromNBT(par1NBTTagCompound);
        this.setSheared(par1NBTTagCompound.getBoolean("Sheared"));
    }
	
	@Override
	protected void entityInit()
    {
        super.entityInit();
        this.dataWatcher.addObject(16, new Byte((byte)0));
    }
    
	/**
	 * Returns boolean indicating whether this chicken is sheared.
	 *
	 * @return boolean indicating whether this chicken is sheared
	 */
    public boolean getSheared()
    {
        return (this.dataWatcher.getWatchableObjectByte(16) & 16) != 0;
    }

    /**
     * Sets whether this chicken is sheared.
     *
     * @param isSheared whether this chicken is sheared
     */
    public void setSheared(boolean isSheared)
    {
        byte b0 = this.dataWatcher.getWatchableObjectByte(16);

        if (isSheared)
        {
        	this.dataWatcher.updateObject(16, Byte.valueOf((byte)(b0 | 16)));
        }
        else
        {
        	this.dataWatcher.updateObject(16, Byte.valueOf((byte)(b0 & -17)));
        }
    }
	
	@Override
	public EntityPluckableChicken createChild(EntityAgeable ageable)
    {
        return new EntityPluckableChicken(this.worldObj);
    }

	@Override
	public boolean isShearable(ItemStack item, IBlockAccess world, BlockPos pos)
	{
		return !getSheared() && !isChild();
	}

	@Override
	public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune)
	{
		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
        setSheared(true);
        int i = rand.nextInt(3) + 2;
        
        for (int j = 0; j < i; j++)
        	ret.add(new ItemStack(Items.feather));

        this.worldObj.playSoundAtEntity(this, "mob.sheep.shear", 1.0F, 1.0F);
        return ret;
	}
}
