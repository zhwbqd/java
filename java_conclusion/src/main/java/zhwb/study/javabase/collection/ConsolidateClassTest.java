/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2012 All rights reserved. =============================
 */

package zhwb.study.javabase.collection;

import java.util.ArrayList;
import java.util.List;

public class ConsolidateClassTest
{

    public static void main(final String[] args)
    {
        ConsolidateClassTest test = new ConsolidateClassTest();

        List<ServiceInfo> summaryInfo = test.constructRawServiceInfo(prepareRawInfo());

        for (ServiceInfo serviceInfo : summaryInfo)
        {
            System.out.println(serviceInfo);
        }
    }

    private List<ServiceInfo> constructRawServiceInfo(final List<ServiceInfo> prepareRawInfo)
    {
        /*fast but complex*/

        //                Map<ServiceInfo, Float> map = new LinkedHashMap<ServiceInfo, Float>();
        //                for (ServiceInfo rawServiceInfo : prepareRawInfo)
        //                {
        //                    if (!map.containsKey(rawServiceInfo))
        //                    {
        //                        map.put(rawServiceInfo, rawServiceInfo.getPurchaseCredits());
        //                    }
        //                    else
        //                    {
        //                        float oldValue = map.get(rawServiceInfo).floatValue();
        //                        float newValue = oldValue + rawServiceInfo.getPurchaseCredits();
        //                        map.put(rawServiceInfo, newValue);
        //                    }
        //                }
        //        
        //                List<ServiceInfo> infos = new ArrayList<ServiceInfo>(map.size());
        //                for (Entry<ServiceInfo, Float> set : map.entrySet())
        //                {
        //                    ServiceInfo serviceInfo = set.getKey();
        //                    float sum = set.getValue().floatValue();
        //                    serviceInfo.setPurchaseCredits(sum);
        //                    infos.add(serviceInfo);
        //                }
        //        
        //                return infos;

        /*slowly but easy */

        List<ServiceInfo> infos = new ArrayList<ServiceInfo>();
        for (ServiceInfo rawServiceInfo : prepareRawInfo)
        {
            if (infos.contains(rawServiceInfo))
            {
                int index = infos.indexOf(rawServiceInfo);
                ServiceInfo serviceInfo = infos.get(index);
                infos.remove(index);
                serviceInfo.setPurchaseCredits(serviceInfo.getPurchaseCredits() + rawServiceInfo.getPurchaseCredits());
                infos.add(index, serviceInfo);
            }
            else
            {
                infos.add(rawServiceInfo);
            }
        }
        return infos;
    }

    private static List<ServiceInfo> prepareRawInfo()
    {
        List<ServiceInfo> infos = new ArrayList<ServiceInfo>();
        ServiceInfo info1 = new ServiceInfo();
        info1.setServiceName("ServiceA");
        info1.setServiceCatagory("All");
        info1.setPurchaseCredits(30.0F);
        infos.add(info1);

        ServiceInfo info2 = new ServiceInfo();
        info2.setServiceName("ServiceB");
        info2.setServiceCatagory("Part");
        info2.setPurchaseCredits(10.0F);
        infos.add(info2);

        ServiceInfo info3 = new ServiceInfo();
        info3.setServiceName("ServiceA");
        info3.setServiceCatagory("All");
        info3.setPurchaseCredits(20.0F);
        infos.add(info3);

        ServiceInfo info4 = new ServiceInfo();
        info4.setServiceName("ServiceB");
        info4.setServiceCatagory("All");
        info4.setPurchaseCredits(40.0F);
        infos.add(info4);

        return infos;
    }

}

class ServiceInfo
{
    private String serviceCatagory;

    private String serviceName;

    private float purchaseCredits;

    /**
     * Get the property serviceCatagory
     *
     * @return the serviceCatagory
     */
    public final String getServiceCatagory()
    {
        return serviceCatagory;
    }

    /**
     * Set the property serviceCatagory
     *
     * @param serviceCatagory the serviceCatagory to set
     */
    public final void setServiceCatagory(final String serviceCatagory)
    {
        this.serviceCatagory = serviceCatagory;
    }

    /**
     * Get the property serviceName
     *
     * @return the serviceName
     */
    public final String getServiceName()
    {
        return serviceName;
    }

    /**
     * Set the property serviceName
     *
     * @param serviceName the serviceName to set
     */
    public final void setServiceName(final String serviceName)
    {
        this.serviceName = serviceName;
    }

    /**
     * Get the property purchaseCredits
     *
     * @return the purchaseCredits
     */
    public final float getPurchaseCredits()
    {
        return purchaseCredits;
    }

    /**
     * Set the property purchaseCredits
     *
     * @param purchaseCredits the purchaseCredits to set
     */
    public final void setPurchaseCredits(final float purchaseCredits)
    {
        this.purchaseCredits = purchaseCredits;
    }

    /** {@inheritDoc}
     *  @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((serviceCatagory == null) ? 0 : serviceCatagory.hashCode());
        result = prime * result + ((serviceName == null) ? 0 : serviceName.hashCode());
        return result;
    }

    /** {@inheritDoc}
     *  @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (!(obj instanceof ServiceInfo))
        {
            return false;
        }
        ServiceInfo other = (ServiceInfo)obj;
        if (serviceCatagory == null)
        {
            if (other.serviceCatagory != null)
            {
                return false;
            }
        }
        else if (!serviceCatagory.equals(other.serviceCatagory))
        {
            return false;
        }
        if (serviceName == null)
        {
            if (other.serviceName != null)
            {
                return false;
            }
        }
        else if (!serviceName.equals(other.serviceName))
        {
            return false;
        }
        return true;
    }

    /** {@inheritDoc}
     *  @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return String.format("ServiceInfo [serviceCatagory=%s, serviceName=%s, purchaseCredits=%s]", serviceCatagory,
                serviceName, purchaseCredits);
    }

}
