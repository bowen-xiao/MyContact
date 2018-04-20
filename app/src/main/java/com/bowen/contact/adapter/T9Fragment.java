package com.bowen.contact.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.inputmethodservice.Keyboard;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.bowen.contact.R;
import com.bowen.contact.keyboard.BaseKeyboard;
import com.bowen.contact.keyboard.KeyboardManager;
import com.bowen.contact.keyboard.NumberKeyboard;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link T9Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class T9Fragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.fragment_input_phone)
    EditText mInputPhone;
    //    @BindView(R.id.fragment_main_text)
    //    TextView mMainText;
    private NumberKeyboard numberKeyboard;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Unbinder mBind;

    public T9Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment T9Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static T9Fragment newInstance(String param1, String param2) {
        T9Fragment fragment = new T9Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_t9, container, false);
        mBind = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        //        mMainText.setText(mParam1 + " ::: " + mParam2 );
        initNumberKeyboard();
        keyboardManagerNumber = new KeyboardManager(getContext());
        initNumberKeyboard();
        keyboardManagerNumber.bindToEditor(mInputPhone, numberKeyboard);
    }

    @Override
    public void onDestroyView() {
        mBind.unbind();
        super.onDestroyView();
    }

    /**
     * 此方法目前仅适用于标示ViewPager中的Fragment是否真实可见
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(keyboardManagerNumber == null){return;}
        if (!isVisibleToUser ) {
            keyboardManagerNumber.hideKeyboard();
        }else{
            mInputPhone.setFocusable(true);
            mInputPhone.setFocusableInTouchMode(true);
            mInputPhone.requestFocus();
        }
    }

    private KeyboardManager keyboardManagerNumber;
    private void initNumberKeyboard() {
        numberKeyboard = new NumberKeyboard(getContext(),NumberKeyboard.DEFAULT_NUMBER_XML_LAYOUT);
        numberKeyboard.setActionDoneClickListener(new NumberKeyboard.ActionDoneClickListener() {
            @Override
            public void onActionDone(CharSequence charSequence) {
                if(TextUtils.isEmpty(charSequence) || charSequence.toString().equals("0") || charSequence.toString().equals("0.0")) {
                    Toast.makeText(getContext(), "请输入内容", Toast.LENGTH_SHORT).show();
                }else {
                    onNumberkeyActionDone();
                }
            }
        });

        numberKeyboard.setKeyStyle(new BaseKeyboard.KeyStyle() {
            @Override
            public Drawable getKeyBackound(Keyboard.Key key) {
                if(key.iconPreview != null) {
                    return key.iconPreview;
                } else {
                    return ContextCompat.getDrawable(getContext(),R.drawable.key_number_bg);
                }
            }

            @Override
            public Float getKeyTextSize(Keyboard.Key key) {
                if(key.codes[0] == getContext().getResources().getInteger(R.integer.action_done)) {
                    return convertSpToPixels(getContext(), 20f);
                }
                return convertSpToPixels(getContext(), 24f);
            }

            @Override
            public Integer getKeyTextColor(Keyboard.Key key) {
                if(key.codes[0] == getContext().getResources().getInteger(R.integer.action_done)) {
                    return Color.WHITE;
                }
                return null;
            }

            @Override
            public CharSequence getKeyLabel(Keyboard.Key key) {
                return null;
            }
        });
    }

    //确定按钮
    private void onNumberkeyActionDone() {
        // TODO
    }

    public float convertSpToPixels(Context context, float sp) {
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, context.getResources().getDisplayMetrics());
        return px;
    }
}
