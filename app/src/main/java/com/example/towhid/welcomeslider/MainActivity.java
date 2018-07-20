package com.example.towhid.welcomeslider;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private ViewPager mSlideViewPager;
    private LinearLayout mdotLayout;
    private TextView []mdots;
    private SliderAdapter sliderAdapter;
    private Button mNextBtn;
    private Button mBackBtn;
    private int mCurrentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSlideViewPager=(ViewPager)findViewById(R.id.viewpager_id);
        mdotLayout=(LinearLayout)findViewById(R.id.dotsLayout);
        mNextBtn=(Button)findViewById(R.id.next_btn);
        mBackBtn=(Button)findViewById(R.id.privious_btn);



        sliderAdapter=new SliderAdapter(this);
        mSlideViewPager.setAdapter(sliderAdapter);

        addDotsIndicator(0);
        mSlideViewPager.addOnPageChangeListener(viewListener);

        mNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSlideViewPager.setCurrentItem(mCurrentPage+1);
            }
        });
        mBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSlideViewPager.setCurrentItem(mCurrentPage-1);
            }
        });

    }
    public void addDotsIndicator(int position){
        mdots=new TextView[3];
        mdotLayout.removeAllViews();
        for (int i=0;i<mdots.length;i++)
        {
            mdots[i]=new TextView(this);
            mdots[i].setText(Html.fromHtml("&#8226"));
            mdots[i].setTextSize(35);
            mdots[i].setTextColor(getResources().getColor(R.color.colorTransparentWhite));
            mdotLayout.addView(mdots[i]);
        }
        if (mdots.length>0)
            mdots[position].setTextColor(getResources().getColor(R.color.colorWhite));
    }
    ViewPager.OnPageChangeListener viewListener=new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDotsIndicator(position);
            mCurrentPage=position;
            if (position==0)
            {
                mNextBtn.setVisibility(View.VISIBLE);
                mBackBtn.setVisibility(View.GONE);
            }
            else if (position==mdots.length-1)
            {   mNextBtn.setText("Finish");
                mNextBtn.setVisibility(View.VISIBLE);
                mBackBtn.setVisibility(View.VISIBLE);
            }
            else
            {
                mNextBtn.setText("Next");
                mNextBtn.setVisibility(View.VISIBLE);
                mBackBtn.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}
