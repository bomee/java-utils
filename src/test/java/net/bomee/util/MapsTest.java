package net.bomee.util;

import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

public class MapsTest {

    @Test
    public void of() {
        Map<String, String> map = Maps.of("k", "v");
        assertEquals("v", map.get("k"));
    }

    @Test
    public void ofBuilder() {
        Map<String, String> map = Maps.<String, String>ofMap()
                .put("k1", "v1")
                .put("k2", "v2")
                .asMap();

        assertEquals("v1", map.get("k1"));
        assertEquals("v2", map.get("k2"));

        map = Maps.ofMap(map).put("k3", "v3").asMap();
        assertEquals("v3", map.get("k3"));

        map = Maps.ofMap(map).clear().asMap();
        assertEquals(0, map.size());

        Map<String, String> remove = Maps.<String, String>ofMap()
                .put("k1", "v1")
                .put("k2", "v2")
                .remove("k1")
                .asMap();

        assertNull(remove.get("k1"));
        assertEquals("v2", remove.get("k2"));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void ofBuilder_unmodifiableMap() {
        Map<Object, Object> unmodifiableMap = Maps.ofMap().put("k1", "v1").unmodifiableMap();
        unmodifiableMap.clear();
    }

    @Test
    public void get() {
        Map<Object, Object> map = Maps.ofMap()
                .put("k1", null)
                .put("k2", "v2")
                .put("k3", 0)
                .put("k4", true)
                .put("k5", 1.0)
                .asMap();

        assertNull(Maps.get(map, "k1", String.class));
        assertEquals("v2", Maps.get(map, "k2", String.class));
        assertEquals(0, (int)Maps.get(map, "k3", Integer.class));
        assertTrue(Maps.get(map, "k4", Boolean.class));
        assertEquals(1.0, Maps.get(map, "k5", Double.class), 0.1);
    }
}
