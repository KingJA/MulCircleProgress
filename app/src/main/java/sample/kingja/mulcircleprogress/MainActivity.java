package sample.kingja.mulcircleprogress;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.kingja.mulcircleprogress.MulCircleProgress;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MulCircleProgress mcp = findViewById(R.id.mcp);
        List<Progress> progressList = new ArrayList<>();
        progressList.add(new Progress("#999999","#2196F3",0.55f));
        progressList.add(new Progress("#999999","#9C27B0",0.23f));
        progressList.add(new Progress("#999999","#4CAF50",0.33f));
        progressList.add(new Progress("#999999","#FFC107",0.85f));
        progressList.add(new Progress("#999999","#E91E63",0.75f));
        progressList.add(new Progress("#999999","#FF5722",0.45f));
        progressList.add(new Progress("#999999","#009688",1.0f));
        mcp.setData(progressList);
    }
}
