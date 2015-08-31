package com.udinic.perfdemo;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

/**
 * Demonstrates running an animation with a more "challenging" rendering conditions.
 *
 * There are 2 checkboxes that enable features:
 *
 *      - Use HW layer - This will enable HW layer for the animated item. Notice that the
 *      item is being updated during the animation, turning on "Show hardware layers updates" will show
 *      how this view is flashing green throughout the animation.
 *
 *      - Alpha overlay - This will set an alpha value to a layout overlay in the center of the
 *      screen. Using transparency we're making the GPU do more complicated rendering, which is shown
 *      as an alert on Systrace.
 *
 */
public class HeavyAnimationActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heavy_animation);

        final CheckBox chkHwLayer = (CheckBox) findViewById(R.id.chkUseHwLayer);
        final CheckBox chkAlpha = (CheckBox) findViewById(R.id.chkUseAlpha);
        final View alphaLayout = findViewById(R.id.alphaLayout);

        chkAlpha.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                alphaLayout.setAlpha(isChecked ? 0.5f : 1f);
            }
        });

        findViewById(R.id.btnStart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final View view = findViewById(R.id.udinic);

                ViewPropertyAnimator animator = view.animate()
                    .translationX(240f)
                    .translationY(-600)
                    .alpha(0.2f)
                    .rotation(3000f)
                    .scaleX(2f)
                    .setDuration(8000)
                    .setUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator animation) {
                            // Updating the text during the animation.
                            // This will cause constant HW layer updates.
                            ((Button) view.findViewById(R.id.btn2))
                                .setText("a" + animation.getAnimatedFraction() * 100);
                        }
                    });

                if (chkHwLayer.isChecked()) animator.withLayer();

                animator.start();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_animation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
