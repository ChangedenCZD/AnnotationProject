package net.changeden.annotationproject.simple;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.widget.TextView;

import net.changeden.annotationproject.R;
import net.changeden.annotationproject.annotations.annotation.paramsAnnotation.OnResize;
import net.changeden.annotationproject.annotations.annotation.paramsAnnotation.OnSetMargin;
import net.changeden.annotationproject.annotations.annotation.paramsAnnotation.OnSetPadding;
import net.changeden.annotationproject.annotations.annotation.paramsAnnotation.OnSetTextSize;
import net.changeden.annotationproject.annotations.manager.AnnotationManager;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnnotationManager.debugable(true);
        AnnotationManager.getInstance().init(this);

        ViewPager viewPager= (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return 2;
            }

            @Override
            public Fragment getItem(int position) {
                return new MainFragment();
            }
        });
    }

    @OnSetTextSize(id=R.id.textView,size = 30,unit = TypedValue.COMPLEX_UNIT_PX)
    @OnSetPadding(id=R.id.textView,padding = 20)
    @OnSetMargin(id=R.id.textView,margin = 10)
    @OnResize(id = R.id.textView,width = 540,height = 100)
    TextView textView;
}
