package network;

import com.compucompare.compucompare.network.BestBuySource;

import org.junit.jupiter.api.Test;

public class BestBuySourceTest
{
    @Test
    public void laptopFetchTest()
    {
        BestBuySource source = new BestBuySource();
        source.getAllLaptops();
    }
}
