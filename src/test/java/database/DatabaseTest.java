package database;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import com.compucompare.compucompare.App;
import com.compucompare.compucompare.components.BatteryComponent;
import com.compucompare.compucompare.components.CPUComponent;
import com.compucompare.compucompare.components.DisplayComponent;
import com.compucompare.compucompare.components.GPUComponent;
import com.compucompare.compucompare.components.NetworkComponent;
import com.compucompare.compucompare.components.RAMComponent;
import com.compucompare.compucompare.components.StorageComponent;
import com.compucompare.compucompare.computerType.Laptop;
import com.compucompare.compucompare.database.LaptopRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest(classes = App.class)
@Transactional
public class DatabaseTest
{
    @Autowired
    private LaptopRepository laptopRepository;

    private Laptop testLaptop;

    /**
     * Store an example laptop in the database.
     */
    @BeforeEach
    public void setup()
    {
        Set<StorageComponent> drives = new HashSet<>();
        drives.add(new StorageComponent("Samsung", "EVO", 256, true, true));
        drives.add(new StorageComponent("WD", "Blue", 1000, false, false));
        Set<NetworkComponent> interfaces = new HashSet<>();
        Set<String> supportedWirelessStandards = new HashSet<>();
        supportedWirelessStandards.add("BT");
        supportedWirelessStandards.add("802.11a");
        supportedWirelessStandards.add("802.11b");
        supportedWirelessStandards.add("802.11g");
        supportedWirelessStandards.add("802.11n");
        supportedWirelessStandards.add("802.11ac");
        supportedWirelessStandards.add("802.11ax");
        interfaces.add(new NetworkComponent("Intel", "AX200", 2400, true, supportedWirelessStandards));
        testLaptop = new Laptop("HP", "dsuyf7tud",
            new CPUComponent("Intel", "9750H", 1000, 2000, 4, "x86"),
            new GPUComponent("Nvidia", "GTX 1660Ti Mobile", 1000),
            new RAMComponent("Crucial", "Ballistix", 16, 2400, true),
            drives,
            interfaces,
            new DisplayComponent("HP", "8743yevfn", 15, 1920, 1080, 55),
            new BatteryComponent("Unknown", "Unknown", 5.5, 5000));
        laptopRepository.save(testLaptop);
    }

    @AfterEach
    public void cleanup()
    {
        laptopRepository.delete(testLaptop);
    }

    /**
     * Test case to store a Laptop in the database
     * and retrieve it to ensure all the properties
     * have been preserved.
     * 
     * As a product of the Laptop class having
     * every available Component object as a
     * member, this test subsequently tests
     * the persistence of every Component. 
     */
    @Test
    public void storeLaptopTest()
    {
        int id = testLaptop.getId();
        Optional<Laptop> results;
        results = laptopRepository.findById(id);
        assertTrue(results.isPresent());
        Laptop foundLaptop = results.get();
        assertEquals(testLaptop, foundLaptop);
    }
}
