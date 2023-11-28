package com.example.pitch_management.activity.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;

import com.example.pitch_management.MyApplication;
import com.example.pitch_management.R;
import com.example.pitch_management.activity.SelectTypeActivity;
import com.example.pitch_management.database.MyDatabase;
import com.example.pitch_management.fragment.adminfragment.DatSanFragment;
import com.example.pitch_management.fragment.adminfragment.DichVuFrgment;
import com.example.pitch_management.fragment.adminfragment.KhachHangFragment;
import com.example.pitch_management.fragment.adminfragment.PhieuThongTinFragment;
import com.example.pitch_management.fragment.adminfragment.RequestBuyFragment;
import com.example.pitch_management.fragment.adminfragment.SanBongFragment;
import com.example.pitch_management.fragment.adminfragment.ThongBaoFragmentAdmin;
import com.example.pitch_management.fragment.adminfragment.TimeFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    public static String ACCOUNT = "";
    public static int ID_MAX_ORDER = 0;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    public static int CURRENT_FRAGMENT = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ID_MAX_ORDER = MyDatabase.getInstance(this).orderDAO().getIdMax();

        ACCOUNT = getIntent().getStringExtra("account");

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawerlayout);
        navigationView = findViewById(R.id.navigationView);

        toolbar.setNavigationOnClickListener(v->{
            drawerLayout.openDrawer(GravityCompat.START);
        });

        if(!ACCOUNT.equals("Admin")){
            Menu menu = navigationView.getMenu();
        }

        navigationView.setNavigationItemSelectedListener(item -> {

            Menu menu = navigationView.getMenu();

            if(item.getItemId() == R.id.item_datsan && CURRENT_FRAGMENT!=0){
                menu.findItem(item.getItemId()).setChecked(true);
                CURRENT_FRAGMENT = 0;
                replaceFragment(new DatSanFragment());
                getSupportActionBar().setTitle("Đặt Sân");
            }else if(item.getItemId() == R.id.item_sanbong && CURRENT_FRAGMENT!=1){
                menu.findItem(item.getItemId()).setChecked(true);
                CURRENT_FRAGMENT = 1;
                replaceFragment(new SanBongFragment());
                getSupportActionBar().setTitle("Sân Bóng");
            }else if(item.getItemId() == R.id.item_khachhang && CURRENT_FRAGMENT!=3){
                menu.findItem(item.getItemId()).setChecked(true);
                CURRENT_FRAGMENT = 3;
                replaceFragment(new KhachHangFragment());
                getSupportActionBar().setTitle("Khách Hàng");
            }else if(item.getItemId() == R.id.item_phieuthongtin && CURRENT_FRAGMENT!=4){
                menu.findItem(item.getItemId()).setChecked(true);
                CURRENT_FRAGMENT = 4;
                replaceFragment(new PhieuThongTinFragment());
                getSupportActionBar().setTitle("Phiếu Thông Tin");
            }else if(item.getItemId() == R.id.item_dichvu && CURRENT_FRAGMENT!=5){
                menu.findItem(item.getItemId()).setChecked(true);
                CURRENT_FRAGMENT = 5;
                replaceFragment(new DichVuFrgment());
                getSupportActionBar().setTitle("Dịch Vụ");
            }else if(item.getItemId() == R.id.item_man_hinh_thong_bao && CURRENT_FRAGMENT!=9)  {
                menu.findItem(item.getItemId()).setChecked(true);
                CURRENT_FRAGMENT = 9;
                replaceFragment(new ThongBaoFragmentAdmin());
                getSupportActionBar().setTitle("Thông báo");
            }else if(item.getItemId() == R.id.item_accept_naptien && CURRENT_FRAGMENT!=10)  {
                menu.findItem(item.getItemId()).setChecked(true);
                CURRENT_FRAGMENT = 10;
                replaceFragment(new RequestBuyFragment());
                getSupportActionBar().setTitle("Yêu cầu nạp tiền");
            } else if(item.getItemId() == R.id.item_dangxuat){
                finishAffinity();
                CURRENT_FRAGMENT = 0;
                MyApplication.CURRENT_TYPE = -1;
                Intent intent = new Intent(MainActivity.this, SelectTypeActivity.class);
                startActivity(intent);
            }

            drawerLayout.close();
            return true;
        });

        replaceFragment(new DatSanFragment());
        getSupportActionBar().setTitle("Đặt Sân");
    }

    public void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameLayout_content,fragment);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isOpen())drawerLayout.close();
        else{
            finishAffinity();
            System.exit(0);
        }
    }
}