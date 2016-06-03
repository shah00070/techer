package com.schoolteacher.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.schoolteacher.R;
import com.schoolteacher.main.DealsAndOfferListingFragment;
import com.schoolteacher.main.OfferDetailsFragment;
import com.schoolteacher.pojos.DealsAndOfferResponse;

import java.util.List;

/**
 * Created by chandan on 10/12/15.
 */
public class DealsAndOfferAdapter extends BaseAdapter {

    private List<DealsAndOfferResponse> offerList;
    private Fragment fragment;

    public DealsAndOfferAdapter(Fragment fragment, List<DealsAndOfferResponse> offerlist) {
        this.fragment = fragment;
        this.offerList = offerlist;
    }

    @Override
    public int getCount() {
        return offerList.size();
    }

    @Override
    public Object getItem(int position) {
        return offerList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {

        final ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) fragment.getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.deals_offer_list_item, null);
            holder.offerTitleTxtVw = (TextView) convertView.findViewById(R.id.offer_title);
            holder.couponCodeTxtVw = (TextView) convertView.findViewById(R.id.coupon_code);
            holder.detailsBtn = (TextView) convertView.findViewById(R.id.details_btn);
            holder.grabOfferBtn = (TextView) convertView.findViewById(R.id.grab_offer_btn);


            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();

        holder.offerTitleTxtVw.setText(offerList.get(position).getName());
        holder.couponCodeTxtVw.setText(offerList.get(position).getCode());

        holder.detailsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment offerFragment = OfferDetailsFragment.getInstance(offerList.get(position));
                final FragmentTransaction ft = fragment.getFragmentManager().beginTransaction();
                ft.replace(R.id.container_body, offerFragment, null);
                ft.commit();
            }
        });



        boolean x=offerList.get(position).isClaimable();
        if(x==false){
            holder.grabOfferBtn.setVisibility(View.INVISIBLE);
        }

        holder.grabOfferBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((DealsAndOfferListingFragment) fragment).requestForGrabOffer(position);
            }
        });

        return convertView;
    }


    public class ViewHolder {
        TextView offerTitleTxtVw;
        TextView couponCodeTxtVw;
        TextView detailsBtn;
        TextView grabOfferBtn;
    }

}
