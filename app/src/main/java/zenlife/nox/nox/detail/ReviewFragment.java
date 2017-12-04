package zenlife.nox.nox.detail;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import zenlife.nox.nox.R;

public class ReviewFragment extends Fragment {

    public static ReviewFragment newInstance() {
        ReviewFragment fragment = new ReviewFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_review, container, false);
        return view;
    }

}
