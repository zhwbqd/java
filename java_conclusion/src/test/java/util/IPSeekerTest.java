package util;

import org.testng.annotations.Test;

import static org.testng.Assert.assertNotEquals;
import static org.testng.AssertJUnit.assertEquals;

public class IPSeekerTest {

    @Test
    public void testGetAddress() {
        String areaName = IPSeeker.getInstance().getAddress("116.236.194.253");
        assertEquals("上海市 /普陀区电信", areaName);
    }

    @Test(description = "直辖市")
    public void testGetCountrySH() {
        String areaName = IpSeekerFactory.getCountry("116.236.194.253");
        String areaName1 = IpSeekerFactory.getCountry("114.80.166.240");
        String areaName2 = IpSeekerFactory.getCountry("114.80.163.38");

        assertEquals(areaName, areaName1);
        assertEquals(areaName1, areaName2);
    }

    @Test(description = "普通城市")
    public void testGetCountrySD() {
        String areaName = IPSeeker.getInstance().getCountry("58.57.106.210");
        String areaName1 = IPSeeker.getInstance().getCountry("58.57.106.214");
        String areaName2 = IPSeeker.getInstance().getCountry("114.80.163.38");

        assertEquals(areaName, areaName1);
        assertNotEquals(areaName1, areaName2);
    }

    @Test(description = "普通城市济宁")
    public void testGetCountryJN() {
        String areaName = IPSeeker.getInstance().getCountry("222.173.146.54");
        String areaName1 = IPSeeker.getInstance().getCountry("222.173.148.75");
        String areaName2 = IPSeeker.getInstance().getCountry("222.173.149.175");
        String areaName3 = IPSeeker.getInstance().getCountry("222.173.159.1");

        assertEquals(areaName, areaName1);
        assertEquals(areaName1, areaName2);
        assertEquals(areaName2, areaName3);
    }

    @Test
    public void testGetCountry() {
        String areaName = IPSeeker.getInstance().getCountry("122.226.169.100");
        System.out.println(areaName);
        assertEquals("浙江省台州市", areaName);
    }

    @Test
    public void testGetCountry_QD() {
        String areaName = IPSeeker.getInstance().getCountry("218.58.54.217");
        System.out.println(areaName);
        assertEquals("山东省青岛市", areaName);
    }

    @Test
    public void testGetArea() {
        String areaName = IPSeeker.getInstance().getArea("218.58.54.217");
        System.out.println(areaName);
        assertEquals("联通", areaName);
    }
}
