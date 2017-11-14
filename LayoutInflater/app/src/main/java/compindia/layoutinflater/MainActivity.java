package compindia.layoutinflater;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by compindi on 24-10-2017.
 */

public class MainActivity extends AppCompatActivity
{
    int num = 0;
    Button btnAddViews;
    Button btnClearAllViews;
    RelativeLayout loutMain;
    PopupWindow popupWindow;
    Button btnCancel, btnSubmit;
    EditText edtNumber;
    TextInputLayout loutNumber;
    LinearLayout loutItems;
    int no_of_views_count = 0;
    TextView txtEnterNumber;
    int viewCount = 1;
    View itempopview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAddViews = (Button) findViewById(R.id.btn_add_views);
        btnClearAllViews = (Button) findViewById(R.id.btn_clear_all_views);
        loutMain = (RelativeLayout) findViewById(R.id.lout_main);
        loutItems = (LinearLayout) findViewById(R.id.lout_items);
        no_of_views_count = loutItems.getChildCount();
        btnAddViews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final LayoutInflater inflater = (LayoutInflater) MainActivity.this.getSystemService(LAYOUT_INFLATER_SERVICE);
                View popupview = inflater.inflate(R.layout.popup, null);
                popupWindow = new PopupWindow(popupview, ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
                popupWindow.showAtLocation(loutMain, Gravity.CENTER, 0, 0);
//                popupWindow.setOutsideTouchable(false);
                popupWindow.setFocusable(true);
               popupWindow.update();
                edtNumber = (EditText) popupview.findViewById(R.id.edt_number);
                loutNumber = (TextInputLayout) popupview.findViewById(R.id.lout_number);
                btnCancel = (Button) popupview.findViewById(R.id.btn_cancel);
                btnSubmit = (Button) popupview.findViewById(R.id.btn_submit);
                txtEnterNumber = (TextView) popupview.findViewById(R.id.txt_enter_the_number);
                btnSubmit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String number = edtNumber.getText().toString();
                        if (number.isEmpty()) {
                            loutNumber.setError("Please enter Number");
                        }

                        if (!number.isEmpty()) {
                            num = Integer.parseInt(number);
                            Snackbar snackbar=Snackbar.make(loutMain,num+" VIEWS ADDED",Snackbar.LENGTH_SHORT);
                            snackbar.show();
                            num = num + no_of_views_count;
                            if (num >= 1 && num <= 15) {

                                popupWindow.dismiss();

//                                popupWindow.setOutsideTouchable(true);
                                for (int i = no_of_views_count + 1; i <= num; i++)
                                {
                                    LayoutInflater inflater1 = (LayoutInflater) MainActivity.this.getSystemService(LAYOUT_INFLATER_SERVICE);
                                    itempopview = inflater1.inflate(R.layout.activity_item, null);
                                    final TextView txtItem = (TextView) itempopview.findViewById(R.id.txt_item);
                                    final Button btnDelete=(Button) itempopview.findViewById(R.id.btn_delete);
                                  final View view1=itempopview.findViewById(R.id.view);
                                    txtItem.setText("TextView " + viewCount++);
                                    loutItems.addView(itempopview);
                                    btnDelete.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view)
                                        {
                                            loutItems.removeView((View) view.getParent().getParent());
                                        }
                                    });
                                }

                            } else {
                                loutNumber.setError("Please enter Number between 1 to 15");
                            }
                        }
                        if (num < 15) {
                            no_of_views_count = num;
                        } else {
                            num = 0;
                            no_of_views_count = 0;
                        }
                    }
                });
                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        popupWindow.dismiss();
                    }
                });
            }
        });
        btnClearAllViews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if (viewCount==1)
                {
                    Snackbar snackbar=Snackbar.make(loutMain,"Views already cleared",Snackbar.LENGTH_SHORT);
                    snackbar.show();
                }
                if (viewCount > 1) {
                    loutItems.removeAllViews();
                    Snackbar snackbar=Snackbar.make(loutMain,viewCount-1+" Views cleared",Snackbar.LENGTH_SHORT);
                    snackbar.show();
                    num = 0;
                    no_of_views_count = 0;
                    viewCount = 1;
                }
            }
        });
    }
//    @Override
//    public void onClick(View view2) {
//        switch (view2.getId()) {
//            case R.id.btn_delete:
//                loutItems.removeView((View) view2.getParent());
//                break;
//            default:
//                break;
//        }
//    }

}
