/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2012 All rights reserved. =============================
 */

package zhwb.study.jvm;


import java.lang.management.ManagementFactory;
import java.lang.management.MemoryPoolMXBean;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


/**
 * SBS Resource Data
 * @author andrewch
 * @since 1.0.1
 */
public class JVMheapCheck
{
    /** Literal for storing 100% */
    private static final double ONE_HUNDRED_PERCENT = 100.00;

    /** Timestamp */
    public String timestamp;

    /** Thread Count */
    public Integer activeThreadCount;

    /** Memory Statistics */
    public List<ServiceMemoryData> memoryStatistics;

    /**
     * Constructor
     */
    public JVMheapCheck()
    {
        // Track the time the resource allocation was calculated
        Calendar currentTime = Calendar.getInstance();
        this.timestamp = String.format( "%tD %tT", currentTime, currentTime );

        // Track the threads executing
        this.activeThreadCount = Thread.activeCount();

        // Create a new list of memory allocations
        this.memoryStatistics = new ArrayList<ServiceMemoryData>();

        // Perform Garbage Collection
        Runtime envRuntime = Runtime.getRuntime();
        envRuntime.gc();

        // Check the heap memory of the JVM
        Long committedMemory = envRuntime.totalMemory();
        Long freeMemory = envRuntime.freeMemory();
        Long maxMemory = envRuntime.maxMemory();
        Long usedMemory = committedMemory - freeMemory;
        Double usagePercentage = ( (double) usedMemory / (double) committedMemory ) * ONE_HUNDRED_PERCENT;
        ServiceMemoryData memData = new ServiceMemoryData( "Heap Memory", freeMemory, committedMemory, maxMemory, usedMemory,
                usagePercentage );
        this.memoryStatistics.add( memData );

        // Analyze the memory pool
        for ( MemoryPoolMXBean memoryItem : ManagementFactory.getMemoryPoolMXBeans() ) {
            // Calculate the usage percentage
            committedMemory = memoryItem.getUsage().getCommitted();
            usedMemory = memoryItem.getUsage().getUsed();
            maxMemory = memoryItem.getUsage().getMax();
            freeMemory = maxMemory - usedMemory;
            usagePercentage = ( (double) usedMemory / (double) committedMemory ) * ONE_HUNDRED_PERCENT;

            // Add a memory item for the found values
            memData = new ServiceMemoryData( memoryItem.getName(), freeMemory, committedMemory, maxMemory, usedMemory,
                    usagePercentage );
            this.memoryStatistics.add( memData );
        }
    }

}
