package com.example.ivanbaguspinuntun.ebookhope;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by risnawan on 2/3/2017.
 */
public class FilterFragment extends DialogFragment implements AdapterView.OnItemSelectedListener{

    private Spinner spin2;
    private List<String> list2 = new ArrayList<String>();
    private List<String> list3 = new ArrayList<String>();
    private List<String> list4 = new ArrayList<String>();


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.fragment_filter, null);

        Spinner spin;
        spin = (Spinner)view.findViewById(R.id.spinner1);


        spin2 = (Spinner)view.findViewById(R.id.spinner2);

        List<String> list = new ArrayList<String>();
        list.add("SD");
        list.add("SMP");
        list.add("SMA");


        //Second List

        list2.add("1");
        list2.add("2");
        list2.add("3");
        list2.add("4");
        list2.add("5");
        list2.add("6");

        //Third List
        //List<String> list3 = new ArrayList<String>();
        list3.add("7");
        list3.add("8");
        list3.add("9");

        //Fourth List
        //List<String> list4 = new ArrayList<String>();
        list4.add("10 IPA");
        list4.add("11 IPA");
        list4.add("12 IPA");
        list4.add("10 IPS");
        list4.add("11 IPS");
        list4.add("12 IPS");


        ArrayAdapter<String> kategoriAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, list);
        kategoriAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);



        spin.setAdapter(kategoriAdapter);
        spin.setOnItemSelectedListener(this);
        spin2.setOnItemSelectedListener(this);
        builder

                .setView(view)
                .setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // FIRE ZE MISSILES!
                        Toast.makeText(getActivity(), "This is my Toast message!",
                                Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                        dismiss();
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Spinner spinner = (Spinner) parent;
        ArrayAdapter<String> kelasSDAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, list2);
        kelasSDAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<String> kelasSMPAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, list3);
        kelasSMPAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<String> kelasSMAAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, list4);
        kelasSMAAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        if(spinner.getId() == R.id.spinner1)
        {
            String sselected = parent.getItemAtPosition(position).toString();
            Toast.makeText(getActivity(), sselected, Toast.LENGTH_SHORT).show();
            if (sselected == "SD")
            {
                spin2.setAdapter(null);
                spin2.setAdapter(kelasSDAdapter);
            }
            else if(sselected == "SMP"){
                spin2.setAdapter(null);
                spin2.setAdapter(kelasSMPAdapter);
            }
            else if (sselected == "SMA"){
                spin2.setAdapter(null);
                spin2.setAdapter(kelasSMAAdapter);
            }
        }
        else if(spinner.getId() == R.id.spinner2)
        {
            String ss = parent.getItemAtPosition(position).toString();
            Toast.makeText(getActivity(), ss, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
