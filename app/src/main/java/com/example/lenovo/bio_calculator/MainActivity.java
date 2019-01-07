package com.example.lenovo.bio_calculator;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.bio_calculator.FunctionFragment.MainFragment;
import com.example.lenovo.bio_calculator.UserFragment.LoginFragment;
import com.facebook.CallbackManager;
import com.kakao.auth.Session;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;
import com.kakao.usermgmt.callback.MeResponseCallback;
import com.kakao.usermgmt.response.model.UserProfile;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements LoginFragment.KakaoUserInfoListener {

    @BindView(R.id.login)
    Button login;
    @BindView(R.id.login_sign)
    Button loginSign;
    @BindView(R.id.login_userinfo)
    TextView login_userinfo;

    String usernickName;
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        View view = getWindow().getDecorView();
        view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.parseColor("#f2f2f2"));
        }

        pref = getSharedPreferences("lan",MODE_PRIVATE);

        if(pref.getString("lan","").equals("kor")){
            login.setText(getString(R.string.main_login_text_kor));
            loginSign.setText(getString(R.string.main_sign_text_kor));
        }

        getSupportFragmentManager().beginTransaction().add(R.id.main_fragment, new MainFragment()).commit();
        kakaoLoginChecker();

        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                replaceFragment(new LoginFragment());
            }
        });
        loginSign.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) { Toast.makeText(getApplicationContext(),"회원가입 탭",Toast.LENGTH_SHORT).show(); }
        });
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_fragment, fragment).addToBackStack(null).commit();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        pref = getSharedPreferences("lan", MODE_PRIVATE);
        if(pref.getString("lan","").equals("kor")){
            getMenuInflater().inflate(R.menu.menu, menu);
            return true;
        } else{
            // 영문
            return true;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_search) {
            Toast.makeText(this, "검색 클릭", Toast.LENGTH_SHORT).show();
            return true;
        }
        if (id == R.id.action_logout) {
            if (Session.getCurrentSession().isOpened()) {
                Session.getCurrentSession().close();
                kakaoLogout();
                Toast.makeText(this, "로그아웃 되었습니다.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "로그인 먼저 해주세요!", Toast.LENGTH_SHORT).show();
            }
            return true;
        }
        if (id == R.id.action_share) {
            Toast.makeText(this, "공유 클릭", Toast.LENGTH_SHORT).show();
            return true;
        }
        if (id == R.id.action_about) {
            Toast.makeText(this, "about 클릭", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void kakaoLogout() {
        UserManagement.requestLogout(new LogoutResponseCallback() {
            @Override
            public void onCompleteLogout() {
                login_userinfo.setText("로그인을 해주세요");
            }
        });
    }

    public void kakaoLoginChecker() {
        if (Session.getCurrentSession().isOpened()) {
            Log.e("kakao", "로그인되어잇음");
            UserManagement.requestMe(new MeResponseCallback() {
                @Override
                public void onSessionClosed(ErrorResult errorResult) {
                    Log.e("kakao", "실패");
                }

                @Override
                public void onNotSignedUp() {
                }

                @Override
                public void onSuccess(UserProfile result) {
                    Log.e("kakao", result.getNickname() + "님 환영합니다.");
                    login_userinfo.setText(result.getNickname() + "님 환영합니다.");
                }
            });
        } else {
            login_userinfo.setText("");
        }
    }

    @Override
    public void userNickname(String userNickname) {
        Log.d("kakao listener", "" + userNickname);
        usernickName = userNickname;
        login_userinfo.setText(usernickName + "님 환영합니다.");
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        CallbackManager mCallbackManager = new CallbackManager() {
            @Override
            public boolean onActivityResult(int requestCode, int resultCode, Intent data) {
                return false;
            }
        };
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        pref = getSharedPreferences("lan", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.remove("lan");
        editor.commit();
    }

}
