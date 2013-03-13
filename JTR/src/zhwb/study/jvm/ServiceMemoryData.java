package zhwb.study.jvm;

import javax.xml.bind.annotation.XmlElement;

public class ServiceMemoryData
{
	/** Memory Category */
	public String category;

	/** Free Memory - Amount of memory available */
	public Long freeMemory;

	/** Committed Memory - Amount of memory allocated */
	public Long committedMemory;

	/** Max Memory - Maximum memory available */
	public Long maxMemory;

	/** Used Memory - Amount of memory utilized */
	public Long usedMemory;

	/** Usage Percentage - Percentage of max memory utilized */
	public Double usagePercentage;

	/**
	 * Default Constructor
	 */
	public ServiceMemoryData()
	{
		this.category = null;
		this.freeMemory = null;
		this.committedMemory = null;
		this.maxMemory = null;
		this.usedMemory = null;
		this.usagePercentage = null;
	}

	/**
	 * Parameter Constructor
	 * @param category Category
	 * @param freeMemory Free
	 * @param committedMemory Committed
	 * @param maxMemory Max
	 * @param usedMemory Used
	 * @param usagePercentage Percentage
	 */
	public ServiceMemoryData( final String category, final Long freeMemory, final Long committedMemory, final Long maxMemory,
			final Long usedMemory, final Double usagePercentage )
	{
		this.category = category;
		this.freeMemory = freeMemory;
		this.committedMemory = committedMemory;
		this.maxMemory = maxMemory;
		this.usedMemory = usedMemory;
		this.usagePercentage = usagePercentage;
	}

	/**
	 * Get Memory Category
	 * @return category
	 */
	@XmlElement( name = "category" )
	public final String getCategory()
	{
		return this.category;
	}

	/**
	 * Set Memory Category
	 * @param category Memory Category
	 */
	public final void setCategory( final String category )
	{
		this.category = category;
	}

	/**
	 * Get Free Memory
	 * @return memory
	 */
	@XmlElement( name = "freeMemory" )
	public final Long getFreeMemory()
	{
		return this.freeMemory;
	}

	/**
	 * Set Free Memory
	 * @param memory Free Memory
	 */
	public final void setFreeMemory( final Long memory )
	{
		this.freeMemory = memory;
	}

	/**
	 * Get Committed Memory
	 * @return memory
	 */
	@XmlElement( name = "committedMemory" )
	public final Long getCommittedMemory()
	{
		return this.committedMemory;
	}

	/**
	 * Set Committed Memory
	 * @param memory Committed Memory
	 */
	public final void setCommittedMemory( final Long memory )
	{
		this.committedMemory = memory;
	}

	/**
	 * Get Max Memory
	 * @return memory
	 */
	@XmlElement( name = "maxMemory" )
	public final Long getMaxMemory()
	{
		return this.maxMemory;
	}

	/**
	 * Set Max Memory
	 * @param memory Max Memory
	 */
	public final void setMaxMemory( final Long memory )
	{
		this.maxMemory = memory;
	}

	/**
	 * Get Used Memory
	 * @return memory
	 */
	@XmlElement( name = "usedMemory" )
	public final Long getUsedMemory()
	{
		return this.usedMemory;
	}

	/**
	 * Set Used Memory
	 * @param memory Used Memory
	 */
	public final void setUsedMemory( final Long memory )
	{
		this.usedMemory = memory;
	}

	/**
	 * Get Usage Percentage
	 * @return memory
	 */
	@XmlElement( name = "usagePercentage" )
	public final Double getUsagePercentage()
	{
		return this.usagePercentage;
	}

	/**
	 * Set Usage Percentage
	 * @param percentage Usage Percentage
	 */
	public final void setUsagePercentage( final Double percentage )
	{
		this.usagePercentage = percentage;
	}

}
