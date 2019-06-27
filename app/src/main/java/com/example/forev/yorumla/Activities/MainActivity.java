package com.example.forev.yorumla.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.forev.yorumla.Fragments.CarFragment;
import com.example.forev.yorumla.Fragments.ComputerFragment;
import com.example.forev.yorumla.Fragments.GameFragment;
import com.example.forev.yorumla.Fragments.HomeFragment;
import com.example.forev.yorumla.Fragments.MakeupFragment;
import com.example.forev.yorumla.Models.CarModel;
import com.example.forev.yorumla.Models.ComputerModel;
import com.example.forev.yorumla.Models.GameModel;
import com.example.forev.yorumla.Models.MakeupModel;
import com.example.forev.yorumla.R;
import com.example.forev.yorumla.RestApi.ManagerAll;
import com.example.forev.yorumla.Utils.ChangeFragments;
import com.example.forev.yorumla.Utils.GetSharedPreferences;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private SharedPreferences getSharedPreferences;
    private GetSharedPreferences preferences;
    ChangeFragments changeFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        define();
        controlUser();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new HomeFragment()).commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headView = navigationView.getHeaderView(0);

        TextView user = headView.findViewById(R.id.nav_header_username);
        TextView mail = headView.findViewById(R.id.nav_header_email);

        user.setText(getSharedPreferences.getString("username",null));
        mail.setText(getSharedPreferences.getString("email",null));
    }

    public void define()
    {
        preferences = new GetSharedPreferences(MainActivity.this);
        getSharedPreferences = preferences.getSession();
        changeFragments = new ChangeFragments(getApplicationContext());
    }

    public void controlUser()
    {
        if(getSharedPreferences.getString("id",null) == null && getSharedPreferences.getString("email",null) == null &&
                getSharedPreferences.getString("username",null) == null)
        {
            Intent intent = new Intent(MainActivity.this,LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.category_car) {
            dialogAc();
        } else if (id == R.id.category_game) {
            dialogOyunAc();
        } else if (id == R.id.category_makeup) {
            dialogMakyajAc();
        } else if (id == R.id.category_computer) {
            dialogComputerAc();
        } else if (id == R.id.home) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new HomeFragment()).commit();
        }else if (id == R.id.contact) {
            dialogContactAc();
        }else if (id == R.id.exit) {
            GetSharedPreferences getSharedPreferences = new GetSharedPreferences(MainActivity.this);
            getSharedPreferences.deleteToSession();
            Intent intent = new Intent(MainActivity.this,MainActivity.class);
            startActivity(intent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void dialogContactAc()
    {
        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.alertdialog_contact,null);

        final EditText yorum = (EditText)view.findViewById(R.id.yorumName);

        Button gonder = (Button)view.findViewById(R.id.alert_gonder);

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setView(view);
        final AlertDialog dialog = alert.create();

        gonder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!yorum.getText().toString().equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Öneriniz alındı, yorumunuz için teşekkür ederiz.",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Lütfen tüm alanları doldurunuz..",Toast.LENGTH_LONG).show();
                }
                dialog.cancel();
            }
        });

        dialog.show();
    }

    public void dialogComputerAc()
    {
        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.alertdialog_computer,null);

        final EditText hardwareName = (EditText)view.findViewById(R.id.hardwareName);
        final EditText markaName = (EditText)view.findViewById(R.id.markaName);

        Button search = (Button)view.findViewById(R.id.alert_hardware_search);

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setView(view);
        final AlertDialog dialog = alert.create();

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!hardwareName.getText().toString().equals("") &&
                        !markaName.getText().toString().equals(""))
                {
                    getComputer(hardwareName.getText().toString(),markaName.getText().toString());
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Lütfen tüm alanları doldurunuz..",Toast.LENGTH_LONG).show();
                }
                dialog.cancel();
            }
        });

        dialog.show();
    }

    public void getComputer(String hardwarename,String markaname)
    {
        Call<List<ComputerModel>> req = ManagerAll.getInstance().getComputer(hardwarename,markaname);
        req.enqueue(new Callback<List<ComputerModel>>() {
            @Override
            public void onResponse(Call<List<ComputerModel>> call, Response<List<ComputerModel>> response) {
                Bundle bundle = new Bundle();
                bundle.putString("markaName", response.body().get(0).getMarkaname().toString() );
                bundle.putString("hardwareName", response.body().get(0).getHardwarename().toString() );
                bundle.putString("id",response.body().get(0).getId().toString());
                ComputerFragment computerFragment = new ComputerFragment();
                computerFragment.setArguments(bundle);
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.frameLayout, computerFragment);
                ft.commit();
            }

            @Override
            public void onFailure(Call<List<ComputerModel>> call, Throwable t) {

            }
        });
    }

    public void dialogMakyajAc()
    {
        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.alertdialog_makeup,null);

        final EditText makyajName = (EditText)view.findViewById(R.id.makyajMarkaName);
        final EditText malzemeName = (EditText)view.findViewById(R.id.makyajMalzemeName);

        Button search = (Button)view.findViewById(R.id.alert_makyaj_search);

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setView(view);
        final AlertDialog dialog = alert.create();

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!makyajName.getText().toString().equals("") &&
                        !malzemeName.getText().toString().equals(""))
                {
                    getMakeup(makyajName.getText().toString(),malzemeName.getText().toString());
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Lütfen tüm alanları doldurunuz..",Toast.LENGTH_LONG).show();
                }
                dialog.cancel();
            }
        });

        dialog.show();
    }

    public void getMakeup(String makyajName, String makyajMalzeme)
    {
        Call<List<MakeupModel>> req=ManagerAll.getInstance().getMakeup(makyajName,makyajMalzeme);
        req.enqueue(new Callback<List<MakeupModel>>() {
            @Override
            public void onResponse(Call<List<MakeupModel>> call, Response<List<MakeupModel>> response) {
                Bundle bundle = new Bundle();
                bundle.putString("makyajName", response.body().get(0).getMakyajName().toString() );
                bundle.putString("makyajMalzeme", response.body().get(0).getMakyajMalzeme().toString() );
                bundle.putString("id",response.body().get(0).getId().toString());
                MakeupFragment makeupFragment = new MakeupFragment();
                makeupFragment.setArguments(bundle);
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.frameLayout, makeupFragment);
                ft.commit();
            }

            @Override
            public void onFailure(Call<List<MakeupModel>> call, Throwable t) {

            }
        });
    }

    public void dialogOyunAc()
    {
        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.alertdialog_game,null);

        final EditText gameName = (EditText)view.findViewById(R.id.game_name);
        final EditText gameType = (EditText)view.findViewById(R.id.game_type);

        Button search = (Button)view.findViewById(R.id.alert_game_search);

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setView(view);
        final AlertDialog dialog = alert.create();

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!gameName.getText().toString().equals("") &&
                        !gameType.getText().toString().equals(""))
                {
                    //getCar(marka.getText().toString(),model.getText().toString(),motor.getText().toString());
                    getGame(gameName.getText().toString(),gameType.getText().toString());
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Lütfen tüm alanları doldurunuz..",Toast.LENGTH_LONG).show();
                }
                dialog.cancel();
            }
        });

        dialog.show();
    }

    public void getGame(String gameName,String gameType)
    {
        Call<List<GameModel>> req = ManagerAll.getInstance().getGame(gameName,gameType);
        req.enqueue(new Callback<List<GameModel>>() {
            @Override
            public void onResponse(Call<List<GameModel>> call, Response<List<GameModel>> response) {
                Bundle bundle = new Bundle();
                bundle.putString("gameName", response.body().get(0).getGameName().toString() );
                bundle.putString("gameType", response.body().get(0).getGameType().toString() );
                bundle.putString("id", response.body().get(0).getId().toString() );
                GameFragment gameFragment = new GameFragment();
                gameFragment.setArguments(bundle);
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.frameLayout, gameFragment);
                ft.commit();
            }

            @Override
            public void onFailure(Call<List<GameModel>> call, Throwable t) {

            }
        });
    }

    public void dialogAc()
    {
        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.alertdialog_car,null);

        final EditText marka = (EditText)view.findViewById(R.id.marka);
        final EditText model = (EditText)view.findViewById(R.id.model);
        final EditText motor = (EditText)view.findViewById(R.id.motor);

        Button search = (Button)view.findViewById(R.id.alert_search);

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setView(view);
        final AlertDialog dialog = alert.create();

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!marka.getText().toString().equals("") &&
                        !model.getText().toString().equals("") &&
                        !motor.getText().toString().equals(""))
                {
                    getCar(marka.getText().toString(),model.getText().toString(),motor.getText().toString());
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Lütfen tüm alanları doldurunuz..",Toast.LENGTH_LONG).show();
                }
                dialog.cancel();
            }
        });

        dialog.show();
    }

    public void getCar(String marka,String model,String motor)
    {
        Call<List<CarModel>> req = ManagerAll.getInstance().getCar(marka,model,motor);
        req.enqueue(new Callback<List<CarModel>>() {
            @Override
            public void onResponse(Call<List<CarModel>> call, Response<List<CarModel>> response) {
                if(response.isSuccessful())
                {
                    //changeFragments.changeWithParameters(new CarFragment(),response.body().get(0).getId().toString());
                    Bundle bundle = new Bundle();
                    //bundle.putString("id", response.body().get(0).getId().toString() );
                    bundle.putString("marka", response.body().get(0).getMarka().toString() );
                    bundle.putString("model", response.body().get(0).getModel().toString() );
                    bundle.putString("motor", response.body().get(0).getMotor().toString() );
                    bundle.putString("id", response.body().get(0).getId().toString() );
                    bundle.putString("userid",getSharedPreferences.getString("id",null));
                    CarFragment fragInfo = new CarFragment();
                    fragInfo.setArguments(bundle);
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.frameLayout, fragInfo);
                    ft.commit();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Lütfen bilgileri doğru giriniz..",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<CarModel>> call, Throwable t) {

            }
        });
    }
}
