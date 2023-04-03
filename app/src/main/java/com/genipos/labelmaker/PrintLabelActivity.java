package com.genipos.labelmaker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.genipos.labelmaker.roomdb.ProRepository;
import com.genipos.labelmaker.roomdb.ProViewModel;
import com.genipos.labelmaker.roomdb.Product;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;

public class PrintLabelActivity extends AppCompatActivity {

    Context mContext;
    AppEnv  gAppEnv;
//    private ListView prodlistview;
    TextInputEditText editTextCName, editTextPName, editTextPDesc,editTextMfgDate, editTextExpDate, editTextNetWt, editTextUnit, editTextBatchNo, editTextPrice,
        editTextMfgAt, editTextLicNo,editTextLabelCnt ;
    TextView textViewCName, textViewPName, textViewPDesc,textViewMfgDate, textViewExpDate, textViewNetWt, textViewBatchNo, textViewPrice,
            textViewMfgAt, textViewLicNo;
    private Button btprint;
    //ProductInfo newprodinfo=null;
//    ArrayList<String> itemList;
//    private ArrayAdapter<String> listAdapter ;
    ProViewModel proViewModel;
    ProRepository proRepository;
    Product test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print_label);

        mContext = this;
        gAppEnv = (AppEnv) getApplicationContext();
        proViewModel = new ProViewModel(getApplication());
        proRepository = new ProRepository(getApplication());
