/*
 * Copyright 2013 wada811<at.wada811@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package at.wada811.android.library.demos.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import at.wada811.android.library.demos.R;
import at.wada811.utils.LogUtils;

public class ExecutorActivity extends FragmentActivity implements OnClickListener {

    final ExecutorActivity self = this;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_executor);

        ((Button)findViewById(R.id.button1)).setText("newSingleThreadExecutor");
        ((Button)findViewById(R.id.button1)).setOnClickListener(this);
        ((Button)findViewById(R.id.button2)).setText("newFixedThreadPool");
        ((Button)findViewById(R.id.button2)).setOnClickListener(this);
        ((Button)findViewById(R.id.button3)).setText("newCachedThreadPool");
        ((Button)findViewById(R.id.button3)).setOnClickListener(this);
        ((Button)findViewById(R.id.button4)).setText("newSingleThreadScheduledExecutor(1)");
        ((Button)findViewById(R.id.button4)).setOnClickListener(this);
        ((Button)findViewById(R.id.button5)).setText("newSingleThreadScheduledExecutor(2)");
        ((Button)findViewById(R.id.button5)).setOnClickListener(this);
        ((Button)findViewById(R.id.button6)).setText("newScheduledThreadPool");
        ((Button)findViewById(R.id.button6)).setOnClickListener(this);
        ((Button)findViewById(R.id.button7)).setText("AtFixedRate(1)");
        ((Button)findViewById(R.id.button7)).setOnClickListener(this);
        ((Button)findViewById(R.id.button8)).setText("AtFixedRate(2)");
        ((Button)findViewById(R.id.button8)).setOnClickListener(this);
        ((Button)findViewById(R.id.button9)).setText("WithFixedDelay(1)");
        ((Button)findViewById(R.id.button9)).setOnClickListener(this);
        ((Button)findViewById(R.id.button10)).setText("WithFixedDelay(2)");
        ((Button)findViewById(R.id.button10)).setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        LogUtils.d();
        switch(v.getId()){
            case R.id.button1:
                newSingleThreadExecutorTest();
                break;
            case R.id.button2:
                newFixedThreadPoolTest();
                break;
            case R.id.button3:
                newCachedThreadPoolTest();
                break;
            case R.id.button4:
                newSingleThreadScheduledExecutorTest();
                break;
            case R.id.button5:
                newSingleThreadScheduledExecutorRunningTest();
                break;
            case R.id.button6:
                newScheduledThreadPoolTest();
                break;
            case R.id.button7:
                newSingleThreadScheduledExecutorAtFixedRateTest();
                break;
            case R.id.button8:
                newSingleThreadScheduledExecutorAtFixedRateRunningTest();
                break;
            case R.id.button9:
                newSingleThreadScheduledExecutorWithFixedDelayTest();
                break;
            case R.id.button10:
                newSingleThreadScheduledExecutorWithFixedDelayRunningTest();
                break;
            default:
                break;
        }
        LogUtils.d();
    }

    public void newSingleThreadExecutorTest(){
        LogUtils.d();
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(new ExecutorRunnable("A", 1));
        executorService.submit(new ExecutorRunnable("B", 1));
        executorService.submit(new ExecutorRunnable("C", 1));
        executorService.submit(new ExecutorRunnable("D", 1));
    }

    public void newFixedThreadPoolTest(){
        LogUtils.d();
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(new ExecutorRunnable("A", 1));
        executorService.submit(new ExecutorRunnable("B", 1));
        executorService.submit(new ExecutorRunnable("C", 1));
        executorService.submit(new ExecutorRunnable("D", 1));
    }

    public void newCachedThreadPoolTest(){
        LogUtils.d();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(new ExecutorRunnable("A", 1));
        executorService.submit(new ExecutorRunnable("B", 1));
        executorService.submit(new ExecutorRunnable("C", 1));
        executorService.submit(new ExecutorRunnable("D", 1));
    }

    public void newSingleThreadScheduledExecutorTest(){
        LogUtils.d();
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.schedule(new ExecutorRunnable("A", 1), 10, TimeUnit.SECONDS);
        executorService.schedule(new ExecutorRunnable("B", 1), 5, TimeUnit.SECONDS);
        executorService.schedule(new ExecutorRunnable("C", 1), 0, TimeUnit.SECONDS);
        executorService.schedule(new ExecutorRunnable("D", 1), 15, TimeUnit.SECONDS);
    }

    public void newSingleThreadScheduledExecutorRunningTest(){
        LogUtils.d();
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.schedule(new ExecutorRunnable("A", 1), 1, TimeUnit.SECONDS);
        executorService.schedule(new ExecutorRunnable("B", 1), 1, TimeUnit.SECONDS);
        executorService.schedule(new ExecutorRunnable("C", 1), 1, TimeUnit.SECONDS);
        executorService.schedule(new ExecutorRunnable("D", 1), 1, TimeUnit.SECONDS);
    }

    public void newScheduledThreadPoolTest(){
        LogUtils.d();
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(2);
        executorService.schedule(new ExecutorRunnable("A", 1), 1, TimeUnit.SECONDS);
        executorService.schedule(new ExecutorRunnable("B", 1), 1, TimeUnit.SECONDS);
        executorService.schedule(new ExecutorRunnable("C", 1), 1, TimeUnit.SECONDS);
        executorService.schedule(new ExecutorRunnable("D", 1), 1, TimeUnit.SECONDS);
    }

    public void newSingleThreadScheduledExecutorAtFixedRateTest(){
        LogUtils.d();
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(new ExecutorRunnable("A", 1), 1, 2, TimeUnit.SECONDS);
        shutdown(executorService);
    }

    public void newSingleThreadScheduledExecutorAtFixedRateRunningTest(){
        LogUtils.d();
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(new ExecutorRunnable("A", 3), 1, 2, TimeUnit.SECONDS);
        shutdown(executorService);
    }

    public void newSingleThreadScheduledExecutorWithFixedDelayTest(){
        LogUtils.d();
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleWithFixedDelay(new ExecutorRunnable("A", 1), 1, 2, TimeUnit.SECONDS);
        shutdown(executorService);
    }

    public void newSingleThreadScheduledExecutorWithFixedDelayRunningTest(){
        LogUtils.d();
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleWithFixedDelay(new ExecutorRunnable("A", 3), 1, 2, TimeUnit.SECONDS);
        shutdown(executorService);
    }

    public void shutdown(ExecutorService executorService){
        try{
            TimeUnit.SECONDS.sleep(8);
            LogUtils.d("shutdown");
            executorService.shutdown();
            if(!executorService.awaitTermination(1, TimeUnit.SECONDS)){
                LogUtils.d("shutdownNow");
                executorService.shutdownNow();
            }
        }catch(InterruptedException e){
            e.printStackTrace();
            LogUtils.d("shutdownNow: " + e.getMessage());
            executorService.shutdownNow();
        }
    }

    public class ExecutorRunnable implements Runnable {
        private String mName;
        private int mSeconds;

        public ExecutorRunnable(String name, int seconds) {
            mName = name;
            mSeconds = seconds;
        }

        @Override
        public void run(){
            LogUtils.d(mName + ": ThreadId: " + Thread.currentThread().getId());
            LogUtils.d(mName + ": start");
            try{
                TimeUnit.SECONDS.sleep(mSeconds);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            LogUtils.d(mName + ": end");
        }
    }

}
