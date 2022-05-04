package component;

import java.util.Timer;

public class test {



    Timer timer = new Timer();

    timer.schedule(new TimerTask() {
        @Override
        public void run() {
            // Your database code here
        }
    }, 2*60*1000);
// Since Java-8
timer.schedule(() -> /* your database code here */, 2*60*1000);
}
