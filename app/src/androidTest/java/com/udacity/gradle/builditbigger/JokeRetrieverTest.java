package com.udacity.gradle.builditbigger;

import android.test.AndroidTestCase;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class JokeRetrieverTest extends AndroidTestCase implements JokeRetriever.JokeRetrieverDelegate {

    final CountDownLatch latch = new CountDownLatch(1);

    public void testAsyncTaskGetsNonEmptyString() {

        JokeRetriever jr = new JokeRetriever(this);
        jr.execute();

        try {
            latch.await(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            fail();
        }

    }


    @Override
    public void handleJoke(String joke) {
        assertTrue(joke != null);
        assertTrue(!joke.isEmpty());
        latch.countDown();
    }
}
