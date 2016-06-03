package com.schoolteacher.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.schoolteacher.MyApplication;
import com.schoolteacher.R;
import com.schoolteacher.pojos.DealsAndOfferResponse;

/**
 * Created by chandan on 11/12/15.
 */
public class OfferDetailsFragment extends Fragment {

    private DealsAndOfferResponse offerDetail;

    public static OfferDetailsFragment getInstance(DealsAndOfferResponse offerDetail) {

        OfferDetailsFragment offerDetailsFragment = new OfferDetailsFragment();
        offerDetailsFragment.offerDetail = offerDetail;
        return offerDetailsFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        MyApplication.getInstance().trackScreenView("Offer Detail Screen");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.offer_details_fragment, container,
                false);

        TextView descriptionTxtvw = (TextView) rootView.findViewById(R.id.description_txtvw);
        descriptionTxtvw.setText(offerDetail.getDescription().toString());
        TextView termsTxtvw = (TextView) rootView.findViewById(R.id.terms_txtvw);
        termsTxtvw.setText(Html.fromHtml(offerDetail.getTermAndConditions()).toString());
        return rootView;

    }
}
