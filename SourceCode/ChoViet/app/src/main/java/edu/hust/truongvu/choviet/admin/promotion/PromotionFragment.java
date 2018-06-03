package edu.hust.truongvu.choviet.admin.promotion;


import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.helper.MyHelper;
import edu.hust.truongvu.choviet.helper.customview.DeleteItemDialog;
import edu.hust.truongvu.choviet.model.entity.Promotion;

/**
 * A simple {@link Fragment} subclass.
 */
public class PromotionFragment extends Fragment implements View.OnClickListener, PromotionView{
    private View btnAdd, btnGenerate;
    private RecyclerView recyclerView;
    private PromotionAdapter adapter;
    private PromotionPresenter presenter;

    public PromotionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_promotion, container, false);
        initView(view);
        presenter = new PromotionPresenterImp(getContext(), this);
        presenter.loadListPromotion();
        return view;
    }

    private void initView(View view){
        btnAdd = view.findViewById(R.id.btn_add);
        btnGenerate = view.findViewById(R.id.btn_generate);
        recyclerView = view.findViewById(R.id.list_promotion);
        btnAdd.setOnClickListener(this);
        btnGenerate.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_add:
                callDialog("");
                break;
            case R.id.btn_generate:
                callDialog(MyHelper.randomString(6));
                break;
        }
    }

    private void callDialog(String s){
        PromotionDialog dialog = new PromotionDialog(getContext(), s, new PromotionDialog.PromotionDialogListener() {
            @Override
            public void onApply(Promotion promotion) {
                presenter.addPromotion(promotion);
            }

            @Override
            public void onCancel() {

            }
        });
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    @Override
    public void loadListSuccessful(final ArrayList<Promotion> data) {
        adapter = new PromotionAdapter(getContext(), data, new PromotionAdapter.PromotionListener() {
            @Override
            public void onDelete(final Promotion promotion) {
                DeleteItemDialog dialog = new DeleteItemDialog(getContext(), promotion.getId(), new DeleteItemDialog.DeleteItemCartListener() {
                    @Override
                    public void onDelete(int id) {
                        presenter.deletePromotion(id);
                        data.remove(promotion);
                        adapter.notifyDataSetChanged();
                    }
                });
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void loadListFalse() {

    }

    @Override
    public void deleteSuccessful() {

    }

    @Override
    public void deleteFalse() {

    }

    @Override
    public void addPromotionSuccessful() {
        Toast.makeText(getContext(), getContext().getString(R.string.add_successful), Toast.LENGTH_SHORT).show();
        presenter.loadListPromotion();
    }

    @Override
    public void addPromotionFalse() {
        Toast.makeText(getContext(), getContext().getString(R.string.code_already_exist), Toast.LENGTH_SHORT).show();
    }
}