//        prodlistview = (ListView)findViewById(R.id.lvprodlist);
//        itemList = new ArrayList<>();//gAppEnv.getProductManager().getProductNames();
//        listAdapter = new ArrayAdapter<String>(this, R.layout.menu_list_row, itemList);
//        prodlistview.setAdapter(listAdapter);
//        prodlistview.setOnIte0mClickListener(new AdapterView.OnItemClickListener() {
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String prodname = itemList.get(position);
//                edname.setText(prodname);
//            }
//        });
        init();
        Intent intent = getIntent();
        int valeu = intent.getIntExtra("selected_ID", -1);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                test = proRepository.getSingleData(valeu);
            }
        });
        if(valeu > -1) {
            try {
                thread.start();
                thread.join();
                setTextValue(test);
                System.out.println("Pass Id: " + test.cName);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //textChangeListener
        onTextChange();

        /*
        edmfgdate.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(mContext, mfgdatecallback, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        edexpdate.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(mContext, expdatecallback, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });*/


        btprint = (Button)findViewById(R.id.btprint);
        btprint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                if (!gAppEnv.getTscPrinterManager().isPrinterInitialized())
                {
                    Toast.makeText(getApplicationContext(), "Printer is Offline", Toast.LENGTH_SHORT).show();
                    return;
                }
                if( (editTextPName.getText().toString().isEmpty()) || (editTextLabelCnt.getText().toString().isEmpty()) )
                {
                    //Show error message/alert
                    Toast.makeText(getApplicationContext(), "Enter required fields", Toast.LENGTH_LONG).show();
                    return;
                }
                Product product = new Product(
                        0,
                        editTextCName.getText().toString(),
                        editTextPName.getText().toString(),
                        editTextPDesc.getText().toString(),
                        editTextMfgDate.getText().toString(),
                        editTextExpDate.getText().toString(),
                        editTextNetWt.getText().toString(),
                        editTextUnit.getText().toString(),
                        editTextPrice.getText().toString(),
                        editTextBatchNo.getText().toString(),
                        editTextMfgAt.getText().toString(),
                        editTextLicNo.getText().toString()
                );
//                proViewModel.insertProduct(product);
                proRepository.insertProduct(product);


                LabelInfo labelinfo = new LabelInfo();
                labelinfo.title = editTextCName.getText().toString();
                labelinfo.prodname = editTextPName.getText().toString();
                labelinfo.prodinfo = editTextPDesc.getText().toString();
                labelinfo.mfgdate = editTextMfgDate.getText().toString();
                labelinfo.expdate = editTextExpDate.getText().toString();
                labelinfo.netweight = editTextNetWt.getText().toString();
                labelinfo.wtunit = editTextUnit.getText().toString();
                labelinfo.batchno = editTextBatchNo.getText().toString();
                labelinfo.price = editTextPrice.getText().toString();
                labelinfo.mfgsite = editTextMfgAt.getText().toString();
                labelinfo.licno = editTextLicNo.getText().toString();
                String scnt = editTextLabelCnt.getText().toString();
                Integer lpcnt = Integer.parseInt(scnt);
                labelinfo.count = lpcnt;

                //ProductInfo prodinfo = gAppEnv.getProductManager().getProductInfo(labelinfo.prodname);
//                labelinfo.prodinfo = "jjkjk jlkjlkjkl nklklkl";//prodinfo.info;
                labelinfo.barcode = "1234568952";//prodinfo.barcode;
//                labelinfo.price = "50";//prodinfo.price;
                labelinfo.validity = "xxxx";//prodinfo.validity.toString()+ "Days";
//                labelinfo.netweight = "fggtt";//prodinfo.netweight;
//                labelinfo.wtunit = "ffhttt";//prodinfo.wtunit;
//                labelinfo.title = "Weavy";//gAppEnv.getLabelHeader();
                labelinfo.footer = "Thank You";//gAppEnv.getLabelFooter();
//                labelinfo.mfgsite = "Bhubaneswar";gAppEnv.getMfgSite();
//                labelinfo.licno = "xxxxxx";//gAppEnv.getLicNo();

                gAppEnv.getTscPrinterManager().PrintLabel(labelinfo);
            }
        });
    }
    private void init() {
        editTextCName = (TextInputEditText)findViewById(R.id.edtcompanyname);
        editTextPName = (TextInputEditText)findViewById(R.id.edtproductname);
        editTextPDesc = (TextInputEditText)findViewById(R.id.edtproductdesc);
        editTextMfgDate = (TextInputEditText)findViewById(R.id.edtmfgdt);
        editTextExpDate = (TextInputEditText)findViewById(R.id.edtuseby);
        editTextNetWt = (TextInputEditText)findViewById(R.id.edtnetwt);
        editTextUnit = (TextInputEditText)findViewById(R.id.edtunit);
        editTextBatchNo = (TextInputEditText)findViewById(R.id.edtbatchno);
        editTextPrice = (TextInputEditText)findViewById(R.id.edtprice);
        editTextMfgAt = (TextInputEditText)findViewById(R.id.edtmfgat);
        editTextLicNo = (TextInputEditText)findViewById(R.id.edtlicno);
        editTextLabelCnt = (TextInputEditText)findViewById(R.id.edtprintlabel);
        textViewCName = (TextView) findViewById(R.id.print_company_name);
        textViewPName = (TextView) findViewById(R.id.print_product_name);
        textViewPDesc = (TextView) findViewById(R.id.print_product_info);
        textViewMfgDate = (TextView) findViewById(R.id.print_mfg_dt_value);
        textViewExpDate = (TextView) findViewById(R.id.print_use_by_value);
        textViewNetWt = (TextView) findViewById(R.id.print_net_wt_value);
        textViewBatchNo = (TextView) findViewById(R.id.print_batch_no_value);
        textViewPrice = (TextView) findViewById(R.id.print_price_value);
        textViewMfgAt = (TextView) findViewById(R.id.print_mfg_at_value);
        textViewLicNo = (TextView) findViewById(R.id.print_fssai_lic_no_value);
    }
    private void onTextChange() {
        editTextCName.addTextChangedListener(new DataTextWatcher(this,editTextCName,editTextCName, textViewCName));
        editTextPName.addTextChangedListener(new DataTextWatcher(this,editTextPName,editTextPName, textViewPName));
        editTextPDesc.addTextChangedListener(new DataTextWatcher(this,editTextPDesc,editTextPDesc, textViewPDesc));
//        editTextMfgDate.addTextChangedListener(new DataTextWatcher(this,  editTextMfgDate, textViewMfgDate));
//        editTextExpDate.addTextChangedListener(new DataTextWatcher(this,  editTextExpDate, textViewExpDate));
        editTextNetWt.addTextChangedListener(new DataTextWatcher(this,editTextNetWt,editTextNetWt, textViewNetWt));
        editTextUnit.addTextChangedListener(new DataTextWatcher(this, editTextNetWt, editTextUnit, textViewNetWt));
        editTextBatchNo.addTextChangedListener(new DataTextWatcher(this,editTextBatchNo,editTextBatchNo, textViewBatchNo));
        editTextPrice.addTextChangedListener(new DataTextWatcher(this,editTextPrice,editTextPrice, textViewPrice));
        editTextMfgAt.addTextChangedListener(new DataTextWatcher(this,editTextMfgAt,editTextMfgAt, textViewMfgAt));
        editTextLicNo.addTextChangedListener(new DataTextWatcher(this,editTextLicNo,editTextLicNo, textViewLicNo));
        editTextMfgDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(PrintLabelActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        editTextMfgDate.setText(i2+"/"+(i1 + 1)+"/"+i);
                        textViewMfgDate.setText(i2+"/"+(i1 + 1)+"/"+i);
                    }
                }, year,month,day);
                datePickerDialog.show();
            }
        });
        editTextExpDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(PrintLabelActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        editTextExpDate.setText(i2+"/"+(i1 + 1)+"/"+i);
                        textViewExpDate.setText(i2+"/"+(i1 + 1)+"/"+i);
                    }
                }, year,month,day);
                datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis());
                datePickerDialog.show();
            }
        });
    }
    private void setTextValue(Product test) {
        editTextCName.setText(test.getcName());
        editTextPName.setText(test.getpName());
        editTextPDesc.setText(test.getpDesc());
        editTextMfgDate.setText(test.getpMfg());
        editTextExpDate.setText(test.getpExp());
        editTextNetWt.setText(test.getpWeight());
        editTextUnit.setText(test.getpUnit());
        editTextBatchNo.setText(test.getpBatch());
        editTextPrice.setText(test.getpPrice());
        editTextMfgAt.setText(test.getpMfgAt());
        editTextLicNo.setText(test.getpLic());
        textViewCName.setText(test.getcName());
        textViewPName.setText(test.getpName());
        textViewPDesc.setText(test.getpDesc());
        textViewMfgDate.setText(test.getpMfg());
        textViewExpDate.setText(test.getpExp());
        textViewNetWt.setText(test.getpWeight());
        textViewBatchNo.setText(test.getpBatch());
        textViewPrice.setText(test.getpPrice());
        textViewMfgAt.setText(test.getpMfgAt());
        textViewLicNo.setText(test.getpLic());
    }
}