package sample.kingja.mulcircleprogress;

import android.graphics.Color;

import com.kingja.mulcircleprogress.IProgressBar;

/**
 * Description:TODO
 * Create Time:2020/4/17 0017 下午 2:43
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class Progress implements IProgressBar {
    private String BlackgroundColor;
    private String ProgressColor;
    private float progress;

    public Progress(String blackgroundColor, String progressColor, float progress) {
        BlackgroundColor = blackgroundColor;
        ProgressColor = progressColor;
        this.progress = progress;
    }

    @Override
    public int getBlackgroundColor() {
        return Color.parseColor(BlackgroundColor) ;
    }

    @Override
    public int getProgressColor() {
        return Color.parseColor(ProgressColor) ;
    }

    @Override
    public float getProgress() {
        return progress;
    }
}
