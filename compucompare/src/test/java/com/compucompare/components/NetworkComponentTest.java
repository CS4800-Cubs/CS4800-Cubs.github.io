package com.compucompare.components;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;

import com.compucompare.compucompare.components.NetworkComponent;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test various functions of the NetworkComponent class.
 */
public class NetworkComponentTest
{
    NetworkComponent testComponent;

    public NetworkComponent createExampleComponent()
    {
        Set<String> supportedWirelessStandards = new HashSet<>();
        supportedWirelessStandards.add("BT");
        supportedWirelessStandards.add("802.11a");
        supportedWirelessStandards.add("802.11b");
        supportedWirelessStandards.add("802.11g");
        supportedWirelessStandards.add("802.11n");
        supportedWirelessStandards.add("802.11ac");
        supportedWirelessStandards.add("802.11ax");
        return new NetworkComponent("Intel", "AX200", 2400, true, supportedWirelessStandards);
    }

    @BeforeEach
    public void setup()
    {
        testComponent = createExampleComponent();
    }

    /**
     * Make sure the internally referenced
     * set of network standards cannot be
     * unintentionally maniuplated.
     */
    @Test
    public void setModificationTest()
    {
        // Retrieved Set Shouldn't Match Internal Reference
        Set<String> standards = testComponent.getStandards();
        standards.add("New Standard");
        standards = testComponent.getStandards();
        assertFalse(standards.contains("New Standard"));
        // Given Set Shouldn't Be Referenced Internally
        standards.clear();
        testComponent.setStandards(standards);
        standards.add("New Standard");
        standards = testComponent.getStandards();
        assertFalse(standards.contains("New Standard"));
    }

    /**
     * Make sure different references describing
     * the same component are considered equal.
     */
    @Test
    public void equalsTest()
    {
        NetworkComponent other = createExampleComponent();
        assertTrue(testComponent.equals(other));
    }

    /**
     * Make sure different references describing
     * different components are not considered the same.
     */
    @Test
    public void notEqualsTest()
    {
        NetworkComponent other = createExampleComponent();
        other.setMaxSpeed(3000);
        assertFalse(testComponent.equals(other));
    }
}
