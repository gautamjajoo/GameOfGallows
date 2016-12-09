package com.example.sridhar123.hangman;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.zip.Inflater;

/**
 * Created by sridhar123 on 8/12/16.
 */

public class LetterAdapter extends ArrayAdapter<String> {

    private Button letterbtn;

//    private LayoutInflater letterinflate;


    public LetterAdapter(Context context , String[] alphabets) {
        super(context, 0,alphabets);
    }

    public View getView (int position , View convertview , ViewGroup parent){


        LayoutInflater letterinflate = LayoutInflater.from(getContext());
        View customView = letterinflate.inflate(R.layout.button,parent,false);

        String alpha = getItem(position);
        letterbtn = (Button) customView.findViewById(R.id.button1);
        letterbtn.setText(alpha);
        letterbtn.setTextColor(Color.WHITE);
        return letterbtn;


    }


}
