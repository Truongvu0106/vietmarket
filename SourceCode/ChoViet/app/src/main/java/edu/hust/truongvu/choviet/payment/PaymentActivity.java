package edu.hust.truongvu.choviet.payment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.shuhart.stepview.StepView;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.payment.address.AddressFragment;
import edu.hust.truongvu.choviet.payment.confirm.ConfirmFragment;
import edu.hust.truongvu.choviet.payment.paymethod.PayMethodFragment;
import edu.hust.truongvu.choviet.payment.transport.TransportFragment;

public class PaymentActivity extends AppCompatActivity {
    private Toolbar toolbar;
    public static StepView stepView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        toolbar = findViewById(R.id.toolbar_payment);
        stepView = findViewById(R.id.step_view);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        stepView.go(0, true);
        loadFragment(AddressFragment.getInstance());
    }

    public void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container_payment, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
//        int count = getFragmentManager().getBackStackEntryCount();
//        if (count == 0) {
//            super.onBackPressed();
//        } else {
//            getFragmentManager().popBackStack();
//        }
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.frame_container_payment);
        if (fragment instanceof AddressFragment){
            finish();
        }else if (fragment instanceof TransportFragment){
            stepView.go(0, true);
            stepView.done(false);
            loadFragment(AddressFragment.getInstance());
        }else if (fragment instanceof PayMethodFragment){
            stepView.go(1, true);
            stepView.done(false);
            loadFragment(TransportFragment.getInstance());
        }else if (fragment instanceof ConfirmFragment){
            stepView.go(2, true);
            stepView.done(false);
            loadFragment(PayMethodFragment.getInstance());
        }else {
            return;
        }
    }
}
