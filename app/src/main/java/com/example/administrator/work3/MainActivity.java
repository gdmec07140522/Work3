package com.example.administrator.work3;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.administrator.work3.R;

public class MainActivity extends Activity {
    private Button button;
    private CheckBox manCheckBox;
    private CheckBox womanCheckBox;
    private EditText editText;
    private TextView textView5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=(Button)findViewById(R.id.button);
        manCheckBox=(CheckBox)findViewById(R.id.man);
        womanCheckBox=(CheckBox)findViewById(R.id.woman);
        editText=(EditText)findViewById(R.id.editText);
        textView5=(TextView)findViewById(R.id.textView5);
    }

    @Override
    protected void onStart() {
        super.onStart();
        registerEvent();
    }

    private void registerEvent() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!editText.getText().toString().trim().equals("")) {
                    if (manCheckBox.isChecked() || womanCheckBox.isChecked()) {
                        Double weight = Double.parseDouble(editText.getText().toString());
                        StringBuffer sb = new StringBuffer();
                        sb.append("=========评估结果=========\n");
                        if (manCheckBox.isChecked()) {
                            sb.append("男性标准身高：");
                            double result = evaluateHeight(weight, "男");
                            sb.append((int) result + "（厘米）");
                        }
                        if (womanCheckBox.isChecked()) {

                            sb.append("女性标准身高：");
                            double result = evaluateHeight(weight, "女");
                            sb.append((int) result + "（厘米）");
                        }
                        textView5.setText(sb.toString());
                    } else {
                        showMessage("请选择性别！");
                    }
                } else {
                    showMessage("请输入体重！");
                }
            }
        });
    }


    private double evaluateHeight(Double weight, String sex) {
        double height;
        if(sex=="男"){
            height=170-(62-weight)/0.6;
        }else {
            height=158-(52-weight)/0.5;
        }
        return  height;
    }

    private void showMessage(String message) {
        AlertDialog alert=new AlertDialog.Builder(this).create();
        alert.setTitle("系统消息");
        alert.setMessage(message);
        alert.setButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

            }
        });
        alert.show();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(Menu.NONE,1,Menu.NONE,"退出");
        // Inflate the menu; this adds items to the action bar if it is present.
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()){
            case  1://退出
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
