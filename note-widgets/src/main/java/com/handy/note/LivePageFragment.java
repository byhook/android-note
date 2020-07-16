package com.handy.note;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.handy.note.adapter.AbsFragmentPagerAdapter;
import com.handy.note.adapter.CyclePagerAdapter;
import com.handy.note.widgets.NewViewPager;
import com.handy.note.widgets.R;

import java.util.Random;

/**
 * @author: handy
 * @date: 2020-07-15
 * @description:
 */
public class LivePageFragment extends Fragment {

    private static final String TAG = "LivePageFragment";
    private View rootView;
    private TextView btnShow;

    public static LivePageFragment newInstance() {
        Bundle args = new Bundle();
        LivePageFragment fragment = new LivePageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.new_page_main,container,false);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View targetView = rootView.getRootView();
        NewViewPager newViewPager = targetView.findViewById(R.id.main_view_pager);
        Log.d("TAG","targetView=" + targetView);
        Random random = new Random(System.currentTimeMillis());
        btnShow = rootView.findViewById(R.id.btn_add);
        btnShow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CyclePagerAdapter adapter = (CyclePagerAdapter) newViewPager.getFragmentAdapter();
                        if(adapter.insertNextData(new TestData(8 + random.nextInt(7)))){
                            newViewPager.turnNextItem();
                        }
                    }
                });

        ViewPager viewPager = rootView.findViewById(R.id.view_pager);
        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return 3;
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                return true;
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                View rootView = LayoutInflater.from(container.getContext()).inflate(R.layout.new_page,container,false);
                container.addView(rootView);
                return rootView;
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                View rootView = (View) object;
                container.removeView(rootView);
            }

        });
    }

    public void updateFragment(TestData data){
        btnShow.setText("roomId=" + data.roomId);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"onCreate");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy");
    }

}
