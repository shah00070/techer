package com.schoolteacher.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.schoolteacher.R;
import com.schoolteacher.library.main.SignUpLoginActivity;
import com.schoolteacher.main.CircleImageView;
import com.schoolteacher.mylibrary.session.JeevomSession;
import com.schoolteacher.mylibrary.util.CommonCode;
import com.schoolteacher.pojos.JeevLinkedResult;
import com.schoolteacher.pojos.JeevSearchResult;
import com.schoolteacher.search.JeevSearchFragment;
import com.schoolteacher.util.JeevomUtilsClass;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SearchListAdapter extends BaseAdapter {
    private Fragment fragment;
    private List<JeevSearchResult> listItems;
    int sdk;
    // Animation animation;
    JeevSearchResult jeevSearchObject;

    public SearchListAdapter(Fragment fragment,
                             List<JeevSearchResult> searchResults) {
        this.fragment = fragment;
        this.listItems = searchResults;

    }

    @Override
    public int getItemViewType(int position) {
        return (listItems.get(position).getType().equals("Professional")) ? 0
                : 1;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getCount() {
        return listItems.size();
    }

    @Override
    public Object getItem(int location) {
        return listItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        sdk = android.os.Build.VERSION.SDK_INT;
        final ViewHolder holder;
        jeevSearchObject = listItems.get(position);
        final int type = getItemViewType(position);
        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) fragment.getActivity()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            if (type == 0) {
                convertView = inflater.inflate(R.layout.jeev_list_row, null);
                holder.thumbNail = (CircleImageView) convertView
                        .findViewById(R.id.thumbnail);
                holder.name = (TextView) convertView.findViewById(R.id.name);
                holder.degrees = (TextView) convertView
                        .findViewById(R.id.degrees);
                holder.specialization = (TextView) convertView
                        .findViewById(R.id.specialization);
                holder.recommended = (ImageView) convertView
                        .findViewById(R.id.recommended);
                holder.certified = (ImageView) convertView
                        .findViewById(R.id.certified);
                holder.menu = (ImageView) convertView.findViewById(R.id.menu);
                holder.likes_value = (TextView) convertView
                        .findViewById(R.id.likes_value);
                holder.recommended_text = (TextView) convertView
                        .findViewById(R.id.recommended_text);
                holder.fee_text = (TextView) convertView
                        .findViewById(R.id.fee_text);
                holder.review_text = (TextView) convertView
                        .findViewById(R.id.review_text);

                holder.clinics_layout = (LinearLayout) convertView
                        .findViewById(R.id.clinics_layout);
                holder.extras = (ImageView) convertView
                        .findViewById(R.id.extras);

                holder.clinic_contain = (RelativeLayout) convertView
                        .findViewById(R.id.clinic_contain);
                holder.clinic_name = (TextView) convertView
                        .findViewById(R.id.clinic_name);
                holder.distance = (TextView) convertView
                        .findViewById(R.id.distance);
                holder.address = (TextView) convertView
                        .findViewById(R.id.address);

                holder.imageView_instant_apoint = (ImageView) convertView
                        .findViewById(R.id.imagevw_instant_appointment);

                convertView.setTag(holder);
            } else {
                convertView = inflater.inflate(R.layout.jeev_facility_list_row,
                        null);
                holder.thumbNail = (CircleImageView) convertView
                        .findViewById(R.id.thumbnail);
                holder.name = (TextView) convertView
                        .findViewById(R.id.facility_name);
                holder.degrees = (TextView) convertView
                        .findViewById(R.id.facility_address);
                holder.specialization = (TextView) convertView
                        .findViewById(R.id.facility_services);
                holder.recommended = (ImageView) convertView
                        .findViewById(R.id.recommended);
                holder.certified = (ImageView) convertView
                        .findViewById(R.id.certified);
                holder.menu = (ImageView) convertView.findViewById(R.id.menu);
                holder.likes_value = (TextView) convertView
                        .findViewById(R.id.likes_value);
                holder.recommended_text = (TextView) convertView
                        .findViewById(R.id.recommended_text);
                holder.fee_text = (TextView) convertView
                        .findViewById(R.id.fee_text);
                holder.review_text = (TextView) convertView
                        .findViewById(R.id.review_text);


                holder.imageView_instant_apoint = (ImageView) convertView
                        .findViewById(R.id.imagevw_instant_appointment);

                convertView.setTag(holder);
            }

        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        if (jeevSearchObject.isIsVerified()) {
            holder.recommended.setVisibility(View.VISIBLE);
        } else {
            holder.recommended.setVisibility(View.INVISIBLE); // hide visibility
        }
        if (jeevSearchObject.isDiscountOffered()) {
            holder.certified.setVisibility(View.VISIBLE);
        } else {
            holder.certified.setVisibility(View.INVISIBLE); // hide visibility
        }

        // add network image
        String img = null;

        if (!(CommonCode.isNullOrEmpty(jeevSearchObject.getProfilePhoto()))) {
            img = jeevSearchObject.getProfilePhoto().replace(" ", "%20");
        }

        if (type == 0) {
            if (!CommonCode.isNullOrEmpty(img)) {
                Picasso.with(fragment.getActivity()).load(img)
                        .placeholder(R.drawable.jeevom_back)
                        .error(R.drawable.jeevom_back).into(holder.thumbNail);
            } else {
                Picasso.with(fragment.getActivity()).load("")
                        .placeholder(R.drawable.jeevom_back)
                        .error(R.drawable.jeevom_back).into(holder.thumbNail);
            }

            // for professional
            String doctor_title = jeevSearchObject.getTitle();
            String firstName = jeevSearchObject.getFirstName();
            String lastName = jeevSearchObject.getLastName();

            if (!CommonCode.isNullOrEmpty(doctor_title)) {
                if (!CommonCode.isNullOrEmpty(firstName)) {
                    if (!CommonCode.isNullOrEmpty(lastName)) {
                        holder.name.setText((doctor_title + " " + firstName
                                + " " + lastName).trim());
                    } else {
                        holder.name.setText((doctor_title + " " + firstName)
                                .trim());
                    }
                } else if (!CommonCode.isNullOrEmpty(lastName)) {
                    holder.name.setText((doctor_title + " " + lastName).trim());
                }

            } else {

                if (!CommonCode.isNullOrEmpty(firstName)) {
                    holder.name.setVisibility(View.VISIBLE);
                    if (!CommonCode.isNullOrEmpty(lastName)) {
                        holder.name.setText(firstName + " " + lastName);
                    } else {
                        holder.name.setText(firstName);
                    }
                } else if (!CommonCode.isNullOrEmpty(lastName)) {
                    holder.name.setVisibility(View.VISIBLE);
                    holder.name.setText(lastName);
                } else {
                    holder.name.setVisibility(View.INVISIBLE);
                }

            }

            // 2. Assigning Educational Qualifications
            List<String> educationalQualifications = jeevSearchObject
                    .getEducationalQualifications();
            if (educationalQualifications != null
                    && !educationalQualifications.isEmpty()) {
                holder.degrees.setVisibility(View.VISIBLE);
                String educational_qualification = CommonCode
                        .toCommaSeparatedString(jeevSearchObject
                                .getEducationalQualifications());
                holder.degrees.setText(educational_qualification);
            } else {
                holder.degrees.setVisibility(View.INVISIBLE);
            }

            // Assigning Specialities
            if (jeevSearchObject.getSpecialities() != null) {
                holder.specialization.setVisibility(View.VISIBLE);
                holder.specialization.setText(CommonCode
                        .toCommaSeparatedString(jeevSearchObject
                                .getSpecialities()));
            } else {
                holder.specialization.setVisibility(View.INVISIBLE);
            }

            // set likes
            if (jeevSearchObject.getProfileViewCount() > 0)

                if (jeevSearchObject.getProfileViewCount() < 50)
                    holder.likes_value.setText("<50");
                else
                    holder.likes_value.setText(String.valueOf(jeevSearchObject
                            .getProfileViewCount()));

            else
                holder.likes_value.setText("<50");

            // set fee
            if (!CommonCode.isNullOrEmpty(jeevSearchObject.getFees()))
                holder.fee_text.setText(jeevSearchObject.getFees());
            else
                holder.fee_text.setText("N/A");

            // set experience
            if (!CommonCode.isNullOrEmpty(jeevSearchObject
                    .getExperienceYearsText())) {
                holder.recommended_text.setText(jeevSearchObject
                        .getExperienceYearsText());
            }

            // set clinics
            if (jeevSearchObject.getLinkedResults() != null
                    && jeevSearchObject.getLinkedResults().size() > 0) {
                holder.clinic_contain.setVisibility(View.VISIBLE);

                List<JeevLinkedResult> linkedResults = jeevSearchObject
                        .getLinkedResults();

                // set name
                holder.clinic_name.setText(linkedResults.get(0).getName());

                // set address
                if (linkedResults.get(0).getContactInfo() != null) {
                    StringBuilder addresss = new StringBuilder();
                    if (linkedResults.get(0).getContactInfo().getAddress() != null)
                        if (!CommonCode.isNullOrEmpty(linkedResults.get(0)
                                .getContactInfo().getAddress().getArea())) {
                            addresss.append(linkedResults.get(0)
                                    .getContactInfo().getAddress().getArea()
                                    + ", ");

                        }

                    if (!CommonCode.isNullOrEmpty(linkedResults.get(0)
                            .getContactInfo().getAddress().getCity())) {
                        addresss.append(linkedResults.get(0).getContactInfo()
                                .getAddress().getCity());
                    }

                    holder.address.setText(addresss.toString());

                    // set distance

                    if (((int) linkedResults.get(0).getDistance()) >= 0) {
                        if (((int) linkedResults.get(0).getDistance()) == 0) {
                            holder.distance.setText("<1 Km");
                        } else {
                            holder.distance.setText(linkedResults.get(0)
                                    .getDistance() + " Km");
                        }
                    } else {
                        holder.distance.setText("<1 Km");
                    }
                }
            } else {
                holder.clinic_contain.setVisibility(View.GONE);

            }

            // set linked results
            holder.clinics_layout.removeAllViews();
            if (jeevSearchObject.getLinkedResults() != null
                    && jeevSearchObject.getLinkedResults().size() > 1) {

                holder.extras.setVisibility(View.VISIBLE);
                View view = null;
                List<JeevLinkedResult> linkedResults = jeevSearchObject
                        .getLinkedResults();

                // for (JeevLinkedResult jeevLinkedResult : linkedResults) {
                for (int i = 1; i < linkedResults.size(); i++) {
                    JeevLinkedResult jeevLinkedResult = linkedResults.get(i);
                    LayoutInflater inflator = (LayoutInflater) fragment
                            .getActivity().getSystemService(
                                    Context.LAYOUT_INFLATER_SERVICE);

                    view = inflator.inflate(R.layout.jeev_list_clinics_layout,
                            null);

                    TextView clinic_name = (TextView) view
                            .findViewById(R.id.clinic_name);
                    TextView distance = (TextView) view
                            .findViewById(R.id.distance);
                    TextView address = (TextView) view
                            .findViewById(R.id.address);

                    if (!CommonCode.isNullOrEmpty(jeevLinkedResult.getName())) {
                        clinic_name.setText(jeevLinkedResult.getName());
                    }

                    if (jeevLinkedResult.getContactInfo() != null) {
                        StringBuilder addresss = new StringBuilder();
                        if (jeevLinkedResult.getContactInfo().getAddress() != null)
                            if (!CommonCode.isNullOrEmpty(jeevLinkedResult
                                    .getContactInfo().getAddress().getArea())) {
                                addresss.append(jeevLinkedResult
                                        .getContactInfo().getAddress()
                                        .getArea()
                                        + ", ");

                            }

                        if (!CommonCode.isNullOrEmpty(jeevLinkedResult
                                .getContactInfo().getAddress().getCity())) {
                            addresss.append(jeevLinkedResult.getContactInfo()
                                    .getAddress().getCity());
                        }

                        address.setText(addresss.toString());
                    }

                    // set distance
                    if (((int) linkedResults.get(0).getDistance()) >= 0) {
                        if (((int) linkedResults.get(0).getDistance()) == 0) {
                            holder.distance.setText("<1 Km");
                        } else {
                            holder.distance.setText(linkedResults.get(0)
                                    .getDistance() + " Km");
                        }
                    } else {
                        holder.distance.setText("<1 Km");
                    }

                    if (((int) jeevLinkedResult.getDistance()) >= 0) {
                        if (((int) jeevLinkedResult.getDistance()) == 0) {
                            distance.setText("<1 Km");
                        } else {
                            distance.setText(jeevLinkedResult.getDistance()
                                    + "");
                        }
                    } else {
                        holder.distance.setText("<1 Km");
                    }
                    holder.clinics_layout.addView(view);
                }

            } else {
                holder.extras.setVisibility(View.GONE);

            }

            holder.extras.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (holder.clinics_layout.getVisibility() == View.VISIBLE) {
                        holder.clinics_layout.setVisibility(View.GONE);
                        JeevomUtilsClass.rotate12to6ClockWise(holder.extras);
                    } else {
                        holder.clinics_layout.setVisibility(View.VISIBLE);

                        JeevomUtilsClass.rotate6to12ClockWise(holder.extras);
                    }

                }
            });
        } else {

            // set image
            if (!CommonCode.isNullOrEmpty(img)) {
                Picasso.with(fragment.getActivity()).load(img)
                        .placeholder(R.drawable.jeevom_back)
                        .error(R.drawable.jeevom_back).into(holder.thumbNail);
            } else {
                Picasso.with(fragment.getActivity()).load("")
                        .placeholder(R.drawable.jeevom_back)
                        .error(R.drawable.jeevom_back).into(holder.thumbNail);
            }

            // set name
            if (!CommonCode.isNullOrEmpty(jeevSearchObject.getFullName())) {
                holder.name.setText(jeevSearchObject.getFullName());
            }

            // set contact information
            if (jeevSearchObject.getContactInformations() != null
                    && jeevSearchObject.getContactInformations().size() >= 1) {
                String area = jeevSearchObject.getContactInformations().get(0)
                        .getAddress().getArea();
                String addressLine = jeevSearchObject.getContactInformations()
                        .get(0).getAddress().getCity();

                if (!CommonCode.isNullOrEmpty(area)) {
                    if (!CommonCode.isNullOrEmpty(addressLine)) {
                        holder.degrees.setText((area + "," + addressLine)
                                .trim());
                    } else {
                        holder.degrees.setText(area.trim());
                    }
                } else if (!CommonCode.isNullOrEmpty(addressLine)) {
                    holder.degrees.setText(addressLine.trim());
                } else {
                    holder.degrees.setVisibility(View.VISIBLE);
                }
            } else {
                holder.degrees.setVisibility(View.VISIBLE);
            }

            // set services

            if (jeevSearchObject.getServicesOffered() != null
                    && jeevSearchObject.getServicesOffered().size() > 0) {
                holder.specialization.setText(CommonCode
                        .commaSeparatedString(jeevSearchObject
                                .getServicesOffered()));
            } else {
                holder.specialization.setVisibility(View.INVISIBLE);
            }

            // set likes
            if (jeevSearchObject.getProfileViewCount() > 0)

                if (jeevSearchObject.getProfileViewCount() < 50)
                    holder.likes_value.setText("<50");
                else
                    holder.likes_value.setText(String.valueOf(jeevSearchObject
                            .getProfileViewCount()));
            else
                holder.likes_value.setText("<50");

            // set fee
            if (!CommonCode.isNullOrEmpty(jeevSearchObject.getFees()))
                holder.fee_text.setText(jeevSearchObject.getFees());
            else
                holder.fee_text.setText("N/A");

            // set experience
            if (!CommonCode.isNullOrEmpty(jeevSearchObject
                    .getExperienceYearsText())) {
                holder.recommended_text.setText(jeevSearchObject
                        .getExperienceYearsText());
            }

        }

        if (jeevSearchObject.getServiceRequisitionTypes().size() > 0 && jeevSearchObject.getServiceRequisitionTypes().contains("SG1")) {
            holder.imageView_instant_apoint.setVisibility(View.VISIBLE);
        } else {
            holder.imageView_instant_apoint.setVisibility(View.INVISIBLE);
        }

        holder.imageView_instant_apoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JeevomSession session = new JeevomSession(fragment.getActivity());
                if (session.getLoggedInStatus()) {
                    if (fragment instanceof JeevSearchFragment)
                        ((JeevSearchFragment) fragment).setInstantAppointment(position);
                } else {
                    Intent signup_intent = new Intent(fragment.getActivity(), SignUpLoginActivity.class);
                    session.setAppType("jeevom");
                    signup_intent.putExtra("item_position", position);
                    signup_intent.putExtra("isInContext", true);
                    fragment.startActivityForResult(signup_intent, 1010);
                    fragment.getActivity().overridePendingTransition(com.schoolteacher.mylibrary.R.anim.trans_left_in, com.schoolteacher.mylibrary.R.anim.trans_left_exit);
                }
            }
        });

        return convertView;
    }

    public static class ViewHolder {
        private CircleImageView thumbNail;
        private TextView name;
        private TextView degrees;
        private TextView specialization;
        private ImageView recommended;
        private ImageView certified;
        private ImageView menu;
        private TextView likes_value;
        private TextView recommended_text;
        private TextView fee_text;
        private TextView review_text;
        LinearLayout clinics_layout;
        private ImageView extras;

        private RelativeLayout clinic_contain;
        private TextView clinic_name, distance, address;
        private ImageView imageView_instant_apoint;

    }
}