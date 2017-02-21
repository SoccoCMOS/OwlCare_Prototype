package co.owlmed_dz.owlcare; /**
 * Created by DUALCOMPUTER on 1/11/2017.
 */
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import android.app.Activity;
import java.util.ArrayList;
import android.view.*;
import android.view.View.*;
import android.content.res.Resources;
import android.content.*;

import co.owlmed_dz.owlcare.Model.Doctor;
import co.owlmed_dz.owlcare.Model.Nurse;
import co.owlmed_dz.owlcare.R;

public class NurseAdapter extends BaseAdapter implements OnClickListener {

    private Activity activity;
    private ArrayList<Nurse> data;
    private static LayoutInflater inflater=null;
    public Resources res;
    Nurse tempValues=null;

    public NurseAdapter(Activity activity, ArrayList<Nurse> data, Resources res) {
        this.activity = activity;
        this.data = data;
        this.res = res;

        /***********  Layout inflator to call external xml layout () ***********/
        inflater = ( LayoutInflater )activity.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        if(data.size()<=0)
            return 1;
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi=convertView;
        ViewHolder viewHolder;

        if(convertView==null)
        {
            /****** Inflate tabitem.xml file for each row ( Defined below ) *******/
            vi = inflater.inflate(R.layout.nurse_adapter, null);

            /****** View Holder Object to contain tabitem.xml file elements ******/

            viewHolder = new ViewHolder();
            viewHolder.tvNom = (TextView) vi.findViewById(R.id.name);
            viewHolder.tvAdresse = (TextView) vi.findViewById(R.id.adress);
            viewHolder.tvTel = (TextView) vi.findViewById(R.id.tel);
            viewHolder.tvMetier = (TextView) vi.findViewById(R.id.spec);
            viewHolder.tvEmail = (TextView) vi.findViewById(R.id.mail);
            viewHolder.ivPhoto=(FrameLayout)vi.findViewById(R.id.pic);

            /************  Set holder with LayoutInflater ************/
            vi.setTag( viewHolder );

        }
        else
        {
            viewHolder=(ViewHolder)vi.getTag();
        }

        if(data.size()>0)
        {
            tempValues=null;
            tempValues = ( Nurse ) data.get( position );

            /************  Set Model values in Holder elements ***********/

            viewHolder.tvNom.setText( tempValues.getNom()+ "  " + tempValues.getPrenom() );
            viewHolder.tvMetier.setText( tempValues.getmetier() );
            viewHolder.tvTel.setText(tempValues.getPhone()+"");
            viewHolder.tvAdresse.setText(tempValues.getAdresse());
            viewHolder.tvEmail.setText(tempValues.getEmail());
            viewHolder.ivPhoto.setBackground(ContextCompat.getDrawable(activity,R.drawable.nurse));

            /******** Set Item Click Listner for LayoutInflater for each row *******/
            /*vi.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(activity,"Clicked !",Toast.LENGTH_LONG).show();
                }
            }); */

        }

        return vi;
    }

    @Override
    public void onClick(View v) {

    }



    public static class ViewHolder{

        public TextView tvNom;
        public TextView tvEmail;
        public TextView tvAdresse;
        public TextView tvTel;
        public TextView tvMetier;
        public FrameLayout ivPhoto;

    }
}
