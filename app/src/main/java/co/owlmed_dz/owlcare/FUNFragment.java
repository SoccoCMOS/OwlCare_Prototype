package co.owlmed_dz.owlcare;

import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.*;

import java.util.*;

import android.widget.Toast;
import android.app.Dialog;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.telephony.SmsManager;

import co.owlmed_dz.owlcare.Model.Doctor;
import co.owlmed_dz.owlcare.Model.Nurse;

import android.content.res.*;
import android.net.Uri;

import static android.R.id.message;
import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by DUALCOMPUTER on 1/11/2017.
 */
public class FUNFragment extends Fragment {

    ListView listView;
    ArrayList<Nurse> arrayList;
    NurseAdapter adapter;
    final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;
    private static final int MY_PERMISSIONS_REQUEST_SMS = 2 ;
    private static final int MY_PERMISSIONS_REQUEST_EMAIL = 3 ;
    String tel,email;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_follow_up, container, false);
        listView = (ListView) rootView.findViewById(R.id.listContacts);
        fillData();
        Resources res = getResources();

        adapter = new NurseAdapter(getActivity(), arrayList, res);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getActivity(), "Clicked !" + arrayList.get(position).getNom(), Toast.LENGTH_LONG).show();

                final Nurse nurse = arrayList.get(position);
                // custom dialog
                final Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.dialog_action_contact);
                dialog.setTitle("M. " + nurse.getNom());

                TextView text = (TextView) dialog.findViewById(R.id.dName);
                text.setText("Dr " + nurse.getNom() + " " + nurse.getPrenom());

                ImageButton imgCall, imgSms, imgMail;
                imgCall = (ImageButton) dialog.findViewById(R.id.dCall);
                imgCall.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        tel=""+nurse.getPhone();
                        Toast.makeText(getContext(), "Call " + tel, Toast.LENGTH_SHORT).show();

                        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE)
                                != PackageManager.PERMISSION_GRANTED) {
                            //Toast.makeText(getActivity(), "No permission to call", Toast.LENGTH_LONG).show();
                            ActivityCompat.requestPermissions(getActivity(),
                                    new String[]{Manifest.permission.CALL_PHONE},
                                    MY_PERMISSIONS_REQUEST_CALL_PHONE);
                        } else
                        {
                            //Toast.makeText(getActivity(), "You  have permission to call", Toast.LENGTH_LONG).show();
                            Intent callIntent = new Intent(Intent.ACTION_CALL);
                            callIntent.setData(Uri.parse("tel:"+tel));
                            startActivity(callIntent);
                        }
                    }
                });

                imgSms = (ImageButton) dialog.findViewById(R.id.dSms);
                imgSms.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getContext(), "Text " + nurse.getPhone(), Toast.LENGTH_SHORT).show();
                        tel=""+nurse.getPhone();

                        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.SEND_SMS)
                                != PackageManager.PERMISSION_GRANTED) {
                            //Toast.makeText(getActivity(), "No permission to call", Toast.LENGTH_LONG).show();
                            ActivityCompat.requestPermissions(getActivity(),
                                    new String[]{Manifest.permission.SEND_SMS},
                                    MY_PERMISSIONS_REQUEST_SMS);
                        } else
                        {
                            SmsManager smsManager = SmsManager.getDefault();
                            smsManager.sendTextMessage(tel, null,"Please call me back.", null, null);
                            //Toast.makeText(getApplicationContext(), "SMS sent.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                imgMail = (ImageButton) dialog.findViewById(R.id.dMail);
                imgMail.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getContext(), "Email " + nurse.getEmail(), Toast.LENGTH_SHORT).show();
                        email=""+nurse.getEmail();
                        Intent emailIntent = new Intent(Intent.ACTION_SEND);
                        emailIntent.setData(Uri.parse("mailto:"));
                        emailIntent.setType("text/plain");
                        emailIntent.putExtra(Intent.EXTRA_EMAIL,email);

                        try {
                            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                            //getActivity().finish();
                        } catch (android.content.ActivityNotFoundException ex) {
                            Toast.makeText(getActivity(), "There is no email client installed.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                dialog.show();
            }
        });
        return rootView;
    }

    private void fillData() {
        arrayList = new ArrayList<>();
        arrayList.add(new Nurse("Zerkani", "Maya", "Infirmière", "33 rue Hales Said El Mouradia", 9903, "maya.simoussi@gmail.com", null));
        arrayList.add(new Nurse("Benzerfa", "El Habib", "Femme au foyer", "07 rue Tayeb Matipene ElMouradia", 3368, "habib.benzerfa@gmail.com", null));
        arrayList.add(new Nurse("Douadi", "Abderrahmene", "Fonctionnaire", "01, rue Maurice Audin Alger", 1873, "abder.douadi@gmail.com", null));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CALL_PHONE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getActivity(),"Permission Granted",Toast.LENGTH_SHORT).show();
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:"+tel));
                    startActivity(callIntent);
                } else {
                    Toast.makeText(getActivity(),"Permission Revoked",Toast.LENGTH_SHORT).show();
                    Intent callIntent = new Intent(Intent.ACTION_DIAL);
                    callIntent.setData(Uri.parse("tel:"+tel));
                    startActivity(callIntent);
                }
                return;
            }

            case MY_PERMISSIONS_REQUEST_SMS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getActivity(),"Permission Granted",Toast.LENGTH_SHORT).show();

                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(tel, null,"Hi i'm Owl.", null, null);
                    Toast.makeText(getApplicationContext(), "SMS sent.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(),"Permission Revoked",Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }

}
