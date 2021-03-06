package com.snowypeaksystems.mobportals.warps;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import com.snowypeaksystems.mobportals.mock.FakeWorld;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.io.File;
import java.io.IOException;
import org.bukkit.Location;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * Tests for the Warp object.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
@SuppressFBWarnings("RV_RETURN_VALUE_IGNORED_BAD_PRACTICE")
public class WarpTest {
  private static File warpDir;

  /** Creates a WarpTest with a warpDir File. */
  @BeforeAll
  public static void setup() {
    warpDir = new File("tests/warps/");
    warpDir.delete();
    warpDir.mkdirs();
  }

  @Test
  public void getName() {
    IWarp w = new Warp("Test", new Location(new FakeWorld(), 0, 0, 0), warpDir);
    assertEquals("Test", w.getName());
  }

  @Test
  public void getDestination() {
    Location loc = new Location(new FakeWorld(), 0, 0, 0);
    IWarp w = new Warp("Test", loc, warpDir);
    assertEquals(loc, w.getDestination());
  }

  @Test
  public void save() {
    Location loc = new Location(new FakeWorld(), 0, 0, 0);
    try {
      IWarp w = new Warp("Test", loc, warpDir);
      w.save();
      assertTrue(new File(warpDir, String.valueOf(w.getName().toLowerCase().hashCode())).exists());

      w.delete();
    } catch (IOException e) {
      fail();
    }
  }

  @Test
  public void delete() {
    Location loc = new Location(new FakeWorld(), 0, 0, 0);
    try {
      IWarp w = new Warp("Test", loc, warpDir);
      w.save();
      w.delete();
      assertFalse(new File(warpDir, String.valueOf(w.getName().toLowerCase().hashCode())).exists());
    } catch (IOException e) {
      fail();
    }
  }

  @AfterAll
  public static void tearDown() {
    warpDir.delete();
    assertFalse(warpDir.exists());
  }
}