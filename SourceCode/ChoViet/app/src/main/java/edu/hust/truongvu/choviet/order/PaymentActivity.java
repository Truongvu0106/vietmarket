package edu.hust.truongvu.choviet.order;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.shuhart.stepview.StepView;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.helper.customview.MyToolbarExtra;
import edu.hust.truongvu.choviet.order.address.AddressFragment;
import edu.hust.truongvu.choviet.order.confirm.ConfirmFragment;
import edu.hust.truongvu.choviet.order.paymethod.PayMethodFragment;
import edu.hust.truongvu.choviet.order.transport.TransportFragment;

public class PaymentActivity extends AppCompatActivity {
    private View toolbar;
    public static StepView stepView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        new MyToolbarExtra(this, getString(R.string.payment), R.drawable.ic_check, new MyToolbarExtra.OnExtraToolbarListener() {
            @Override
            public void onMoreClick() {

            }

            @Override
            public void onBackClick() {
                backHandle();
            }
        });
        stepView = findViewById(R.id.step_view);
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
        backHandle();
    }

    private void backHandle(){
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
