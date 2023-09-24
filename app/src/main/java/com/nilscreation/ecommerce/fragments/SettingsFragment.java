package com.nilscreation.ecommerce.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.nilscreation.ecommerce.R;

public class SettingsFragment extends Fragment {

    LinearLayout btnShare, btnPrivacy, btnfaq, btnContact, btnMore;

    public SettingsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        btnShare = view.findViewById(R.id.btnShare);
        btnPrivacy = view.findViewById(R.id.btnPrivacy);
        btnfaq = view.findViewById(R.id.btnfaq);
        btnContact = view.findViewById(R.id.btnContact);
        btnMore = view.findViewById(R.id.btnMore);

        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String appUrl = "Get Exciting deals on Clothes, Accessories, Electronics and much more. Download the app now. " + "\nhttps://play.google.com/store/apps/details?id=" + getActivity().getApplicationContext().getPackageName();

                Intent sharing = new Intent(Intent.ACTION_SEND);
                sharing.setType("text/plain");
                sharing.putExtra(Intent.EXTRA_SUBJECT, "Download Now");
                sharing.putExtra(Intent.EXTRA_TEXT, appUrl);
                startActivity(Intent.createChooser(sharing, "Share via"));
            }
        });

        btnPrivacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://thenilscreation.blogspot.com/p/ecommerce-privacy.html";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });
        btnfaq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://thenilscreation.blogspot.com/p/faq.html";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

        btnContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    Intent intent = new Intent(Intent.ACTION_SENDTO);
                    intent.setData(Uri.parse("mailto:"));
                    String[] to = {"nilssonawanen1@gmail.com"};
                    intent.putExtra(Intent.EXTRA_EMAIL, to);

                    startActivity(Intent.createChooser(intent, "Send Email"));

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://play.google.com/store/apps/developer?id=Nils+Creation";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });
        return view;
    }
}