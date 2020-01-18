package org.caffinitas.ohc.chunked;

import org.caffinitas.ohc.OHCache;
import org.testng.Assert;

import java.io.IOException;

import static org.caffinitas.ohc.chunked.ChunkedCacheImplTest.cache;
import static org.testng.Assert.assertTrue;

public class MainTest {

    public static void main(String[] args) throws IOException {
        try (OHCache<Integer, String> cache = cache())
        {
            Assert.assertEquals(cache.freeCapacity(), cache.capacity());

            cache.put(11, "hello world \u00e4\u00f6\u00fc\u00df");

            assertTrue(cache.freeCapacity() < cache.capacity());

            String v = cache.get(11);
            Assert.assertEquals(v, "hello world \u00e4\u00f6\u00fc\u00df");

            cache.remove(11);

            TestUtils.fill5(cache);

            TestUtils.check5(cache);

            // implicitly compares stats
            cache.stats();
        }
    }
}
